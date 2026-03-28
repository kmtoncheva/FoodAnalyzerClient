package bg.sofia.uni.fmi.mjt.client.constants;

/**
 * Defines constant values related to client-side logging configuration.
 * Used to standardize log file paths, directory names, and logging-related messages.
 */
public final class LoggerConstants {
    public static final String LOG_FILE = "logs/client.log";
    public static final String PATH_NAME = "logs";
    public static final String SETUP_ERROR_MSG = "Could not set up logger: ";
    public static final String LOCK_EXTENSION = ".lck";

    public static final String SERVER_CONNECTION_ERROR_MSG = "Client could not connect or lost connection to server";

    private LoggerConstants() { }
}
