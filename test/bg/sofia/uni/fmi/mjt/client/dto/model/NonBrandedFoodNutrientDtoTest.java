package bg.sofia.uni.fmi.mjt.client.dto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class NonBrandedFoodNutrientDtoTest {

    @Test
    void testNoArgsConstructor() {
        NonBrandedFoodNutrientDto dto = new NonBrandedFoodNutrientDto();
        assertNotNull(dto, "No-args constructor should create instance");
    }

    @Test
    void testGettersReturnNullByDefault() {
        NonBrandedFoodNutrientDto dto = new NonBrandedFoodNutrientDto();

        assertNull(dto.getType(), "getType should return null by default");
        assertNull(dto.getNutrient(), "getNutrient should return null by default");
        assertNull(dto.getAmount(), "getAmount should return null by default");
    }

    @Test
    void testNutrientInfoNoArgsConstructor() {
        NonBrandedFoodNutrientDto.NutrientInfo info = new NonBrandedFoodNutrientDto.NutrientInfo();
        assertNotNull(info, "NutrientInfo no-args constructor should create instance");
    }

    @Test
    void testNutrientInfoGettersReturnNullByDefault() {
        NonBrandedFoodNutrientDto.NutrientInfo info = new NonBrandedFoodNutrientDto.NutrientInfo();

        assertNull(info.getName(), "getName should return null by default");
        assertNull(info.getUnitName(), "getUnitName should return null by default");
    }
}