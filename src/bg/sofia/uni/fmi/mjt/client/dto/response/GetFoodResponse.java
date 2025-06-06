package bg.sofia.uni.fmi.mjt.client.dto.response;

import bg.sofia.uni.fmi.mjt.client.dto.model.SearchFoodItemDto;

import java.util.List;

public class GetFoodResponse {
    private List<SearchFoodItemDto> foodItemDtos;

    public GetFoodResponse(List<SearchFoodItemDto> foodItemDtos) {
        this.foodItemDtos = foodItemDtos;
    }

    public List<SearchFoodItemDto> getFoodItemDtos() {
        return foodItemDtos;
    }
}