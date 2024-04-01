package newsonthego;

import static newsonthego.storage.TopicsFile.saveTopics;
import static org.junit.jupiter.api.Assertions.*;

import newsonthego.newstopic.NewsTopic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class NewsOnTheGoTest {

    private static final String PREFERENCES_FILE = "userPreferences.txt";
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
    void setUpUserPreferences() {
        // Initialize UserPreferences before each test
        userPreferences = new UserPreferences();
        userPreferences.getInterestedTopics().clear();
    }

    @AfterEach
    void tearDownUserPreferences() throws Exception {
        Files.deleteIfExists(Paths.get(PREFERENCES_FILE));
    }

    @Test
    void testAddAndRemoveTopic() {
        String testTopic = "science";
        userPreferences.addTopic(testTopic);
        assertTrue(userPreferences.getInterestedTopics().contains(testTopic), "Topic should be added to preferences");

        userPreferences.removeTopic(testTopic);
        assertFalse(userPreferences.getInterestedTopics().contains(testTopic), "Topic should be removed");
    }

    @Test
    void testPersistence() {
        String testTopic = "technology";
        userPreferences.addTopic(testTopic);

        // Simulate reloading preferences by creating a new instance
        UserPreferences newUserPreferences = new UserPreferences();
        Set<String> loadedTopics = newUserPreferences.getInterestedTopics();

        assertTrue(loadedTopics.contains(testTopic), "Persisted topic should be loaded on new instance initialization");
    }

    @Test
    void testToString() {
        String expectedInitialMessage = "You are not currently interested in any topics.";
        assertEquals(expectedInitialMessage, userPreferences.toString(), "Message should indicate no interests");

        String testTopic = "health";
        userPreferences.addTopic(testTopic);
        String expectedMessageAfterAddition = "You are interested in the following topics:\n- health\n";
        assertEquals(expectedMessageAfterAddition, userPreferences.toString(), "Message should list added topics");
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
