package bg.sofia.uni.fmi.mjt.client.utils;

import bg.sofia.uni.fmi.mjt.client.dto.model.FoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.model.SearchFoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.response.ServerResponseDto;
import com.google.gson.*;

import java.lang.reflect.Type;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.TYPE_FIELD_NAME;

public class JsonUtils {
    private static Gson GSON;

    public static ServerResponseDto parseServerResponse(String jsonResponse,
                                                        String searchType,
                                                        Class<? extends FoodItemDto> foodType) {
        GSON = new GsonBuilder()
                .registerTypeAdapter(FoodItemDto.class, new JsonDeserializer<FoodItemDto>() {
                    @Override
                    public FoodItemDto deserialize(JsonElement json, Type typeOfT,
                                                   JsonDeserializationContext context) throws JsonParseException {
                        return context.deserialize(json, foodType);
                    }
                })
                .create();

        return GSON.fromJson(jsonResponse, ServerResponseDto.class);
    }

    public static Gson getGson() {
        return GSON;
    }

    private JsonUtils(String searchType) {
    }
}