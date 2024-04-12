# Ahmed Shaiyan - Project Portfolio Page

## Project: NewOnTheGo

NewsOnTheGo is a command line interface (CLI) application that helps users stay up-to-date with the latest news
articles by providing users with a list of the current headlines. Users are able to personalise this application by
saving their preferred topics and articles.

<h2> Code Contributed: 
<a target="_blank" href="https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=ahmedshaiyan&breakdown=true">
RepoSense Link
</a>
</h2>



### Enhancements Implemented:
#### Developed the Suggest function to recommend news articles based on user-preferred topics.

**What it does**: The Suggest function reads the user's favorite topics from a saved file and suggests random news articles from those topics. If no favorite topics are saved, it prompts the user to star a topic first.

**Justification:** This feature enhances user engagement by personalizing content suggestions, making the news reading experience more tailored and relevant to individual preferences.

**Highlights:** Implements randomness to ensure that suggestions are varied on each invocation, maintaining user interest over time. The function gracefully handles scenarios where favorite topics might not correspond to available news articles, providing feedback to guide the user.

#### Created the Headlines command to quickly access the headlines of the top news articles.

**What it does**: This command allows users to view a specified number of the most recent headlines from the news articles database, making it easy for users to get updates at a glance.

**Justification:** It provides a concise summary of news, which is useful for users who are short on time but want to stay informed about the latest events.

**Highlights:** Features dynamic input handling that adjusts the number of displayed headlines based on user requests, with robust error handling to manage incorrect inputs or requests beyond the available number of articles.

#### Created a Load function to retrieve and display saved news articles.

**What it does:** The Load function reads news articles that the user has previously saved into a reading list, allowing for easy access and review at any time.

**Justification:** Encourages users to save articles of interest for later reading, enhancing the usability of the application by making it a valuable tool for information retention and management.

**Highlights:** Includes comprehensive error handling to manage scenarios where the saved articles file might be inaccessible or corrupted, ensuring the application remains robust and reliable.

###
#### Implemented the URLs display feature within the daily news command.

**What it does:** This enhancement extracts and displays URLs for all news articles presented by the daily news feature, providing direct links to full articles.

**Justification:** Adds practical value by facilitating easy access to full articles, allowing users to delve deeper into news stories of interest directly from the application.

**Highlights:** Streamlines user interaction by integrating URL display directly within the daily news browsing experience, enhancing functionality without complicating the user interface.
