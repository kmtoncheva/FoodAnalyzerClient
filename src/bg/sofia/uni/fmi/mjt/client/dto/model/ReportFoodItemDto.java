package bg.sofia.uni.fmi.mjt.client.dto.model;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.DESCR_NAME;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.FDC_ID_NAME;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.INGREDIENTS_NAME;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.INGR_MISSING_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTR_MISSING_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.UPC_NAME;

public class ReportFoodItemDto implements FoodItemDto {
    private String description;
    private String ingredients;
    private LabelNutrientsDto labelNutrients;

    public ReportFoodItemDto() {
    }

    public ReportFoodItemDto(String description, String ingredients, LabelNutrientsDto labelNutrients) {
        this.description = description;
        this.ingredients = ingredients;
        this.labelNutrients = labelNutrients;
    }


    @Override
    public void print() {
        String ingredientsDisplay;
        String nutrientsDisplay;

        if (ingredients != null) {
            ingredientsDisplay = INGREDIENTS_NAME + ingredients;
        } else {
            ingredientsDisplay = INGR_MISSING_MSG;
        }
        if (labelNutrients != null) {
            nutrientsDisplay = labelNutrients.formatNutrientLabel();
        } else {
            nutrientsDisplay = NUTR_MISSING_MSG;
        }

        System.out.println(DESCR_NAME + description);
        System.out.println(ingredientsDisplay);
        System.out.println(nutrientsDisplay);
    }
}