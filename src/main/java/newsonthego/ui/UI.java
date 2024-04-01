package newsonthego.ui;

import newsonthego.NewsArticle;
import newsonthego.newstopic.NewsTopic;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static newsonthego.NewsFile.SAVED_NEWS_PATH;

public class UI {
    public static final String INVALID_ARTICLE_INDEX_MESSAGE = "Please provide a valid article index!!";
    private static final Logger logger = Logger.getLogger("NewsOnTheGo");
    public static void initializeUI(Scanner in) {
        logger.log(Level.INFO, "Starting NewsOnTheGo");
        String logo = "\n" +
                ",-,-.                 ,---.     ,--,--'.       ,---.      \n" +
                "` | |   ,-. . , , ,-. |   | ,-. `- |   |-. ,-. |  -'  ,-. \n" +
                "  | |-. |-' |/|/  `-. |   | | |  , |   | | |-' |  ,-' | | \n" +
                " ,' `-' `-' ' '   `-' `---' ' '  `-'   ' ' `-' `---|  `-' \n" +
                "                                                ,-.|      \n" +
                "                                                `-+'      \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        System.out.println("Hello " + in.nextLine());
    }

    public static void printError(String message) {
        System.err.println(message);
    }

    public static void printHeadline(String headline) {
        System.out.println(headline);
    }

    public static void printArticlesInList(List<NewsArticle> articles) {
        int i = 1; // index for user starts from 1
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
