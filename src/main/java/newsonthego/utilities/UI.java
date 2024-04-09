package newsonthego.utilities;

import newsonthego.NewsArticle;
import newsonthego.newstopic.NewsTopic;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static newsonthego.storage.NewsFile.SAVED_NEWS_PATH;

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

    public static void printInvalidDateFormatMessage() {
        System.out.println("Date format is invalid! \n" +
                "The date format is: \n" +
                "\"MM dd yyyy\" (01 02 2024), \n" +
                "\"MMMM dd yyyy\" (January 02 2024), \n" +
                "\"dd MMMM yyyy\" (02 January 2024)");
        printEmptyLine();
    }

    public static void printHeadlinesFound(String date) {
        System.out.println("Sure! Here are the headlines for today (" + date +") :");
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
        for (NewsTopic topic : newsTopics) {
            System.out.println(" - " + topic.getTopicName());
        }
    }

    public static void printAllTopics(List<NewsTopic> newsTopics) {
        System.out.println("Here are the list of topics for your viewing:");
        printTopics(newsTopics);
    }

    public static void printFavouriteTopics(List<NewsTopic> favouriteTopics) {
        if (favouriteTopics.size() <= 0) {
            System.out.println("You do not have any favourite topics. \n" +
                    "Use the command 'star [topic name]' to add a topic to your favourites.");
        }
        System.out.println("Here is the list of your favourite topics: ");
        printTopics(favouriteTopics);
    }

    public static void printHelpMessage() {
        System.out.println(
                "+------------+------------------------------------------------------------------------------------+--------------------------------+\n" +
                        "| Command    | Description                                                                        | Example                        |\n" +
                        "+------------+------------------------------------------------------------------------------------+--------------------------------+\n" +
                        "| `DAILY`    | Gives articles published on a specific date.                                       | `daily 10 March 2024`          |\n" +
                        "| `TOPICS`   | Gives a comprehensive list of topics that the articles are classified by.          | `topics`                       |\n" +
                        "| `FILTER`   | Gives a list of articles related to the specified topic.                           | `filter politics`              |\n" +
                        "| `STAR`     | Bookmarks a topic as a favorite.                                                   | `star business`                |\n" +
                        "| `STARRED`  | Gives the list of bookmarked topics.                                               | `starred`                      |\n" +
                        "| `REMOVE`   | Removes a topic from the bookmarked list.                                          | `remove business`              |\n" +
                        "| `URL`      | Gives the url of the article                                                       | `url 2`                        |\n" +
                        "| `GET`      | Gives all the details relating to a specific article.                              | `get 3`                        |\n" +
                        "| `INFO`     | Gives the information on the importance, reliability, and bias of an article.      | `info 3`                       |\n" +
                        "| `SOURCE`   | Gives the source of the article.                                                   | `source 3`                     |\n" +
                        "| `SAVE`     | Saves the specified article into a reading list.                                   | `save 3`                       |\n" +
                        "| `LOAD`     | Displays the list of saved articles.                                               | `load`                         |\n" +
                        "| `SUGGEST`  | Suggest random articles based on the user's bookmarked topics.                     | `suggest`                      |\n" +
                        "| `CLEAR`    | Clears the current list of saved articles.                                         | `clear`                        |\n" +
                        "+------------+------------------------------------------------------------------------------------+--------------------------------+\n" +
                        "Thank you for using News On The Go! \n" +
                        "Enjoy reading :))"
        );
    }

}
