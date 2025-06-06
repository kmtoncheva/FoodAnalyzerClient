package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.enums.ResponseStatusType;
import bg.sofia.uni.fmi.mjt.client.dto.model.FoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.model.ReportFoodItemDto;
import bg.sofia.uni.fmi.mjt.client.dto.request.ClientRequestDto;
import bg.sofia.uni.fmi.mjt.client.dto.response.ServerResponseDto;
import bg.sofia.uni.fmi.mjt.client.utils.JsonUtils;

import java.util.List;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.MATCHED_FOODS_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.FIRST_ELEMENT_IND;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.GET_FOOD_REPORT_CMD;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.REPORT_TYPE_LABEL;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.SEARCH_TYPE_LABEL;

public class GetFoodReportCommand implements Command {
    private String fcdId;

    public GetFoodReportCommand(String fcdId) {
        this.fcdId = fcdId;
    }

    @Override
    public void printOutputToClient(String jsonResponse) {
        ServerResponseDto serverResponse = JsonUtils.parseServerResponse(jsonResponse, REPORT_TYPE_LABEL,
            ReportFoodItemDto.class);

        if (serverResponse.getStatus().equals(ResponseStatusType.OK)) {
            System.out.println(MATCHED_FOODS_MSG + System.lineSeparator());

            FoodItemDto food = serverResponse.getFoods().get(FIRST_ELEMENT_IND);
            food.print();
        } else {
            System.out.println(serverResponse.getMessage());
        }
    }

    @Override
    public ClientRequestDto getRequest() {
        return new ClientRequestDto(GET_FOOD_REPORT_CMD, fcdId);
    }
}