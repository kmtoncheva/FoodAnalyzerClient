package bg.sofia.uni.fmi.mjt.client.dto.model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchFoodItemDtoTest {

    @Test
    void testConstructorWithAllParameters() {
        SearchFoodItemDto dto = new SearchFoodItemDto("12345", "Apple", "0123456789");
        assertNotNull(dto, "Constructor should create instance");
    }

    @Test
    void testConstructorWithNullGtinUpc() {
        SearchFoodItemDto dto = new SearchFoodItemDto("12345", "Apple", null);
        assertNotNull(dto, "Constructor should handle null gtinUpc");
    }

    @Test
    void testPrintWithAllFieldsPresent() {
        SearchFoodItemDto dto = new SearchFoodItemDto("12345", "Apple, raw", "0123456789");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            dto.print();
            String output = outputStream.toString();

            assertTrue(output.contains("12345"), "Should print FDC ID");
            assertTrue(output.contains("Apple, raw"), "Should print description");
            assertTrue(output.contains("0123456789"), "Should print GTIN/UPC");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPrintWithNullGtinUpc() {
        SearchFoodItemDto dto = new SearchFoodItemDto("12345", "Apple, raw", null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            dto.print();
            String output = outputStream.toString();

            assertTrue(output.contains("12345"), "Should print FDC ID");
            assertTrue(output.contains("Apple, raw"), "Should print description");
        } finally {
            System.setOut(originalOut);
        }
    }
}