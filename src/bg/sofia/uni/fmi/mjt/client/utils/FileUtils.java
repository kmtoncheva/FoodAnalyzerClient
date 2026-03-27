package bg.sofia.uni.fmi.mjt.client.utils;

import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidFilePathException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.EMPTY_PATH_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.FILE_DOES_NOT_EXIST_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.INVALID_PATH_FORMAT;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.NOT_ABSOLUTE_PATH_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.NOT_A_REG_FILE_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.PERMISSION_MSG;

/**
 * A utility class providing helper methods for file path validation.
 * <p>
 * Used to validate image file paths provided by users for barcode scanning operations.
 * Ensures that paths are absolute, exist, are regular files, and are readable.
 */
public final class FileUtils {
    private FileUtils() {
    }

    /**
     * Validates that the specified image path is valid and accessible.
     * <p>
     * Performs the following checks:
     * <ul>
     *   <li>Path is not {@code null} or blank</li>
     *   <li>Path has valid syntax</li>
     *   <li>Path is absolute</li>
     *   <li>File exists at the specified path</li>
     *   <li>Path refers to a regular file (not a directory)</li>
     *   <li>File is readable</li>
     * </ul>
     *
     * @param pathName the file path string to validate
     * @throws InvalidFilePathException if any validation check fails, with a descriptive message
     */
    public static void validateImagePath(String pathName) throws InvalidFilePathException {
        if (pathName == null || pathName.isBlank()) {
            throw new InvalidFilePathException(pathName, EMPTY_PATH_MSG);
        }

        Path path;

        try {
            path = Paths.get(pathName);
        } catch (InvalidPathException e) {
            throw new InvalidFilePathException(pathName, INVALID_PATH_FORMAT);
        }

        if (!path.isAbsolute()) {
            throw new InvalidFilePathException(pathName, NOT_ABSOLUTE_PATH_MSG);
        }
        if (!Files.exists(path)) {
            throw new InvalidFilePathException(pathName, FILE_DOES_NOT_EXIST_MSG);
        }
        if (!Files.isRegularFile(path)) {
            throw new InvalidFilePathException(pathName, NOT_A_REG_FILE_MSG);
        }
        if (!Files.isReadable(path)) {
            throw new InvalidFilePathException(pathName, PERMISSION_MSG);
        }
    }
}