package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.enums.ResponseStatusType;
import bg.sofia.uni.fmi.mjt.client.dto.model.SearchFoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.response.ServerResponseDto;
import bg.sofia.uni.fmi.mjt.client.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class GetFoodCommandTest {

    private static final String VALID_JSON =
            "{\"status\":\"OK\",\"message\":\"Success\"," +
                    "\"foods\":[{\"fdcId\":\"171539\",\"description\":\"Soup, beef noodle\",\"gtinUpc\":null}]}";

    private static final String ERROR_JSON =
            "{\"status\":\"ERROR\",\"message\":\"Service unavailable\",\"foods\":null}";


    @Test
    void testPrintOutputToClientWithOkResponsePrintsFoods() {
        GetFoodCommand cmd = new GetFoodCommand(new String[]{"soup"});

        SearchFoodItemDto foodItem = mock(SearchFoodItemDto.class);
        ServerResponseDto response = mock(ServerResponseDto.class);
        when(response.getStatus()).thenReturn(ResponseStatusType.OK);
        when(response.getFoods()).thenReturn(List.of(foodItem));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try (MockedStatic<JsonUtils> mockedJsonUtils = mockStatic(JsonUtils.class)) {
            mockedJsonUtils.when(() -> JsonUtils.parseServerResponse(
                    eq(VALID_JSON), eq("search"), any())).thenReturn(response);

            cmd.printOutputToClient(VALID_JSON);
        } finally {
            System.setOut(System.out);
        }
    }

    @Test
    void testPrintOutputToClientWithErrorResponsePrintsMessage() {
        GetFoodCommand cmd = new GetFoodCommand(new String[]{"soup"});

        ServerResponseDto response = mock(ServerResponseDto.class);
        when(response.getStatus()).thenReturn(ResponseStatusType.ERROR);
        when(response.getMessage()).thenReturn("Service unavailable");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try (MockedStatic<JsonUtils> mockedJsonUtils = mockStatic(JsonUtils.class)) {
            mockedJsonUtils.when(() -> JsonUtils.parseServerResponse(
                    eq(ERROR_JSON), eq("search"), any())).thenReturn(response);

            cmd.printOutputToClient(ERROR_JSON);

            assertTrue(outputStream.toString().contains("Service unavailable"),
                    "Should print the error message from the server response");
        } finally {
            System.setOut(originalOut);
        }
    }
}
