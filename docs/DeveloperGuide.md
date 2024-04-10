# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Article Scrapper

The `ArticleScrapper` class is designed to scrape information from web articles given their URLs. It utilizes the Jsoup
library for web scraping. 

Here's a breakdown of its key functionalities:

#### Scrape Articles Functionality:

`scrapeArticles(String inputFilePath, String outputFolderPath)`: 

Reads a list of article URLs from a text file specified 
by `inputFilePath` and scrapes each article using the `scrapeArticle` method.

#### Web Scraping Logic 1:

`extractTheme(Document doc)`: 

Attempts to extract the theme of the article from its metadata using various formats such 
as Open Graph metadata, "categories" metadata, "theme" metadata, or "article:section" metadata.

#### Web Scraping Logic 2:

`extractPublishedDate(Document doc)`: 

Tries to extract the published date of the article using different metadata formats
like "article:published_time" or "cXenseParse:recs:publishtime".

#### Web Scraping Logic 3:

`extractAuthor(Document doc)`: 

Extracts the author's name from the article metadata using the "cXenseParse:author" 
metadata tag.

#### File Handling:

Uses Java's file handling classes (`BufferedReader`, `BufferedWriter`, `FileReader`, `FileWriter`) to 
read input URLs from a text file and write scraped data to an output text file.

#### Dependency:

