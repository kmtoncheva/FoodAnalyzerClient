package bg.sofia.uni.fmi.mjt.client;

import bg.sofia.uni.fmi.mjt.client.commands.Command;
import bg.sofia.uni.fmi.mjt.client.commands.CommandFactory;
import bg.sofia.uni.fmi.mjt.client.dto.request.ClientRequestDto;
import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidCommandException;
import bg.sofia.uni.fmi.mjt.client.utils.LoggerConfigUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientAttrConstants.BUFFER_SIZE;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientAttrConstants.SERVER_HOST;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientAttrConstants.SERVER_PORT;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.GREETING_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.HELP_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.INPUT_PROMPT;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.NETWORK_CONNECTION_PROBLEM_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.PROMPT_ARROW;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.SEE_LOGS_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.LoggerConstants.SERVER_CONNECTION_ERROR_MSG;

/**
 * The main client class responsible for managing the connection to the Food Analyzer server
 * and handling user interaction.
 * <p>
 * This class establishes a socket connection to the server, processes user commands,
 * sends requests, receives responses, and displays results. It uses non-blocking I/O
 * with {@link java.nio.channels.SocketChannel} and {@link java.nio.channels.Selector}
 * for efficient network communication.
 * <p>
 * The client runs in a loop, accepting user input, parsing commands via
 * {@link bg.sofia.uni.fmi.mjt.client.commands.CommandFactory}, and delegating
 * response handling to the appropriate {@link bg.sofia.uni.fmi.mjt.client.commands.Command} implementation.
 */
public class FoodAnalyzerClient {
    private final ByteBuffer buffer;

    private Command command;
    private static final Logger LOGGER = LoggerConfigUtils.createLogger(FoodAnalyzerClient.class.getName());

    /**
     * Constructs a new {@code FoodAnalyzerClient} instance.
     *
     * <p>Initializes the internal {@link ByteBuffer} used for communication
     * with the server.</p>
     */
    public FoodAnalyzerClient() {
        buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
    }

    /**
     * Starts the client application.
     *
     * <p>Establishes a connection to the server using a {@link SocketChannel},
     * then enters a loop to:
     * <ul>
     *     <li>Read user input from the console</li>
     *     <li>Parse the input into a {@link Command}</li>
     *     <li>Send a request to the server</li>
     *     <li>Receive and process the server response</li>
     *     <li>Display the result to the user</li>
     * </ul>
     *
     * <p>The loop continues until a terminating command is issued by the user.</p>
     *
     * <p>If a network error occurs, it is logged and a user-friendly message is displayed.</p>
     */
    public void start() {
        try (SocketChannel socketChannel = SocketChannel.open();
             Scanner scanner = new Scanner(System.in)) {
            socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));

            System.out.println(GREETING_MSG + HELP_MSG);

            while(true) {
                System.out.print(INPUT_PROMPT + PROMPT_ARROW);
                String input = scanner.nextLine().trim();
                String response = null;

                try {
                    command = parseCommand(input);
                }
                catch (InvalidCommandException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                if (sendRequest(socketChannel, command) && !command.isTerminatingCommand()) {
                    response = getResponse(socketChannel);
                }

                command.printOutputToClient(response);

                if(command.isTerminatingCommand()) {
                    break;
                }
            }
        }
        catch (IOException e) {
            LOGGER.log(Level.SEVERE, SERVER_CONNECTION_ERROR_MSG, e);
            System.out.println(NETWORK_CONNECTION_PROBLEM_MSG + SEE_LOGS_MSG);
        }
    }

    /**
     * Parses raw user input into a {@link Command} instance.
     *
     * <p>Delegates command creation to {@link CommandFactory}.</p>
     *
     * @param input the raw user input from the console
     * @return a {@link Command} corresponding to the input
     * @throws InvalidCommandException if the input does not represent a valid command
     */
    private Command parseCommand(String input) throws InvalidCommandException {
        return CommandFactory.create(input);
    }

    /**
     * Sends a request to the server based on the provided {@link Command}.
     *
     * <p>The command is converted into a {@link ClientRequestDto}, which is then
     * serialized to JSON and written to the {@link SocketChannel}.</p>
     *
     * @param socketChannel the channel used to communicate with the server
     * @param command the command containing the request data
     * @return {@code true} if a request was sent, {@code false} if the command does not require a request
     * @throws IOException if an I/O error occurs during writing to the channel
     */
    private boolean sendRequest(SocketChannel socketChannel, Command command) throws IOException {
        ClientRequestDto request = command.getRequest();
        if (request == null) {
            return false;
        }

        String json = request.toJson();

        buffer.clear();
        buffer.put(json.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        socketChannel.write(buffer);

        return true;
    }

    /**
     * Reads a response from the server.
     *
     * <p>Continuously reads data from the {@link SocketChannel} into a buffer,
     * accumulating it until a termination condition is met (end-of-stream or newline).</p>
     *
     * @param socketChannel the channel used to receive data from the server
     * @return the complete response from the server as a {@link String}
     * @throws IOException if an I/O error occurs during reading from the channel
     */
    private String getResponse(SocketChannel socketChannel) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        buffer.clear();

        while (true) {
            int bytesRead = socketChannel.read(buffer);
            if (bytesRead == -1) break; // EOF

            if (bytesRead == 0) {
                // If non-blocking, you'd wait on a selector
                break;
            }

            buffer.flip();
            byte[] chunk = new byte[buffer.remaining()];
            buffer.get(chunk);
            out.write(chunk);
            buffer.clear();

            // Check if we've reached the end marker (e.g. newline)
            String dataSoFar = out.toString(StandardCharsets.UTF_8);
            if (dataSoFar.endsWith("\n")) break;
        }

        return out.toString(StandardCharsets.UTF_8);
    }
}