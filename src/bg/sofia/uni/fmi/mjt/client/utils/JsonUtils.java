package bg.sofia.uni.fmi.mjt.client.utils;

import bg.sofia.uni.fmi.mjt.client.dto.model.FoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.model.SearchFoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.response.ServerResponseDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.TYPE_FIELD_NAME;

public class JsonUtils {
    private static Gson GSON;

    public static ServerResponseDto parseServerResponse(String jsonResponse,
                                                        String searchType,
                                                        Class<? extends FoodItemDto> foodType) {
        GSON = new GsonBuilder()
            .registerTypeAdapterFactory(
                RuntimeTypeAdapterFactory
                    .of(FoodItemDto.class, TYPE_FIELD_NAME)
                    .registerSubtype(foodType, searchType)
            )
            .create();

        return GSON.fromJson(jsonResponse, ServerResponseDto.class);
    }

    public static Gson getGson() {
        return GSON;
    }

    private JsonUtils(String searchType) {
    }
}