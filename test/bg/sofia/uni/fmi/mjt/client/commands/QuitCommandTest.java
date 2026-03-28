package bg.sofia.uni.fmi.mjt.client.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuitCommandTest {

    @Test
    void testPrintOutputToClientDoesNotThrow() {
        QuitCommand cmd = new QuitCommand();

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(
                () -> cmd.printOutputToClient(null),
                "QuitCommand printOutputToClient should not throw");
    }

    @Test
    void testIsTerminatingCommandReturnsTrue() {
        QuitCommand cmd = new QuitCommand();
        assertTrue(cmd.isTerminatingCommand(), "QuitCommand should be terminating");
    }

    @Test
    void testGetRequestReturnsNull() {
        QuitCommand cmd = new QuitCommand();
        assertNotNull(cmd.getRequest(), "QuitCommand should return null request");
    }
}
