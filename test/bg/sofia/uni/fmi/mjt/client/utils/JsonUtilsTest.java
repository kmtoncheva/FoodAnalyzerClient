package bg.sofia.uni.fmi.mjt.client.utils;

import bg.sofia.uni.fmi.mjt.client.dto.enums.ResponseStatusType;
import bg.sofia.uni.fmi.mjt.client.dto.model.SearchFoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.response.ServerResponseDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonUtilsTest {
    @Test
    void testParseServerResponseWithValidJsonAndSearchType() {
        String jsonResponse = "{\"status\":\"OK\",\"message\":\"Success\",\"foods\":[{\"fdcId\":\"171539\",\"description\":\"Soup, beef noodle, canned, condensed\",\"gtinUpc\":\"0071339\"}]}";

        ServerResponseDto response = JsonUtils.parseServerResponse(jsonResponse, "search", SearchFoodItemDto.class);

        assertNotNull(response);
        assertEquals(ResponseStatusType.OK, response.getStatus());
        assertEquals("Success", response.getMessage());
        assertNotNull(response.getFoods());
        assertEquals(1, response.getFoods().size());
    }

    @Test
    void testParseServerResponseWithEmptyFoodsList() {
        String jsonResponse = "{\"status\":\"OK\",\"message\":\"No foods found\",\"foods\":[]}";

        ServerResponseDto response = JsonUtils.parseServerResponse(jsonResponse, "search", SearchFoodItemDto.class);

        assertNotNull(response);
        assertEquals(ResponseStatusType.OK, response.getStatus());
        assertEquals(0, response.getFoods().size());
    }

    @Test
    void testParseServerResponseWithErrorStatus() {
        String jsonResponse = "{\"status\":\"ERROR\",\"message\":\"Invalid request\",\"foods\":null}";

        ServerResponseDto response = JsonUtils.parseServerResponse(jsonResponse, "search", SearchFoodItemDto.class);

        assertNotNull(response);
        assertEquals(ResponseStatusType.ERROR, response.getStatus());
        assertEquals("Invalid request", response.getMessage());
    }

    @Test
    void testGetGsonReturnsValidInstance() {
        Gson gson = JsonUtils.getGson();

        assertNotNull(gson);}
}

