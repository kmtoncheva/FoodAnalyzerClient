package bg.sofia.uni.fmi.mjt.client.dto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class NutrientDtoTest {

    @Test
    void testNoArgsConstructor() {
        NutrientDto dto = new NutrientDto();
        assertNotNull(dto, "No-args constructor should create instance");
        assertNull(dto.getValue(), "Default value should be null");
    }

    @Test
    void testGetValueReturnsNullByDefault() {
        NutrientDto dto = new NutrientDto();
        assertNull(dto.getValue(), "getValue should return null by default");
    }

    @Test
    void testGetValueAfterDeserialization() throws Exception {
        NutrientDto dto = new NutrientDto();

        // Simulate JSON deserialization by setting value via reflection
        java.lang.reflect.Field field = NutrientDto.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set(dto, 42.5f);

        assertEquals(42.5f, dto.getValue(), "getValue should return the set value");
    }
}