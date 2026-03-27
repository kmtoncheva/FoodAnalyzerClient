package bg.sofia.uni.fmi.mjt.client.dto.model;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.DESCR_NAME;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.FDC_ID_NAME;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.UPC_NAME;

/**
 * A Data Transfer Object (DTO) representing a food item found during a search operation.
 * Contains basic identifying information such as FDC ID, description, and optional barcode.
 * <p>
 * This DTO is used for displaying search results and implements {@link FoodItemDto}
 * to provide formatted console output.
 */
public class SearchFoodItemDto implements FoodItemDto {
    private String fdcId;
    private String description;
    private String gtinUpc;

    /**
     * Constructs a new {@code SearchFoodItemDto} with the specified attributes.
     *
     * @param fdcId       the unique FoodData Central identifier for this food item
     * @param description the food item's description
     * @param gtinUpc     the GTIN/UPC barcode; may be {@code null} for non-branded foods
     */
    public SearchFoodItemDto(String fdcId, String description, String gtinUpc) {
        this.fdcId = fdcId;
        this.description = description;
        this.gtinUpc = gtinUpc;
    }

    /**
     * Prints the food item's search result to the console in a formatted manner.
     * Displays FDC ID, description, and GTIN/UPC code (if available).
     */
    @Override
    public void print() {
        System.out.println(FDC_ID_NAME + fdcId);
        System.out.println(DESCR_NAME + description);
        if (gtinUpc != null) {
            System.out.println(UPC_NAME + gtinUpc);
        }
    }
}