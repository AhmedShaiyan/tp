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

    private String readFileContent(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
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
        // Define the test URL and output folder path
        String testUrl = "https://edition.cnn.com/2024/03/28/india/india-" +
                "state-department-kejriwal-objection-intl-hnk/index.html";
        String outputFolderPath = "./test-output";

        // Create the output folder if it doesn't exist
        Files.createDirectories(Paths.get(outputFolderPath));

        // Define the expected outcome
        String expectedData = "\"India summons US State Department official over call for " +
                "fair legal treatment of arrested opposition leader | CNN\";" +
                "Rhea Mogul;March 28, 2024;CNN;" +
                "https://edition.cnn.com/2024/03/28/india/india-state-department-kejriwal-" +
                "objection-intl-hnk/index.html;" +
                "India’s Ministry of External Affairs has made a “strong objection” to " +
                "State Department comments calling for a fair and transparent legal process after the arrest " +
                "of opposition leader and Delhi chief minister Arvind Kejriwal.;World\n";

        // Call the scrapeArticle method
        ArticleScraper.scrapeArticle(testUrl, outputFolderPath);

        // Define the output file path
        String outputFilePath = outputFolderPath + File.separator + "testArticleScraper.txt";

        // Check that the output file exists
        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists(), "The output file should exist.");

        // Read the content of the output file
        String fileContent = readFileContent(outputFilePath);

        // Compare the expected data with the actual data in the output file
        assertEquals(expectedData, fileContent, "The content of the output file should match the expected data.");
    }

}


