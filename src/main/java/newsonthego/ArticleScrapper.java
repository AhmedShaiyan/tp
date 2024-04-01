package newsonthego;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;



public class ArticleScrapper {

    public static void scrapeArticles(String inputFilePath, String outputFolderPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String url;
            while ((url = reader.readLine()) != null) {
                scrapeArticle(url, outputFolderPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scrapeArticle(String url, String outputFolderPath) {
        try {
            Document doc = Jsoup.connect(url).get();

            // Extract theme from navigation menu (assuming it's in a specific element)
            String theme = extractTheme(doc);

            // Extract published date in multiple formats
            String publishedDate = extractPublishedDate(doc);

            // Extract author name
            String author = extractAuthor(doc);

            // Extract abstract
            Element abstractElement = doc.selectFirst("meta[name=description]");
            String abstractText = (abstractElement != null) ? abstractElement.attr("content") : "Abstract not found";

            // Extract headlines
            String headline = doc.title();

            // Generate the output file path within the data folder
            String outputFilePath = outputFolderPath + File.separator + "testArticleScrapper.txt";

            // Write the extracted information to the output file in append mode
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
                writer.write("\"" + headline + "\";" + author + ";" + publishedDate + ";" + theme + ";" + url + ";");
                writer.write("Abstract: " + abstractText + "\n");
                //System.out.println("Data saved to: " + outputFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String extractTheme(Document doc) {
        // Try to extract theme from Open Graph metadata (og:category)
        Element metaTag = doc.selectFirst("meta[property=og:category]");
        if (metaTag != null) {
            return metaTag.attr("content");
        }

        // If theme not found in Open Graph metadata, try another format
        Element themeElement = doc.selectFirst("meta[name=categories]");
        if (themeElement != null) {
            return themeElement.attr("content");
        }

        // If theme not found in the second format, try "theme" metadata
        Element themeMetaElement = doc.selectFirst("meta[name=theme]");
        if (themeMetaElement != null) {
            return themeMetaElement.attr("content");
        }

        // If theme not found in the third format, try "article:section" metadata
        Element sectionElement = doc.selectFirst("meta[property=article:section]");
        return (sectionElement != null) ? sectionElement.attr("content") : "Theme not found";
    }

    private static String extractPublishedDate(Document doc) {
        // Try to extract published date in multiple formats
        Element dateElement = doc.selectFirst("meta[property=article:published_time]");
        if (dateElement != null) {
            return dateElement.attr("content");
        }

        // If published date not found in the first format, try another format
        Element anotherDateElement = doc.selectFirst("meta[name=cXenseParse:recs:publishtime]");
        return (anotherDateElement != null) ? anotherDateElement.attr("content") : "Published date not found";
    }

    private static String extractAuthor(Document doc) {
        // Try to extract author name
        Element authorElement = doc.selectFirst("meta[name=cXenseParse:author]");
        return (authorElement != null) ? authorElement.attr("content") : "Unknown";
    }
}
