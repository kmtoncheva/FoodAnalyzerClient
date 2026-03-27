package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.enums.ResponseStatusType;
import bg.sofia.uni.fmi.mjt.client.dto.model.BarcodeDto;
import bg.sofia.uni.fmi.mjt.client.dto.model.BarcodeFoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.model.FoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.request.ClientRequestDto;
import bg.sofia.uni.fmi.mjt.client.dto.response.ServerResponseDto;
import bg.sofia.uni.fmi.mjt.client.utils.JsonUtils;

import java.util.List;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.NO_FOODS_FOUND_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.BARCODE_CMD;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.GET_FOOD_BY_BARCODE_CMD;

/**
 * A command that retrieves food information by barcode.
 * <p>
 * Accepts either a barcode code string or an absolute image file path containing a barcode.
 * Sends the request to the server and displays the matching food item's details.
 * Implements {@link Command} to handle the complete request-response cycle.
 */
public final class GetFoodByBarcodeCommand implements Command {
    private BarcodeDto params;

    public GetFoodByBarcodeCommand(BarcodeDto params) {
        this.params = params;
    }

    /**
     * {@inheritDoc}
     * @param jsonResponse the JSON response string received from the server
     */
    @Override
    public void printOutputToClient(String jsonResponse) {
        ServerResponseDto response = JsonUtils.parseServerResponse(jsonResponse, BARCODE_CMD, BarcodeFoodItemDto.class);

        if (response.getStatus().equals(ResponseStatusType.OK)) {
            List<FoodItemDto> foods = response.getFoods();

            if (foods == null || foods.isEmpty()) {
                System.out.println(NO_FOODS_FOUND_MSG);
                return;
            }

            for (FoodItemDto food : foods) {
                food.print();
            }
        } else {
            System.out.println(response.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * @return the request sent to the server.
     */
    @Override
    public ClientRequestDto getRequest() {
        return new ClientRequestDto(GET_FOOD_BY_BARCODE_CMD, params);
    }
}