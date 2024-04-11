package newsonthego.commands;

import newsonthego.NewsArticle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class URLCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor)); // Capture the System.out prints
    }

    @Test
    void printArticleURL_ValidIndex_PrintsURL() {

        List<NewsArticle> list = List.of(new NewsArticle("Title1", "Author1", "Date1", "Source1",
                "https://example.com/article1", "Content1"));
        String line = "url 1"; // The user command to print URL of first article
        URLCommand.printArticleURL(line, list);

        // Assert
        assertTrue(outputStreamCaptor.toString().trim().contains("Article URL: https://example.com/article1"));
    }



    @Test
    void printArticleURLsForDay_PrintsURLs() {
        List<NewsArticle> articles = new ArrayList<>();
        articles.add(new NewsArticle("Title1", "Author1", "Date1", "Source1",
                "https://example.com/article1", "Content1"));
        articles.add(new NewsArticle("Title2", "Author2", "Date2", "Source2",
                "https://example.com/article2", "Content2"));
        URLCommand.printArticleURLsForDay(articles);

        // Assert
        assertTrue(outputStreamCaptor.toString().contains("https://example.com/article1"));
        assertTrue(outputStreamCaptor.toString().contains("https://example.com/article2"));
    }

    @Test
    void parseArticleURL_ValidLine_ReturnsURL() {
        String articleLine = "Title;Author;Date;Source;https://example.com/article;Content";
        String result = URLCommand.parseArticleURL(articleLine);

        // Assert
        assertEquals("https://example.com/article", result);
    }

    @Test
    void parseArticleURL_InvalidLine_ReturnsErrorMessage() {
        String articleLine = "Title;Author;Date;Source"; // Missing URL part
        String result = URLCommand.parseArticleURL(articleLine);

        // Assert
        assertEquals("URL not found", result);
    }



}
