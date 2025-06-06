package bg.sofia.uni.fmi.mjt.client.dto.response;

import bg.sofia.uni.fmi.mjt.client.dto.enums.ResponseStatusType;
import bg.sofia.uni.fmi.mjt.client.dto.model.FoodItemDto;

import java.util.List;

public final class ServerResponseDto {
    private ResponseStatusType status; // "ok" or "error" or "not_found"
    private String message;
    private List<FoodItemDto> foods;

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