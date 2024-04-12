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
        // Define the test URL
        String testUrl = "https://edition.cnn.com/2024/04/07/entertainment/kristen-wiig-ryan-gosling-matt-damon-snl/index.html";

        // Define the expected outcome
        String expectedData = "\"Kristen Wiig initiated into ‘SNL’ five-timers club by Ryan Gosling, " +
                "Matt Damon and… Lorne Michaels | CNN\";Alli Rosenbloom;" +
                "April 07, 2024;CNN;" +
                "https://edition.cnn.com/2024/04/07/entertainment/kristen-wiig-ryan-gosling-matt-damon-snl/index.html;" +
                "Kristen Wiig got inducted into the coveted “Saturday Night Live” five-timers club in style this weekend " +
                "when she hosted the long-running NBC sketch show for the fifth time, and she had a little bit of help from some huge stars.;Entertainment\n";

        // Call the scrapeArticle method
        ArticleScraper.scrapeArticle(testUrl, outputFolderPath);

        // Check that the output file exists
        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists(), "The output file should exist.");

        // Read the content of the output file
        String fileContent = readFileContent(outputFilePath);

        // Compare the expected data with the actual data in the output file
        assertEquals(expectedData, fileContent, "The content of the output file should match the expected data.");
    }
}


