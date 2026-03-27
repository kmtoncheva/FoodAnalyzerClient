package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.model.BarcodeDto;
import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidCommandException;

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

/**
 * A factory class responsible for creating {@link Command} instances based on user input.
 * <p>
 * Parses the user's command string and instantiates the appropriate command object.
 * Supported commands include:
 * <ul>
 *   <li>{@code "get-food"} - searches for food items by keywords</li>
 *   <li>{@code "get-food-report"} - retrieves a detailed report by FDC ID</li>
 *   <li>{@code "get-food-by-barcode"} - looks up food by barcode code or image</li>
 *   <li>{@code "help"} - displays available commands</li>
 *   <li>{@code "quit"} - terminates the client application</li>
 * </ul>
 * If an unrecognized command is provided, an {@link InvalidCommandException} is thrown.
 */
public final class CommandFactory {
    private static final Map<String, Supplier<Command>> simpleCommands = Map.of(
        QUIT_CMD, QuitCommand::new,
        HELP_CMD, HelpCommand::new
    );

    /**
     * Parses the user input string and creates the corresponding {@link Command} instance.
     *
     * @param input the raw user input string containing the command and arguments
     * @return a concrete {@link Command} instance corresponding to the parsed input
     * @throws InvalidCommandException if the command is not recognized, has invalid syntax,
     *                                 or contains malformed parameters
     */
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

    /**
     * Creates commands "quit" or "help".
     */
    private static Command createSimpleCmd(String command) throws InvalidCommandException {
        Command cmd = Optional.ofNullable(simpleCommands.get(command.toLowerCase()))
            .map(Supplier::get)
            .orElse(null);

        if (cmd == null) {
            throw new InvalidCommandException(UNRECOGNIZED_CMD_MSG + command + System.lineSeparator() + HELP_MSG);
        }
        return cmd;
    }

    /**
     * Parses the Barcode params and creates the command.
     */
    private static Command createComplexCmd(String[] tokens) throws InvalidCommandException {
        BarcodeDto params = CommandParserHelper.parseBarcodeCommand(tokens);

        return new GetFoodByBarcodeCommand(params);
    }
}