package bg.sofia.uni.fmi.mjt.client.dto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BarcodeDtoTest {

    @Test
    void testRecordWithBothParameters() {
        BarcodeDto dto = new BarcodeDto("123456789", "/path/to/image.jpg");

        assertNotNull(dto, "Record should be created");
        assertEquals("123456789", dto.code(), "Code should match");
        assertEquals("/path/to/image.jpg", dto.imagePath(), "Image path should match");
    }

    @Test
    void testRecordWithNullCode() {
        BarcodeDto dto = new BarcodeDto(null, "/path/to/image.jpg");

        assertNull(dto.code(), "Code should be null");
        assertEquals("/path/to/image.jpg", dto.imagePath(), "Image path should match");
    }

    @Test
    void testRecordWithNullImagePath() {
        BarcodeDto dto = new BarcodeDto("123456789", null);

        assertEquals("123456789", dto.code(), "Code should match");
        assertNull(dto.imagePath(), "Image path should be null");
    }

    @Test
    void testRecordWithBothNull() {
        BarcodeDto dto = new BarcodeDto(null, null);

        assertNull(dto.code(), "Code should be null");
        assertNull(dto.imagePath(), "Image path should be null");
    }

    @Test
    void testRecordEquality() {
        BarcodeDto dto1 = new BarcodeDto("123456789", "/path/to/image.jpg");
        BarcodeDto dto2 = new BarcodeDto("123456789", "/path/to/image.jpg");

        assertEquals(dto1, dto2, "Records with same values should be equal");
    }

    @Test
    void testRecordHashCode() {
        BarcodeDto dto1 = new BarcodeDto("123456789", "/path/to/image.jpg");
        BarcodeDto dto2 = new BarcodeDto("123456789", "/path/to/image.jpg");

        assertEquals(dto1.hashCode(), dto2.hashCode(), "Records with same values should have same hash code");
    }
}