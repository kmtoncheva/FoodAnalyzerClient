package bg.sofia.uni.fmi.mjt.client.dto.model;

import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.RESET;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.YELLOW;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_CALORIES;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_CARBOHYDRATES;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_FAT;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_FIBER;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.EMOJI_PROTEIN;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.FORMAT_NUTRIENT_ALIGNED;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_CALORIES;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_CARBOHYDRATES;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_FAT;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_FIBER;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.NUTRIENT_PROTEIN;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.UNIT_GRAMS;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.UNIT_KCAL;

/**
 * A Data Transfer Object (DTO) representing the standardized nutritional label data
 * for branded food items, typically found on product packaging.
 * <p>
 * Contains the five key nutrients required for food labels: calories, protein, fat,
 * carbohydrates, and fiber. Each nutrient is represented as a {@link NutrientDto}.
 */
public class LabelNutrientsDto {
    private NutrientDto calories;
    private NutrientDto protein;
    private NutrientDto fat;
    private NutrientDto carbohydrates;
    private NutrientDto fiber;

    /**
     * Default no-args constructor for JSON deserialization.
     */
    public LabelNutrientsDto() {
    }

    /**
     * Constructs a new {@code LabelNutrientsDto} with all nutrient values.
     *
     * @param calories       the calorie content
     * @param protein        the protein content
     * @param fat            the fat content
     * @param carbohydrates  the carbohydrate content
     * @param fiber          the fiber content
     */
    public LabelNutrientsDto(NutrientDto calories, NutrientDto protein, NutrientDto fat, NutrientDto carbohydrates,
                             NutrientDto fiber) {
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.fiber = fiber;
    }

    /**
     * Formats the nutritional label data into a colorful, aligned string representation
     * with emojis and proper units for console display for the nutrients available.
     *
     * @return a formatted string containing all available nutrients with their values and units;
     *         nutrients with {@code null} values are omitted
     */
    public String formatNutrientLabel() {
        StringBuilder sb = new StringBuilder();

        appendIfPresent(sb, NUTRIENT_CALORIES, EMOJI_CALORIES, calories, UNIT_KCAL);
        appendIfPresent(sb, NUTRIENT_PROTEIN, EMOJI_PROTEIN, protein, UNIT_GRAMS);
        appendIfPresent(sb, NUTRIENT_FAT, EMOJI_FAT, fat, UNIT_GRAMS);
        appendIfPresent(sb, NUTRIENT_CARBOHYDRATES, EMOJI_CARBOHYDRATES, carbohydrates, UNIT_GRAMS);
        appendIfPresent(sb, NUTRIENT_FIBER, EMOJI_FIBER, fiber, UNIT_GRAMS);

        return sb.toString().trim();
    }

    private void appendIfPresent(StringBuilder sb, String label, String emoji, NutrientDto nutrient, String unit) {
        if (nutrient != null && nutrient.getValue() != null) {
            appendIfNotNull(sb, label, emoji, nutrient.getValue(), unit);
        }
    }

    private void appendIfNotNull(StringBuilder sb, String name, String emoji, Float value, String unit) {
        if (value != null) {
            sb.append(String.format(FORMAT_NUTRIENT_ALIGNED,
                    emoji,
                    YELLOW,
                    name,
                    RESET,
                    YELLOW,
                    value,
                    unit,
                    RESET));
        }
    }
}