package bg.sofia.uni.fmi.mjt.client.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientRequestDtoTest {

    @Test
    void testConstructorWithStringArgs() {
        ClientRequestDto<String> dto = new ClientRequestDto<>("get-food-report", "12345");
        assertNotNull(dto, "Constructor should create instance");
    }

    @Test
    void testConstructorWithArrayArgs() {
        ClientRequestDto<String[]> dto = new ClientRequestDto<>("get-food", new String[]{"apple", "pie"});
        assertNotNull(dto, "Constructor should create instance with array args");
    }

    @Test
    void testToJsonWithStringArgs() {
        ClientRequestDto<String> dto = new ClientRequestDto<>("get-food-report", "12345");
        String json = dto.toJson();

        assertNotNull(json, "JSON should not be null");
        assertTrue(json.contains("get-food-report"), "JSON should contain command");
        assertTrue(json.contains("12345"), "JSON should contain args");
    }

    @Test
    void testToJsonWithArrayArgs() {
        ClientRequestDto<String[]> dto = new ClientRequestDto<>("get-food", new String[]{"apple", "pie"});
        String json = dto.toJson();

        assertNotNull(json, "JSON should not be null");
        assertTrue(json.contains("get-food"), "JSON should contain command");
        assertTrue(json.contains("apple"), "JSON should contain first arg");
        assertTrue(json.contains("pie"), "JSON should contain second arg");
    }

    @Test
    void testToJsonWithNullArgs() {
        ClientRequestDto<String> dto = new ClientRequestDto<>("quit", null);
        String json = dto.toJson();

        assertNotNull(json, "JSON should not be null");
        assertTrue(json.contains("quit"), "JSON should contain command");
    }
}