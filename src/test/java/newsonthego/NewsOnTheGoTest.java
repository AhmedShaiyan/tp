package newsonthego;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import newsonthego.newstopic.NewsTopic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


class NewsOnTheGoTest {


    private static final Path SAVED_TOPICS_PATH = Paths.get("data", "saved_topics.txt");


    private UserPreferences userPreferences;

    @Test
    public void sampleTest() {
        assertTrue(true);
        assertTrue(true);
    }

    @Test
    public void sampleTestSource() {
        List<NewsArticle> newsArticles = NewsImporter.importNewsFromText("data/sampleNews.txt", new ArrayList<>());
        assertEquals("Financial Times", newsArticles.get(1).getSource());
    }

    @Test
    public void testFindTopicIndex() {
        ArrayList<NewsTopic> topics = new ArrayList<>();
        int index = NewsOnTheGo.findTopicIndex("abcdefg", topics);
        assertEquals(-1,index);
    }

    @Test
    public void testInfoNewsValidIndex() {
        List<NewsArticle> newsArticles = NewsImporter.importNewsFromText("data/sampleNews.txt", new ArrayList<>());
        String expectedOutput = "Importance: 9\nReliability: 9\nBias: 3";
        assertEquals(expectedOutput, "Importance: " + newsArticles.get(1).getImportance() +
                "\nReliability: " + newsArticles.get(1).getReliability() +
                "\nBias: " + newsArticles.get(1).getBias());
    }

    // UserPreferences Tests

    @BeforeEach
    void setUp() throws IOException {
        Path dataDirectory = Paths.get("data");
        if (!Files.exists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }
        Files.writeString(SAVED_TOPICS_PATH, "", StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
    }


    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(SAVED_TOPICS_PATH);
    }

    @Test
    void getSuggestedArticlesFromFavoriteTopics_WithNoFavoriteTopics_ReturnsNoFavoriteTopicsMessage() {
        String suggestions = UserPreferences.getSuggestedArticlesFromFavoriteTopics();
        String expectedMessage = "You do not have any favorite topics. Please star a topic first.\n";
        assertEquals(expectedMessage, suggestions);
    }

    @Test
    void getSuggestedArticlesFromFavoriteTopics_WithFavoriteTopicsButNoArticles_ReturnsNoArticlesFoundMessage() throws IOException {

        Files.writeString(SAVED_TOPICS_PATH, "NonexistentTopic\n");

        String suggestions = UserPreferences.getSuggestedArticlesFromFavoriteTopics();
        assertTrue(suggestions.contains("No articles found for the topic: NonexistentTopic"));
    }

    @Test
    void getSuggestedArticlesFromFavoriteTopics_WithFavoriteTopics_ReturnsSuggestions() throws IOException {
        String knownTopic = "Science";
        Files.writeString(SAVED_TOPICS_PATH, knownTopic + "\n");


        String suggestions = UserPreferences.getSuggestedArticlesFromFavoriteTopics();

        assertFalse(suggestions.trim().isEmpty());
        assertTrue(suggestions.contains("Suggesting an article from your favorite topic: " + knownTopic));
    }

}
