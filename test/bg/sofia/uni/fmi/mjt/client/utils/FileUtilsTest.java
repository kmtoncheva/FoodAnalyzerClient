package bg.sofia.uni.fmi.mjt.client.utils;

import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidFilePathException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.EMPTY_PATH_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.FILE_DOES_NOT_EXIST_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.INVALID_PATH_FORMAT;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.NOT_ABSOLUTE_PATH_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.NOT_A_REG_FILE_MSG;
import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {

    @TempDir
    Path tempDir;

    @Test
    void testValidateImagePathWithNullPathThrowsException() {
        InvalidFilePathException ex = assertThrows(InvalidFilePathException.class,
                () -> FileUtils.validateImagePath(null),
                "Should throw InvalidFilePathException when path is null");

        assertTrue(ex.getMessage().contains(EMPTY_PATH_MSG));
    }

    @Test
    void testValidateImagePathWithEmptyPathThrowsException() {
        InvalidFilePathException ex = assertThrows(InvalidFilePathException.class,
                () -> FileUtils.validateImagePath(""),
                "Should throw InvalidFilePathException when path is empty");

        assertTrue(ex.getMessage().contains(EMPTY_PATH_MSG));
    }

    @Test
    void testValidateImagePathWithBlankPathThrowsException() {
        InvalidFilePathException ex = assertThrows(InvalidFilePathException.class,
                () -> FileUtils.validateImagePath("   "),
                "Should throw InvalidFilePathException when path is blank");

        assertTrue(ex.getMessage().contains(EMPTY_PATH_MSG));
    }

    @Test
    void testValidateImagePathWithRelativePathThrowsException() {
        InvalidFilePathException ex = assertThrows(
                InvalidFilePathException.class,
                () -> FileUtils.validateImagePath("relative/path/image.jpg"),
                "Should throw InvalidFilePathException when path is not absolute"
        );

        assertTrue(ex.getMessage().contains(NOT_ABSOLUTE_PATH_MSG));
    }

    @Test
    void testValidateImagePathWithNonExistentFileThrowsException() {
        String nonExistentPath = tempDir.resolve("nonexistent.jpg").toAbsolutePath().toString();

        InvalidFilePathException ex = assertThrows(InvalidFilePathException.class,
                () -> FileUtils.validateImagePath(nonExistentPath),
                "Should throw InvalidFilePathException when file does not exist");

        assertTrue(ex.getMessage().contains(FILE_DOES_NOT_EXIST_MSG));
    }

    @Test
    void testValidateImagePathWithDirectoryThrowsException() {
        String dirPath = tempDir.toAbsolutePath().toString();

        InvalidFilePathException ex = assertThrows(InvalidFilePathException.class,
                () -> FileUtils.validateImagePath(dirPath),
                "Should throw InvalidFilePathException when path points to a directory");

        assertTrue(ex.getMessage().contains(NOT_A_REG_FILE_MSG));

    }

    @Test
    void testValidateImagePathWithValidFileDoesNotThrow() throws IOException {
        Path validFile = tempDir.resolve("valid-image.jpg");
        Files.createFile(validFile);

        assertDoesNotThrow(() -> FileUtils.validateImagePath(validFile.toAbsolutePath().toString()),
                "Should not throw for a valid, readable, absolute file path");
    }

    @Test
    void testValidateImagePathWithInvalidPathFormatThrowsException() {
        // Null character is invalid on both Windows and Linux
        String invalidPath = "C:\\invalid\0path\\image.jpg";

        InvalidFilePathException ex = assertThrows(InvalidFilePathException.class,
                () -> FileUtils.validateImagePath(invalidPath),
                "Should throw InvalidFilePathException for invalid path format");

        assertTrue(ex.getMessage().contains(INVALID_PATH_FORMAT));
    }
}
