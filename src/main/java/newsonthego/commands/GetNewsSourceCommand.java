package newsonthego.commands;

import newsonthego.NewsArticle;
import newsonthego.utilities.UI;

import java.util.List;

import static newsonthego.NewsArticle.apaCitation;
import static newsonthego.NewsArticle.parseToText;
import static newsonthego.NewsOnTheGo.NEWS_TOPICS;
import static newsonthego.utilities.UI.INDENT;
import static newsonthego.utilities.UI.printMessage;

public class GetNewsSourceCommand {

    /**
     * Retrieves and prints the source of a news article based on its index in the provided list.
     *
     * @param line The input line containing the article number.
     * @param list The list of news articles.
     */
    public static void getNewsSource(String line, int topicIndex, List<NewsArticle> list) {
        String[] split = line.split(" ");
//        int topicIndex = NewsTopic.findTopicIndex(line.substring(6).trim(), NEWS_TOPICS);
        try {
            int index = Integer.parseInt(split[1]) - 1;
            String outputMessage = parseToText(list.get(index)) + System.lineSeparator()
                    + apaCitation(list.get(index));
            printMessage(outputMessage);
            if (topicIndex >= 0) {
                printMessage("You are currently in access to the list of articles in "
                        + NEWS_TOPICS.get(topicIndex).getTopicName() + ", \n" +
                        INDENT + "use command 'BACK' to return to main list of articles.");
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printMessage(UI.INVALID_ARTICLE_INDEX_MESSAGE);
        }
    }
}
