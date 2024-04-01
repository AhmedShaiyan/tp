package newsonthego.commands;

import newsonthego.NewsArticle;
import newsonthego.NewsFile;
import newsonthego.newstopic.NewsTopic;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static newsonthego.FormatDate.formatFromUser;
import static newsonthego.NewsFile.saveNews;
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

    private static final int dateindex = 1;
    private static final int commandidx = 0;
    private static final int articleidx = 1;


    /**
     * Finds articles that match the date input by the user and prints out the list
     *
     * @param input is the user input
     * @param list is the list of articles
     */
    public DailyNewsCommand(String input, List<NewsArticle> list, List<NewsTopic> topics) {
        assert !list.isEmpty();

        String[] splitInput = input.split(" ", 2);
        String date = splitInput[dateindex];

        String formattedDate = formatFromUser(date);

        if (formattedDate == null) {
            LOGGER.log(Level.WARNING, "Invalid date format");
            return;
        }

        articlesOfTheDay = list.stream()
                .filter(article -> article.getDate().equals(formattedDate))
                .collect(Collectors.toList());


        for (NewsArticle article : articlesOfTheDay) {
            for (NewsTopic topic : topics) {
                if (topic.relatedNewsArticles.contains(article)) {
                    article.setTopic(topic.getTopicName());
                    break;
                }
            }
        }

        if (articlesOfTheDay.isEmpty()) {
            printHeadlinesNotFound(date);
        } else {
            printHeadlinesFound();
            printEmptyLine();
            printArticlesInList(articlesOfTheDay);
            printEmptyLine();
            saveDailyArticlesParser();
        }
    }


    /**
     * Allows user to save daily news articles shown to their reading list
     */
    private static void saveDailyArticlesParser() {
        boolean isPolling = true;
        Scanner dailyIn = new Scanner(System.in);
        while (isPolling) {
            String[] dailyLine = dailyIn.nextLine().split(" ");
            String command = dailyLine[commandidx].toLowerCase();
            switch (command) {
            case "save":
                save(dailyLine);
                break;
            case "back":
                isPolling = false;
                break;
            default:
                printSaveDailyDefaultMessage();
                break;
            }
        }
    }

    /**
     * Checks whether the index the user keyed in is valid
     * It will save the article if it is valid and return an error otherwise
     *
     * @param input is the user's input
     */
    private static void save(String[] input) {
        int articleIdx;
        try {
            articleIdx = Integer.parseInt(input[articleidx]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input. Please specify the article index to save.");
            return;
        }

        if (articleIdx < 0 || articleIdx >= articlesOfTheDay.size()) {
            printIndexError(articlesOfTheDay); // Pass the articles list to the method
            return;
        }
        NewsArticle article = articlesOfTheDay.get(articleIdx);

        try {
            if (article.isSaved()) {
                printArticleIsSaved(article);
            } else {
                // Use the article's topic to save it, ensuring the topic information is included
                String topic = article.getTopic(); // Retrieve the topic from the article
                NewsFile.saveNewsWithTopic(article, topic); // Save the article with its topic
                article.setSaved(true);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the article: " + e.getMessage());
        }
    }



    public List<NewsArticle> getArticlesOfTheDay() {
        return articlesOfTheDay;
    }
}
