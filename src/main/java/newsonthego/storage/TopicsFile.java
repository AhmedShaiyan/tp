package newsonthego.storage;

import newsonthego.newstopic.NewsTopic;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class TopicsFile {
    public static final String SAVED_NEWS_PATH = Paths.get("data","saved_topics.txt")
            .normalize().toString();
    private static final String pathName = SAVED_NEWS_PATH;
    public static void saveTopics(ArrayList<NewsTopic> favouriteTopics) throws IOException {
        Path filePath = Path.of(pathName);
        Files.deleteIfExists(filePath);
        Files.createFile(filePath);
        for (NewsTopic topic : favouriteTopics) {
            Files.write(filePath, (topic.getTopicName()).getBytes(), StandardOpenOption.APPEND);
        }
    }

    public static void loadTopics(ArrayList<NewsTopic> favouriteTopics) throws IOException {
        Files.createDirectories(Paths.get("data"));
        if (Files.exists(Path.of(pathName), LinkOption.NOFOLLOW_LINKS)) {
            File savedTopics = new File(pathName);
            Scanner s = new Scanner(savedTopics);
            while (s.hasNextLine()){
                String topic = s.nextLine();
                favouriteTopics.add(new NewsTopic(topic));
            }
            s.close();
        }
    }
}
