package newsonthego.commands;

import newsonthego.NewsArticle;
import newsonthego.NewsFile;
import newsonthego.newstopic.NewsTopic;
import newsonthego.UserPreferences;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static newsonthego.FormatDate.formatFromUser;
import static newsonthego.NewsOnTheGo.newsTopics;
import static newsonthego.ui.UI.printArticleIsSaved;
import static newsonthego.ui.UI.printIndexError;
import static newsonthego.ui.UI.printHeadlinesFound;
import static newsonthego.ui.UI.printHeadlinesNotFound;
import static newsonthego.ui.UI.printEmptyLine;
import static newsonthego.ui.UI.printArticlesInList;
import static newsonthego.ui.UI.printSaveDailyDefaultMessage;

import java.util.logging.Logger;

public class DailyNewsCommand {

    private static final Logger LOGGER = Logger.getLogger("NewsOnTheGo");
    private static List<NewsArticle> articlesOfTheDay;
    private static final UserPreferences userPreferences = new UserPreferences(); // One instance for all uses

    private static final int dateindex = 1;
    private static final int commandidx = 0;
    private static final int articleidx = 1;

    public DailyNewsCommand(String input, List<NewsArticle> list, List<NewsTopic> topics) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length < 2) {
            LOGGER.log(Level.WARNING, "Invalid command syntax for DAILY.");
            return;
        }
        String dateInput = splitInput[dateindex];
        String formattedDate = formatFromUser(dateInput);

        if (formattedDate == null) {
            LOGGER.log(Level.WARNING, "Invalid date format or date could not be parsed: " + dateInput);
            printHeadlinesNotFound(dateInput);
            return;
        }

        articlesOfTheDay = list.stream()
                .filter(article -> formattedDate.equals(article.getDate()))
                .collect(Collectors.toList());

        if (articlesOfTheDay.isEmpty()) {
            printHeadlinesNotFound(formattedDate);
        } else {
            printHeadlinesFound();
            printEmptyLine();
            printArticlesInList(articlesOfTheDay);
            printEmptyLine();
            saveDailyArticlesParser();
        }
    }

    private static void saveDailyArticlesParser() {
        Scanner dailyIn = new Scanner(System.in);
        boolean isPolling = true;
        while (isPolling) {
            System.out.println("Enter the article index to save or type 'back' to return: ");
            String line = dailyIn.nextLine();
            String[] splitLine = line.split(" ");
            if (splitLine[0].equalsIgnoreCase("back")) {
                isPolling = false;
            } else {
                save(splitLine);
            }
        }
    }


    private static void save(String[] input) {
        int articleIdx;
        try {
            articleIdx = Integer.parseInt(input[articleidx]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input. Please specify the article index to save.");
            return;
        }

        if (articleIdx < 0 || articleIdx >= articlesOfTheDay.size()) {
            printIndexError(articlesOfTheDay);
            return;
        }
        NewsArticle article = articlesOfTheDay.get(articleIdx);

        try {
            if (article.isSaved()) {
                printArticleIsSaved(article);
            } else {
                String topic = findArticleTopic(article);
                if (topic.equals("Unknown")) {
                    System.out.println("The topic for this article could not be found.");
                    return;
                }
                NewsFile.saveNewsWithTopic(article, topic);
                article.setSaved(true);
                userPreferences.suggestArticleForUser(article.getAuthor());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the article: " + e.getMessage());
        }
    }

    // Method to find an article's topic
    private static String findArticleTopic(NewsArticle article) {
        for (NewsTopic topic : newsTopics) {
            if (topic.relatedNewsArticles.contains(article)) {
                return topic.getTopicName();
            }
        }
        return "Unknown";
    }



    public List<NewsArticle> getArticlesOfTheDay() {
        return articlesOfTheDay;
    }
}
