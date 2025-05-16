package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.ClientRequestDto;
import bg.sofia.uni.fmi.mjt.client.dto.FoodItemDtos;
import bg.sofia.uni.fmi.mjt.client.dto.GetFoodDto;
import bg.sofia.uni.fmi.mjt.client.dto.ServerResponseDto;
import bg.sofia.uni.fmi.mjt.client.dto.enums.ResponseStatusType;
import com.google.gson.Gson;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.GET_FOOD_CMD;

public class GetFoodCommand implements Command{
    private String[] tokens; // can be more than one : beef noodles ???

    public GetFoodCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void printOutputToClient(String jsonResponse) {
        // FIX !!! - JSON mapper maybe
        Gson gson = new Gson();

        ServerResponseDto serverResponse = ServerResponseDto.fromJson(jsonResponse);
        if(serverResponse.getStatus().equals(ResponseStatusType.OK)) {

//            String innerJson = serverResponse.getMessage(); // this is the JSON string inside the message field
//            GetFoodDto getFoodDto = gson.fromJson(innerJson, GetFoodDto.class);


            GetFoodDto getFoodDto = gson.fromJson(serverResponse.getMessage(), GetFoodDto.class);

            for (FoodItemDtos foodItem : getFoodDto.foods()) {
                System.out.println("Description of food : " + foodItem.description());
                System.out.println("FDC ID : " + foodItem.fdcId());
                if(foodItem.gtinUpc() != null) {
                    System.out.println("GTIN UPC : " + foodItem.gtinUpc() + "\n");
                }
            }
        }
        else {
            System.out.println(serverResponse.getMessage());
        }

        // print food descr + id + gtp (if present)
    }

    @Override
    public ClientRequestDto getRequest() {
        return new ClientRequestDto(GET_FOOD_CMD, tokens);
    }
}