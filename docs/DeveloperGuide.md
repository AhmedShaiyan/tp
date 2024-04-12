<p style="font-size: xxx-large">Developer Guide</p>


<ul style="font-size: large">
    <li><strong><a href="#acknowledgements">Acknowledgements</a></strong></li>
    <li><strong><a href="#setting-up">Setting Up, Getting Started</a></strong></li>
    <li><strong><a href="#design-implementation">Design and Implementation</a></strong></li>
        <ul>
            <li><a href="#architecture">Architecture</a></li>
        </ul>
        <ul>
            <li><a href="#article-scrapper">Article Scrapper</a></li>
            <ul>
                <li><a href="#scrapper-implementation">Implementation</a></li>
                <li><a href="#scrapper-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#daily">Daily feature</a></li>
            <ul>
                <li><a href="#daily-implementation">Implementation</a></li>
                <li><a href="#daily-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#topics">Topics feature</a></li>
            <ul>
                <li><a href="#topics-implementation">Implementation</a></li>
                <li><a href="#topics-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#star">Star feature</a></li>
            <ul>
                <li><a href="#star-implementation">Implementation</a></li>
                <li><a href="#star-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#starred">Starred feature</a></li>
            <ul>
                <li><a href="#starred-implementation">Implementation</a></li>
                <li><a href="#starred-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#remove">Remove feature</a></li>
            <ul>
                <li><a href="#remove-implementation">Implementation</a></li>
                <li><a href="#remove-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#suggest">Suggest feature</a></li>
            <ul>
                <li><a href="#suggest-implementation">Implementation</a></li>
                <li><a href="#suggest-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#filter">Filter feature</a></li>
            <ul>
                <li><a href="#filter-implementation">Implementation</a></li>
                <li><a href="#filter-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#save">Save feature</a></li>
            <ul>
                <li><a href="#save-implementation">Implementation</a></li>
                <li><a href="#save-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#source">Source feature</a></li>
            <ul>
                <li><a href="#source-implementation">Implementation</a></li>
                <li><a href="#source-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#url">URL feature</a></li>
            <ul>
                <li><a href="#url-implementation">Implementation</a></li>
                <li><a href="#url-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#headlines">Headlines feature</a></li>
            <ul>
                <li><a href="#headlines-implementation">Implementation</a></li>
                <li><a href="#headlines-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#get">Get feature</a></li>
            <ul>
                <li><a href="#get-implementation">Implementation</a></li>
                <li><a href="#get-design">Design Consideration</a></li>
            </ul>
        </ul>
        <ul>
            <li><a href="#load">Load feature</a></li>
            <ul>
                <li><a href="#load-implementation">Implementation</a></li>
                <li><a href="#load-design">Design Consideration</a></li>
            </ul>
        </ul>
    <li><strong><a href="#requirements">Appendix: Requirements</a></strong></li>
        <ul style="font-size: medium">
            <li><a href="#Product scope">Product scope</a></li>
            <li><a href="#User stories">User stories</a></li>
            <li><a href="#Use cases">Use cases</a></li>
            <li><a href="#Non-Functional Requirements">Non-Functional Requirements</a></li>
            <li><a href="#Glossary">Glossary</a></li>
        </ul>
</ul>

<h2 id="acknowledgements"> Acknowledgements </h2>
<ul>
    <li>
        <p>
            Referenced to 
            <a href="https://se-education.org/addressbook-level3/DeveloperGuide.html">
                AB-3 Developer Guide
            </a>
        </p>
    </li>
    <li>
        <p>
            Use of 
            <a href="https://jsoup.org/">
                jsoup library
            </a>
            to scrap articles
        </p>
    </li>
</ul>

<p>{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- 
include links to the original source as well}</p>

<h2 id="setting-up">Setting up, Getting Started</h2>
First, fork this repo, and clone the fork into your computer.

If you plan to use Intellij IDEA (highly recommended):

<ol>
    <li>
        Configure the JDK: Follow the guide 
        <a href="https://se-education.org/guides/tutorials/intellijImportGradleProject.html">
            [se-edu/guides] IDEA: Configuring the JDK 
        </a>
        to to ensure Intellij is configured to use JDK 11.</li>
    <li>
        Import the project as a Gradle project: Follow the guide
        <a href="https://se-education.org/guides/tutorials/intellijImportGradleProject.html">
            [se-edu/guides] IDEA: Importing a Gradle project 
        </a>
        to import the project into IDEA.
    </li>
<span style="color: red"> &#33; </span> Note: Importing a Gradle project is slightly different from importing a normal Java project.
    <li>
    Verify the setup:
    <ol>
        <li>
            Run NewsOnTheGo and try a few commands from our 
            <a href="UserGuide.md">User Guide.</a>
        </li>
    </ol>
</ol>



Alternatively, refer to our [User Guide](UserGuide.md) for quick start details.

<h2 id="architecture">Architecture</h2>

The Architecture Design given above gives a visualisation of the higher level design of our application.


<h2 id="design-implementation">Design and Implementation</h2>

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

<h3 id="article-scrapper"> Article Scrapper</h3>

The `ArticleScrapper` class is designed to scrape information from web articles given their URLs. It utilizes the Jsoup
library for web scraping. 

