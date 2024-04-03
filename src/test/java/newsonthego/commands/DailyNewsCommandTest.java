package newsonthego.commands;

import newsonthego.NewsArticle;
import newsonthego.NewsImporter;
import newsonthego.NewsOnTheGo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DailyNewsCommandTest {

    @TempDir
    public static Path testFolder;

    private static final String TEST_DATA_FILENAME = "test/data/DailyNewsCommandTest";

    private static List<NewsArticle> newsArticles;

    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void setupAll() {
        newsArticles = NewsImporter.importNewsFromText(NewsOnTheGo.FILENAME, new ArrayList<>());
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputContent));
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(null); // To prevent NullPointerExceptions
    }

    @Test
    public void dailyFunctionTest() {
        String input = "daily March 10 2024";
        String expected = "\"Scientists Discover New Species of Butterfly in the Amazon\"";

        // To test dailyParser
        String dailyParserInput = "back";
        System.setIn(new ByteArrayInputStream(dailyParserInput.getBytes()));

        DailyNewsCommand command = new DailyNewsCommand(input, newsArticles);
        List<NewsArticle> outputHeadlines = command.getArticlesOfTheDay();
        assertEquals(1, outputHeadlines.size()); // Only one article found
        String output = outputHeadlines.get(0).getHeadline();
        assertEquals(expected, output);
    }

    @Test
    public void noArticlesFoundTest() {
        String input = "daily 10 03 2025";
        String expected = "Nothing is found on this day: October 03, 2025\n";

        DailyNewsCommand command = new DailyNewsCommand(input, newsArticles);
        List<NewsArticle> outputHeadlines = command.getArticlesOfTheDay();
        assertEquals(0, outputHeadlines.size());
        assertEquals(expected, outputContent.toString());
    }

    @Test
    public void invalidDateFormat() {
        String input = "daily 30 March 2024";
        String expected = "Date format is invalid! \n" +
                "The date format is: \n" +
                "\"MM dd yyyy\" (01 02 2024), \n" +
                "\"MMMM dd yyyy\" (January 02 2024), \n" +
                "\"dd MMMM yyyy\" (02 January 2024)\n\n";

        new DailyNewsCommand(input, newsArticles);
        assertEquals(expected, outputContent.toString());
    }
    @Test
    public void invalidDayFormat() {
        String input = "daily 1 32 2025";
        String expected = "Date format is invalid! \n" +
                "The date format is: \n" +
                "\"MM dd yyyy\" (01 02 2024), \n" +
                "\"MMMM dd yyyy\" (January 02 2024), \n" +
                "\"dd MMMM yyyy\" (02 January 2024)\n\n";

        new DailyNewsCommand(input, newsArticles);
        assertEquals(expected, outputContent.toString());
    }

    @Test
    public void invalidMonthFormat() {
        String input = "daily 15 31 2024";
        String expected = "Date format is invalid! \n" +
                "The date format is: \n" +
                "\"MM dd yyyy\" (01 02 2024), \n" +
                "\"MMMM dd yyyy\" (January 02 2024), \n" +
                "\"dd MMMM yyyy\" (02 January 2024)\n\n";

        new DailyNewsCommand(input, newsArticles);
        assertEquals(expected, outputContent.toString());
    }
}
