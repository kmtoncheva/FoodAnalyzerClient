package bg.sofia.uni.fmi.mjt.client.constants;

import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.BOLD;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.CYAN;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.CYAN_BOLD;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.GREEN;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.GREEN_BOLD;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.PURPLE;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.RESET;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.WHITE;
import static bg.sofia.uni.fmi.mjt.client.constants.ColorConstants.YELLOW;

/**
 * Defines constant values related to command parsing, handling, and display formatting.
 * Used to standardize the structure of recognized commands, parsing logic, and nutrient display
 * across the client application.
 */
public final class CommandConstants {
    // Command names
    public static final String GET_FOOD_CMD = "get-food";
    public static final String GET_FOOD_REPORT_CMD = "get-food-report";
    public static final String GET_FOOD_BY_BARCODE_CMD = "get-food-by-barcode";
    public static final String BARCODE_CMD = "barcode";
    public static final String QUIT_CMD = "quit";
    public static final String HELP_CMD = "help";

    // Field names
    public static final String TYPE_FIELD_NAME = "type";
    public static final String SEARCH_TYPE_LABEL = "search";
    public static final String REPORT_TYPE_LABEL = "report";

    // Numeric constants
    public static final int FIRST_ELEMENT_IND = 0;
    public static final int ONE_TOKEN = 1;
    public static final int TWO_TOKENS = 2;
    public static final int THREE_TOKENS = 3;

    public static final int KEYWORD_TOKEN_INDEX = 0;
    public static final int PARAM_TOKEN_INDEX = 1;
    public static final int INVALID_TOKEN_INDEX = -1;

    // Parsing constants
    public static final String WHITESPACE_SPLIT_REGEX = "\\s+";
    public static final char PARAMS_SPLIT_SYMBOL = '=';

    // Parameter names
    public static final String CODE_PARAM = "--code";
    public static final String IMG_PARAM = "--img";

    // Help message with colors and emojis
    public static final String DISPLAY_ALL_COMMANDS_MSG =
            CYAN_BOLD + "\n╔════════════════════════════════════════════════════════════════╗\n" + RESET +
                    CYAN_BOLD + "║" + RESET + GREEN_BOLD
                    + "                    📚 AVAILABLE COMMANDS                       " +
                    RESET + CYAN_BOLD + "║\n" + RESET +
                    CYAN_BOLD + "╚════════════════════════════════════════════════════════════════╝\n" + RESET +
                    "\n" +
                    GREEN + "  🔍 " + CYAN_BOLD + "get-food" + RESET + " " + YELLOW + "<food_name>" + RESET + "\n" +
                    "     " + WHITE + "Search for food items by name" + RESET + "\n" +
                    "     " + PURPLE + "Example: " + RESET + "get-food raffaello treat\n" +
                    "\n" +
                    GREEN + "  📊 " + CYAN_BOLD + "get-food-report" + RESET + " " + YELLOW + "<food_fdcId>" + RESET
                    + "\n" + "     " + WHITE + "Get detailed nutritional information for a food item" + RESET + "\n" +
                    "     " + PURPLE + "Example: " + RESET + "get-food-report 2709187\n" +
                    "\n" +
                    GREEN + "  📷 " + CYAN_BOLD + "get-food-by-barcode" + RESET + "\n" +
                    "     " + YELLOW + "--code=" + RESET + WHITE + "<gtinUpc_code>" + RESET +
                    "  " + WHITE + "Look up food by barcode code" + RESET + "\n" +
                    "     " + PURPLE + "Example: " + RESET + "get-food-by-barcode --code=009800146130\n" +
                    "\n" +
                    "     " + YELLOW + "--img=" + RESET + WHITE + "<image_file>" + RESET +
                    "    " + WHITE + "Look up food by barcode image" + RESET + "\n" +
                    "     " + PURPLE + "Example: " + RESET
                    + "get-food-by-barcode --C:\\Users\\<user>\\JAVA\\FoodAnalyzerClient\\upc-barcode.gif" +
                    "\n" +
                    GREEN + "  ❓ " + CYAN_BOLD + "help" + RESET + "\n" +
                    "     " + WHITE + "Show this help message again" + RESET + "\n" +
                    "\n" +
                    GREEN + "  👋 " + CYAN_BOLD + "quit" + RESET + "\n" +
                    "     " + WHITE + "Exit the program" + RESET + "\n" +
                    "\n" +
                    CYAN + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" + RESET;

