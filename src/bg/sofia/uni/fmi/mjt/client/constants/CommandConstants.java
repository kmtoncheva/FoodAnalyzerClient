package bg.sofia.uni.fmi.mjt.client.constants;

import java.security.PublicKey;

public final class CommandConstants {
    public static final String GET_FOOD_CMD = "get-food";
    public static final String GET_FOOD_REPORT_CMD = "get-food-report";
    public static final String GET_FOOD_BY_BARCODE_CMD = "get-food-by-barcode";
    public static final String QUIT_CMD = "quit";
    public static final String HELP_CMD = "help";
    public static final String TYPE_FIELD_NAME = "type";
    public static final String SEARCH_TYPE_LABEL = "search";
    public static final String REPORT_TYPE_LABEL = "report";

    public static final int FIRST_ELEMENT_IND = 0;
    public static final int ONE_TOKEN = 1;
    public static final int TWO_TOKENS = 2;
    public static final int THREE_TOKENS = 3;

    public static final int KEYWORD_TOKEN_INDEX = 0;
    public static final int PARAM_TOKEN_INDEX = 1;
    public static final int INVALID_TOKEN_INDEX = -1;

    public static final String WHITESPACE_SPLIT_REGEX = "\\s+";
    public static final char PARAMS_SPLIT_SYMBOL = '=';

    public static final String CODE_PARAM = "--code";
    public static final String IMG_PARAM = "--img";

    public static final String DISPLAY_ALL_COMMANDS_MSG = """
        Available commands:
        - get-food <food_name>             : Searches for food items by name.
        - get-food-report <food_fdcId>     : Retrieves detailed nutritional information for a food item.
        - get-food-by-barcode 
            --code=<gtinUpc_code>          : Looks up food by barcode.
            --img=<image_file>             : Looks up food by image of the barcode.
        - help                             : Show this help message again.
        - quit                             : Exit the program.
        """;

    public static final String FDC_ID_NAME = "\uD83D\uDC49 FDC ID: ";
    public static final String DESCR_NAME = "\u2022 Descr: ";
    public static final String UPC_NAME = "\u2022 GTIN UPC: ";

    public static final String INGREDIENTS_NAME = "\uD83D\uDC49 Ingredients: ";

    public static final String INGR_MISSING_MSG =
        "The selected food item does not include an ingredients list because it is not a branded or packaged product.";
    public static final String NUTR_MISSING_MSG = """
        Nutrients are only available for branded and packaged foods. 
        Other food types, like raw or research-based items, 
        may include lab-analyzed nutrient data but not the standardized label set.
        """;
    private CommandConstants() {}
}