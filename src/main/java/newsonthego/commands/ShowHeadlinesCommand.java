package newsonthego.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * This class is responsible for handling the "headlines" command, which displays
 * a specified number of article headlines from the beginning of the news articles list.
 */
public class ShowHeadlinesCommand {
    private static final Path NEWS_FILE_PATH = Path.of("data", "testArticleScraper.txt");

    /**
     * Displays the headlines of the first few articles specified by the index from the news articles text file.
     *
     * @param line the input command line that contains the "list" command followed by an index.
     *             The index determines how many article headlines will be shown.
     */
    public static void showHeadlines(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 2) {
            System.out.println("Invalid command format. Usage: list <number of articles>");
            return;
        }

        try {
            int numberOfArticles = Integer.parseInt(parts[1]);
            List<String> lines = Files.readAllLines(NEWS_FILE_PATH);

            if (numberOfArticles > lines.size()) {
                System.out.println("Invalid index, too high. There are only " + lines.size() + " articles.");
                return;
            }

            AtomicInteger counter = new AtomicInteger(1);
            List<String> headlines = lines.stream()
                    .map(fileLine -> String.format("%d. %s", counter.getAndIncrement(), fileLine.split(";")[0]))
                    .limit(numberOfArticles)
                    .collect(Collectors.toList());

            if (headlines.isEmpty()) {
                System.out.println("No articles found.");
            } else {
                System.out.println("Displaying the first " + numberOfArticles + " article headlines:");
                headlines.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid number for the number of articles.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
