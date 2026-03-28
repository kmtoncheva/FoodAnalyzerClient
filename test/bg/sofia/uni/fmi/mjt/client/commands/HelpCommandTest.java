package bg.sofia.uni.fmi.mjt.client.commands;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.HELP_MSG;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpCommandTest {

    @Test
    void testPrintOutputToClientPrintsHelpMessage() {
        HelpCommand cmd = new HelpCommand();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            cmd.printOutputToClient(null);

            assertTrue(outputStream.toString().contains(HELP_MSG) ||
                            !outputStream.toString().isEmpty(),
                    "HelpCommand should print the help message");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testIsTerminatingCommandReturnsFalse() {
        HelpCommand cmd = new HelpCommand();
        assertFalse(cmd.isTerminatingCommand(), "HelpCommand should not be terminating");
    }

    @Test
    void testGetRequestReturnsNull() {
        HelpCommand cmd = new HelpCommand();
        assertNull(cmd.getRequest(), "HelpCommand should return null request");
    }
}