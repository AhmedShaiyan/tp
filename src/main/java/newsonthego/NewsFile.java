package newsonthego;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static newsonthego.Parser.parseToText;

public class NewsFile {
    public static final String SAVED_NEWS_PATH = java.nio.file.Paths.get("data","saved_news.txt")
            .normalize().toString();
    private static String pathName;
    public NewsFile() {
        pathName = SAVED_NEWS_PATH;
    }

    public String getPathName() {
        return pathName;
    }

    public static void saveNews(NewsArticle article) throws IOException {
        Files.createDirectories(Paths.get("data"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathName, true))) {
            writer.write(parseToText(article));
            writer.newLine();
            System.out.println("Successfully saved " + article.getHeadline() + "\n" +
                    "find your saved articles at " + pathName);
        } catch (IOException e) {
            System.out.println("An error occurred while appending text to the file: " + e.getMessage());
        }
    }


    // In NewsFile class
    // In NewsFile class, adding a method to save news with topic information
    public static void saveNewsWithTopic(NewsArticle article, String topicName) throws IOException {
        Files.createDirectories(Paths.get("data")); // Ensure the directory exists
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathName, true))) {
            String articleText = parseToText(article) + " | Topic: " + topicName; // Combine article text with topic
            writer.write(articleText);
            writer.newLine();
            System.out.println("Successfully saved " + article.getHeadline() + " under topic " + topicName + "\n" +
                    "Find your saved articles at " + pathName);
        } catch (IOException e) {
            System.out.println("An error occurred while appending text to the file: " + e.getMessage());
        }
    }


    public void clearFile() {
        try {
            FileWriter fw = new FileWriter(pathName);
            fw.close();
            System.out.println("File cleared successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while clearing the file: " + e.getMessage());
        }
    }
}
