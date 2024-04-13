package newsonthego.storage;

import newsonthego.NewsArticle;
import newsonthego.newstopic.NewsTopic;
import newsonthego.newstopic.TopicComparator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NewsImporter {

    public static List<NewsArticle> importNewsFromText(String filename, ArrayList<NewsTopic> newsTopics) {
        List<String> stringList;
        try {
            stringList = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            /* if there is any problem with the file or the file does
               not exist, return an empty list. this means if the file is
               corrupted it will not be used, and overwritten later */
            return new ArrayList<>();
        }

        List<NewsArticle> list = new ArrayList<>();
        for (String s : stringList) {
            String[] split = s.split("\\s*;\\s*");
            String headline = split[0];
            String formattedHeadline = formatHeadline(headline);
            String author = split[1];
            String date = split[2];
            String source = split[3];
            String url = split[4];
            String content = split[5];
            NewsArticle newsArticle = null;
            if (formattedHeadline.isEmpty()) {
                newsArticle = new NewsArticle(headline, author, date, source, url, content);
            } else {
                newsArticle = new NewsArticle(headline, formattedHeadline, author, date, source, url, content);
            }

            list.add(newsArticle);
            //identify related topic to the article
            String topic = split[6];
            boolean topicFound = false;
            //checks against current list of topics
            //if topic is recurring, adds article to the current topic list
            //else, a new topic will be added to the list of topics
            for (NewsTopic t : newsTopics) {
                if (topic.equalsIgnoreCase(t.getTopicName())) {
                    t.addNewsArticle(newsArticle);
                    topicFound = true;
                }
            }
            if (!topicFound) {
                NewsTopic newsTopic = new NewsTopic(topic);
                newsTopic.addNewsArticle(newsArticle);
                newsTopics.add(newsTopic);
            }
        }
        newsTopics.sort(new TopicComparator());
        return list;
    }

    private static String formatHeadline(String headline) {
        return headline.replaceAll("‘","").replaceAll("’","")
                .replaceAll(",","")
                .replaceAll("…","").replaceAll("[|]","");
    }
}

