package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.request.ClientRequestDto;

public interface Command {
    void printOutputToClient(String jsonResponse);

    /**
     * Returns the request object to be serialized and sent to the server.
     * Can return null if this command does not require a request (e.g., help).
     */
    ClientRequestDto getRequest();

    default boolean isTerminatingCommand() {
        return false;
    }
}