package bg.sofia.uni.fmi.mjt.client.dto.model;

import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.DESCR_NAME;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.FDC_ID_NAME;
import static bg.sofia.uni.fmi.mjt.client.constants.CommandConstants.UPC_NAME;

public class SearchFoodItemDto implements FoodItemDto {
    private String fdcId;
    private String description;
    private String gtinUpc;

    public SearchFoodItemDto(String fdcId, String description, String gtinUpc) {
        this.fdcId = fdcId;
        this.description = description;
        this.gtinUpc = gtinUpc;
    }

    @Override
    public void print() {
        System.out.println(FDC_ID_NAME + fdcId);
        System.out.println(DESCR_NAME + description);
        if (gtinUpc != null) {
            System.out.println(UPC_NAME + gtinUpc);
        }
    }
}