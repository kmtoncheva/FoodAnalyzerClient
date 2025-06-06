package bg.sofia.uni.fmi.mjt.client.constants;

public final class ClientMessagesConstants {
    public static final String GREETING_MSG = "Welcome to Food Analyzer! ";

    public static final String DISCONNECT_FROM_SERVER_MSG = "Disconnecting from the server...";
    public static final String SEE_LOGS_MSG = "Please try again later or see logs for details.";
    public static final String NETWORK_CONNECTION_PROBLEM_MSG = "There is a problem with the network communication. ";

    public static final String HELP_MSG = "Type 'help' to see available commands.";
    public static final String NO_COMMAND_MSG = "No command entered. ";
    public static final String UNRECOGNIZED_CMD_MSG = "Unrecognized command: ";
    public static final String INVALID_PARAMS_FORMAT_MSG =
        "Invalid parameter format: expected '--code=value' or '--img=path'.";
    public static final String UNKNOWN_PARAM_MSG = "Unknown parameter: '%s'. Expected '--code=' or '--img='.";
    public static final String DUPLICATE_CODE_PARAM_MSG = "Duplicate '--code' parameter found. Only one is allowed.";
    public static final String DUPLICATE_IMG_PARAM_MSG = "Duplicate '--img' parameter found. Only one is allowed.";
    public static final String MISSING_PARAMS_MSG = "Missing parameters. Provide at least '--code=' or '--img='.";

    public static final String INVALID_PATH_GENERAL_MSG = "Path '%s' is invalid: %s";
    public static final String EMPTY_PATH_MSG = "The image path cannot be empty.";
    public static final String INVALID_PATH_FORMAT = "The path format is illegal.";
    public static final String NOT_ABSOLUTE_PATH_MSG = "The provided path must be an absolute path.";
    public static final String FILE_DOES_NOT_EXIST_MSG = "The file does not exist at the specified location.";
    public static final String NOT_A_REG_FILE_MSG = "The specified path does not refer to a regular file.";
    public static final String PERMISSION_MSG = "The file is not readable. Please check its permissions.";

    public static final String MATCHED_FOODS_MSG = "\uD83D\uDD0D All items that matched your searched criteria: ";

    private ClientMessagesConstants() {}
}