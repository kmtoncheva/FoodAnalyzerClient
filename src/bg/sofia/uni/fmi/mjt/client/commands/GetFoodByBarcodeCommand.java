package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.BarcodeRequestDto;
import bg.sofia.uni.fmi.mjt.client.dto.ClientRequestDto;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.GET_FOOD_BY_BARCODE_CMD;

public class GetFoodByBarcodeCommand implements Command{
    private BarcodeRequestDto params;

    public GetFoodByBarcodeCommand(BarcodeRequestDto params) {
        this.params = params;
    }

    @Override
    public void printOutputToClient(String jsonResponse) {
        // parse JSON
    }

    @Override
    public ClientRequestDto getRequest() {
        return new ClientRequestDto(GET_FOOD_BY_BARCODE_CMD, params);
    }
}
