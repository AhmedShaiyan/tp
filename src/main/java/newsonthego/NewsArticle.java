package newsonthego;

public class NewsArticle {
    protected String headline;
    protected String author;
    protected String date;
    protected String source;
    protected String content;
    protected boolean isSaved;
    protected String url;

    public NewsArticle(String headline, String author, String date, String source, String url, String content) {
        this.headline = headline;
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

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getAuthor() {
        return author;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}

