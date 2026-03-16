package bg.sofia.uni.fmi.mjt.client.commands;

import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.UNRECOGNIZED_CMD_MSG;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandFactoryTest {
    @Test
    void testCreateQuitCommand() throws InvalidCommandException {
        Command cmd = CommandFactory.create("quit");

        assertInstanceOf(QuitCommand.class, cmd,
                "Should create a QuitCommand for input 'quit'");
    }

    @Test
    void testCreateQuitCommandCaseInsensitive() throws InvalidCommandException {
        Command cmd = CommandFactory.create("QUIT");

        assertInstanceOf(QuitCommand.class, cmd,
                "Should create a QuitCommand regardless of case");
    }

    @Test
    void testCreateHelpCommand() throws InvalidCommandException {
        Command cmd = CommandFactory.create("help");

        assertInstanceOf(HelpCommand.class, cmd,
                "Should create a HelpCommand for input 'help'");
    }

    @Test
    void testCreateHelpCommandCaseInsensitive() throws InvalidCommandException {
        Command cmd = CommandFactory.create("HELP");

        assertInstanceOf(HelpCommand.class, cmd,
                "Should create a HelpCommand regardless of case");
    }

    @Test
    void testCreateGetFoodCommandWithSingleKeyword() throws InvalidCommandException {
        Command cmd = CommandFactory.create("get-food soup");

        assertInstanceOf(GetFoodCommand.class, cmd,
                "Should create a GetFoodCommand for 'get-food <keyword>'");
    }

    @Test
    void testCreateGetFoodCommandWithMultipleKeywords() throws InvalidCommandException {
        Command cmd = CommandFactory.create("get-food beef noodle soup");

        assertInstanceOf(GetFoodCommand.class, cmd,
                "Should create a GetFoodCommand for 'get-food <multiple keywords>'");
    }

    @Test
    void testCreateGetFoodCommandCaseInsensitive() throws InvalidCommandException {
        Command cmd = CommandFactory.create("GET-FOOD soup");

        assertInstanceOf(GetFoodCommand.class, cmd,
                "Should create a GetFoodCommand regardless of case");
    }

    @Test
    void testCreateGetFoodReportCommandWithFdcId() throws InvalidCommandException {
        Command cmd = CommandFactory.create("get-food-report 171539");

        assertInstanceOf(GetFoodReportCommand.class, cmd,
                "Should create a GetFoodReportCommand for 'get-food-report <fdcId>'");
    }

    @Test
    void testCreateGetFoodReportCommandCaseInsensitive() throws InvalidCommandException {
        Command cmd = CommandFactory.create("GET-FOOD-REPORT 171539");

        assertInstanceOf(GetFoodReportCommand.class, cmd,
                "Should create a GetFoodReportCommand regardless of case");
    }

    @Test
    void testCreateGetFoodReportCommandWithTooManyTokensThrowsException() {
        InvalidCommandException ex = assertThrows(InvalidCommandException.class,
                () -> CommandFactory.create("get-food-report 171539 extra"),
                "Should throw InvalidCommandException when get-food-report has more than 2 tokens");

        assertTrue(ex.getMessage().contains(UNRECOGNIZED_CMD_MSG) ||
                ex.getMessage().contains("get-food-report 171539 extra"));
    }

    @Test
    void testCreateGetFoodByBarcodeCommandWithCode() throws InvalidCommandException {
        Command cmd = CommandFactory.create("get-food-by-barcode --code=009800146130");

        assertInstanceOf(GetFoodByBarcodeCommand.class, cmd,
                "Should create a GetFoodByBarcodeCommand for '--code=<barcode>'");
    }

    @Test
    void testCreateGetFoodByBarcodeCommandWithImg() throws InvalidCommandException {
        Command cmd = CommandFactory.create("get-food-by-barcode --img=C:\\Users\\ktoncheva\\JAVA\\FoodAnalyzerClient\\test\\resources\\barcode.gif");

        assertInstanceOf(GetFoodByBarcodeCommand.class, cmd,
                "Should create a GetFoodByBarcodeCommand for '--img=<path>'");
    }

    // PLEASE PROVIDE AN ABSOLUTE PATH TO AN EXISTING IMAGE FILE ON YOUR SYSTEM FOR THIS TEST TO PASS - YOU CAN GET AN IMAGE FROM
    // THE TEST RESOURCES FOLDER IN THE PROJECT
    @Test
    void testCreateGetFoodByBarcodeCommandWithBothParams() throws InvalidCommandException {
        Command cmd = CommandFactory.create(
                "get-food-by-barcode --code=009800146130 --img=C:\\Users\\ktoncheva\\JAVA\\FoodAnalyzerClient\\test\\resources\\barcode.gif");

        assertInstanceOf(GetFoodByBarcodeCommand.class, cmd,
                "Should create a GetFoodByBarcodeCommand for both '--code' and '--img'");
    }

    @Test
    void testCreateWithUnrecognizedCommandThrowsException() {
        InvalidCommandException ex = assertThrows(InvalidCommandException.class,
                () -> CommandFactory.create("unknown-command"),
                "Should throw InvalidCommandException for unknown command");

        assertTrue(ex.getMessage().contains("unknown-command"),
                "Exception message should contain the unrecognized command");
    }

    @Test
    void testCreateWithEmptyStringThrowsException() {
        assertThrows(InvalidCommandException.class,
                () -> CommandFactory.create(""),
                "Should throw InvalidCommandException for empty input");
    }

    @Test
    void testCreateWithBlankStringThrowsException() {
        assertThrows(InvalidCommandException.class,
                () -> CommandFactory.create("   "),
                "Should throw InvalidCommandException for blank input");
    }
}
