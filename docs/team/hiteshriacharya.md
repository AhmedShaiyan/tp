# Hiteshri's Project Portfolio Page

## Project: NewsOnTheGo

NewsOnTheGo is a command line interface (CLI) application that helps users stay up-to-date with the latest news
articles by providing users with a list of the current headlines. Users are able to personalise this application by
saving their preferred topics and articles.

Code Contributed: 
<a target="_blank" href="https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=hiteshriacharya&breakdown=true">
RepoSense Link
</a>


### Summary of Contributions

#### Article Scraper

The `ArticleScraper` class is designed to scrape articles from a given URL and extract essential information, 
including the theme, published date, author, abstract, headline, and source. Utilizing the Jsoup library, the class 
effectively parses the HTML content of the webpage and retrieves metadata such as the article's theme, published date,
author, source, and other details.

The class standardizes the extracted theme and date formats for consistency across different articles and
handles various scenarios to format the data accurately. Additionally, the class saves the extracted information in 
a structured format to a specified output file, organizing data such as the headline, author, published date, URL, 
abstract, theme, and source of the article.

This functionality facilitates the categorization and summarization of news articles, making it a valuable tool 
for efficiently organizing and accessing information from online sources.

#### Quote Generator

The random quote function is designed to randomly output one of several famous quotes. It is implemented as a class 
called `QuoteGenerator` that contains a list of 10-20 famous quotes and a method called `getRandomQuote()`. This method 
randomly selects and returns a quote from the list.

### Contributions to Documentation

#### Contributions to the UG:
- Added documentation for the `quote` command 
#### Contributions to the DG:
- Added documentation for the implementation of the `Article Scraper` 
- Illustrated how the code works using two sequence diagrams and one class diagram 
- Added documentation for the `quote` function
- Illustrated how the code works using one sequence diagram 

### Contributions to Team-Based Tasks
- Organised code into different classes like `NewsImporter`, `UI` and `Parser` 
- Integrated the Article Scraper to our application : [#113](https://github.com/AY2324S2-CS2113-T12-1/tp/issues/113)
- Documented and resolved bugs for the team related to integration of the different functions : [#167](https://github.com/AY2324S2-CS2113-T12-1/tp/issues/167)
- Conducted testing of the JAR file and surfaced potential bugs: [#182](https://github.com/AY2324S2-CS2113-T12-1/tp/issues/182), 
[#183](https://github.com/AY2324S2-CS2113-T12-1/tp/issues/183), 
[#185](https://github.com/AY2324S2-CS2113-T12-1/tp/issues/185)

### Contributions beyond Project Team

- Reported bugs and suggestions for other teams: [1](https://github.com/HiteshriAcharya/ped/issues/1), 
[2](https://github.com/HiteshriAcharya/ped/issues/4), 
[3](https://github.com/HiteshriAcharya/ped/issues/5),
[4](https://github.com/HiteshriAcharya/ped/issues/6),
[5](https://github.com/HiteshriAcharya/ped/issues/7), 
[6](https://github.com/HiteshriAcharya/ped/issues/8), 
[7](https://github.com/HiteshriAcharya/ped/issues/10)
