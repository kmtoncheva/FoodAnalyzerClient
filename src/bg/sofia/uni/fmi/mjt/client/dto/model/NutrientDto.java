package bg.sofia.uni.fmi.mjt.client.dto.model;

/**
 * A Data Transfer Object (DTO) representing a single nutrient value.
 * <p>
 * Used as a component within {@link LabelNutrientsDto} to represent individual
 * nutrient measurements (e.g., calories, protein, fat). The value is stored as
 * a {@link Float} wrapper to support null-safety for missing or unavailable data.
 */
public class NutrientDto {
    private Float value; // Use wrapper for null-safety

    /**
     * Default no-args constructor for JSON deserialization.
     */
    public NutrientDto() {
    }

    /**
     * Returns the nutrient value.
     *
     * @return the nutrient value as a {@link Float}; may be {@code null} if the value is unavailable
     */
    public Float getValue() {
        return value;
    }
}