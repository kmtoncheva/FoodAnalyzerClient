package bg.sofia.uni.fmi.mjt.client.dto.model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportFoodItemDtoTest {

    @Test
    void testNoArgsConstructor() {
        ReportFoodItemDto dto = new ReportFoodItemDto();
        assertNotNull(dto, "No-args constructor should create instance");
    }

    @Test
    void testConstructorWithLabelNutrients() {
        LabelNutrientsDto labelNutrients = new LabelNutrientsDto();
        ReportFoodItemDto dto = new ReportFoodItemDto("Apple", "Fresh apple", labelNutrients);
        assertNotNull(dto, "Constructor should create instance");
    }

    @Test
    void testConstructorWithFoodNutrients() {
        List<NonBrandedFoodNutrientDto> foodNutrients = new ArrayList<>();
        ReportFoodItemDto dto = new ReportFoodItemDto("Apple", "Fresh apple", null, foodNutrients);
        assertNotNull(dto, "Constructor should create instance with foodNutrients");
    }

    @Test
    void testPrintWithLabelNutrients() {
        LabelNutrientsDto labelNutrients = createLabelNutrients();
        ReportFoodItemDto dto = new ReportFoodItemDto("RAFFAELLO", "Coconut treat", labelNutrients);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            dto.print();
            String output = outputStream.toString();

            assertTrue(output.contains("RAFFAELLO"), "Should print description");
            assertTrue(output.contains("Coconut treat"), "Should print ingredients");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPrintWithFoodNutrients() {
        List<NonBrandedFoodNutrientDto> foodNutrients = createFoodNutrients();
        ReportFoodItemDto dto = new ReportFoodItemDto("Orange juice", null, null, foodNutrients);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            dto.print();
            String output = outputStream.toString();

            assertTrue(output.contains("Orange juice"), "Should print description");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPrintWithNoNutrients() {
        ReportFoodItemDto dto = new ReportFoodItemDto("Apple", "Fresh apple", null, null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            dto.print();
            String output = outputStream.toString();

            assertTrue(output.contains("Apple"), "Should print description");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPrintWithNullIngredients() {
        ReportFoodItemDto dto = new ReportFoodItemDto("Apple", null, null, null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            dto.print();
            String output = outputStream.toString();

            assertTrue(output.contains("Apple"), "Should print description");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPrintWithEmptyFoodNutrientsList() {
        List<NonBrandedFoodNutrientDto> emptyList = new ArrayList<>();
        ReportFoodItemDto dto = new ReportFoodItemDto("Apple", null, null, emptyList);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            dto.print();
            String output = outputStream.toString();

            assertTrue(output.contains("Apple"), "Should print description");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testGettersAndSetters() {
        ReportFoodItemDto dto = new ReportFoodItemDto();
        List<NonBrandedFoodNutrientDto> foodNutrients = new ArrayList<>();

        dto.setFoodNutrients(foodNutrients);
        assertNotNull(dto.getFoodNutrients(), "getFoodNutrients should return set value");
    }

    private LabelNutrientsDto createLabelNutrients() {
        try {
            NutrientDto calories = createNutrient(100.0f);
            NutrientDto protein = createNutrient(2.0f);
            return new LabelNutrientsDto(calories, protein, null, null, null);
        } catch (Exception e) {
            return new LabelNutrientsDto();
        }
    }

    private List<NonBrandedFoodNutrientDto> createFoodNutrients() {
        List<NonBrandedFoodNutrientDto> nutrients = new ArrayList<>();
        NonBrandedFoodNutrientDto nutrient = new NonBrandedFoodNutrientDto();
        nutrients.add(nutrient);
        return nutrients;
    }

    private NutrientDto createNutrient(float value) throws Exception {
        NutrientDto nutrient = new NutrientDto();
        java.lang.reflect.Field field = NutrientDto.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set(nutrient, value);
        return nutrient;
    }
}