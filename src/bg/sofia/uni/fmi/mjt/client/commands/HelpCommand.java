package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.request.ClientRequestDto;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.DISPLAY_ALL_COMMANDS_MSG;

/**
 * A command that displays the list of available commands to the user.
 * <p>
 * This is a local command that does not require server communication.
 * It prints a formatted help message with all supported commands and their usage.
 */
public final class HelpCommand implements Command {
    @Override
    public void printOutputToClient(String jsonResponse) {
        System.out.println(DISPLAY_ALL_COMMANDS_MSG);
    }

    /**
     *
     * @return null because no request should be sent.
     */
    @Override
    public ClientRequestDto getRequest() {
        return null;
    }
}