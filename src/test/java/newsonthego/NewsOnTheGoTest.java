package newsonthego;

import static newsonthego.storage.TopicsFile.saveTopics;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import newsonthego.newstopic.NewsTopic;
import newsonthego.storage.NewsImporter;
import newsonthego.storage.UserPreferences;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
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
    void getArticleWithNoFavoriteTopics() {
        String suggestions = UserPreferences.getSuggestedArticlesFromFavoriteTopics();
        String expectedMessage = "You do not have any favorite topics. Please star a topic first.\n";
        assertEquals(expectedMessage, suggestions);
    }

    @Test
    void getArticlesWithError() throws IOException {

        Files.writeString(SAVED_TOPICS_PATH, "NonexistentTopic\n");

        String suggestions = UserPreferences.getSuggestedArticlesFromFavoriteTopics();
        assertTrue(suggestions.contains("No articles found for the topic: NonexistentTopic"));
    }

    @Test
    void getArticlesSuccessfully() throws IOException {
        String knownTopic = "Science";
        Files.writeString(SAVED_TOPICS_PATH, knownTopic + "\n");


        String suggestions = UserPreferences.getSuggestedArticlesFromFavoriteTopics();

        assertFalse(suggestions.trim().isEmpty());
        assertTrue(suggestions.contains("Suggesting an article from your favorite topic: " + knownTopic));
    }


    @Test
    void testTopicsFileSaveTopics() {
        try {
            ArrayList<NewsTopic> topics = new ArrayList<>();
            topics.add(new NewsTopic("hello"));
            saveTopics(topics);
            File savedFile = new File(Paths.get("data","saved_topics.txt")
                    .normalize().toString());
            assertTrue(savedFile.exists());

            BufferedReader reader = new BufferedReader(new FileReader(savedFile));
            String savedContent = reader.readLine();
            reader.close();
            assertEquals(savedContent, "hello");
        } catch (IOException e) {
            fail("An error occurred while saving the file: " + e.getMessage());
        } finally {
            // Clean up: Delete the test file after the test
            File fileToDelete = new File(Paths.get("data","saved_topics.txt")
                    .normalize().toString());
            if (fileToDelete.exists()) {
                fileToDelete.delete();
            }
        }
    }
}
