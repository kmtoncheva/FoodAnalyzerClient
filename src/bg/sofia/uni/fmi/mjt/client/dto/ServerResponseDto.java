package bg.sofia.uni.fmi.mjt.client.dto;

import bg.sofia.uni.fmi.mjt.client.dto.enums.ResponseStatusType;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public final class ServerResponseDto {
    private final ResponseStatusType status; // "ok" or "error" or "not_found"
    private final String message;

    private static final Gson gson = new Gson();

    public static ServerResponseDto fromJson(String json) throws JsonSyntaxException {
        return gson.fromJson(json, ServerResponseDto.class);
    }

    private ServerResponseDto(ResponseStatusType status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseStatusType getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}