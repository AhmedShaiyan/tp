package newsonthego.newstopic;

import newsonthego.NewsArticle;

import java.util.ArrayList;
import java.util.List;

import static newsonthego.utilities.UI.INDENT;

public class NewsTopic {
    protected String topicName;
    protected ArrayList<NewsArticle> relatedNewsArticles;
    public NewsTopic(String topicName) {
        this.topicName = topicName;
        relatedNewsArticles = new ArrayList<>();
    }

    public String getTopicName() {
        return topicName;
    }

    public ArrayList<NewsArticle> getRelatedNewsArticles() {
        return relatedNewsArticles;
    }

    public void addNewsArticle(NewsArticle newsArticle) {
        this.relatedNewsArticles.add(newsArticle);
    }

    public void printNewsArticles() {
        int i = 1;
        for (NewsArticle newsArticle : this.relatedNewsArticles) {
            System.out.println(INDENT+ i+ ". " +newsArticle.getHeadline());
            i ++;
        }
    }

    /**
     * Finds the index of a news topic in the list of topics.
     * This method performs a binary search to find the index of the specified topic.
     *
     * @param topic the name of the topic to search for
     * @return the index of the topic if found, or -1 if the topic is not found
     */
    public static int findTopicIndex(String topic, List<NewsTopic> topics) {
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
}
