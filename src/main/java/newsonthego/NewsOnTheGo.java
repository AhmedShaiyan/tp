package newsonthego;


import newsonthego.newstopic.NewsTopic;
import newsonthego.ui.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;


import static newsonthego.ArticleScrapper.scrapeArticle;

public class NewsOnTheGo {

    public static final String FILENAME = "data/sampleNews.txt";
    private static final Logger logger = Logger.getLogger("NewsOnTheGo");
    public static final ArrayList<NewsTopic> newsTopics = new ArrayList<>();
    private static NewsFile savedNews;

    /**
     * Main entry-point for the java.newsonthego.NewsOnTheGo application.
     */
    public static void main(String[] args) throws IOException {

        String url = "https://www.firstpost.com/tech/" +
                "nasas-budget-cuts-may-force-them-to-shut-down-one-of-a-kind-" +
                "chandra-x-ray-observatory-satellite-13753316.html";
        String outputFolderPath = "data";
        scrapeArticle(url, outputFolderPath);

        Scanner in = new Scanner(System.in);
        UI.initializeUI(in);

        savedNews = new NewsFile();

        List<NewsArticle> newsArticles = NewsImporter.importNewsFromText(FILENAME, newsTopics);

        while (true) {
            System.out.println("What do you want from me?");
            String line = in.nextLine();
            String command = line.split(" ")[0];
            try {
                boolean endLoop = processCommand(command, line, newsArticles);
                if (endLoop) {
                    break;
                }
            } catch (Exception e) {
                UI.printError(e.getMessage());
            }
        }
        logger.log(Level.INFO, "Ending NewsOnTheGo");
    }

    public enum Command {
        DAILY, GET, TOPICS, FILTER, SAVE, SOURCE, INFO, CLEAR, LOAD, BYE
    }

    private static boolean processCommand(String command, String line, List<NewsArticle> list) {
        assert !command.isEmpty();

        if (command.equalsIgnoreCase(Command.BYE.toString())) {
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        }


        Parser.handleCommand(command, line, list, newsTopics);
        return command.equalsIgnoreCase(Command.BYE.toString());
    }


    static void getNews(String line, List<NewsArticle> list) {
    }

    /**
     * Finds the index of a news topic in the list of topics.
     * This method performs a binary search to find the index of the specified topic.
     *
     * @param topic the name of the topic to search for
     * @return the index of the topic if found, or -1 if the topic is not found
     */
    static int findTopicIndex(String topic) {
        int left = 0;
        int right = newsTopics.size() - 1;
        String topicToFind = topic.trim();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midTopic = newsTopics.get(mid).getTopicName().trim();
            int comparisonResult = (topicToFind).compareToIgnoreCase(midTopic);
            if (comparisonResult == 0) {
                return mid;
            } else if (comparisonResult < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Filters news articles based on a specified topic.
     * This method finds the index of the specified topic and prints news articles related to that topic.
     *
     * @param line the input string containing the topic to filter
     */
    static void filterNews(String line) {
        int topicIndex = findTopicIndex(line.substring(6).trim());
        assert topicIndex >= 0 : "Topic index should be valid";
        System.out.println(topicIndex);
        if (topicIndex < 0) {
            System.out.println("Sorry, this topic is not available right now :(");
        } else {
            System.out.println("Here are the news articles related to the topic of your interest:");
            newsTopics.get(topicIndex).printNewsArticles();
        }
    }

    // In NewsOnTheGo class
    static void saveNews(String line, List<NewsArticle> list) {
        String[] split = line.split(" ");
        int index = Integer.parseInt(split[1]) - 1;
        if (index >= 0 && index < list.size()) {
            NewsArticle article = list.get(index);
            if (article.isSaved()) {
                System.out.println(article.getHeadline() + " has already been saved! \n" +
                        "find your saved articles at " + savedNews.getPathName());
            } else {
                try {
                    String topicName = findArticleTopic(article); // Implement this method
                    NewsFile.saveNewsWithTopic(article, topicName);
                    article.setSaved(true);
                } catch (IOException e) {
                    System.out.println("An error occurred while appending text to the file: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Please provide a valid news index!");
        }
    }

    static String findArticleTopic(NewsArticle article) {
        for (NewsTopic topic : newsTopics) {
            if (topic.relatedNewsArticles.contains(article)) {
                return topic.getTopicName();
            }
        }
        return "Unknown"; // Default or error value if the article's topic isn't found
    }

    static void loadAndDisplaySavedNews() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(savedNews.getPathName()));
        } catch (IOException e) {
            System.out.println("An error occurred while reading the save file: " + e.getMessage());
            return;
        }

        if (lines.isEmpty()) {
            System.out.println("No saved news articles to display.");
            return;
        }

        System.out.println("Displaying saved news articles:");
        for (String line : lines) {

            System.out.println(line);
        }
    }


    static void clearSavedNews() {
        savedNews.clearFile();
    }

    /**
     * Enter the news article number as stored in the array, and it will return the source of the news article.
     */
    static void sourceNews(String line, List<NewsArticle> list) {
        String[] split = line.split(" ");
        int index = Integer.parseInt(split[1]) + 1;
        System.out.println(list.get(index).getSource());
    }
}
