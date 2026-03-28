package bg.sofia.uni.fmi.mjt.client.dto.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResponseStatusTypeTest {

    @Test
    void testEnumValues() {
        ResponseStatusType[] values = ResponseStatusType.values();

        assertNotNull(values, "Enum values should not be null");
        assertEquals(3, values.length, "Should have 3 enum constants");
    }

    @Test
    void testEnumValueOf() {
        assertEquals(ResponseStatusType.OK, ResponseStatusType.valueOf("OK"));
        assertEquals(ResponseStatusType.ERROR, ResponseStatusType.valueOf("ERROR"));
        assertEquals(ResponseStatusType.NOT_FOUND, ResponseStatusType.valueOf("NOT_FOUND"));
    }

    @Test
    void testEnumOrdinals() {
        assertEquals(0, ResponseStatusType.OK.ordinal());
        assertEquals(1, ResponseStatusType.ERROR.ordinal());
        assertEquals(2, ResponseStatusType.NOT_FOUND.ordinal());
    }
}