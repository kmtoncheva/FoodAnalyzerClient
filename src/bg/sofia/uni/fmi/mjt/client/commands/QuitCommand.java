package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.ClientRequestDto;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.DISCONNECT_FROM_SERVER_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.QUIT_CMD;

public class QuitCommand implements Command{
    @Override
    public void printOutputToClient(String jsonResponse) {
        System.out.println(DISCONNECT_FROM_SERVER_MSG);
    }

    @Override
    public ClientRequestDto getRequest() {
        return new ClientRequestDto(QUIT_CMD, null);
    }

    @Override
    public boolean isTerminatingCommand() {
        return true;
    }
}