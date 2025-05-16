package bg.sofia.uni.fmi.mjt.client.utils;

import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidFilePathException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.EMPTY_PATH_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.FILE_DOES_NOT_EXIST_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.INVALID_PATH_FORMAT;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.NOT_ABSOLUTE_PATH_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.NOT_A_REG_FILE_MSG;
import static bg.sofia.uni.fmi.mjt.client.constants.ClientMessagesConstants.PERMISSION_MSG;

public final class FileUtils {
    private FileUtils() { }

    public static void validateImagePath(String pathName) throws InvalidFilePathException {
        if (pathName == null || pathName.isBlank()) {
            throw new InvalidFilePathException(pathName, EMPTY_PATH_MSG);
        }

        Path path;

        try {
            path = Paths.get(pathName);
        } catch (InvalidPathException e) {
            throw new InvalidFilePathException(pathName, INVALID_PATH_FORMAT);
        }

        if (!path.isAbsolute()) {
            throw new InvalidFilePathException(pathName, NOT_ABSOLUTE_PATH_MSG);
        }
        if (!Files.exists(path)) {
            throw new InvalidFilePathException(pathName, FILE_DOES_NOT_EXIST_MSG);
        }
        if (!Files.isRegularFile(path)) {
            throw new InvalidFilePathException(pathName, NOT_A_REG_FILE_MSG);
        }
        if (!Files.isReadable(path)) {
            throw new InvalidFilePathException(pathName, PERMISSION_MSG);
        }
    }
}