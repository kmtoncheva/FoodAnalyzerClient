package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.model.FoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.model.SearchFoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.request.ClientRequestDto;
import bg.sofia.uni.fmi.mjt.client.dto.response.ServerResponseDto;
import bg.sofia.uni.fmi.mjt.client.dto.enums.ResponseStatusType;
import bg.sofia.uni.fmi.mjt.client.utils.JsonUtils;

import java.util.List;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.MATCHED_FOODS_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.GET_FOOD_CMD;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.SEARCH_TYPE_LABEL;

public class GetFoodCommand implements Command {
    private String[] tokens;

    public GetFoodCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void printOutputToClient(String jsonResponse) {
        ServerResponseDto serverResponse = JsonUtils.parseServerResponse(jsonResponse, SEARCH_TYPE_LABEL,
            SearchFoodItemDto.class);

        if (serverResponse.getStatus().equals(ResponseStatusType.OK)) {
            System.out.println(MATCHED_FOODS_MSG + System.lineSeparator());

            List<FoodItemDto> foods = serverResponse.getFoods();

            for (FoodItemDto foodItem : foods) {
                foodItem.print();
                System.out.println(System.lineSeparator());
            }
        } else {
            System.out.println(serverResponse.getMessage());
        }
    }

    @Override
    public ClientRequestDto getRequest() {
        return new ClientRequestDto(GET_FOOD_CMD, tokens);
    }
}