package bg.sofia.uni.fmi.mjt.client.dto.enums;

/**
 * An enumeration representing the possible status types of a server response.
 * <p>
 * Used to indicate whether a client request was successful, encountered an error,
 * or resulted in no matching data being found.
 */
public enum ResponseStatusType {
    OK,
    ERROR,
    NOT_FOUND
}