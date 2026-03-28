package bg.sofia.uni.fmi.mjt.client.constants;

/**
 * Defines ANSI escape code constants for terminal text formatting and colorization.
 */
public final class ColorConstants {
    // Reset
    public static final String RESET = "\u001B[0m";

    // Regular colors
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Bold
    public static final String BOLD = "\u001B[1m";

    // Bold colors
    public static final String RED_BOLD = "\u001B[1;31m";
    public static final String GREEN_BOLD = "\u001B[1;32m";
    public static final String CYAN_BOLD = "\u001B[1;36m";

    private ColorConstants() { }
}