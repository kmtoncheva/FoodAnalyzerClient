package bg.sofia.uni.fmi.mjt.client.dto.model;

/**
 * A marker interface representing a food item Data Transfer Object (DTO).
 * All food item DTOs must implement this interface to provide a standardized
 * method for printing their details to the console.
 * <p>
 * Implementations include {@link SearchFoodItemDto}, {@link ReportFoodItemDto},
 * and {@link BarcodeFoodItemDto}.
 */
public interface FoodItemDto {
    /**
     * Prints the food item's details to the console in a formatted, user-friendly manner.
     * The exact format depends on the implementing class.
     */
    void print();
}