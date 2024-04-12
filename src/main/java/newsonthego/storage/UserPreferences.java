package newsonthego.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;

import static newsonthego.commands.URLCommand.parseArticleURL;

/**
 * Handles logic for news article suggestions based on favorite topics.
 */
public class UserPreferences {
    private static final Path SAMPLE_NEWS_FILE = Paths.get("data", "sampleNews.txt");
    private static final Path SCRAPPER_NEWS_FILE = Paths.get("data", "testArticleScraper.txt");
    private static final Path SAVED_TOPICS_PATH = Paths.get("data", "saved_topics.txt");

    /**
     * Generates a list of suggested articles with numbered listing from the user's favorite topics.
     * It reads the favorite topics from a saved file and then suggests random articles from those topics.
     * If no favorite topics are saved, it suggests the user to star a topic first.
     * If no articles are found for a topic, it notes that no articles were found.
     *
     * @return A string containing the numbered suggestions or error message.
     */
    public static String getSuggestedArticlesFromFavoriteTopics() {
        StringBuilder suggestions = new StringBuilder();
        Random random = new Random();
        AtomicInteger articleNumber = new AtomicInteger(1);

        try {
            List<String> favoriteTopics = Files.readAllLines(SAVED_TOPICS_PATH);
            if (favoriteTopics.isEmpty()) {
                return "You do not have any favorite topics. Please star a topic first.\n";
            }

            List<String> allArticles = Files.readAllLines(SCRAPPER_NEWS_FILE);

            for (String topic : favoriteTopics) {
                List<String> articlesForTopic = allArticles.stream()
                        .filter(article -> article.endsWith(";" + topic.trim()))
                        .collect(Collectors.toList());

                if (!articlesForTopic.isEmpty()) {
                    String randomArticle = articlesForTopic.get(random.nextInt(articlesForTopic.size()));
                    suggestions.append(articleNumber.getAndIncrement())
                            .append(". ")
                            .append("Suggesting an article from your favorite topic: ")
                            .append(topic.trim())
                            .append("\n")
                            .append("    Title: ").append(parseArticleTitle(randomArticle))
                            .append("\n")
                            .append("URL: ").append(parseArticleURL(randomArticle))
                            .append("\n");
                } else {
                    suggestions.append("No articles found for the topic: ").append(topic.trim()).append("\n");
                }
            }
        } catch (IOException e) {
            return "An error occurred while suggesting an article: " + e.getMessage() + "\n";
        }
        return suggestions.toString();
    }

    /**
     * Extracts and returns the article title from a given line of text.
     * Assumes that the article title is the first element in a semicolon-separated line.
     *
     * @param articleLine The line of text from which to extract the title.
     * @return The extracted title.
     */
    private static String parseArticleTitle(String articleLine) {
        return articleLine.split(";")[0];
    }
}
