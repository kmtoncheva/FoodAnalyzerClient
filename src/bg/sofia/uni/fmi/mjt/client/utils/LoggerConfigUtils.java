package bg.sofia.uni.fmi.mjt.client.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static bg.sofia.uni.fmi.mjt.client.constants.LoggerConstants.LOG_FILE;
import static bg.sofia.uni.fmi.mjt.client.constants.LoggerConstants.PATH_NAME;
import static bg.sofia.uni.fmi.mjt.client.constants.LoggerConstants.SETUP_ERROR_MSG;

public final class LoggerConfigUtils {
    private LoggerConfigUtils() {
    }

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

        } catch (IOException e) {
            System.err.println(SETUP_ERROR_MSG + e.getMessage());
        }

        return logger;
    }
}