    // Display labels for food items
    public static final String FDC_ID_NAME =
            PURPLE + "🆔 FDC ID: " + RESET;

    public static final String DESCR_NAME =
            BOLD + "📝 Description: " + RESET;

    public static final String UPC_NAME =
            CYAN + "🔢 GTIN/UPC: " + RESET;

    public static final String INGREDIENTS_NAME =
            GREEN + "🧪 Ingredients: " + RESET;

    // Missing data messages
    public static final String INGR_MISSING_MSG =
            YELLOW + "ℹ️  The selected food item does not include an ingredients list because " +
                    "it is not a branded or packaged product." + RESET;

    public static final String NUTR_MISSING_MSG =
            YELLOW + "ℹ️  Nutrients are only available for branded and packaged foods.\n" +
                    "   Other food types, like raw or research-based items,\n" +
                    "   may include lab-analyzed nutrient data but not the standardized label set." + RESET;

    // Nutrient display headers
    public static final String BRANDED_NUTRIENTS_HEADER = "\n📊 Nutrition Facts (Branded Food):\n";
    public static final String NON_BRANDED_NUTRIENTS_HEADER = "\n🔬 Lab-Analyzed Nutrients (per 100g):\n";

    // Nutrient names for branded foods
    public static final String NUTRIENT_CALORIES = "Calories";
    public static final String NUTRIENT_PROTEIN = "Protein";
    public static final String NUTRIENT_FAT = "Fat";
    public static final String NUTRIENT_CARBOHYDRATES = "Carbohydrates";
    public static final String NUTRIENT_FIBER = "Fiber";

    // Units
    public static final String UNIT_KCAL = "kcal";
    public static final String UNIT_GRAMS = "g";
    public static final String UNIT_EMPTY = "";

    // Nutrient emojis for branded foods
    public static final String EMOJI_CALORIES = "⚡";
    public static final String EMOJI_PROTEIN = "💪";
    public static final String EMOJI_FAT = "🥑";
    public static final String EMOJI_CARBOHYDRATES = "🌾";
    public static final String EMOJI_FIBER = "🌿";

    // Nutrient names for non-branded foods
    public static final String NUTRIENT_ENERGY = "Energy";
    public static final String NUTRIENT_TOTAL_LIPID = "Total lipid (fat)";
    public static final String NUTRIENT_CARBOHYDRATE_DIFF = "Carbohydrate, by difference";
    public static final String NUTRIENT_FIBER_DIETARY = "Fiber, total dietary";
    public static final String NUTRIENT_TOTAL_SUGARS = "Total Sugars";
    public static final String NUTRIENT_CALCIUM = "Calcium, Ca";
    public static final String NUTRIENT_IRON = "Iron, Fe";
    public static final String NUTRIENT_VITAMIN_C = "Vitamin C, total ascorbic acid";
    public static final String NUTRIENT_SODIUM = "Sodium, Na";
    public static final String NUTRIENT_POTASSIUM = "Potassium, K";
    public static final String NUTRIENT_VITAMIN_A = "Vitamin A, RAE";
    public static final String NUTRIENT_VITAMIN_D = "Vitamin D (D2 + D3)";

    // Emojis for non-branded nutrients
    public static final String EMOJI_ENERGY = "⚡";
    public static final String EMOJI_TOTAL_LIPID = "🥑";
    public static final String EMOJI_CARBOHYDRATE = "🌾";
    public static final String EMOJI_SUGARS = "🍬";
    public static final String EMOJI_CALCIUM = "🦴";
    public static final String EMOJI_IRON = "🩸";
    public static final String EMOJI_VITAMIN_C = "🍊";
    public static final String EMOJI_SODIUM = "🧂";
    public static final String EMOJI_POTASSIUM = "⚡";
    public static final String EMOJI_VITAMIN_A = "👁️";
    public static final String EMOJI_VITAMIN_D = "☀️";
    public static final String EMOJI_INFO = "ℹ️";

    // Formatting constants
    public static final String FORMAT_NUTRIENT_ALIGNED = "%s %s%-50s%s %s%6.2f %s%s%n";

    // Messages
    public static final String NO_KEY_NUTRIENTS_MSG = "  Nutrient data available but no standard nutrients found.";

    private CommandConstants() { }
}