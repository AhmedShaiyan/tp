package newsonthego.commands;

import newsonthego.NewsArticle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowExtractCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Test invalid index format")
    public void testInvalidIndexFormat() {
        List<NewsArticle> articles = Arrays.asList(
                new NewsArticle("Headline1", "Author1", "Date1", "Source1", "URL1", "Content1"),
                new NewsArticle("Headline2", "Author2", "Date2", "Source2", "URL2", "Content2")
        );

        ShowExtractCommand.showExtract("extract two", articles);
        assertTrue(outputStreamCaptor.toString().trim().contains("Invalid index format. Please provide a numeric article index."));
    }

    @Test
    @DisplayName("Test index out of bounds")
    public void testIndexOutOfBounds() {
        List<NewsArticle> articles = Arrays.asList(
                new NewsArticle("Headline1", "Author1", "Date1", "Source1", "URL1", "Content1"),
                new NewsArticle("Headline2", "Author2", "Date2", "Source2", "URL2", "Content2")
        );

        ShowExtractCommand.showExtract("extract 3", articles);
        assertTrue(outputStreamCaptor.toString().trim().contains("Invalid article index. Please provide an index within the valid range."));
    }

    @Test
    @DisplayName("Test invalid command usage")
    public void testInvalidCommandUsage() {
        List<NewsArticle> articles = Arrays.asList(
                new NewsArticle("Headline1", "Author1", "Date1", "Source1", "URL1", "Content1"),
                new NewsArticle("Headline2", "Author2", "Date2", "Source2", "URL2", "Content2")
        );

        ShowExtractCommand.showExtract("invalid 1", articles);
        assertTrue(outputStreamCaptor.toString().trim().contains("Invalid command format. Usage: extract <article index>"));
    }

    @Test
    @DisplayName("Test correct extract command usage")
    public void testCorrectCommandUsage() {
        List<NewsArticle> articles = Arrays.asList(
                new NewsArticle("Headline1", "Author1", "Date1", "Source1", "URL1", "Content1"),
                new NewsArticle("Headline2", "Author2", "Date2", "Source2", "URL2", "Content2")
        );

        ShowExtractCommand.showExtract("extract 1", articles);
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Article Extract: Content1"));
    }
}
