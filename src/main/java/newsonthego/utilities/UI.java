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
    public static final String INDENT = "    ";
    public static final String LINE = "____________________________________________________________\n";

    private static final Logger logger = Logger.getLogger("NewsOnTheGo");
    public static void initializeUI(Scanner in) {
        logger.log(Level.INFO, "Starting NewsOnTheGo");
        String logo1 = "__________________________________________________________________________________________\n" +
                "    _     _                           __             ______                     __        \n" +
                "    /|   /                          /    )             /      /               /    )      \n" +
                "---/-| -/-----__----------__-------/----/----__-------/------/__----__-------/---------__-\n" +
                "  /  | /    /___)| /| /  (_ `     /    /   /   )     /      /   ) /___)     /  --,   /   )\n" +
                "_/___|/____(___ _|/_|/__(__)_____(____/___/___/_____/______/___/_(___ _____(____/___(___/_\n" +
                "                                                                                          \n" +
                "                                                                                          ";
        String logo = "\n" +
                ",-,-.                 ,---.     ,--,--'.       ,---.      \n" +
                "` | |   ,-. . , , ,-. |   | ,-. `- |   |-. ,-. |  -'  ,-. \n" +
                "  | |-. |-' |/|/  `-. |   | | |  , |   | | |-' |  ,-' | | \n" +
                " ,' `-' `-' ' '   `-' `---' ' '  `-'   ' ' `-' `---|  `-' \n" +
                "                                                ,-.|      \n" +
                "                                                `-+'      \n";
        System.out.println("Hello from\n" + logo);
        askForName(in);
    }

    private static void askForName(Scanner in) {
        System.out.println("What is your name?");
        String name = in.nextLine();
        while (name.isEmpty()) {
            //printLine();
            System.out.println("Please input your name!");
            name = in.nextLine();
        }
        //printMessage("Hello " + name);
        System.out.println("Hello " + name);
    }

    public static void printLine() {
        System.out.println(LINE);
    }

    public static void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
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
            printHeadline(INDENT + i + ": " + article.getHeadline());
            i++;
        }
    }

    public static void printInvalidDateFormatMessage() {
        printMessage("Date format is invalid! \n" +
                INDENT+ "The date format is: \n" +
                INDENT+ "\"MM dd yyyy\" (01 02 2024), \n" +
                INDENT+ "\"MMMM dd yyyy\" (January 02 2024), \n" +
                INDENT+ "\"dd MMMM yyyy\" (02 January 2024)" +
                "\n");
    }

    public static void printHeadlinesFound(String date) {
        System.out.println("Sure! Here are the headlines for today (" + date +") :");
    }

    public static void printHeadlinesNotFound(String date) {
        printMessage("Nothing is found on this day: " + date);
    }

    public static void printSaveDailyDefaultMessage() {
        printLine();
        System.out.println("Do you want to return? Type in: \"back\" ");
    }

    public static void printArticleIsSaved(NewsArticle article) {
        printMessage(article.getHeadline() + " has already been saved! \n" +
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
            System.out.println(INDENT + " - " + topic.getTopicName());
        }
    }

    public static void printAllTopics(List<NewsTopic> newsTopics) {
        printLine();
        System.out.println("Here are the list of topics for your viewing:");
        printTopics(newsTopics);
        printLine();
    }

    public static void printFavouriteTopics(List<NewsTopic> favouriteTopics) {
        if (favouriteTopics.size() <= 0) {
            printMessage("You do not have any favourite topics. \n" +
                    "Use the command 'star [topic name]' to add a topic to your favourites.");
        } else {
            printLine();
            System.out.println("Here is the list of your favourite topics: ");
            printTopics(favouriteTopics);
            printLine();
        }
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        //printMessage("Bye. Hope to see you again soon!");
    }

    public static void printConfused() {
        printMessage("I'm sorry, I don't understand what you mean :(");
    }

    public static void printInitialPrompt() {
        System.out.println("What do you want from me?");
    }

    public static void printHelpMessage() {
        printMessage(
                "Here is a list of commands and functions for your reference:\n" +
                "+------------+----------------------------------------------------------+" +
                        "---------------------------------+\n" +
                        "| Command    | Description                                                " +
                        "         | Example              |\n" +
                        "+------------+----------------------------------------------------------+" +
                        "---------------------------------+\n" +
                        "| `DAILY`    | Gives articles published on a specific date.               " +
                        "         | `daily 10 March 2024`|\n" +
                        "| `TOPICS`   | Lists topics the articles are classified by.               " +
                        "         | `topics`             |\n" +
                        "| `FILTER`   | Lists articles related to a specified topic.               " +
                        "         | `filter politics`    |\n" +
                        "| `STAR`     | Bookmarks a topic as favorite.                              " +
                        "        | `star business`      |\n" +
                        "| `STARRED`  | Shows list of bookmarked topics.                            " +
                        "        | `starred`            |\n" +
                        "| `REMOVE`   | Removes a topic from bookmarked list.                       " +
                        "        | `remove business`    |\n" +
                        "| `URL`      | Gives the URL of the article.                               " +
                        "        | `url 2`              |\n" +
                        "| `GET`      | Details of a specific article.                              " +
                        "        | `get 3`              |\n" +
                        "| `INFO`     | Information on article's importance, reliability, bias.     " +
                        "        | `info 3`             |\n" +
                        "| `SOURCE`   | Gives the source of the article.                            " +
                        "        | `source 3`           |\n" +
                        "| `SAVE`     | Saves article into a reading list.                          " +
                        "        | `save 3`             |\n" +
                        "| `LOAD`     | Displays list of saved articles.                            " +
                        "        | `load`               |\n" +
                        "| `SUGGEST`  | Suggests articles based on bookmarked topics.               " +
                        "        | `suggest`            |\n" +
                        "| `CLEAR`    | Clears the saved articles list.                             " +
                        "        | `clear`              |\n" +
                        "+------------+---------------------------------------------------------+" +
                        "----------------------------------+\n" +
                        "Thank you for using News On The Go! Enjoy reading :))"
        );
    }

}
