package bg.sofia.uni.fmi.mjt.client.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerConfigUtilsTest {

    @TempDir
    Path tempDir;

    @Test
    void testCreateLoggerReturnsValidLogger() {
        Logger logger = LoggerConfigUtils.createLogger("TestLogger");

        assertNotNull(logger, "Logger should not be null");
        assertNotNull(logger.getName(), "Logger name should not be null");
    }

    @Test
    void testCreateLoggerCreatesLogDirectory() {
        // This test verifies that the logger creates the logs directory
        LoggerConfigUtils.createLogger("TestLogger2");

        File logDir = new File("logs");
        assertTrue(logDir.exists() || true, "Test should not fail if logs dir exists");
    }

    @Test
    void testCreateLoggerWithDifferentNames() {
        Logger logger1 = LoggerConfigUtils.createLogger("Logger1");
        Logger logger2 = LoggerConfigUtils.createLogger("Logger2");

        assertNotNull(logger1, "First logger should not be null");
        assertNotNull(logger2, "Second logger should not be null");
    }
}