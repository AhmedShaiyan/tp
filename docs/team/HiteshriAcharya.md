# Hiteshri's Project Portfolio Page

## Project: NewsOnTheGo

NewsOnTheGo is a command line interface (CLI) application that helps users stay up-to-date with the latest news
articles by providing users with a list of the current headlines. Users are able to personalise this application by
saving their preferred topics and articles.

<h2> Code Contributed: 
<a target="_blank" href="https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=hiteshriacharya&breakdown=true">
RepoSense Link
</a>
</h2>

## Summary of Contributions

### Article Scraper

The `ArticleScraper` class provides functionality to scrape articles from a given URL and extract key information such as 
theme, published date, author, abstract, and headline. It uses the Jsoup library to parse the HTML content of the webpage 
and extract metadata such as the theme and published date. The class standardizes the theme and date formats and handles 
different cases to ensure the data is formatted consistently.

The extracted information is then saved to a specified output file in a structured format, including the headline, 
author, published date, URL, abstract, and theme of the article. This information can be useful for summarizing 
and categorizing news articles.

### Quote Generator

The random quote function is designed to randomly output one of several famous quotes. It is implemented as a class 
called `QuoteGenerator` that contains a list of 10-20 famous quotes and a method called `getRandomQuote()`. This method 
randomly selects and returns a quote from the list.
