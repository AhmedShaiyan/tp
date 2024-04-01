package newsonthego.ui;

import newsonthego.NewsArticle;
import newsonthego.newstopic.NewsTopic;
import newsonthego.UserPreferences;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static newsonthego.NewsFile.SAVED_NEWS_PATH;

public class UI {
    private static final Logger logger = Logger.getLogger("NewsOnTheGo");
    private static final UserPreferences userPreferences = new UserPreferences();

    public static void initializeUI(Scanner in) throws IOException {
        logger.log(Level.INFO, "Starting NewsOnTheGo");
        String logo = "\n" +
                ",-,-.                 ,---.     ,--,--'.       ,---.      \n" +
                "` | |   ,-. . , , ,-. |   | ,-. `- |   |-. ,-. |  -'  ,-. \n" +
                "  | |-. |-' |/|/  `-. |   | | |  , |   | | |-' |  ,-' | | \n" +
                " ,' `-' `-' ' '   `-' `---' ' '  `-'   ' ' `-' `---|  `-' \n" +
                "                                                ,-.|      \n" +
                "                                                `-+'      \n";
        System.out.println("Hello from\n" + logo);

        System.out.print("What is your name? ");
        String userName = in.nextLine();
        System.out.println("Hello " + userName);

        userPreferences.suggestArticleForUser(userName);
        displaySuggestedArticle(userName);
    }

    private static void displaySuggestedArticle(String userName) throws IOException {
        try {
            List<String> lines = Files.readAllLines(UserPreferences.PREFERENCES_FILE);
            String suggestedArticle = lines.stream()
                    .filter(line -> line.contains("Suggested Article for " + userName + ":"))
                    .findFirst()
                    .orElse("No article suggestion available.");

            System.out.println(suggestedArticle);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the suggested article: " + e.getMessage());
        }
    }

    public static void printError(String message) {
        System.err.println(message);
    }

    public static void printHeadline(String headline) {
        System.out.println(headline);
    }

    public static void printArticlesInList(List<NewsArticle> articles) {
        int i = 1;
        for (NewsArticle article : articles) {
            printHeadline(i + ": " + article.getHeadline());
            i++;
        }
    }

    public static void printHeadlinesFound() {
        System.out.println("Sure! Here are the headlines for today:");
    }

    public static void printHeadlinesNotFound(String date) {
        System.out.println("Nothing is found on this day: " + date);
    }

    public static void printSaveDailyDefaultMessage() {
        System.out.println("Do you want to return? Type in: \"back\" ");
    }

    public static void printArticleIsSaved(NewsArticle article) {
        System.out.println(article.getHeadline() + " has already been saved! \n" +
                "find your saved articles at " + SAVED_NEWS_PATH);
    }

    public static void printIndexError(List<NewsArticle> articleList) {
        printError("Theres only " + articleList.size() + " articles in the list...");
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    /**
     * Displays the list of available news topics.
     * This method prints the list of topics along with their names.
     */
    public static void printTopics(List<NewsTopic> newsTopics) {
        System.out.println("Here are the list of topics for your viewing:");
        for (NewsTopic topic : newsTopics) {
            System.out.println(" - " + topic.getTopicName());
        }
    }
}
