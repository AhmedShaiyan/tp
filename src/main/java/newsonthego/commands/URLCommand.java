package newsonthego.commands;

import newsonthego.NewsArticle;
import java.util.List;

import static newsonthego.utilities.UI.INDENT;
import static newsonthego.utilities.UI.printMessage;

/**
 * This class provides the command to print the URL of a news article.
 */
public class URLCommand {

    /**
     * Prints the URL of the specified article in a clickable format.
     * The method takes a string which should contain a command followed by the index
     * of the news article whose URL should be printed. The index is expected to be a
     * 1-based integer, and if valid, the method prints the URL of the article at the
     * corresponding index in the provided list.
     * If the index is invalid, an appropriate error message is printed.
     *
     * @param line The input line containing the index of the news article.
     * @param list The list of NewsArticle objects from which to retrieve the URL.
     */
    public static void printArticleURL(String line, List<NewsArticle> list) {
        String[] split = line.split(" ");
        try {
            int index = Integer.parseInt(split[1]) - 1;
            NewsArticle article = list.get(index);
            String url = article.getUrl();
            printMessage("Article URL: " + url );
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printMessage("Invalid index. Please provide a valid article index.");
        }
    }

    /**
     * Prints URLs for articles provided by the DailyNewsCommand.
     * This method loops through the list of articles and prints their URLs.
     *
     * @param articles The list of articles for the day.
     */
    public static void printArticleURLsForDay(List<NewsArticle> articles) {
        for (NewsArticle article : articles) {
            System.out.println(INDENT + "URL: " + article.getUrl());
        }
    }

    /**
     * Parses the URL of an article from a given line of text.
     * This method assumes that the article line is a semicolon-separated string where the URL is the fifth element.
     *
     * @param articleLine The line of text representing an article, expected to be semicolon-separated.
     * @return The URL of the article if present in the article line; otherwise, returns "URL not found".
     */
    public static String parseArticleURL(String articleLine) {
        String[] parts = articleLine.split(";");
        return parts.length > 4 ? parts[4].trim() : "URL not found";
    }

}


