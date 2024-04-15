package newsonthego;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static newsonthego.utilities.FormatDate.FILE_FORMAT;
import static newsonthego.utilities.UI.INDENT;

public class NewsArticle {
    protected String headline;
    protected String author;
    protected String date;
    protected String source;
    protected String content;
    protected boolean isSaved;
    protected String url;
    protected String formattedHeadline;

    public NewsArticle(String headline, String author, String date, String source, String url, String content) {
        this.headline = headline;
        this.formattedHeadline = null;
        this.author = author;
        this.date = date;
        this.source = source;
        this.content = content;
        this.isSaved = false;
        this.url = url;
    }

    public NewsArticle(String headline, String formattedHeadline, String author, String date, String source,
                       String url, String content) {
        this.headline = headline;
        this.formattedHeadline = formattedHeadline;
        this.author = author;
        this.date = date;
        this.source = source;
        this.content = content;
        this.isSaved = false;
        this.url = url;
    }

    public String getHeadline() {
        return headline;
    }
    public String getAuthor() {
        return author;
    }
    public String getUrl() {
        return url;
    }
    public String getDate() {
        return date;
    }
    public String getSource() {
        return source;
    }
    public String getContent() {
        return content;
    }
    public boolean isSaved() {
        return isSaved;
    }
    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public static String parseToText (NewsArticle article) {
        String headline = article.getHeadline();
        String url = article.getUrl();
        String author = article.getAuthor();
        String date = article.getDate();
        String source = article.getSource();

        return (headline + "\n" +
                INDENT + "URL: "+ url + "\n" +
                INDENT + "By: " + author + INDENT + "On: " + date + "\n" +
                INDENT + source + "\n");

    }

    public static String apaCitation(NewsArticle article) {
        String headline = article.getHeadline();
        String[] authorSplit = article.getAuthor().split(" ");
        char[] authorChars = authorSplit[0].toCharArray();
        String date = article.getDate();
        String source = article.getSource();
        String url = article.getUrl();
        String dateCitation = null;
        try {
            Date dateFormatted = FILE_FORMAT.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormatted);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            String monthFormatted = monthToWord(month + 1);
            int year = calendar.get(Calendar.YEAR);
            dateCitation = year + ", " + monthFormatted + " " + day;
        } catch (ParseException e) {
            dateCitation = "n.d";
        }

        // APA Format: Author. (Date). Headline. Source. URL
        if (authorSplit.length == 2) {
            return "APA Citation: " + authorSplit[1] + ", " + authorChars[0] + ". (" + dateCitation + "). "
                    + headline + ". " + source + ". " + url;
        } else {
            return "APA Citation: " + article.getAuthor() + ". (" + dateCitation + "). "
                    + headline + ". " + source + ". " + url;
        }

    }

    private static String monthToWord(int month) {
        switch (month) {
        case 1:
            return "January";
        case 2:
            return "February";
        case 3:
            return "March";
        case 4:
            return "April";
        case 5:
            return "May";
        case 6:
            return "June";
        case 7:
            return "July";
        case 8:
            return "August";
        case 9:
            return "September";
        case 10:
            return "October";
        case 11:
            return "November";
        case 12:
            return "December";
        default:
            return " ";
        }
    }
}

