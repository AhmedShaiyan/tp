package newsonthego.commands;

import newsonthego.NewsArticle;
import newsonthego.NewsImporter;
import newsonthego.NewsOnTheGo;
import newsonthego.newstopic.NewsTopic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DailyNewsCommandTest {

    ArrayList<NewsTopic> newsTopics = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        newsTopics = new ArrayList<>();

    }

    @Test
    public void dailyFunctionTest() {
        String input = "daily March 10 2024";
        String expected = "\"Scientists Discover New Species of Butterfly in the Amazon\"";
        List<NewsArticle> newsArticles = NewsImporter.importNewsFromText(NewsOnTheGo.FILENAME, newsTopics);
        String dailyParserInput = "back";

        System.setIn(new ByteArrayInputStream(dailyParserInput.getBytes()));


        DailyNewsCommand command = new DailyNewsCommand(input, newsArticles, newsTopics);
        List<NewsArticle> outputHeadlines = command.getArticlesOfTheDay();
        assertEquals(1, outputHeadlines.size());
        String output = outputHeadlines.get(0).getHeadline();
        assertEquals(expected, output);
    }
}
