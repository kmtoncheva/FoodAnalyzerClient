package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.enums.ResponseStatusType;
import bg.sofia.uni.fmi.mjt.client.dto.model.BarcodeDto;
import bg.sofia.uni.fmi.mjt.client.dto.model.BarcodeFoodItemDto;
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

public class GetFoodByBarcodeCommandTest {

    private static final String BARCODE_CODE = "009800146130";
    private static final String VALID_JSON =
            "{\"status\":\"OK\",\"message\":\"Success\"," +
                    "\"foods\":[{\"description\":\"Soup\",\"ingredients\":\"BEEF\"}]}";
    private static final String ERROR_JSON =
            "{\"status\":\"ERROR\",\"message\":\"Barcode not found\",\"foods\":null}";

    @Test
    void testPrintOutputToClientWithOkResponsePrintsFoodDetails() {
        BarcodeDto params = new BarcodeDto(BARCODE_CODE, null);
        GetFoodByBarcodeCommand cmd = new GetFoodByBarcodeCommand(params);

        BarcodeFoodItemDto foodItem = mock(BarcodeFoodItemDto.class);
        ServerResponseDto response = mock(ServerResponseDto.class);
        when(response.getStatus()).thenReturn(ResponseStatusType.OK);
        when(response.getFoods()).thenReturn(List.of(foodItem));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try (MockedStatic<JsonUtils> mockedJsonUtils = mockStatic(JsonUtils.class)) {
            mockedJsonUtils.when(() -> JsonUtils.parseServerResponse(
                    eq(VALID_JSON), eq("barcode"), any())).thenReturn(response);

            cmd.printOutputToClient(VALID_JSON);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPrintOutputToClientWithErrorResponsePrintsMessage() {
        BarcodeDto params = new BarcodeDto(BARCODE_CODE, null);
        GetFoodByBarcodeCommand cmd = new GetFoodByBarcodeCommand(params);

        ServerResponseDto response = mock(ServerResponseDto.class);
        when(response.getStatus()).thenReturn(ResponseStatusType.ERROR);
        when(response.getMessage()).thenReturn("Barcode not found");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try (MockedStatic<JsonUtils> mockedJsonUtils = mockStatic(JsonUtils.class)) {
            mockedJsonUtils.when(() -> JsonUtils.parseServerResponse(
                    eq(ERROR_JSON), eq("barcode"), any())).thenReturn(response);

            cmd.printOutputToClient(ERROR_JSON);

            assertTrue(outputStream.toString().contains("Barcode not found"),
                    "Should print the error message from the server response");
        } finally {
            System.setOut(originalOut);
        }
    }
}
