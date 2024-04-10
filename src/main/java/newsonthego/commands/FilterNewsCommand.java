package newsonthego.commands;

import newsonthego.newstopic.NewsTopic;

import static newsonthego.NewsOnTheGo.NEWS_TOPICS;
import static newsonthego.utilities.UI.printMessage;
import static newsonthego.utilities.UI.printLine;
import static newsonthego.utilities.UI.INDENT;

public class FilterNewsCommand {
    /**
     * Filters news articles based on a specified topic.
     * This method finds the index of the specified topic and prints news articles related to that topic.
     *
     * @param line the input string containing the topic to filter
     */
    public static int filterNews(String line) {
        if (line.substring(6).trim().isEmpty()) {
            printMessage("Please provide a topic.");
            return -1;
        }
        int topicIndex = NewsTopic.findTopicIndex(line.substring(6).trim(), NEWS_TOPICS);
        if (topicIndex < 0) {
            printMessage("Sorry, this topic is not available right now :(");
        } else {
            printLine();
            System.out.println("Here are the news articles related to "
                    + NEWS_TOPICS.get(topicIndex).getTopicName() + ": ");
            NEWS_TOPICS.get(topicIndex).printNewsArticles();
            printMessage("You are currently in access to the list of articles in "
                    + NEWS_TOPICS.get(topicIndex).getTopicName() + ", \n" +
                    INDENT + "use command 'BACK' to return to main list of articles.");
        }
        return topicIndex;
    }
}
