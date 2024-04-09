package newsonthego.commands;

import newsonthego.NewsArticle;
import newsonthego.utilities.UI;

import java.util.List;

import static newsonthego.Parser.parseToText;

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
            System.out.println(parseToText(list.get(index)));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(UI.INVALID_ARTICLE_INDEX_MESSAGE);
        }
    }
}
