package newsonthego.storage;

import newsonthego.newstopic.NewsTopic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TopicsFile {
    public static final String SAVED_NEWS_PATH = Paths.get("data","saved_topics.txt")
            .normalize().toString();
    private static final String pathName = SAVED_NEWS_PATH;

    public static void saveTopics(List<NewsTopic> favouriteTopics) throws IOException {
        Path filePath = Paths.get(pathName);
        Files.deleteIfExists(filePath);
        Files.createFile(filePath);
        for (NewsTopic topic : favouriteTopics) {
            Files.writeString(filePath, topic.getTopicName() + System.lineSeparator(), StandardOpenOption.APPEND);
        }
    }


    public static void loadTopics(List<NewsTopic> favouriteTopics) throws IOException {
        Files.createDirectories(Paths.get("data"));
        Path filePath = Paths.get(pathName);
        if (Files.exists(filePath, LinkOption.NOFOLLOW_LINKS)) {
            List<String> topicNames = Files.readAllLines(filePath);
            for (String topicName : topicNames) {
                favouriteTopics.add(new NewsTopic(topicName));
            }
        }
    }
}
