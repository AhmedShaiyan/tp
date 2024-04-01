package newsonthego;

import java.util.List;

import newsonthego.commands.DailyNewsCommand;
import newsonthego.commands.InfoNewsCommand;
import newsonthego.newstopic.NewsTopic;
import newsonthego.ui.UI;

public class Parser {
    public static final String INDENT = "    ";

    public static void handleCommand(String command, String line, List<NewsArticle> list, List<NewsTopic> topics) {
        switch (NewsOnTheGo.Command.valueOf(command.toUpperCase())) {
        case DAILY:
            new DailyNewsCommand(line, list, topics);
            break;
        case GET:
            NewsOnTheGo.getNews(line, list);
            break;
        case TOPICS:
            UI.printTopics(topics);
            break;
        case FILTER:
            NewsOnTheGo.filterNews(line);
            break;
        case SAVE:
            NewsOnTheGo.saveNews(line, list);
            break;
        case LOAD:
            NewsOnTheGo.loadAndDisplaySavedNews();
            break;
        case CLEAR:
            NewsOnTheGo.clearSavedNews();
            break;
        case SOURCE:
            NewsOnTheGo.sourceNews(line, list);
            break;
        case INFO:
            InfoNewsCommand.printNewsInfo(line, list);
            break;
        case BYE:
            System.out.println("Bye. Hope to see you again soon!");
            break;
        default:
            System.out.println("I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

    public static String parseToText (NewsArticle article) {
        String headline = article.getHeadline();
        String author = article.getAuthor();
        String date = article.getDate();
        String source = article.getSource();
        int importance = article.getImportance();
        int reliability = article.getReliability();
        int bias = article.getBias();
        String content = article.getContent();
        return (headline + "\n" +
                INDENT + "By: " + author + INDENT + "On: " + date + "\n" +
                INDENT + source + "\n" +
                INDENT + "| IMPORTANCE: " + importance + " | BIAS: " + bias +
                " | RELIABILITY: " + reliability + " | \n" +
                content + "\n");
    }
}
