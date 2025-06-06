package bg.sofia.uni.fmi.mjt.client.dto.model;

public class NutrientDto {
    private Float value; // Use wrapper for null-safety

    public NutrientDto() {
    } // Required for Gson

    public Float getValue() {
        return value;
    }
}