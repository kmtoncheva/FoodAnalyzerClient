package bg.sofia.uni.fmi.mjt.client.exceptions;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.INVALID_PATH_GENERAL_MSG;

/**
 * An exception thrown when a user provides an invalid, inaccessible, or malformed file path.
 * <p>
 * This exception is used during file path validation to signal that the provided path
 * cannot be used (e.g., does not exist, is not absolute, is not readable).
 */
public class InvalidFilePathException extends Exception {
    /**
     * Constructs a new {@code InvalidFilePathException} with the specified path and detail message.
     *
     * @param path    the invalid file path that caused this exception
     * @param message the detail message explaining why the path is invalid
     */
    public InvalidFilePathException(String path, String message) {
        super(String.format(INVALID_PATH_GENERAL_MSG, path, message));
    }

    /**
     * Constructs a new {@code InvalidFilePathException} with the specified path, detail message, and cause.
     *
     * @param path    the invalid file path that caused this exception
     * @param message the detail message explaining why the path is invalid
     * @param cause   the underlying cause of this exception
     */
    public InvalidFilePathException(String path, String message, Throwable cause) {
        super(String.format(INVALID_PATH_GENERAL_MSG, path, message), cause);
    }
}