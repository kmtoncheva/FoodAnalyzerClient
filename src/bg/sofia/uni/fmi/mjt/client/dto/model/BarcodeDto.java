package bg.sofia.uni.fmi.mjt.client.dto.model;

/**
 * A Data Transfer Object (DTO) representing the parameters required for processing
 * a barcode-related request on the client side.
 * <p>
 * Used for identifying a food item by its barcode code or by scanning a barcode image.
 *
 * @param code      the barcode value (usually a UPC or EAN code) provided for the food item;
 *                  may be {@code null} if an image path is provided instead
 * @param imagePath the absolute file system path to the image containing the barcode;
 *                  may be {@code null} if a code is provided directly
 */
public record BarcodeDto(String code, String imagePath) {
}