package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.model.BarcodeDto;
import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidCommandException;
import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidFilePathException;
import bg.sofia.uni.fmi.mjt.client.utils.FileUtils;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.DUPLICATE_CODE_PARAM_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.DUPLICATE_IMG_PARAM_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.INVALID_PARAMS_FORMAT_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.MISSING_PARAMS_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.NO_COMMAND_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.UNKNOWN_PARAM_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.CODE_PARAM;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.DOUBLE_QUOTE;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.IMG_PARAM;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.INVALID_TOKEN_INDEX;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.MIN_QUOTED_STRING_LENGTH;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.ONE_TOKEN;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.PARAMS_SPLIT_SYMBOL;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.QUOTE_START_INDEX;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.WHITESPACE_SPLIT_REGEX;

/**
 * A utility class providing helper methods for parsing and validating user command input.
 * <p>
 * Handles splitting command strings, extracting parameters, and validating barcode command
 * arguments. Used by {@link bg.sofia.uni.fmi.mjt.client.commands.CommandFactory} to process
 * raw user input into structured command objects.
 */
public final class CommandParserHelper {
    /**
     * Splits the user input string into tokens based on whitespace.
     *
     * @param input the raw user input string
     * @return an array of tokens representing the command and its arguments
     * @throws InvalidCommandException if the input is {@code null}, empty, or blank
     */
    public static String[] splitClientInput(String input) throws InvalidCommandException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidCommandException(NO_COMMAND_MSG);
        }

        return input.trim().split(WHITESPACE_SPLIT_REGEX);
    }

    /**
     * Parses barcode command parameters from the provided tokens.
     * <p>
     * Expects parameters in the format {@code --code=value} or {@code --img=path}.
     * Validates that at least one parameter is provided and that no duplicates exist.
     * Automatically strips surrounding double quotes from values to support paths.
     *
     * @param params an array of parameter tokens to parse
     * @return a {@link BarcodeDto} containing the parsed barcode code and/or image path
     * @throws InvalidCommandException if parameters are missing, malformed, duplicated,
     *                                 or contain unknown parameter names
     */
    public static BarcodeDto parseBarcodeCommand(String[] params) throws InvalidCommandException {
        validateParams(params);

        String code = null;
        String imagePath = null;

        for (String token : params) {
            String lowerToken = token.toLowerCase();
            int eqIndex = token.indexOf(PARAMS_SPLIT_SYMBOL);

            validateTokenFormat(token, eqIndex);
            String substringPattern = token.substring(eqIndex + ONE_TOKEN);

            substringPattern = stripSurroundingQuotes(substringPattern);

            if (lowerToken.startsWith(CODE_PARAM)) {
                code = handleCodeParameter(code, substringPattern);
            } else if (lowerToken.startsWith(IMG_PARAM)) {
                imagePath = handleImageParameter(imagePath, substringPattern);
            } else {
                throw new InvalidCommandException(String.format(UNKNOWN_PARAM_MSG, token));
            }
        }

        validateAtLeastOneParam(code, imagePath);
        return new BarcodeDto(code, imagePath);
    }

    private static void validateParams(String[] params) throws InvalidCommandException {
        if (params == null) {
            throw new InvalidCommandException(MISSING_PARAMS_MSG);
        }
    }

    private static void validateTokenFormat(String token, int eqIndex) throws InvalidCommandException {
        if (eqIndex == INVALID_TOKEN_INDEX || eqIndex == token.length() - ONE_TOKEN) {
            throw new InvalidCommandException(INVALID_PARAMS_FORMAT_MSG);
        }
    }

    private static String handleCodeParameter(String code, String value) throws InvalidCommandException {
        if (code != null) {
            throw new InvalidCommandException(DUPLICATE_CODE_PARAM_MSG);
        }
        return value;
    }

    private static String handleImageParameter(String imagePath, String value) throws InvalidCommandException {
        if (imagePath != null) {
            throw new InvalidCommandException(DUPLICATE_IMG_PARAM_MSG);
        }
        try {
            FileUtils.validateImagePath(value);
        } catch (InvalidFilePathException e) {
            throw new InvalidCommandException(e.getMessage(), e);
        }
        return value;
    }

    private static void validateAtLeastOneParam(String code, String imagePath) throws InvalidCommandException {
        if (code == null && imagePath == null) {
            throw new InvalidCommandException(MISSING_PARAMS_MSG);
        }
    }

    private static String stripSurroundingQuotes(String value) {
        if (value == null || value.length() < MIN_QUOTED_STRING_LENGTH) {
            return value;
        }

        if (value.charAt(0) == DOUBLE_QUOTE && value.charAt(value.length() - ONE_TOKEN) == DOUBLE_QUOTE) {
            return value.substring(QUOTE_START_INDEX, value.length() - ONE_TOKEN);
        }

        return value;
    }

    private CommandParserHelper() {
    }
}