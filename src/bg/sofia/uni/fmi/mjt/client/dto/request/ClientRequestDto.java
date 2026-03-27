package bg.sofia.uni.fmi.mjt.client.dto.request;

import com.google.gson.Gson;

/**
 * A Data Transfer Object (DTO) representing a client request to be sent to the server.
 * <p>
 * Encapsulates the command name and its arguments in a generic structure that can be
 * serialized to JSON for network transmission. The type parameter {@code T} allows
 * for flexible argument types (e.g., {@code String}, {@code String[]}, {@code BarcodeDto}).
 *
 * @param <T> the type of the command arguments; can be any serializable type
 */
public final class ClientRequestDto<T> {
    private String command;
    private T args;

    /**
     * Constructs a new {@code ClientRequestDto} with the specified command and arguments.
     *
     * @param command the command name (e.g., "get-food", "get-food-report")
     * @param args    the command arguments; type depends on the specific command
     */
    public ClientRequestDto(String command, T args) {
        this.command = command;
        this.args = args;
    }

    /**
     * Serializes this request DTO to a JSON string for network transmission.
     *
     * @return a JSON string representation of this request
     */
    public String toJson() {
        return new Gson().toJson(this);
    }
}