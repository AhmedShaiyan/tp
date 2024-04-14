package newsonthego.commands;

import newsonthego.storage.ArticleScraper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArticleScraperTest {

    private final String outputFolderPath = "./test-output";
    private final String outputFilePath = outputFolderPath + File.separator + "testArticleScraper.txt";

    /**
     * Helper method to read the content of a file into a string.
     */
    private String readFileContent(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * Helper method to remove double-quote characters (") from a string and normalize whitespace and newlines.
     */
    private String removeDoubleQuotes(String data) {
        // Create a StringBuilder for efficient manipulation
        StringBuilder cleanedData = new StringBuilder();

        // Iterate through each character in the data
        for (int i = 0; i < data.length(); i++) {
            char currentChar = data.charAt(i);

            // Check if the current character is not a double-quote (")
            if (currentChar != '"') {
                // Append the character to the cleaned data
                cleanedData.append(currentChar);
            }
        }

        // Convert the StringBuilder to a String
        String result = cleanedData.toString();

        // Normalize newlines to "\n" and trim leading and trailing whitespace
        result = result.replaceAll("\r\n", "\n").trim();

        return result;
    }

    @BeforeEach
    public void setUp() throws IOException {
        // Ensure the test-output directory exists
        Files.createDirectories(Paths.get(outputFolderPath));

        // Clear the output file before each test
        Files.deleteIfExists(Paths.get(outputFilePath));
    }

    @Test
    public void testScrapeArticleCNN() throws IOException {
        // Define the test URL
        String testUrl = "https://www.forbes.com/sites/anafaguy/2024/03/31/king-" +
                "charles-attends-1st-public-event-" +
                "amid-cancer-scare/?sh=3b773b35b2d6";

        // Define the expected outcome
        String expectedData = "King Charles Makes First Major Appearance Since Cancer Diagnosis;Ana Faguy;" +
                "March 31, 2024;Forbes;https://www.forbes.com/sites/anafaguy/2024/03/31/king-" +
                "charles-attends-1st-public-event-amid-cancer-scare/?sh=3b773b35b2d6;Both " +
                "King Charles and Princess Kate are currently undergoing treatment " +
                "for cancer. ;Business";

        // Call the scrapeArticle method
        ArticleScraper.scrapeArticle(testUrl, outputFolderPath);

        // Check that the output file exists
        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists(), "The output file should exist.");

        // Read the content of the output file
        String fileContent = readFileContent(outputFilePath);

        // Remove special characters from the actual data
        String cleanedFileContent = removeDoubleQuotes(fileContent);


        // Compare the expected data with the cleaned actual data
        assertEquals(expectedData, cleanedFileContent, "The content of the " +
                "output file should match the expected data.");
    }
}
