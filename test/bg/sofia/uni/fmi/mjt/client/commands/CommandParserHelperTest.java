package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.dto.model.BarcodeDto;
import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

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

//    @Test
//    public void testParseBarcodeCommandWithValidImageOnly() throws InvalidCommandException{
//        String[] input = {"--img=/valid/path.jpg"};
//
//        try (MockedStatic<FileUtils> mockedFileUtils = mockStatic(FileUtils.class)) {
//            mockedFileUtils.when(() -> FileUtils.validateImagePath("/some/path/to/image.png"))
//                .thenAnswer(invocation -> null);
//
//
//        }
//            BarcodeCommandParams result = CommandParserHelper.parseBarcodeCommand(input);
//        assertNull(result.code());
//        assertEquals("/valid/path.jpg", result.imagePath());
//    }

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
}