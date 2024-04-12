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
        String testUrl = "https://edition.cnn.com/2024/04/03/entertainment/matrix-fifth-movie/index.html";
        String outputFolderPath = "./test-output";

        // Create the output folder if it doesn't exist
        Files.createDirectories(Paths.get(outputFolderPath));

        // Define the expected outcome
        String expectedData = "\"‘The Matrix’ has a fifth film in the works and, no, this is not a simulation | CNN\";" +
                "Alli Rosenbloom;April 03, 2024;CNN;" +
                "https://edition.cnn.com/2024/04/03/entertainment/matrix-fifth-movie/index.html;" +
                "Red pill or blue pill, anyone? A fifth installment of the beloved sci-fi film franchise “The Matrix” is in the works.;Entertainment\n";

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


