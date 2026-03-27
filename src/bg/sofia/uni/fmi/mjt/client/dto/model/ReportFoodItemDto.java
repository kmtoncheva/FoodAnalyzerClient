package bg.sofia.uni.fmi.mjt.client.dto.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.CYAN_BOLD;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.GREEN_BOLD;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.RESET;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.YELLOW;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.BRANDED_NUTRIENTS_HEADER;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.DESCR_NAME;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_CALCIUM;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_CARBOHYDRATE;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_ENERGY;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_FIBER;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_INFO;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_IRON;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_POTASSIUM;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_PROTEIN;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_SODIUM;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_SUGARS;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_TOTAL_LIPID;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_VITAMIN_A;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_VITAMIN_C;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_VITAMIN_D;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.FORMAT_NUTRIENT_ALIGNED;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.INGREDIENTS_NAME;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.INGR_MISSING_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NON_BRANDED_NUTRIENTS_HEADER;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NO_KEY_NUTRIENTS_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_CALCIUM;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_CARBOHYDRATE_DIFF;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_ENERGY;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_FIBER_DIETARY;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_IRON;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_POTASSIUM;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_PROTEIN;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_SODIUM;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_TOTAL_LIPID;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_TOTAL_SUGARS;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_VITAMIN_A;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_VITAMIN_C;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_VITAMIN_D;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTR_MISSING_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.UNIT_EMPTY;

/**
 * A Data Transfer Object (DTO) representing a detailed food item report retrieved
 * from the USDA FoodData Central API.
 * <p>
 * Contains descriptive information, ingredients, and nutritional data. Supports both
 * branded foods (with {@code labelNutrients}) and non-branded foods (with {@code foodNutrients}).
 * This DTO implements {@link FoodItemDto} to provide formatted console output with
 * intelligent fallback logic for displaying available nutrient data.
 */
public class ReportFoodItemDto implements FoodItemDto {
    private String description;
    private String ingredients;
    private LabelNutrientsDto labelNutrients;
    private List<NonBrandedFoodNutrientDto> foodNutrients;

    /**
     * Default no-args constructor for JSON deserialization.
     */
    public ReportFoodItemDto() {
    }

    /**
     * Constructs a new {@code ReportFoodItemDto} for branded foods with label nutrients.
     *
     * @param description    the food item's description
     * @param ingredients    the list of ingredients; may be {@code null} for non-branded foods
     * @param labelNutrients the standardized nutritional label data; may be {@code null}
     */
    public ReportFoodItemDto(String description, String ingredients, LabelNutrientsDto labelNutrients) {
        this.description = description;
        this.ingredients = ingredients;
        this.labelNutrients = labelNutrients;
    }

    /**
     * Constructs a new {@code ReportFoodItemDto} with support for both branded and non-branded foods.
     *
     * @param description    the food item's description
     * @param ingredients    the list of ingredients; may be {@code null} for non-branded foods
     * @param labelNutrients the standardized nutritional label data; may be {@code null}
     * @param foodNutrients  the detailed lab-analyzed nutrient data; may be {@code null}
     */
    public ReportFoodItemDto(String description, String ingredients, LabelNutrientsDto labelNutrients,
                             List<NonBrandedFoodNutrientDto> foodNutrients) {
        this.description = description;
        this.ingredients = ingredients;
        this.labelNutrients = labelNutrients;
        this.foodNutrients = foodNutrients;
    }

