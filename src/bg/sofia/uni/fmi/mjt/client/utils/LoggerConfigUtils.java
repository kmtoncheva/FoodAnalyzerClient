package bg.sofia.uni.fmi.mjt.client.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static bg.sofia.uni.fmi.mjt.client.constants.LoggerConstants.LOCK_EXTENSION;
import static bg.sofia.uni.fmi.mjt.client.constants.LoggerConstants.LOG_FILE;
import static bg.sofia.uni.fmi.mjt.client.constants.LoggerConstants.PATH_NAME;
import static bg.sofia.uni.fmi.mjt.client.constants.LoggerConstants.SETUP_ERROR_MSG;

/**
 * A utility class providing helper methods for configuring client-side logging.
 * <p>
 * Creates and configures {@link Logger} instances with file handlers for persistent logging.
 * Automatically sets up the log directory and registers a shutdown hook to clean up
 * lock files ({@code .lck}) created by the Java logging framework.
 */
public final class LoggerConfigUtils {
    private LoggerConfigUtils() {
    }

    /**
     * Creates and configures a {@link Logger} instance with the specified name.
     * <p>
     * The logger writes to a file specified in {@link bg.sofia.uni.fmi.mjt.client.constants.LoggerConstants#LOG_FILE}
     * and uses {@link SimpleFormatter} for log formatting. A shutdown hook is registered to
     * automatically clean up lock files when the application exits.
     *
     * @param name the name of the logger to create
     * @return a configured {@link Logger} instance
     */
    public static Logger createLogger(String name) {
        Logger logger = Logger.getLogger(name);
        logger.setUseParentHandlers(false); // Avoid default console output

        File logDir = new File(PATH_NAME);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

            // Add shutdown hook to clean up .lck files
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                fileHandler.close();
                cleanupLockFiles();
            }));

        } catch (IOException e) {
            System.err.println(SETUP_ERROR_MSG + e.getMessage());
        }

        return logger;
    }

    /**
     * Cleans up all lock files ({@code .lck}) in the log directory.
     * <p>
     * This method is called automatically via a shutdown hook when the application exits.
     * Lock files are created by the Java logging framework to prevent concurrent access
     * to log files and should be removed when the application terminates.
     */
    private static void cleanupLockFiles() {
        File logDir = new File(PATH_NAME);
        if (logDir.exists() && logDir.isDirectory()) {
            File[] lockFiles = logDir.listFiles((dir, name) -> name.endsWith(LOCK_EXTENSION));
            if (lockFiles != null) {
                for (File lockFile : lockFiles) {
                    lockFile.delete();
                }
            }
        }
    }
}