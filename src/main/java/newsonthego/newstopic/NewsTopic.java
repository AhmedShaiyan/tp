package newsonthego.newstopic;

import newsonthego.NewsArticle;

import java.util.ArrayList;

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
            System.out.println(i+ ". " +newsArticle.getHeadline());
            i ++;
        }
    }
}
