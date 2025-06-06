package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.model.BarcodeDto;
import bg.sofia.uni.fmi.mjt.client.dto.request.ClientRequestDto;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.GET_FOOD_BY_BARCODE_CMD;

public class GetFoodByBarcodeCommand implements Command {
    private BarcodeDto params;

    public GetFoodByBarcodeCommand(BarcodeDto params) {
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