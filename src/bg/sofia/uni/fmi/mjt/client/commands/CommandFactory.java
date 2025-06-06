package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.model.BarcodeDto;
import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidCommandException;
import bg.sofia.uni.fmi.mjt.client.utils.CommandParserHelper;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.HELP_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.UNRECOGNIZED_CMD_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.GET_FOOD_BY_BARCODE_CMD;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.GET_FOOD_CMD;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.GET_FOOD_REPORT_CMD;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.HELP_CMD;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.KEYWORD_TOKEN_INDEX;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.ONE_TOKEN;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.PARAM_TOKEN_INDEX;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.QUIT_CMD;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.THREE_TOKENS;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.TWO_TOKENS;

public final class CommandFactory {
    private static final Map<String, Supplier<Command>> simpleCommands = Map.of(
        QUIT_CMD, QuitCommand::new,
        HELP_CMD, HelpCommand::new
    );

    public static Command create(String input) throws InvalidCommandException {
        String[] tokens = CommandParserHelper.splitClientInput(input);
        String keyword = tokens[KEYWORD_TOKEN_INDEX];
        int tokensCount = tokens.length;

        if (tokensCount == ONE_TOKEN) {
            return createSimpleCmd(keyword.toLowerCase());
        }
        if (keyword.equalsIgnoreCase(GET_FOOD_CMD)) {
            return new GetFoodCommand(Arrays.copyOfRange(tokens, PARAM_TOKEN_INDEX, tokensCount));
        }
        if (keyword.equalsIgnoreCase(GET_FOOD_REPORT_CMD) && tokensCount == TWO_TOKENS) {
            return new GetFoodReportCommand(tokens[PARAM_TOKEN_INDEX]);
        }
        if (keyword.equalsIgnoreCase(GET_FOOD_BY_BARCODE_CMD) &&
            (tokensCount == TWO_TOKENS || tokensCount == THREE_TOKENS)) {

            return createComplexCmd(Arrays.copyOfRange(tokens, PARAM_TOKEN_INDEX, tokensCount));
        } else {
            throw new InvalidCommandException(UNRECOGNIZED_CMD_MSG + input + System.lineSeparator() + HELP_MSG);
        }
    }

    private CommandFactory() {
    }

    private static Command createSimpleCmd(String command) throws InvalidCommandException {
        Command cmd = Optional.ofNullable(simpleCommands.get(command.toLowerCase()))
            .map(Supplier::get)
            .orElse(null);

        if (cmd == null) {
            throw new InvalidCommandException(UNRECOGNIZED_CMD_MSG + command + System.lineSeparator() + HELP_MSG);
        }
        return cmd;
    }

    private static Command createComplexCmd(String[] tokens) throws InvalidCommandException {
        BarcodeDto params = CommandParserHelper.parseBarcodeCommand(tokens);

        return new GetFoodByBarcodeCommand(params);
    }
}