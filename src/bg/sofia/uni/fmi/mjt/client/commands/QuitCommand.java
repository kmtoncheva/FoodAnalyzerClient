package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.request.ClientRequestDto;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.DISCONNECT_FROM_SERVER_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.QUIT_CMD;

/**
 * A command that terminates the client application.
 * <p>
 * This is a local terminating command that does not require server communication.
 * It signals the client to gracefully disconnect from the server and exit.
 */
public final class QuitCommand implements Command {
    @Override
    public void printOutputToClient(String jsonResponse) {
        System.out.println(DISCONNECT_FROM_SERVER_MSG);
    }

    /**
     *
     * @return quit command to indicate the server that the client has disconnected.
     */
    @Override
    public ClientRequestDto getRequest() {
        return new ClientRequestDto(QUIT_CMD, null);
    }

    @Override
    public boolean isTerminatingCommand() {
        return true;
    }
}