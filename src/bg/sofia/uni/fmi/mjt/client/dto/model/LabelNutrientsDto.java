package bg.sofia.uni.fmi.mjt.client.dto.model;

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
     * Full constructor to initialize all nutrient values.
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

    public String formatNutrientLabel() {
        StringBuilder sb = new StringBuilder();

        appendIfNotNull(sb, "Calories", calories.getValue(), "kcal");
        appendIfNotNull(sb, "Protein", protein.getValue(), "g");
        appendIfNotNull(sb, "Fat", fat.getValue(), "g");
        appendIfNotNull(sb, "Carbohydrates", carbohydrates.getValue(), "g");
        appendIfNotNull(sb, "Fiber", fiber.getValue(), "g");

        return sb.toString().trim();
    }

    private void appendIfNotNull(StringBuilder sb, String name, Float value, String unit) {
        if (value != null) {
            sb.append(String.format("%-15s: %6.2f %s%n", name, value, unit));
        }
    }
}