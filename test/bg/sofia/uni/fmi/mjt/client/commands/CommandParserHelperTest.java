package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.model.BarcodeDto;
import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserHelperTest {
    @Test
    void testSplitClientInputWithManyTokens() throws InvalidCommandException {
        String input = "command --code=1234 --img=path/to/img";

        String[] expectedTokens = {"command", "--code=1234", "--img=path/to/img"};
        String[] result = CommandParserHelper.splitClientInput(input);

        assertArrayEquals(expectedTokens, result, "Input should be split correctly into tokens.");
    }

    @Test
    public void testSplitClientInputWithSingleToken() throws InvalidCommandException {
        String input = "command";

        String[] expectedTokens = {"command"};
        String[] result = CommandParserHelper.splitClientInput(input);

        assertArrayEquals(expectedTokens, result, "Single token input should be handled correctly.");
    }

    @Test
    public void testSplitClientInputWithEmptyInput() {
        String input = "";

        assertThrows(InvalidCommandException.class, () -> {
            CommandParserHelper.splitClientInput(input);
        }, "Empty input should throw InvalidCommandException.");
    }

    @Test
    public void testSplitClientInputWithWhitespaceOnly() {
        String input = "         ";

        assertThrows(InvalidCommandException.class, () -> {
            CommandParserHelper.splitClientInput(input);
        }, "Input with only whitespace should throw InvalidCommandException.");
    }

    @Test
    public void testSplitClientInputWithNullInput() {
        assertThrows(InvalidCommandException.class, () -> {
            CommandParserHelper.splitClientInput(null);
        }, "Null input should be handled with exception.");
    }

    @Test
    public void testSplitClientInputWithMultipleSpaces() throws InvalidCommandException {
        String input = "command      --code=1234   --img=path/to/img";

        String[] expectedTokens = {"command", "--code=1234", "--img=path/to/img"};
        String[] result = CommandParserHelper.splitClientInput(input);

        assertArrayEquals(expectedTokens, result, "Multiple spaces between tokens should be handled correctly.");
    }

    @Test
    public void testParseBarcodeCommandWithValidCodeOnly() throws InvalidCommandException{
        String[] input = {"--code=123456"};
        BarcodeDto result = CommandParserHelper.parseBarcodeCommand(input);
        assertEquals("123456", result.code(), "Code should be parsed correctly");
        assertNull(result.imagePath(), "When no image param exists it should return null");
    }

    @Test
    void testParseBarcodeCommandWithDuplicatedCode() {
        String[] input = {"--code=123", "--code=456"};
        assertThrows(InvalidCommandException.class,
            () -> CommandParserHelper.parseBarcodeCommand(input));
    }

    @Test
    void testParseBarcodeCommandWithUnknownParams() {
        String[] input = {"--foo=bar"};
        assertThrows(InvalidCommandException.class,
            () -> CommandParserHelper.parseBarcodeCommand(input));
    }

    @Test
    void testParseBarcodeCommandWithMissingParams() {
        String[] input = {"  ", ""};
        assertThrows(InvalidCommandException.class,
            () -> CommandParserHelper.parseBarcodeCommand(input));
    }

    @Test
    void testParseBarcodeCommandWithNullParams() {
        assertThrows(InvalidCommandException.class,
            () -> CommandParserHelper.parseBarcodeCommand(null));
    }

    @Test
    void testParseBarcodeCommandWithInvalidFormatMissingEquals() {
        String[] input = {"--code123456"};
        assertThrows(InvalidCommandException.class,
                () -> CommandParserHelper.parseBarcodeCommand(input),
                "Should throw exception when parameter format is invalid");
    }

    @Test
    void testParseBarcodeCommandWithEmptyValue() {
        String[] input = {"--code="};
        assertThrows(InvalidCommandException.class,
                () -> CommandParserHelper.parseBarcodeCommand(input),
                "Should throw exception when parameter value is empty");
    }

    @Test
    void testParseBarcodeCommandWithBothCodeAndImage() throws Exception {
        // Create a temporary file for testing
        Path tempFile = Files.createTempFile("test-barcode", ".jpg");
        try {
            String[] input = {"--code=123456", "--img=" + tempFile.toAbsolutePath()};
            BarcodeDto result = CommandParserHelper.parseBarcodeCommand(input);

            assertNotNull(result.code(), "Code should not be null");
            assertNotNull(result.imagePath(), "Image path should not be null");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void testParseBarcodeCommandCaseInsensitiveParameters() throws InvalidCommandException {
        String[] input = {"--CODE=123456"};
        BarcodeDto result = CommandParserHelper.parseBarcodeCommand(input);

        assertEquals("123456", result.code(), "Should handle uppercase parameter names");
    }

    @Test
    void testParseBarcodeCommandWithQuotedImagePath() throws Exception {
        Path tempFile = Files.createTempFile("test-barcode", ".jpg");
        try {
            String quotedPath = "\"" + tempFile.toAbsolutePath() + "\"";
            String[] input = {"--img=" + quotedPath};
            BarcodeDto result = CommandParserHelper.parseBarcodeCommand(input);

            assertEquals(tempFile.toAbsolutePath().toString(), result.imagePath(),
                    "Quoted image path should have quotes stripped");
            assertNull(result.code(), "Code should be null when only image is provided");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void testParseBarcodeCommandWithQuotedCode() throws InvalidCommandException {
        String[] input = {"--code=\"123456\""};
        BarcodeDto result = CommandParserHelper.parseBarcodeCommand(input);

        assertEquals("123456", result.code(), "Quoted code should have quotes stripped");
        assertNull(result.imagePath(), "Image path should be null when only code is provided");
    }

    @Test
    void testParseBarcodeCommandWithUnquotedImagePath() throws Exception {
        Path tempFile = Files.createTempFile("test-barcode", ".jpg");
        try {
            String[] input = {"--img=" + tempFile.toAbsolutePath()};
            BarcodeDto result = CommandParserHelper.parseBarcodeCommand(input);

            assertEquals(tempFile.toAbsolutePath().toString(), result.imagePath(),
                    "Unquoted image path should work as before (backward compatibility)");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void testParseBarcodeCommandWithSingleQuote() throws Exception {
        // Edge case: only one quote should not be stripped
        Path tempFile = Files.createTempFile("test-barcode", ".jpg");
        try {
            String pathWithQuote = "\"" + tempFile.toAbsolutePath();
            String[] input = {"--img=" + pathWithQuote};

            // This should fail validation because the path with a single quote is invalid
            assertThrows(InvalidCommandException.class,
                    () -> CommandParserHelper.parseBarcodeCommand(input),
                    "Path with only one quote should fail validation");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void testParseBarcodeCommandWithEmptyQuotes() {
        String[] input = {"--img=\"\""};

        assertThrows(InvalidCommandException.class,
                () -> CommandParserHelper.parseBarcodeCommand(input),
                "Empty quoted string should fail validation");
    }
}