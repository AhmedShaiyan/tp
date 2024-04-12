package newsonthego;

import static newsonthego.storage.TopicsFile.saveTopics;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import newsonthego.newstopic.NewsTopic;
import newsonthego.storage.NewsFile;
import newsonthego.storage.NewsImporter;
import newsonthego.storage.UserPreferences;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


class NewsOnTheGoTest {


    private static final Path SAVED_TOPICS_PATH = Paths.get("data", "saved_topics.txt");
    private static final Path USER_DATA_DIRECTORY = Paths.get("user_data");
    private static final Path SAVED_NEWS_PATH = USER_DATA_DIRECTORY.resolve("saved_news.txt");

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void loadSetUp() throws IOException {
        if (!Files.exists(USER_DATA_DIRECTORY)) {
            Files.createDirectories(USER_DATA_DIRECTORY);
        }
        System.setOut(new PrintStream(outputStreamCaptor));

        // Initialize savedNews with a test path
        NewsOnTheGo.savedNews = new NewsFile(); // Adjust if NewsFile requires parameters
        NewsOnTheGo.savedNews.setPathName(SAVED_NEWS_PATH.toString()); 

        // Create a saved_news.txt file with a test article in it
        String testArticle = "Test Article; Author; Date; Source; URL; Content\n";
        Files.writeString(SAVED_NEWS_PATH, testArticle, StandardOpenOption.CREATE);
    }

    @AfterEach
    void tearDownLoadAndDisplay() {
        System.setOut(standardOut);
        // Delete the entire user_data directory
        if (Files.exists(USER_DATA_DIRECTORY)) {
            deleteDirectoryStream(USER_DATA_DIRECTORY);
        }
    }

    // Helper method to delete a directory with files in it
    private void deleteDirectoryStream(Path path) {
        try (Stream<Path> walk = Files.walk(path)) {
            walk.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /* Tests for Load articles feature */
    @Test
    void loadAndDisplayPrintsArticles() throws IOException {
        String testArticle = "Test Article; Author; Date; Source; URL; Content\n";
        Files.writeString(SAVED_NEWS_PATH, testArticle);
        // Act
        NewsOnTheGo.loadAndDisplaySavedNews();

        // Assert
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Displaying saved news articles:"));
        assertTrue(output.contains("Test Article"));
    }

    @Test
    void loadAndDisplayPrintsNoArticlesMessage() throws IOException {
        // Clear the contents of the file to simulate no saved articles
        Files.writeString(SAVED_NEWS_PATH, "", StandardOpenOption.TRUNCATE_EXISTING);

        // Act
        NewsOnTheGo.loadAndDisplaySavedNews();

        // Assert
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("No saved news articles to display."),
                "Output should contain the no articles message.");
    }


    @Test
    void loadAndDisplayPrintsErrorMessage() throws IOException {
        // Arrange
        Files.deleteIfExists(SAVED_NEWS_PATH);

        // Act
        NewsOnTheGo.loadAndDisplaySavedNews();

        // Assert
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("An error occurred while reading the save file:"));
    }

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
        int index = NewsTopic.findTopicIndex("abcdefg", topics);
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


        }
    }
}
