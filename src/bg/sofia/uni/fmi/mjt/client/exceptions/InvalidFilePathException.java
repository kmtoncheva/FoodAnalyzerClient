package bg.sofia.uni.fmi.mjt.client.exceptions;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.INVALID_PATH_GENERAL_MSG;

public class InvalidFilePathException extends Exception {
    public InvalidFilePathException(String path, String message) {
        super(String.format(INVALID_PATH_GENERAL_MSG, path, message));
    }

    public InvalidFilePathException(String path, String message, Throwable cause) {
        super(String.format(INVALID_PATH_GENERAL_MSG, path, message), cause);
    }
}