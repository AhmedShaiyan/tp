# John Doe - Project Portfolio Page

## Overview


### Summary of Contributions

```
package newsonthego.commands;

import newsonthego.NewsArticle;
import newsonthego.utilities.UI;

import java.util.List;

public class InfoNewsCommand {

    /**
     * Prints the importance, reliability, and bias of a news article based on its index in the list.
     *
     * @param line The command line containing the index of the news article.
     * @param list The list of NewsArticle objects containing news articles.
     */
    public static void printNewsInfo(String line, List<NewsArticle> list) {
        String[] split = line.split(" ");
        try {
            int index = Integer.parseInt(split[1]) - 1;
            if (index >= 0 && index < list.size()) {
                NewsArticle article = list.get(index);
                System.out.println("Importance: " + article.getImportance());
                System.out.println("Reliability: " + article.getReliability());
                System.out.println("Bias: " + article.getBias());
            } else {
                System.out.println("Invalid article index.");
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(UI.INVALID_ARTICLE_INDEX_MESSAGE);
        }
    }
}
```

```
    @Test
    public void testInfoNewsValidIndex() {
        List<NewsArticle> newsArticles = NewsImporter.importNewsFromText("data/sampleNews.txt", new ArrayList<>());
        String expectedOutput = "Importance: 9\nReliability: 9\nBias: 3";
        assertEquals(expectedOutput, "Importance: " + newsArticles.get(1).getImportance() +
                "\nReliability: " + newsArticles.get(1).getReliability() +
                "\nBias: " + newsArticles.get(1).getBias());
    }
```