Here's a breakdown of its key functionalities:

<h4> Scrape Articles Functionality: </h4>

`scrapeArticles(String inputFilePath, String outputFolderPath)`: 

Reads a list of article URLs from a text file specified 
by `inputFilePath` and scrapes each article using the `scrapeArticle` method.

<h4 id=""> Web Scraping Logic 1: </h4>

`extractTheme(Document doc)`: 

Attempts to extract the theme of the article from its metadata using various formats such 
as Open Graph metadata, "categories" metadata, "theme" metadata, or "article:section" metadata.

<h4 id=""> Web Scraping Logic 2: </h4>

`extractPublishedDate(Document doc)`: 

Tries to extract the published date of the article using different metadata formats
like "article:published_time" or "cXenseParse:recs:publishtime".

<h4> Web Scraping Logic 3: </h4>

`extractAuthor(Document doc)`: 

Extracts the author's name from the article metadata using the "cXenseParse:author" 
metadata tag.

<h4> File Handling: </h4>

Uses Java's file handling classes (`BufferedReader`, `BufferedWriter`, `FileReader`, `FileWriter`) to 
read input URLs from a text file and write scraped data to an output text file.

<h4> Dependency: </h4>

Relies on the [Jsoup](https://jsoup.org/) library (`org.jsoup.Jsoup`) for web scraping functionalities, 
specifically for parsing HTML and extracting data elements.


<h3> Daily function </h3>


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

<h3> Source Function </h3>


The `sourceNews` function in the `NewsOnTheGo` class is used to retrieve the source of a news article. The function 
takes in a string and a list of `NewsArticle` objects. The string is split into an array and the second 
element (index 1) is parsed as an integer. This integer is used as an index to retrieve a `NewsArticle` from the list, 
and the source of the news article is then printed.

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

<h3> Filter News by Topic Feature </h3>

<h4> Topic Function </h4>

The `showTopics` function in  `NewsOnTheGo` class is used to show the list of topics linked to the current list of news 
articles. 

This mechanism makes use of the `NewsTopic` class to store each distinct News Topic as `NewsTopic` object, stored as a 
`newsTopics` ArrayList. 

The `Topic` function is complemented by the `Filter` Function which displays the list of articles related to the 
specified topic.

<h4> Filter Function </h4>
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

The following sequence diagram shows how the topic operation works. <br>

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
<br>
<img src="UML Diagrams/filterFunctionSequence.png">

<h4> Design Considerations </h4>
Alternative 1 (current choice): check for topicIndex in handleCommand
- Pros: easy to implement
- Con: duplicate checking of topicIndex for article commands

Alternative 2: loop in filter command
- Con: have to come up with handle commands inside the filter command loop
- Con: initialising another Scanner object may cause unexpected conflicts

<h3> Information Function </h3>

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

The `SUGGEST` feature provides users with article recommendations based on their favorite topics. The user's favorite
topics are stored and managed by the `UserPreferences` class, which retrieves and suggests news articles 
related to these topics.

#### How the SUGGEST feature works:

1. When the `SUGGEST` command is invoked, `UserPreferences.getSuggestedArticlesFromFavoriteTopics()` is called.
2. This method reads the user's favorite topics from the `saved_topics.txt` file.
3. It then fetches all news articles from `sampleNews.txt`.
4. For each favorite topic, it filters articles related to that topic and randomly selects one to suggest to the user.

#### Code Snippet:
The following code snippet describes the `getSuggestedArticlesFromFavoriteTopics()` method, highlighting how it 
processes user's favorite topics to suggest random articles. It also includes the parseArticleTitle helper method 
to extract article titles from the data lines.

#### Design Considerations
The decision to use a random selection approach was to provide a dynamic user experience. This encourages users to 
discover a variety of articles within their favourite topics.

#### Alternatives Considered
Alternative 1 (current choice): Randomly select an article from the list of articles corresponding to 
each favorite topic.

- Pros: Simple to implement and ensures a variety of articles are suggested to the user.
- Cons: A user might see the same article suggested multiple times, especially if the topic has a small set of 
related articles.


Alternative 2: Implement a more sophisticated algorithm that keeps track of previously suggested articles and ensures 
a new selection in each suggestion cycle.

- Pros: Ensures that users do not receive the same suggestion more than once until all available articles have 
been suggested.
- Cons: More complex to implement, and might require additional storage to keep track of suggestion history.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

<h2 id="user-stories"> User Stories </h2>

| Version | As a ... | I want to ...                                                          | So that I can ...                                                                                                 |
|-|-|------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| v1.0 | busy student | to know about the news of the day quickly                              | easily find out what is going on a particular day.                                                                |
| v1.0    | non-tech-savvy person | to save the source of the news                                         | go back to them next time.                                                                                  |
| v1.0    | busy student | search based on topics easily                                          | not look through news that I am not concerned with.                                                               |
| v1.0    | student who cares about credibility | be provided information on the reliability and bias of the news sources | judge the trustworthiness of the information and input the correct citations and references in my research paper  |
| v1.0    | busy news consumer | have the system to keep track of the topics I am interested in when the program is first launched  | don't input them every time I am searching for an article of news.                                                |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
