package newsonthego;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static newsonthego.Parser.parseToText;

public class NewsFile {
    public static final String SAVED_NEWS_PATH = java.nio.file.Paths.get("user_data","saved_news.txt")
            .normalize().toString();
    private static String pathName;
    public NewsFile() {
        pathName = SAVED_NEWS_PATH;
    }


    /**
     * Retrieves the path to the saved news file.
     *
     * @return The path name as a String.
     */
    public String getPathName() {
        return pathName;
    }



    /**
     * Saves a given news article to the saved news file.
     * The article is converted to text format and appended to the file.
     *
     * @param article The news article to save.
     * @throws IOException If an I/O error occurs writing to or creating the file.
     */
    public static void saveNews(NewsArticle article) throws IOException {
        Files.createDirectories(Paths.get("user_data"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathName, true))) {
            writer.write(parseToText(article));
            writer.newLine();
            System.out.println("Successfully saved " + article.getHeadline() + "\n" +
                    "find your saved articles at " + pathName);
        } catch (IOException e) {
            System.out.println("An error occurred while appending text to the file: " + e.getMessage());
        }
    }


    /**
     * Clears the saved news file by deleting its contents.
     */
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
