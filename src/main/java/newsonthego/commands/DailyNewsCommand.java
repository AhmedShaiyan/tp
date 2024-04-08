package newsonthego.commands;

import newsonthego.NewsArticle;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static newsonthego.utilities.FormatDate.formatFromUser;
import static newsonthego.storage.NewsFile.saveNews;
import static newsonthego.utilities.UI.printArticleIsSaved;
import static newsonthego.utilities.UI.printIndexError;
import static newsonthego.utilities.UI.printHeadlinesFound;
import static newsonthego.utilities.UI.printHeadlinesNotFound;
import static newsonthego.utilities.UI.printEmptyLine;
import static newsonthego.utilities.UI.printArticlesInList;
import static newsonthego.utilities.UI.printSaveDailyDefaultMessage;
import static newsonthego.utilities.UI.printInvalidDateFormatMessage;

import java.util.logging.Logger;

public class DailyNewsCommand {

    private static final Logger LOGGER = Logger.getLogger("NewsOnTheGo");
    private static List<NewsArticle> articlesOfTheDay;

    private static final int dateindex = 1;
    private static final int commandidx = 0;
    private static final int articleidx = 1;


    /**
     * Finds articles that match the date input by the user and prints out the list
     *
     * @param input is the user input
     * @param list is the list of articles
     */
    public DailyNewsCommand(String input, List<NewsArticle> list) {
        assert !list.isEmpty();

        String[] splitInput = input.split(" ", 2);
        String date = splitInput[dateindex];

        String formattedDate = formatFromUser(date);

        if (formattedDate == null) {
            LOGGER.log(Level.WARNING, "Invalid date format");
            printInvalidDateFormatMessage();
            return;
        }

        articlesOfTheDay = list.stream()
                .filter(article -> article.getDate().equals(formattedDate))
                .collect(Collectors.toList());

        if (articlesOfTheDay.isEmpty()) {
            printHeadlinesNotFound(formattedDate);
        } else {
            printHeadlinesFound(formattedDate);
            printEmptyLine();
            printArticlesInList(articlesOfTheDay);
            URLCommand.printArticleURLsForDay(articlesOfTheDay);
            printEmptyLine();
            saveDailyArticlesParser();
        }
    }

    /**
     * Allows user to save daily news articles shown to their reading list
     */
    private static void saveDailyArticlesParser() {
        boolean isPolling = true;
        Scanner dailyIn = new Scanner(System.in);
        while (isPolling) {
            String[] dailyLine = dailyIn.nextLine().split(" ");
            String command = dailyLine[commandidx].toLowerCase();
            switch (command) {
            case "save":
                save(dailyLine);
                break;
            case "back":
                isPolling = false;
                break;
            default:
                printSaveDailyDefaultMessage();
                break;
            }
        }
    }

    /**
     * Checks whether the index the user keyed in is valid
     * It will save the article if it is valid and return an error otherwise
     *
     * @param input is the user's input
     */
    static void save(String[] input) {
        int articleIdx = Integer.parseInt(input[articleidx]) - 1;
        if (articleIdx < 0 || articleIdx >= articlesOfTheDay.size()) {
            printIndexError(articlesOfTheDay);
            return;
        }
        NewsArticle article = articlesOfTheDay.get(articleIdx);

        try {
            if (article.isSaved()) {
                printArticleIsSaved(article);
            } else {
                saveNews(article);
                article.setSaved(true);
            }
        } catch (IOException ignored) {
            // Exception handled in function
        }
    }

    public static List<NewsArticle> getArticlesOfTheDay() {
        return articlesOfTheDay;
    }
}

