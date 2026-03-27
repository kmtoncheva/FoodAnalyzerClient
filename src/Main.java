import bg.sofia.uni.fmi.mjt.client.FoodAnalyzerClient;
import bg.sofia.uni.fmi.mjt.client.exceptions.InvalidCommandException;

/**
 * The entry point for the Food Analyzer client application.
 * <p>
 * This class initializes and starts the {@link bg.sofia.uni.fmi.mjt.client.FoodAnalyzerClient},
 * which handles all client-side operations including server communication and user interaction.
 */
public class Main {
    public static void main(String[] args) throws InvalidCommandException {
        FoodAnalyzerClient client = new FoodAnalyzerClient();
        client.start();
    }
}