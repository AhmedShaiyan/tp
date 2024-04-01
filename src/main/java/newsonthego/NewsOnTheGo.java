package newsonthego;


import newsonthego.newstopic.NewsTopic;
import newsonthego.storage.NewsFile;
import newsonthego.storage.TopicsFile;
import newsonthego.ui.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;

import static newsonthego.Parser.parseToText;
import static newsonthego.ArticleScrapper.scrapeArticles;

public class NewsOnTheGo {

    public static final String FILENAME = "data/sampleNews.txt";
    private static final Logger logger = Logger.getLogger("NewsOnTheGo");
    private static final ArrayList<NewsTopic> newsTopics = new ArrayList<>();
    private static final ArrayList<NewsTopic> favouriteTopics = new ArrayList<>();
    private static NewsFile savedNews;
    private static TopicsFile savedTopics;

    public enum Command {
        DAILY, GET, TOPICS, FILTER, SAVE, SOURCE, INFO, CLEAR, LOAD, STAR, STARRED, REMOVE, BACK, BYE, VOID
    }

    private static boolean processCommand(String command, String line, List<NewsArticle> list) {
        assert !command.isEmpty();

        Parser.handleCommand(command, line, list, newsTopics, favouriteTopics);
        return command.equalsIgnoreCase(Command.BYE.toString());
    }

    /**
     * Retrieves and displays the details of a news article from the provided list based on the index specified in the
     * input line.
     * The input line is expected to contain the command followed by the index of the news article to retrieve.
     * If the index is valid and the article exists in the list, its details are printed to the console.
     * If the index is out of bounds or not a valid integer, an error message is displayed.
     *
     * @param line The input line containing the command and index of the news article.
     * @param list The list of NewsArticle objects from which to retrieve the news article.
     */
    static void getNews(String line, List<NewsArticle> list) {
        String[] split = line.split(" ");
        try{
            int index = Integer.parseInt(split[1]) - 1;
            System.out.println(parseToText(list.get(index)));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(UI.INVALID_ARTICLE_INDEX_MESSAGE);
        }
    }

