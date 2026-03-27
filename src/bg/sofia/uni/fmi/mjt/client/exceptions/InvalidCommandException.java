package bg.sofia.uni.fmi.mjt.client.exceptions;

/**
 * An exception thrown when a user provides an invalid, unrecognized, or malformed command.
 * <p>
 * This exception is used throughout the command parsing and validation logic to signal
 * that the user's input cannot be processed into a valid {@link bg.sofia.uni.fmi.mjt.client.commands.Command}.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs a new {@code InvalidCommandException} with the specified detail message.
     *
     * @param message the detail message explaining why the command is invalid
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code InvalidCommandException} with the specified detail message and cause.
     *
     * @param message the detail message explaining why the command is invalid
     * @param cause   the underlying cause of this exception
     */
    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}