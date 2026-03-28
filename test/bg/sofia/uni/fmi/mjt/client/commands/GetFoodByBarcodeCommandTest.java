package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.model.BarcodeDto;
import bg.sofia.uni.fmi.mjt.client.dto.request.ClientRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetFoodByBarcodeCommandTest {

    @Test
    void testConstructorWithBarcodeDto() {
        BarcodeDto barcodeDto = new BarcodeDto("123456", null);
        GetFoodByBarcodeCommand cmd = new GetFoodByBarcodeCommand(barcodeDto);

        assertNotNull(cmd, "Constructor should create instance");
    }

    @Test
    void testGetRequestReturnsValidDto() {
        BarcodeDto barcodeDto = new BarcodeDto("123456", null);
        GetFoodByBarcodeCommand cmd = new GetFoodByBarcodeCommand(barcodeDto);

        ClientRequestDto request = cmd.getRequest();

        assertNotNull(request, "Request should not be null");
        assertNotNull(request.toJson(), "Request JSON should not be null");
    }

    @Test
    void testIsTerminatingCommandReturnsFalse() {
        BarcodeDto barcodeDto = new BarcodeDto("123456", null);
        GetFoodByBarcodeCommand cmd = new GetFoodByBarcodeCommand(barcodeDto);

        assertFalse(cmd.isTerminatingCommand(), "GetFoodByBarcodeCommand should not be terminating");
    }

    @Test
    void testPrintOutputToClientDoesNotThrow() {
        BarcodeDto barcodeDto = new BarcodeDto("123456", null);
        GetFoodByBarcodeCommand cmd = new GetFoodByBarcodeCommand(barcodeDto);

        String validJson = "{\"status\":\"OK\",\"message\":\"Success\",\"foods\":[]}";

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(
                () -> cmd.printOutputToClient(validJson),
                "printOutputToClient should not throw");
    }
}