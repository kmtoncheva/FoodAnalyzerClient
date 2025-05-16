import bg.sofia.uni.fmi.mjt.client.FoodAnalyzerClient;
import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidCommandException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidCommandException {
        FoodAnalyzerClient client = new FoodAnalyzerClient();
        client.start();

//        String response = "{\"status\":\"OK\",\"message\":\"{\\\"foods\\\":[{\\\"description\\\":\\\"RAFFAELLO, ALMOND COCONUT TREAT\\\",\\\"fdcId\\\":\\\"2041155\\\",\\\"gtinUpc\\\":\\\"009800146130\\\"},{\\\"description\\\":\\\"Babyfood, juice treats, fruit medley, toddler\\\",\\\"fdcId\\\":\\\"173478\\\",\\\"gtinUpc\\\":null}]}\"}";
//        ServerResponseDto dto = ServerResponseDto.fromJson(response);
//        System.out.println(dto.getStatus());
//        System.out.println(dto.getMessage());
//
//        Gson gson = new Gson();
//        GetFoodDto getFoodDto = gson.fromJson(dto.getMessage(), GetFoodDto.class);
//
//        System.out.println(getFoodDto.foods().size());
//        for (FoodItemDto item : getFoodDto.foods()) {
//            System.out.println("desc: " + item.description());
//        }

    }
}