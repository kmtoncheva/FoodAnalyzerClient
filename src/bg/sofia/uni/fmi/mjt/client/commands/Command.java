package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.request.ClientRequestDto;

/**
 * Represents a command that can be executed on the client side in response to user input.
 * Each implementation of this interface encapsulates the logic for handling a specific
 * type of operation, such as searching for food, retrieving a report, or displaying help.
 * <p>
 * Commands are responsible for creating request DTOs to send to the server and for
 * processing and displaying server responses to the user.
 */
public interface Command {
    /**
     * Processes and prints the server's JSON response to the console in a user-friendly format.
     * The exact formatting depends on the implementing command class.
     *
     * @param jsonResponse the JSON response string received from the server
     */
    void printOutputToClient(String jsonResponse);

    /**
     * Returns the request object to be serialized and sent to the server.
     * <p>
     * Some commands (e.g., {@code help}, {@code quit}) do not require server communication
     * and may return {@code null}.
     *
     * @return a {@link ClientRequestDto} representing the request to send to the server;
     *         may be {@code null} if this command does not require a server request
     */
    ClientRequestDto getRequest();

    /**
     * Indicates whether this command should terminate the client application.
     *
     * @return {@code true} if the command is a terminating command (e.g., "quit");
     *         {@code false} otherwise
     */
    default boolean isTerminatingCommand() {
        return false;
    }
}