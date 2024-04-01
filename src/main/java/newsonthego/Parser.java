package newsonthego;

import java.util.List;

import newsonthego.commands.DailyNewsCommand;
import newsonthego.commands.InfoNewsCommand;
import newsonthego.newstopic.NewsTopic;
import newsonthego.ui.UI;

public class Parser {
    public static final String INDENT = "    ";
    public static int topic = -1;

    public static void handleCommand(String command, String line, List<NewsArticle> list, List<NewsTopic> topics) {
        NewsOnTheGo.Command commandEnum = null;
        try {
            commandEnum = NewsOnTheGo.Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandEnum = NewsOnTheGo.Command.VOID;
        }
        switch (commandEnum) {
        case DAILY:
            new DailyNewsCommand(line, list);
            break;
        case GET:
            NewsOnTheGo.getNews(line, list);
            break;
        case TOPICS:
            UI.printTopics(topics);
            break;
        case FILTER:
            topic = NewsOnTheGo.filterNews(line);
            break;
        case SAVE:
            if (topic >= 0) { //save using index based on the current topic list shown to user
                NewsOnTheGo.saveNews(line, topics.get(topic).getRelatedNewsArticles());
            } else {
                NewsOnTheGo.saveNews(line, list);
            }
            break;
        case LOAD:
            NewsOnTheGo.loadAndDisplaySavedNews();
            break;
        case CLEAR:
            NewsOnTheGo.clearSavedNews();
            break;
        case SOURCE:
            if (topic >= 0) { //find source of news using index based on the current topic list shown to user
                NewsOnTheGo.sourceNews(line, topics.get(topic).getRelatedNewsArticles());
            } else {
                NewsOnTheGo.sourceNews(line, list);
            }
            break;
        case INFO:
            if (topic >= 0) { //display info of news using index based on the current topic list shown to user
                InfoNewsCommand.printNewsInfo(line, topics.get(topic).getRelatedNewsArticles());
            } else {
                InfoNewsCommand.printNewsInfo(line, list);
            }
            break;
        case BACK:
            if (topic >= 0) {
                System.out.println("You have exited the list of articles in " +topics.get(topic).getTopicName()+ "\n" +
                        "Currently in access to the main list of articles");
                topic = -1;
            } else {
                System.out.println("You are already in access to the main list of articles, back command is invalid :(");
            }
            break;
        case BYE:
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case VOID:
            // fall through
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
