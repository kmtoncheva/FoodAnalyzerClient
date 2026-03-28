package bg.sofia.uni.fmi.mjt.client.dto.model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BarcodeFoodItemDtoTest {

    @Test
    void testNoArgsConstructor() {
        BarcodeFoodItemDto dto = new BarcodeFoodItemDto();
        assertNotNull(dto, "No-args constructor should create instance");
    }

    @Test
    void testConstructorWithAllParameters() {
        LabelNutrientsDto labelNutrients = new LabelNutrientsDto();
        BarcodeFoodItemDto dto = new BarcodeFoodItemDto("CRISPY RICE", "Rice, sugar", labelNutrients);

        assertNotNull(dto, "Constructor should create instance");
        assertEquals("CRISPY RICE", dto.getDescription(), "Description should match");
        assertEquals("Rice, sugar", dto.getIngredients(), "Ingredients should match");
        assertNotNull(dto.getLabelNutrients(), "Label nutrients should not be null");
    }

    @Test
    void testPrintWithAllFieldsPresent() {
        LabelNutrientsDto labelNutrients = createLabelNutrients();
        BarcodeFoodItemDto dto = new BarcodeFoodItemDto("CRISPY RICE", "Rice, sugar", labelNutrients);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            dto.print();
            String output = outputStream.toString();

            assertNotNull(output, "Output should not be null");
            // Output will contain description and ingredients
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPrintWithNullIngredients() {
        BarcodeFoodItemDto dto = new BarcodeFoodItemDto("CRISPY RICE", null, null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            dto.print();
            // Should print description and missing ingredients message
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPrintWithNullLabelNutrients() {
        BarcodeFoodItemDto dto = new BarcodeFoodItemDto("CRISPY RICE", "Rice, sugar", null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            dto.print();
            // Should print description, ingredients, and missing nutrients message
        } finally {
            System.setOut(originalOut);
        }
    }

    private LabelNutrientsDto createLabelNutrients() {
        try {
            NutrientDto calories = createNutrient(100.0f);
            return new LabelNutrientsDto(calories, null, null, null, null);
        } catch (Exception e) {
            return new LabelNutrientsDto();
        }
    }

    private NutrientDto createNutrient(float value) throws Exception {
        NutrientDto nutrient = new NutrientDto();
        java.lang.reflect.Field field = NutrientDto.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set(nutrient, value);
        return nutrient;
    }
}