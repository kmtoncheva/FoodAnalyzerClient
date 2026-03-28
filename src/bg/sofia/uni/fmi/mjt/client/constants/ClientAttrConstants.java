package bg.sofia.uni.fmi.mjt.client.constants;

/**
 * Defines constant values related to client configuration and network communication.
 * Used to standardize connection parameters and buffer sizes across the client application.
 */
public final class ClientAttrConstants {
    public static final int SERVER_PORT = 52525;
    public static final String SERVER_HOST = "localhost";
    public static final int BUFFER_SIZE = 1024;

    private ClientAttrConstants() { }
}