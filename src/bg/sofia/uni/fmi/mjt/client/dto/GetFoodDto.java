package bg.sofia.uni.fmi.mjt.client.dto;

import java.util.List;

public record GetFoodDto(List<FoodItemDtos> foods) {
}