package bg.sofia.uni.fmi.mjt.client.dto.response;

import bg.sofia.uni.fmi.mjt.client.dto.enums.ResponseStatusType;
import bg.sofia.uni.fmi.mjt.client.dto.model.FoodItemDto;

import java.util.List;

/**
 * A Data Transfer Object (DTO) representing a server response received by the client.
 * <p>
 * Contains the response status (OK, ERROR, or NOT_FOUND), an optional message,
 * and a list of food items. This DTO is deserialized from JSON responses sent from the server.
 */
public final class ServerResponseDto {
    private ResponseStatusType status; // "ok" or "error" or "not_found"
    private String message;
    private List<FoodItemDto> foods;

    /**
     * Private constructor for JSON deserialization.
     *
     * @param status  the response status type
     * @param message an optional message from the server; may be {@code null}
     * @param foods   the list of food items returned; may be {@code null} or empty
     */
    private ServerResponseDto(ResponseStatusType status, String message, List<FoodItemDto> foods) {
        this.status = status;
        this.message = message;
        this.foods = foods;
    }

    public ResponseStatusType getStatus() {
        return status;
    }

    public List<FoodItemDto> getFoods() {
        return foods;
    }

    public String getMessage() {
        return message;
    }
}