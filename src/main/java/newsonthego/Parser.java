package newsonthego;

import newsonthego.commands.DailyNewsCommand;
import newsonthego.commands.FilterNewsCommand;
import newsonthego.commands.GetNewsSourceCommand;
import newsonthego.commands.ShowHeadlinesCommand;
import newsonthego.commands.URLCommand;
import newsonthego.commands.QuoteGenerator;
import newsonthego.commands.ShowExtractCommand;

import newsonthego.newstopic.NewsTopic;
import newsonthego.utilities.UI;

import static newsonthego.NewsOnTheGo.suggestArticle;
import static newsonthego.utilities.UI.printMessage;
import static newsonthego.utilities.UI.printQuote;


import java.util.List;

public class Parser {
    public static int topic = -1;

    public static void handleCommand(String command, String line,
                                     List<NewsArticle> list, List<NewsTopic> topics, List<NewsTopic> favouriteTopics) {
        NewsOnTheGo.Command commandEnum = null;

        try {
            commandEnum = NewsOnTheGo.Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandEnum = NewsOnTheGo.Command.VOID;
        }

        QuoteGenerator quoteGenerator = new QuoteGenerator();

        switch (commandEnum) {
        case HELP:
            UI.printHelpMessage();
            break;
        case DAILY:
            new DailyNewsCommand(line, list);
            break;
        case GET:
            if (topic >= 0) { //save using index based on the current topic list shown to user
                NewsOnTheGo.getNews(line, topics.get(topic).getRelatedNewsArticles());
            } else {
                NewsOnTheGo.getNews(line, list);
            }
            break;
        case TOPICS:
            UI.printAllTopics(topics);
            break;
        case HEADLINES:
            ShowHeadlinesCommand.showHeadlines(line);
            break;
        case STAR:
            NewsOnTheGo.starTopic(line, topics, favouriteTopics);
            break;
        case STARRED:
            UI.printFavouriteTopics(favouriteTopics);
            break;
        case SUGGEST:
            try {
                suggestArticle(line);
            } catch (NewsOnTheGoExceptions e) {
                System.out.println(e.getMessage());
            }
            break;
        case REMOVE:
            NewsOnTheGo.removeStarredTopic(line, favouriteTopics);
            break;
        case FILTER:
            topic = FilterNewsCommand.filterNews(line);
            break;
        case SAVE:
            if (topic >= 0) { //save using index based on the current topic list shown to user
                NewsOnTheGo.saveNewsFromList(line, topics.get(topic).getRelatedNewsArticles());
            } else {
                NewsOnTheGo.saveNewsFromList(line, list);
            }
            break;
        case LOAD:
            NewsOnTheGo.loadAndDisplaySavedNews();
            break;
        case CLEAR:
            NewsOnTheGo.clearSavedNews();
            break;
        case URL:
            URLCommand.printArticleURL(line, list);
            break;
        case SOURCE:
            if (topic >= 0) { //find source of news using index based on the current topic list shown to user
                GetNewsSourceCommand.getNewsSource(line, topics.get(topic).getRelatedNewsArticles());
            } else {
                GetNewsSourceCommand.getNewsSource(line, list);
            }
            break;
        case BACK:
            if (topic >= 0) {
                printMessage("You have exited the list of articles in " +topics.get(topic).getTopicName()+ "\n" +
                        "Currently in access to the main list of articles");
                topic = -1;
            } else {
                printMessage("You are already in access to the main list of articles, " +
                        "back command is invalid :(");
            }
            break;

        case QUOTE:
            String quote = quoteGenerator.getRandomQuote();
            System.out.println("\n");
            System.out.println("Some words of inspiration today: ");
            printQuote(quote);
            System.out.println("\n");
            break;
        case EXTRACT:
            ShowExtractCommand.showExtract(line, list);
            break;
        case BYE:
            UI.printBye();
            break;
        case VOID:
            // fall through
        default:
            UI.printConfused();
            break;
        }
    }
}
