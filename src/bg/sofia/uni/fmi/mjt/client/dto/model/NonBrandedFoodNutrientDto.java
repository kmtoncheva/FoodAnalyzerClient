package bg.sofia.uni.fmi.mjt.client.dto.model;

/**
 * A Data Transfer Object (DTO) representing a single nutrient entry from the
 * {@code foodNutrients} array in the USDA FoodData Central API response.
 * <p>
 * Used for non-branded foods that have lab-analyzed nutrient data rather than
 * standardized label nutrients. Each nutrient includes metadata (name, unit, etc.)
 * and a measured amount.
 */
public class NonBrandedFoodNutrientDto {
    private String type;
    private NutrientInfo nutrient;
    private Double amount;

    /**
     * Default no-args constructor for JSON deserialization.
     */
    public NonBrandedFoodNutrientDto() {
    }

    public String getType() {
        return type;
    }

    public NutrientInfo getNutrient() {
        return nutrient;
    }

    public Double getAmount() {
        return amount;
    }

    /**
     * A nested class representing detailed metadata about a nutrient.
     * Contains information such as the nutrient's name, unit of measurement,
     * unique identifier, and ranking.
     */
    public static class NutrientInfo {
        private Integer id;
        private String number;
        private String name;
        private Integer rank;
        private String unitName;

        /**
         * Default no-args constructor for JSON deserialization.
         */
        public NutrientInfo() {
        }

        public String getName() {
            return name;
        }

        public String getUnitName() {
            return unitName;
        }
    }
}