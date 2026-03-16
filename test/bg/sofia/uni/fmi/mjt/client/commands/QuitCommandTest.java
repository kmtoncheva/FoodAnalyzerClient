package bg.sofia.uni.fmi.mjt.client.commands;

import org.junit.jupiter.api.Test;

public class QuitCommandTest {

    @Test
    void testPrintOutputToClientDoesNotThrow() {
        QuitCommand cmd = new QuitCommand();

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(
                () -> cmd.printOutputToClient(null),
                "QuitCommand printOutputToClient should not throw");
    }
}
