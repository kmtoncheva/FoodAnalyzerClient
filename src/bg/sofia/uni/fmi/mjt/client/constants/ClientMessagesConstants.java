package bg.sofia.uni.fmi.mjt.client.constants;

import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.BOLD;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.CYAN;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.CYAN_BOLD;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.GREEN_BOLD;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.RED;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.RED_BOLD;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.RESET;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.YELLOW;

/**
 * Defines constant values for user-facing messages displayed by the client application.
 * All messages include ANSI color codes and emojis for enhanced user experience.
 * Used to standardize communication with the user across different client operations.
 */

public final class ClientMessagesConstants {
    // Greeting message
    public static final String GREETING_MSG =
            GREEN_BOLD + "🍎 Welcome to Food Analyzer! " + RESET;

    // Disconnection and status messages
    public static final String DISCONNECT_FROM_SERVER_MSG =
            YELLOW + "👋 Disconnecting from the server..." + RESET;

    public static final String SEE_LOGS_MSG =
            CYAN + "📋 Please try again later or see logs for details." + RESET;

    public static final String NETWORK_CONNECTION_PROBLEM_MSG =
            RED_BOLD + "❌ There is a problem with the network communication. " + RESET;

    // Help and command messages
    public static final String HELP_MSG =
            CYAN + "💡 Type " + BOLD + "'help'" + RESET + CYAN + " to see available commands." + RESET;

    public static final String NO_COMMAND_MSG =
            YELLOW + "⚠️  No command entered. " + RESET;

    public static final String NO_FOODS_FOUND_MSG =
            YELLOW + "🔍 No foods found for the given barcode." + RESET;

    public static final String UNRECOGNIZED_CMD_MSG =
            RED + "❌ Unrecognized command: " + RESET;

    // Parameter validation messages
    public static final String INVALID_PARAMS_FORMAT_MSG =
            RED + "⚠️  Invalid parameter format: expected " + CYAN + "'--code=value'" +
                    RED + " or " + CYAN + "'--img=path'" + RED + "." + RESET;

    public static final String UNKNOWN_PARAM_MSG =
            RED + "❌ Unknown parameter: " + YELLOW + "'%s'" + RED +
                    ". Expected " + CYAN + "'--code='" + RED + " or " + CYAN + "'--img='" + RED + "." + RESET;

    public static final String DUPLICATE_CODE_PARAM_MSG =
            RED + "⚠️  Duplicate " + CYAN + "'--code'" + RED +
                    " parameter found. Only one is allowed." + RESET;

    public static final String DUPLICATE_IMG_PARAM_MSG =
            RED + "⚠️  Duplicate " + CYAN + "'--img'" + RED +
                    " parameter found. Only one is allowed." + RESET;

    public static final String MISSING_PARAMS_MSG =
            RED + "⚠️  Missing parameters. Provide at least " + CYAN + "'--code='" +
                    RED + " or " + CYAN + "'--img='" + RED + "." + RESET;

    // File path validation messages
    public static final String INVALID_PATH_GENERAL_MSG =
            RED + "📁 Path " + YELLOW + "'%s'" + RED + " is invalid: %s" + RESET;

    public static final String EMPTY_PATH_MSG =
            RED + "⚠️  The image path cannot be empty." + RESET;

    public static final String INVALID_PATH_FORMAT =
            RED + "⚠️  The path format is illegal." + RESET;

    public static final String NOT_ABSOLUTE_PATH_MSG =
            RED + "📂 The provided path must be an absolute path." + RESET;

    public static final String FILE_DOES_NOT_EXIST_MSG =
            RED + "❌ The file does not exist at the specified location." + RESET;

    public static final String NOT_A_REG_FILE_MSG =
            RED + "⚠️  The specified path does not refer to a regular file." + RESET;

    public static final String PERMISSION_MSG =
            RED + "🔒 The file is not readable. Please check its permissions." + RESET;

    // Success message
    public static final String MATCHED_FOODS_MSG =
            GREEN_BOLD + "✅ Found matching items:" + RESET;

    // Prompt messages
    public static final String PROMPT_ARROW =
            CYAN_BOLD + ">>> " + RESET;

    public static final String INPUT_PROMPT =
            BOLD + "Enter command" + RESET;

    private ClientMessagesConstants() { }
}