Relies on the [Jsoup](https://jsoup.org/) library (`org.jsoup.Jsoup`) for web scraping functionalities, specifically for parsing HTML and 
extracting data elements.


### Daily function


This daily mechanism is facilitated by a constructor from the `DailyNewsCommand` class. It takes in an input from 
the user and the current list of articles to display the news on published on a particular day to the user.  
This feature also implements the following operations:  
- `DailyNewsCommand#save()` — Saves a news article from the list given to their reading list
- `DailyNewsCommand#back()` — Exits the daily feature loop.

Given below is an example usage of the daily mechanism behaves at each step.

Step 1: The user launches the application. This initialises a list of news articles named `newsArticles` by reading
from a text file.

Step 2: When the user executes the `daily 10 March 2024` to find news articles on that day. The `DailyNewsCommand`
constructor is called, which searches the `newsArticles` list to find the corresponding news articles. The list of 
articles found are collected into a list `articlesOfTheDay`, which will be output to the user.

Step 3: After being shown the list of newsArticles, the user is able to select news article that he wants to read later
by using the `save(1)` command, which saves the first news article on the list.

Step 4: When the user is done saving the desired news articles, he is able to go back to the main function by using the
`back()` command.

The flow can be seen from the sequence diagram below.  
<img src="UML Diagrams/dailyFunctionSequence.png">

### Source Function


The `sourceNews` function in the `NewsOnTheGo` class 
is used to retrieve the source of a news article. 
The function takes in a string and a list of 
`NewsArticle` objects. The string is split into an 
array and the second element (index 1) is parsed as 
an integer. This integer is used as an index to 
retrieve a `NewsArticle` from the list, and the 
source of the news article is then printed.

Here is the code snippet for the `sourceNews` 
function:

```java
/**
 * Enter the news article number as stored in the array, and it will return the source of the news article.
 */
static void sourceNews(String line, List<NewsArticle> list) {
    String[] split = line.split(" ");
    int index = Integer.parseInt(split[1]) + 1;
    System.out.println(list.get(index).getSource());
}
```

### Filter News by Topic Feature

#### Topic Function

The `showTopics` function in  `NewsOnTheGo` class is used to show the list of topics linked to the current list of news 
articles. 

This mechanism makes use of the `NewsTopic` class to store each distinct News Topic as `NewsTopic` object, stored as a 
`newsTopics` ArrayList. 

The `Topic` function is complemented by the `Filter` Function which displays the list of articles related to the 
specified topic.

#### Filter Function
The `filterNews` function in `NewsOnTheGo` class is used to show the list of articles linked to a specific topic.

This mechanism makes use of the ArrayList of `relatedNewsArticles` in a `NewsTopic` object. 

This feature also implements the following operations:
- `FilterNewsCommand#save()` — Saves the list of news articles in the topic to their reading list
- `FilterNewsCommand#get()` — gets the details of the article and displays it to the user.
- `FilterNewsCommand#source()` — displays the source of the article to the user.
- `FilterNewsCommand#info()` — displays the importance, reliability and bias measure of the article to the user.
- `FilterNewsCommand#back()` — Exits the filter topic feature.

Given Below is an example usage scenario and how the filter and topic mechanism behaves at each step.

Step 1. The user inputs the command `TOPICS`. The `handleCommand` method will parse the input message into the command. 
The `TOPICS` command will cause `printAllTopics` in the UI class to be called, which will display the current list of 
topics of the news articles.

The following sequence diagram shows how the topic operation works.
<img src="UML Diagrams/topicFunctionSequence.png">

Step 2. Suppose the user wants to see news articles related to politics, the user then inputs `filter politics`. 
The `handleCommand` takes in the command and calls `filterNews` which used a binary search function `findTopicIndex` to 
search for the index of the topic in the ArrayList of `NewsTopic`, returning -1 if the topic is not valid, else the 
index of the topic in the list will be returned. the `filterNews` function will then print out the list of articles for 
the user. 

output may look like this:
```
What do you want from me?
filter politics
Here are the news articles related to Politics: 
1. "Political Tensions Rise in Region X Following Border Dispute"
2. "Education Reform Bill Passes in Parliament Amid Controversy"
3. "Humanitarian Crisis Deepens in Conflict-Stricken Region"
4. "Investigation Reveals Government Officials Involved in Bribery Scandal"
5. "New Legislation Aims to Address Housing Crisis in Urban Centers"
You are currently in access to the list of articles in Politics, use command 'BACK' to return to main list of articles.
```

Step 3. If the user wants to save the 3rd article in the list displayed, they would then input `save 3`. The 
`handleCommand` in `Parser` will then check the `topicIndex` to identify the correct list to extract the 
specified article from. If `topicIndex` is -1, the article will be taken from the main list of articles. 
The `saveNews` in the `NewsFile` class will save the specified article into the text file `saved_news.txt` in 
`user_data`. 

output may look like this:
```
What do you want from me?
save 3
Successfully saved "Humanitarian Crisis Deepens in Conflict-Stricken Region"
find your saved articles at user_data\saved_news.txt
```

The following sequence diagram shows how the topics and filter mechanism may work in conjunction with other commands.
<img src="UML Diagrams\filterFunctionSequence.png">

#### Design Considerations
Alternative 1 (current choice): check for topicIndex in handleCommand
- Pros: easy to implement
- Con: duplicate checking of topicIndex for article commands

Alternative 2: loop in filter command
- Con: have to come up with handle commands inside the filter command loop
- Con: initialising another Scanner object may cause unexpected conflicts

### Information Function

The Information Feature provides users with insights into the importance, reliability, and bias of news articles stored 
in the application. This feature is implemented through the `InfoNewsCommand` class.

Implementation:

The `InfoNewsCommand` class contains a method `printNewsInfo` that prints the importance, reliability, and bias 
of a news article based on its index in the list of news articles.

Example Usage:

To retrieve information about a specific news article, the user can input this command specifying the index of the 
article they are interested in. 

`info 1`


### User Preferences Function

#### SUGGEST Feature

#### Implementation

The `SUGGEST` feature provides users with article recommendations based on their favorite topics. The user's favorite topics are stored and managed by the `UserPreferences` class, which retrieves and suggests news articles related to these topics.

#### How the SUGGEST feature works:

1. When the `SUGGEST` command is invoked, `UserPreferences.getSuggestedArticlesFromFavoriteTopics()` is called.
2. This method reads the user's favorite topics from the `saved_topics.txt` file.
3. It then fetches all news articles from `sampleNews.txt`.
4. For each favorite topic, it filters articles related to that topic and randomly selects one to suggest to the user.

#### Code Snippet:
The following code snippet describes the `getSuggestedArticlesFromFavoriteTopics()` method, highlighting how it processes user's favorite topics to suggest random articles. It also includes the parseArticleTitle helper method to extract article titles from the data lines.

#### Design Considerations
The decision to use a random selection approach was to provide a dynamic user experience. This encourages users to discover a variety of articles within their favourite topics.

#### Alternatives Considered
Alternative 1 (current choice): Randomly select an article from the list of articles corresponding to each favorite topic.

- Pros: Simple to implement and ensures a variety of articles are suggested to the user.
- Cons: A user might see the same article suggested multiple times, especially if the topic has a small set of related articles.


Alternative 2: Implement a more sophisticated algorithm that keeps track of previously suggested articles and ensures a new selection in each suggestion cycle.

- Pros: Ensures that users do not receive the same suggestion more than once until all available articles have been suggested.
- Cons: More complex to implement, and might require additional storage to keep track of suggestion history.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...             | So that I can ...                                           |
|---------|----------|---------------------------|-------------------------------------------------------------|
| v1.0    | new user | see usage instructions    | refer to them when I forget how to use the application      |
| v2.0    | user     | find a to-do item by name | locate a to-do without having to go through the entire list |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
