package bg.sofia.uni.fmi.mjt.client.dto.model;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.*;

/**
 * A Data Transfer Object (DTO) representing a food item retrieved by barcode lookup.
 * Contains descriptive information, ingredients, and basic nutritional data.
 * <p>
 * This DTO is specifically used for barcode-based food queries and implements
 * {@link FoodItemDto} to provide formatted console output.
 */
public class BarcodeFoodItemDto implements FoodItemDto {
    private String description;
    private String ingredients;
    private LabelNutrientsDto labelNutrients;

    /**
     * Default no-args constructor for JSON deserialization.
     */
    public BarcodeFoodItemDto() {
    }

    /**
     * Constructs a new {@code BarcodeFoodItemDto} with the specified attributes.
     *
     * @param description    the food item's description
     * @param ingredients    the list of ingredients; may be {@code null} for non-branded foods
     * @param labelNutrients the nutritional label data; may be {@code null} if not available
     */
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

    /**
     * Prints the food item's details to the console in a formatted, user-friendly manner.
     * Displays description, ingredients (or a message if missing), and nutritional data
     * (or a message if not available).
     */
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
