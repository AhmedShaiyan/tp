package newsonthego;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;

public class UserPreferences {

    public static final Path PREFERENCES_FILE = Paths.get("data","userPreferences.txt");
    private static final Path SAVED_NEWS_PATH = Paths.get("data", "saved_news.txt");
    private static final Path SAMPLE_NEWS_FILE = Paths.get("data", "sampleNews.txt");

    private String suggestedArticleTitle;

    public UserPreferences() {

    }

    public void suggestArticleForUser(String userName) {
        String mostFrequentTopic = findMostFrequentTopic();
        if (mostFrequentTopic != null) {
            suggestRandomArticleFromTopic(mostFrequentTopic);
            if (suggestedArticleTitle != null) {
                saveSuggestedArticleForUser(userName);
            }
        }
    }

    private String findMostFrequentTopic() {
        try {
            Map<String, Long> topicCounts = Files.lines(SAVED_NEWS_PATH)
                    .filter(line -> line.contains("| Topic: "))
                    .map(line -> line.substring(line.indexOf("| Topic: ") + "| Topic: ".length()).trim())
                    .collect(Collectors.groupingBy(topic -> topic, Collectors.counting()));

            return topicCounts.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
        } catch (IOException e) {
            System.out.println("Could not load saved news to find the most frequent topic.");
            return null;
        }
    }

    private void suggestRandomArticleFromTopic(String topic) {
        try {
            List<String> articles = Files.lines(SAMPLE_NEWS_FILE)
                    .filter(line -> line.endsWith(topic))
                    .collect(Collectors.toList());
            if (!articles.isEmpty()) {
                Collections.shuffle(articles);
                suggestedArticleTitle = articles.get(0).split(";")[0].trim();
            }
        } catch (IOException e) {
            System.out.println("Could not load sample news to suggest an article.");
        }
    }

    private void saveSuggestedArticleForUser(String userName) {
        String userPrefContent = "Suggested Article for " + userName + ": " + suggestedArticleTitle + "\n";
        try {

            Files.writeString(PREFERENCES_FILE, userPrefContent, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Could not save the suggested article for the user.");
        }
    }
}