    /**
     * Prints the food item's details to the console in a formatted, user-friendly manner.
     * <p>
     * Uses intelligent fallback logic:
     * <ul>
     *   <li>If {@code labelNutrients} is present, displays branded food nutrition facts</li>
     *   <li>If {@code labelNutrients} is null but {@code foodNutrients} is present, extracts and
     *       displays key lab-analyzed nutrients</li>
     *   <li>If both are null, displays a message indicating nutrients are not available</li>
     * </ul>
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
            nutrientsDisplay = GREEN_BOLD + BRANDED_NUTRIENTS_HEADER + RESET +
                    labelNutrients.formatNutrientLabel();
        } else if (foodNutrients != null && !foodNutrients.isEmpty()) {
            nutrientsDisplay = CYAN_BOLD + NON_BRANDED_NUTRIENTS_HEADER + RESET +
                    formatFoodNutrients();
        } else {
            nutrientsDisplay = NUTR_MISSING_MSG;
        }

        System.out.println(DESCR_NAME + description);
        System.out.println(ingredientsDisplay);
        System.out.println(nutrientsDisplay);
    }

    public List<NonBrandedFoodNutrientDto> getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(List<NonBrandedFoodNutrientDto> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }

    /**
     * Formats the {@code foodNutrients} array by extracting key nutrients commonly of interest
     * to users (e.g., energy, protein, vitamins, minerals).
     * <p>
     * This method handles non-branded foods with lab-analyzed data and displays them in a
     * colorful, aligned format with emojis for better readability.
     *
     * @return a formatted string containing key nutrients with their values and units;
     *         returns a message if no key nutrients are found
     */
    private String formatFoodNutrients() {
        StringBuilder sb = new StringBuilder();
        Map<String, String> keyNutrients = buildKeyNutrientsMap();

        for (NonBrandedFoodNutrientDto nutrient : foodNutrients) {
            appendNutrientIfValid(sb, nutrient, keyNutrients);
        }

        appendFallbackIfEmpty(sb);
        return sb.toString();
    }

    private Map<String, String> buildKeyNutrientsMap() {
        Map<String, String> keyNutrients = new LinkedHashMap<>();
        keyNutrients.put(NUTRIENT_ENERGY, EMOJI_ENERGY);
        keyNutrients.put(NUTRIENT_PROTEIN, EMOJI_PROTEIN);
        keyNutrients.put(NUTRIENT_TOTAL_LIPID, EMOJI_TOTAL_LIPID);
        keyNutrients.put(NUTRIENT_CARBOHYDRATE_DIFF, EMOJI_CARBOHYDRATE);
        keyNutrients.put(NUTRIENT_FIBER_DIETARY, EMOJI_FIBER);
        keyNutrients.put(NUTRIENT_TOTAL_SUGARS, EMOJI_SUGARS);
        keyNutrients.put(NUTRIENT_CALCIUM, EMOJI_CALCIUM);
        keyNutrients.put(NUTRIENT_IRON, EMOJI_IRON);
        keyNutrients.put(NUTRIENT_VITAMIN_C, EMOJI_VITAMIN_C);
        keyNutrients.put(NUTRIENT_SODIUM, EMOJI_SODIUM);
        keyNutrients.put(NUTRIENT_POTASSIUM, EMOJI_POTASSIUM);
        keyNutrients.put(NUTRIENT_VITAMIN_A, EMOJI_VITAMIN_A);
        keyNutrients.put(NUTRIENT_VITAMIN_D, EMOJI_VITAMIN_D);
        return keyNutrients;
    }

    private void appendNutrientIfValid(StringBuilder sb,
                                       NonBrandedFoodNutrientDto nutrient,
                                       Map<String, String> keyNutrients) {

        if (!isValidNutrient(nutrient)) {
            return;
        }

        String name = nutrient.getNutrient().getName();
        String emoji = keyNutrients.get(name);

        if (emoji != null && nutrient.getAmount() != null) {
            sb.append(formatNutrientLine(nutrient, name, emoji));
        }
    }

    private boolean isValidNutrient(NonBrandedFoodNutrientDto nutrient) {
        return nutrient.getNutrient() != null
                && nutrient.getNutrient().getName() != null;
    }

    private String formatNutrientLine(NonBrandedFoodNutrientDto nutrient,
                                      String name,
                                      String emoji) {

        String unit = nutrient.getNutrient().getUnitName() != null
                ? nutrient.getNutrient().getUnitName()
                : UNIT_EMPTY;

        return String.format(
                FORMAT_NUTRIENT_ALIGNED,
                emoji,
                YELLOW, name, RESET,
                YELLOW, nutrient.getAmount(), unit, RESET
        );
    }

    private void appendFallbackIfEmpty(StringBuilder sb) {
        if (sb.isEmpty()) {
            sb.append(YELLOW)
                    .append(EMOJI_INFO)
                    .append(NO_KEY_NUTRIENTS_MSG)
                    .append(RESET);
        }
    }
}