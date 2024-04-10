package newsonthego.commands;

import newsonthego.NewsArticle;
import newsonthego.utilities.UI;

import java.util.List;

import static newsonthego.NewsArticle.apaCitation;
import static newsonthego.NewsArticle.parseToText;
import static newsonthego.utilities.UI.printMessage;

public class GetNewsSourceCommand {

    /**
     * Retrieves and prints the source of a news article based on its index in the provided list.
     *
     * @param line The input line containing the article number.
     * @param list The list of news articles.
     */
    public static void getNewsSource(String line, List<NewsArticle> list) {
        String[] split = line.split(" ");
        try {
            int index = Integer.parseInt(split[1]) - 1;
            String outputMessage = parseToText(list.get(index)) + System.lineSeparator()
                    + apaCitation(list.get(index));
            printMessage(outputMessage);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printMessage(UI.INVALID_ARTICLE_INDEX_MESSAGE);
        }
    }
}
