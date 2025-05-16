package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.ClientRequestDto;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.DISPLAY_ALL_COMMANDS_MSG;

public class HelpCommand implements Command{
    @Override
    public void printOutputToClient(String jsonResponse) {
        System.out.println(DISPLAY_ALL_COMMANDS_MSG);
    }

    @Override
    public ClientRequestDto getRequest() {
        return null; // No request to send
    }
}
