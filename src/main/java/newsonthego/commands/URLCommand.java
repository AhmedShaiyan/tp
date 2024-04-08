package newsonthego.commands;

import newsonthego.NewsArticle;
import java.util.List;

/**
 * This class provides the command to print the URL of a news article.
 */
public class URLCommand {

    /**
     * Prints the URL of the specified article in a clickable format.
     *
     * The method takes a string which should contain a command followed by the index
     * of the news article whose URL should be printed. The index is expected to be a
     * 1-based integer, and if valid, the method prints the URL of the article at the
     * corresponding index in the provided list.
     *
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
            System.out.println("URL of the article: " + "[Click here](" + url + ")");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid index. Please provide a valid article index.");
        }
    }
}
