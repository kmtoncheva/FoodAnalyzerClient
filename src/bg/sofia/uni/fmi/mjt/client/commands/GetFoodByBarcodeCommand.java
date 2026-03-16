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
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.GET_FOOD_BY_BARCODE_CMD;

public class GetFoodByBarcodeCommand implements Command {
    private BarcodeDto params;

    public GetFoodByBarcodeCommand(BarcodeDto params) {
        this.params = params;
    }

    @Override
    public void printOutputToClient(String jsonResponse) {
        ServerResponseDto response = JsonUtils.parseServerResponse(jsonResponse, "barcode", BarcodeFoodItemDto.class);

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


    @Override
    public ClientRequestDto getRequest() {
        return new ClientRequestDto(GET_FOOD_BY_BARCODE_CMD, params);
    }
}