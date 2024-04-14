package newsonthego.commands;

import newsonthego.NewsArticle;
import java.util.List;
import static newsonthego.utilities.UI.printWrappedMessage;
import static newsonthego.utilities.UI.printMessage;

/**
 * This class provides the functionality to display the summary or extract
 * of a specific news article from a list. It can be used with the default
 * list of articles or with a list provided by another command such as
 * DailyNewsCommand.
 */
public class ShowExtractCommand {

    /**
     * Prints the extract of an article in the default list.
     * This method is intended to be called without prior context set by DailyNewsCommand.
     *
     * @param line The input line containing the index of the news article.
     * @param list The default list of NewsArticle objects to be used.
     */
    public static void showExtract(String line, List<NewsArticle> list) {
        String[] parts = line.trim().split("\\s+");
        // Pass the parsed parts and the list to the shared implementation method
        showExtractImplementation(parts, list);
    }

    /**
     * Prints the extract of an article in the currently set list.
     * This method is intended to be called after setting the context with DailyNewsCommand.
     *
     * @param inputParts The input line containing the index of the news article.
     * @param articles The list of NewsArticle objects from which to retrieve the content.
     */
    public static void showExtract(String[] inputParts, List<NewsArticle> articles) {
        // Re-use the shared implementation method
        showExtractImplementation(inputParts, articles);
    }

    /**
     * Core functionality to print the extract based on the provided list.
     * The list can be either the default list or a dynamically set list.
     *
     * @param inputParts The input line containing the index.
     * @param articles The list to use for extracting the news article content.
     */
    private static void showExtractImplementation(String[] inputParts, List<NewsArticle> articles) {
        if (inputParts.length != 2 || !inputParts[0].equalsIgnoreCase("extract")) {
            printMessage("Invalid command format. Usage: extract <article index>");
            return;
        }

        try {
            int index = Integer.parseInt(inputParts[1]) - 1;
            NewsArticle article = articles.get(index);
            String content = article.getContent();
            // Break the content into lines of manageable length
            printWrappedMessage( "Article Extract: " + content); // Assuming 80 characters is the desired wrap length
        } catch (NumberFormatException e) {
            printMessage("Invalid index format. Please provide a numeric article index.");
        } catch (IndexOutOfBoundsException e) {
            printMessage("Invalid article index. Please provide an index within the valid range.");
        }
    }
}
