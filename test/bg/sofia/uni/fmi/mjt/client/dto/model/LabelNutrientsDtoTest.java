package bg.sofia.uni.fmi.mjt.client.dto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LabelNutrientsDtoTest {

    @Test
    void testFormatNutrientLabelWithAllNutrientsPresent() {
        NutrientDto calories = createNutrient(100.0f);
        NutrientDto protein = createNutrient(5.0f);
        NutrientDto fat = createNutrient(2.5f);
        NutrientDto carbs = createNutrient(15.0f);
        NutrientDto fiber = createNutrient(3.0f);

        LabelNutrientsDto dto = new LabelNutrientsDto(calories, protein, fat, carbs, fiber);
        String result = dto.formatNutrientLabel();

        assertNotNull(result, "Formatted label should not be null");
        assertTrue(result.contains("100.00"), "Should contain calories value");
        assertTrue(result.contains("5.00"), "Should contain protein value");
        assertTrue(result.contains("2.50"), "Should contain fat value");
        assertTrue(result.contains("15.00"), "Should contain carbs value");
        assertTrue(result.contains("3.00"), "Should contain fiber value");
    }

    @Test
    void testFormatNutrientLabelWithSomeNutrientsNull() {
        NutrientDto calories = createNutrient(100.0f);
        NutrientDto protein = createNutrient(5.0f);

        LabelNutrientsDto dto = new LabelNutrientsDto(calories, protein, null, null, null);
        String result = dto.formatNutrientLabel();

        assertNotNull(result, "Formatted label should not be null");
        assertTrue(result.contains("100.00"), "Should contain calories value");
        assertTrue(result.contains("5.00"), "Should contain protein value");
    }

    @Test
    void testFormatNutrientLabelWithAllNutrientsNull() {
        LabelNutrientsDto dto = new LabelNutrientsDto(null, null, null, null, null);
        String result = dto.formatNutrientLabel();

        assertNotNull(result, "Formatted label should not be null");
        assertEquals("", result, "Should return empty string when all nutrients are null");
    }

    @Test
    void testFormatNutrientLabelWithNutrientsHavingNullValues() {
        NutrientDto calories = new NutrientDto(); // value is null
        NutrientDto protein = createNutrient(5.0f);

        LabelNutrientsDto dto = new LabelNutrientsDto(calories, protein, null, null, null);
        String result = dto.formatNutrientLabel();

        assertNotNull(result, "Formatted label should not be null");
        assertTrue(result.contains("5.00"), "Should contain protein value");
    }

    @Test
    void testNoArgsConstructor() {
        LabelNutrientsDto dto = new LabelNutrientsDto();
        assertNotNull(dto, "No-args constructor should create instance");
    }

    @Test
    void testFormatNutrientLabelWithZeroValues() {
        NutrientDto calories = createNutrient(0.0f);
        NutrientDto protein = createNutrient(0.0f);
        NutrientDto fat = createNutrient(0.0f);

        LabelNutrientsDto dto = new LabelNutrientsDto(calories, protein, fat, null, null);
        String result = dto.formatNutrientLabel();

        assertNotNull(result, "Formatted label should not be null");
        assertTrue(result.contains("0.00"), "Should contain zero values");
    }

    private NutrientDto createNutrient(float value) {
        // Using reflection to set private field since there's no setter
        try {
            NutrientDto nutrient = new NutrientDto();
            java.lang.reflect.Field field = NutrientDto.class.getDeclaredField("value");
            field.setAccessible(true);
            field.set(nutrient, value);
            return nutrient;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create NutrientDto", e);
        }
    }
}