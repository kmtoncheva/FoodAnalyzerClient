package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.ClientRequestDto;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.GET_FOOD_REPORT_CMD;

public class GetFoodReportCommand implements Command{
    private String fcdId;

    public GetFoodReportCommand(String fcdId) {
        this.fcdId = fcdId;
    }

    @Override
    public void printOutputToClient(String jsonResponse) {
        // parse JSON
    }

    @Override
    public ClientRequestDto getRequest() {
        return new ClientRequestDto(GET_FOOD_REPORT_CMD, fcdId);
    }
}
