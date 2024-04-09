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
        String logo = "\n" +
                ",-,-.                 ,---.     ,--,--'.       ,---.      \n" +
                "` | |   ,-. . , , ,-. |   | ,-. `- |   |-. ,-. |  -'  ,-. \n" +
                "  | |-. |-' |/|/  `-. |   | | |  , |   | | |-' |  ,-' | | \n" +
                " ,' `-' `-' ' '   `-' `---' ' '  `-'   ' ' `-' `---|  `-' \n" +
                "                                                ,-.|      \n" +
                "                                                `-+'      \n";
        System.out.println("Hello from\n" + logo);
        printLine();
        askForName(in);
    }

    private static void askForName(Scanner in) {
        System.out.println("What is your name?");
        String name = in.nextLine();
        while (name.isEmpty()) {
            printLine();
            System.out.println("Please input your name!");
            name = in.nextLine();
        }
        printMessage("Hello " + name);
    }

    public static void printLine() {
        System.out.println(LINE);
    }

    public static void printMessage(String Message) {
        printLine();
        System.out.println(Message);
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

    public static void printBye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public static void printConfused() {
        printMessage("I'm sorry, I don't understand what you mean :(");
    }

    public static void printInitialPrompt() {
        System.out.println("What do you want from me?");
    }

    public static void printHelpMessage() {
        printMessage("Thank you for using News On The Go! \n" +
                "Refer to below for commands and functions: \n" +
                "DAILY [date]\n" +
                INDENT + "- gives articles published on a specific date \n" +
                INDENT + "e.g. daily 10 March 2024 \n" +
                "TOPICS\n" +
                INDENT + "- gives a comprehensive list of topics that the articles are classified by \n" +
                "FILTER [topic index]\n" +
                INDENT + "- gives a list of articles related to the specified topic \n" +
                INDENT + "e.g. filter politics \n" +
                "STAR [topic index]\n" +
                INDENT + "- bookmarks a topic as favourite \n" +
                INDENT + "e.g. star business \n" +
                "STARRED\n" +
                INDENT + "- gives the list of bookmarked topics \n" +
                "REMOVE [topic index]" +
                INDENT + "- removes a topic from the bookmarked list \n" +
                "GET [article number]\n" +
                INDENT + "- gives all the details relating to a specific article \n" +
                "INFO [article number]\n" +
                INDENT + "- gives the information on the importance, reliability and bias of an article \n"
                + "SOURCE [article number]\n" +
                INDENT + "- gives the source of the article \n" +
                "SAVE [article number]\n" +
                INDENT + "- saves the specified article into a reading list \n" +
                "LOAD\n" +
                INDENT + "- displays the list of saved articles \n" +
                "CLEAR\n" +
                INDENT + "- clears the current list of saved articles \n" +

                "\n" +
                "enjoy reading :))");
    }
}
