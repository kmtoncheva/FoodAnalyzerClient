package bg.sofia.uni.fmi.mjt.client.dto.model;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.*;

public class BarcodeFoodItemDto implements FoodItemDto {
    private String description;
    private String ingredients;
    private LabelNutrientsDto labelNutrients;

    public BarcodeFoodItemDto() {
    }

    public BarcodeFoodItemDto(String description, String ingredients, LabelNutrientsDto labelNutrients) {
        this.description = description;
        this.ingredients = ingredients;
        this.labelNutrients = labelNutrients;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public LabelNutrientsDto getLabelNutrients() {
        return labelNutrients;
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
