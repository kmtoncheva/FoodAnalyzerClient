package bg.sofia.uni.fmi.mjt.client.utils;

import bg.sofia.uni.fmi.mjt.client.dto.model.FoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.response.ServerResponseDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * A utility class providing helper methods for JSON parsing and deserialization.
 * <p>
 * Uses Gson to deserialize server responses into appropriate DTO objects.
 * Supports polymorphic deserialization of {@link FoodItemDto} based on the response type.
 */
public class JsonUtils {
    private static Gson gson;

    /**
     * Parses a JSON server response string into a {@link ServerResponseDto} object.
     * <p>
     * Configures Gson with a custom type adapter to deserialize {@link FoodItemDto}
     * instances into the appropriate concrete type based on the search type.
     *
     * @param jsonResponse the JSON response string from the server
     * @param searchType   the type of search performed (e.g., "search", "report")
     * @param foodType     the concrete {@link FoodItemDto} class to deserialize into
     * @return a {@link ServerResponseDto} containing the parsed response data
     */
    public static ServerResponseDto parseServerResponse(String jsonResponse,
                                                        String searchType,
                                                        Class<? extends FoodItemDto> foodType) {
        gson = new GsonBuilder()
                .registerTypeAdapter(FoodItemDto.class, new JsonDeserializer<FoodItemDto>() {
                    @Override
                    public FoodItemDto deserialize(JsonElement json, Type typeOfT,
                                                   JsonDeserializationContext context) throws JsonParseException {
                        return context.deserialize(json, foodType);
                    }
                })
                .create();

        return gson.fromJson(jsonResponse, ServerResponseDto.class);
    }

    public static Gson getGson() {
        return gson;
    }

    private JsonUtils(String searchType) {
    }
}