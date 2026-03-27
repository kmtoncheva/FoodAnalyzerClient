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

/**
 * A command that searches for food items by keywords.
 * <p>
 * Sends a search request to the server with the provided keywords and displays
 * the matching food items to the user. Implements {@link Command} to handle
 * the complete request-response cycle.
 */
public final class GetFoodCommand implements Command {
    private final String[] tokens;

    public GetFoodCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * {@inheritDoc}
     * @param jsonResponse the JSON response string received from the server
     */
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

    /**
     * {@inheritDoc}
     * @return the request that would be sent to the server.
     */
    @Override
    public ClientRequestDto getRequest() {
        return new ClientRequestDto(GET_FOOD_CMD, tokens);
    }
}