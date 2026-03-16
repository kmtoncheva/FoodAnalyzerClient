package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.enums.ResponseStatusType;
import bg.sofia.uni.fmi.mjt.client.dto.model.ReportFoodItemDto;
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

public class GetFoodReportCommandTest {

    private static final String FDC_ID = "171539";
    private static final String VALID_JSON =
            "{\"status\":\"OK\",\"message\":\"Success\"," +
                    "\"foods\":[{\"description\":\"Soup, beef noodle\",\"ingredients\":\"BEEF, NOODLES\"}]}";
    private static final String ERROR_JSON =
            "{\"status\":\"ERROR\",\"message\":\"Food not found\",\"foods\":null}";

    @Test
    void testPrintOutputToClientWithOkResponsePrintsFoodDetails() {
        GetFoodReportCommand cmd = new GetFoodReportCommand(FDC_ID);

        ReportFoodItemDto foodItem = mock(ReportFoodItemDto.class);
        ServerResponseDto response = mock(ServerResponseDto.class);
        when(response.getStatus()).thenReturn(ResponseStatusType.OK);
        when(response.getFoods()).thenReturn(List.of(foodItem));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try (MockedStatic<JsonUtils> mockedJsonUtils = mockStatic(JsonUtils.class)) {
            mockedJsonUtils.when(() -> JsonUtils.parseServerResponse(
                    eq(VALID_JSON), eq("report"), any())).thenReturn(response);

            cmd.printOutputToClient(VALID_JSON);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPrintOutputToClientWithErrorResponsePrintsMessage() {
        GetFoodReportCommand cmd = new GetFoodReportCommand(FDC_ID);

        ServerResponseDto response = mock(ServerResponseDto.class);
        when(response.getStatus()).thenReturn(ResponseStatusType.ERROR);
        when(response.getMessage()).thenReturn("Food not found");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try (MockedStatic<JsonUtils> mockedJsonUtils = mockStatic(JsonUtils.class)) {
            mockedJsonUtils.when(() -> JsonUtils.parseServerResponse(
                    eq(ERROR_JSON), eq("report"), any())).thenReturn(response);

            cmd.printOutputToClient(ERROR_JSON);

            assertTrue(outputStream.toString().contains("Food not found"),
                    "Should print the error message from the server response");
        } finally {
            System.setOut(originalOut);
        }
    }
}