    /**
     * Finds the index of a news topic in the list of topics.
     * This method performs a binary search to find the index of the specified topic.
     *
     * @param topic the name of the topic to search for
     * @return the index of the topic if found, or -1 if the topic is not found
     */
    static int findTopicIndex(String topic, List<NewsTopic> topics) {
        int left = 0;
        int right = topics.size() - 1;
        String topicToFind = topic.trim();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midTopic = topics.get(mid).getTopicName().trim();
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
    static int filterNews(String line) {
        if (line.substring(6).trim().isEmpty()) {
            System.out.println("Please provide a topic.");
            return -1;
        }
        int topicIndex = findTopicIndex(line.substring(6).trim(), newsTopics);
        if (topicIndex < 0) {
            System.out.println("Sorry, this topic is not available right now :(");
        } else {
            System.out.println("Here are the news articles related to "
                    +newsTopics.get(topicIndex).getTopicName()+ ": ");
            newsTopics.get(topicIndex).printNewsArticles();
            System.out.println("You are currently in access to the list of articles in "
                    +newsTopics.get(topicIndex).getTopicName()+
                    ", use command 'BACK' to return to main list of articles.");
        }
        return topicIndex;
    }

    /**
     * Adds a specified news topic to the user's list of favorite topics.
     * If the provided line contains a valid topic name, it is added to the list of favorite topics.
     * If the topic name is empty or not found in the list of available topics, an appropriate message is displayed.
     *
     * @param line           The input line containing the command and topic name to be starred.
     * @param newsTopics     The list of available NewsTopic objects.
     * @param favouriteTopics The list of favorite NewsTopic objects to which the specified topic will be added.
     */
    public static void starTopic(String line, List<NewsTopic> newsTopics, List<NewsTopic> favouriteTopics) {
        if (line.substring(4).trim().isEmpty()) {
            System.out.println("Please provide a topic to add to your favourites.");
            return;
        }
        int topicIndex = findTopicIndex(line.substring(4).trim(), newsTopics);
        if (topicIndex < 0) {
            System.out.println("Sorry, this topic is not available right now :(");
        } else {
            favouriteTopics.add(newsTopics.get(topicIndex));
            System.out.println(newsTopics.get(topicIndex).getTopicName()+
                    " has been added to your list of favourite topics");
        }
    }

    /**
     * Removes a specified news topic from the user's list of favorite topics.
     * If the provided line contains a valid topic name that exists in the list of favorite topics,
     * it is removed from the list.
     * If the topic name is empty or not found in the list of favorite topics, an appropriate message is displayed.
     *
     * @param line           The input line containing the command and topic name to be removed from favorites.
     * @param favouriteTopics The list of favorite NewsTopic objects from which the specified topic will be removed.
     */
    public static void removeStarredTopic(String line, List<NewsTopic> favouriteTopics) {
        if (line.substring(6).trim().isEmpty()) {
            System.out.println("Please provide a topic to remove from your favourites.");
            return;
        }
        int topicIndex = findTopicIndex(line.substring(6).trim(), favouriteTopics);
        if (topicIndex < 0) {
            System.out.println("Topic is not found in favourites");
        } else {
            System.out.println(newsTopics.get(topicIndex).getTopicName()+
                    " has been removed from your list of favourite topics");
            favouriteTopics.remove(topicIndex);
        }
    }

    /**
     * Saves a news article from the list to a user's reading list based on the index specified in the input line.
     * The input line is expected to contain the command "save" followed by the index of the news article to save.
     * If the index is valid and the article exists in the list, it is saved to the reading list.
     * If the article has already been saved, a message indicating that it's already saved is displayed.
     * If an error occurs while saving the article, an error message is displayed.
     *
     * @param line The input line containing the command and index of the news article.
     * @param list The list of NewsArticle objects from which to retrieve the news article.
     */
    static void saveNews(String line, List<NewsArticle> list) {
        String[] split = line.split(" ");
        try {
            int index = Integer.parseInt(split[1]) - 1;
            if (list.get(index).isSaved()) {
                System.out.println(list.get(index).getHeadline() + " has already been saved! \n" +
                        "find your saved articles at " + savedNews.getPathName());
            } else {
                try {
                    savedNews.saveNews(list.get(index));
                    list.get(index).setSaved(true);
                } catch (IOException e) {
                    System.out.println("An error occurred while appending text to the file: " + e.getMessage());
                }
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(UI.INVALID_ARTICLE_INDEX_MESSAGE);
        }
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
     * Retrieves and displays the details of a news article from the provided list based on the index specified in the
     * input line.
     * The input line is expected to contain the command "source" followed by the index of the news article to retrieve.
     * If the index is valid and the article exists in the list, its details are printed to the console.
     * If the index is out of bounds or not a valid integer, an error message is displayed.
     *
     * @param line The input line containing the command and index of the news article.
     * @param list The list of NewsArticle objects from which to retrieve the news article.
     */
    static void sourceNews(String line, List<NewsArticle> list) {
        String[] split = line.split(" ");
        try{
            int index = Integer.parseInt(split[1]) - 1;
            System.out.println(parseToText(list.get(index)));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(UI.INVALID_ARTICLE_INDEX_MESSAGE);
        }
    }

    /**
    * Main entry-point for the java.newsonthego.NewsOnTheGo application.
    */
    public static void main(String[] args) throws IOException {
        String url = "https://www.firstpost.com/tech/" +
                "nasas-budget-cuts-may-force-them-to-shut-down-one-of-a-kind-" +
                "chandra-x-ray-observatory-satellite-13753316.html";

        String inputFilePath = "data/ListOfURLs.txt";
        String outputFolderPath = "data";
        scrapeArticles(inputFilePath, outputFolderPath);

        Scanner in = new Scanner(System.in);
        UI.initializeUI(in);
        savedNews = new NewsFile();

        List<NewsArticle> newsArticles = NewsImporter.importNewsFromText(FILENAME, newsTopics);
        TopicsFile.loadTopics(favouriteTopics);

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
        TopicsFile.saveTopics(favouriteTopics);
        logger.log(Level.INFO, "Ending NewsOnTheGo");
    }
}
