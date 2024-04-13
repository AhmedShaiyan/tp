# NewsOnTheGo User Guide

NewsOnTheGo is a command-line application (CLI) that helps users stay up-to-date with the latest news articles by 
providing users with a list of up-to-date articles. 

Users are able to personalise this application by saving their
preferred topics and articles.

There are other features, as described below, that further enhance the user experience and convenience
of this application.

This guide will help you get started with using NewsOnTheGo and understand its features.



# Table of Content

<ol type="1" style="font-size: large">
    <li><a href="#quick-start" >Quick Start</a></li>
    <li><a href="#features">Features</a>
        <ol type="1" style="font-size: medium">
            <li><code><a href="#help">help</a></code></li>
            <li>
                <code><a href="#daily">daily</a></code>
                <ol style="font-size: small">
                    <li><code><a href="#daily-save">save</a></code></li>
                    <li><code><a href="#daily-back">back</a></code></li>
                </ol>
            </li>
            <li><code><a href="#topics">topics</a></code></li>
            <li><code><a href="#star">star</a></code></li>
            <li><code><a href="#starred">starred</a></code></li>
            <li><code><a href="#remove">remove</a></code></li>
            <li><code><a href="#suggest">suggest</a></code></li>
            <li><code><a href="#filter">filter</a></code></li>
            <li><code><a href="#save">save</a></code></li>
            <li><code><a href="#source">source</a></code></li>
            <li><code><a href="#back">back</a></code></li>
            <li><code><a href="#url">url</a></code></li>
            <li><code><a href="#headlines">headlines</a></code></li>
            <li><code><a href="#get">get</a></code></li>
            <li><code><a href="#load">load</a></code></li>
            <li><code><a href="#clear">clear</a></code></li>
            <li><code><a href="#bye">bye</a></code></li>
        </ol>
    </li>
    <li><a href="#known-issues">Known Issues</a></li>
    <li><a href="#command-summary">Command Summary</a></li>
</ol>


<h2 id="quick-start"> Quick Start </h2>

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest version of `newsonthego.jar` from [the release page](https://github.com/AY2324S2-CS2113-T12-1/tp/releases/tag/v2.0).
3. Copy the file to the folder you want to use as the home folder for NewsOnTheGo.
4. Open a command terminal, `cd` into the folder where you put the jar file in.
5. Run the command `java -jar newsonthego.jar` to start the application.
6. If you are running the application for the first time, please wait for some time as 
our Article Scraper is hard at work.


The following interface should appear in a few seconds:
```
Hello from
__________________________________________________________________________________________
    _     _                           __             ______                     __
    /|   /                          /    )             /      /               /    )
---/-| -/-----__----------__-------/----/----__-------/------/__----__-------/---------__-
  /  | /    /___)| /| /  (_ `     /    /   /   )     /      /   ) /___)     /  --,   /   )
_/___|/____(___ _|/_|/__(__)_____(____/___/___/_____/______/___/_(___ _____(____/___(___/_


What is your name?
```
6. Type in your name to initialise the system.
The following interface should appear:
```
Hello [name]
What do you want from me?
```
7. Type the command in the command box and press Enter to execute it.
8. Refer to the [Features](#Features) section below for details of each command.


<h2 id="features"> Features </h2>

> [!NOTE]
> Regarding the command format:
> 
> - Words in `{Curly Braces}` are parameters to be supplied by the user.
> 
>   e.g. in `filter {TOPIC}`, `TOPIC` is a parameter which can be used as `filter sports`.
>   - more information on the format of input parameters can be found under each specific command
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `topics`, `bye`) will be ignored.
>   
>   e.g. if the command specifies `help 123`, it will be interpreted as `help`.

<br>

<h3 id="help"> Getting Help: `help` </h3>
Shows all the commands available and their brief description.

Format: `help`

```
What do you want from me?
____________________________________________________________

help
____________________________________________________________

Here is a list of commands and functions for your reference:
+------------+----------------------------------------------------------+---------------------------------+
| Command    | Description                                                         | Example              |
+------------+----------------------------------------------------------+---------------------------------+
| `DAILY`    | Gives articles published on a specific date.                        | `daily 10 March 2024`|
| `TOPICS`   | Lists topics the articles are classified by.                        | `topics`             |
| `STAR`     | Bookmarks a topic as favorite.                                      | `star business`      |
| `STARRED`  | Shows list of bookmarked topics.                                    | `starred`            |
| `REMOVE`   | Removes a topic from bookmarked list.                               | `remove business`    |
| `SUGGEST`  | Suggests articles based on bookmarked topics.                       | `suggest`            |
| `FILTER`   | Lists articles related to a specified topic.                        | `filter politics`    |
| `SAVE`     | Saves article into a reading list.                                  | `save 3`             |
| `SOURCE`   | Gives the source of the article.                                    | `source 3`           |
| `BACK`     | Returns from daily or filter parser.                                | `back`               |
| `URL`      | Gives the URL of the article.                                       | `url 2`              |
| `HEADLINES`| Returns article headlines from the list up til the index            | `headlines 10`       |
| `GET`      | Details of a specific article.                                      | `get 3`              |
| `LOAD`     | Displays list of saved articles.                                    | `load`               |
| `CLEAR`    | Clears the saved articles list.                                     | `clear`              |
| `QUOTE`    | Prints a different inspirational quote each time.                   | `quote`              |
+------------+---------------------------------------------------------+----------------------------------+
Thank you for using News On The Go! Enjoy reading :))
____________________________________________________________

```


<h3 id="daily"> Find articles on a particular day: <code>daily</code> </h3>
Gets the list of articles from our text file and outputs it to the user.

<br>

Format: `daily {DATE}`

> [!IMPORTANT]
> `DATE` format should be as follows:
> - `MM dd yyyy`
> - `MMMM dd yyyy`
> - `dd MMMM yyyy`
> 
> The specified month is not case-sensitive, e.g. `March` and `march` will both be read the same given 
> the input date is valid.

#### Example of usage:

* `daily 03 10 2024`

* `daily March 10 2024`

* `daily 10 March 2024`


```
What do you want from me?
____________________________________________________________

daily 03 10 2024
____________________________________________________________

Sure! Here are the headlines for today (March 10, 2024) :

    1: "Scientists Discover New Species of Butterfly in the Amazon"
    URL: https://www.example.com/science/butterfly-discovery

____________________________________________________________

What do you want from me?
To return to main, type in: back
```

<br>

<h3>After using the <code>daily</code> command, you can use <code>save</code> and <code>back</code> commands.</h3>

<br>

<h3 id="daily-save"> Save articles from the daily function: <code>save</code> </h3>

Format: `save {INDEX OF ARTICLE}`  
This allows the user to save the news articles displayed by the `daily` function.  

#### Example of usage:
`save 1`.  

```
What do you want from me?
To return to main, type in: back
save 1
____________________________________________________________

Successfully saved "Scientists Discover New Species of Butterfly in the Amazon"
find your saved articles at user_data\saved_news.txt
____________________________________________________________

What do you want from me?
To return to main, type in: back
```

<br>

<h3 id="daily-back"> Command to quit the daily function parser: <code>back</code> </h3>

Format: `back`
Use command to exit the daily function after done saving their desired articles.  

#### Example of usage: `back`  

```
What do you want from me?
To return to main, type in: back
back
You are back to the main function!
____________________________________________________________
```

<br>

<h3 id="topics"> Star a topic: <code>topics</code> </h3>

List the different topics our articles are classified into.

Format: `topics`

#### Example of usage:

```
What do you want from me?
____________________________________________________________

topics
____________________________________________________________

Here are the list of topics for your viewing:
- Art
- Business
- Community
- Culture
- Economy
- Education
- Entertainment
- Environment
- Fashion
- Finance
- Food & Drink
- Health
- History
- Politics
- Science
- Space
- Space Exploration
- Sports
- Technology
____________________________________________________________
```

<br>

<h3 id="star"> Star a topic: <code>star</code> </h3>

Stars a topic to add it to your list of favorite topics.

Format: `star {TOPIC}`

#### Example of usage:
- `star Science`  
- `star Sports`

```
What do you want from me?
____________________________________________________________

star technology
____________________________________________________________

technology has been added to your list of favourite topics.
____________________________________________________________
```

<br>

<h3 id="starred"> Star a topic: <code>starred</code> </h3>

List the different topics our articles are classified into.

Format: `starred`

#### Example of usage:

```
What do you want from me?
____________________________________________________________

starred
____________________________________________________________

Here is the list of your favourite topics:
- Science
- Technology
____________________________________________________________
```

<br>

<h3 id="remove"> Remove a topic: <code>remove</code> </h3>

Removes a topic from your list of favorite topics.

Format: `remove {TOPIC}`

#### Example of usage:
- `remove Science`
- `remove Sports`

```
What do you want from me?
____________________________________________________________

remove technology
____________________________________________________________

technology has been removed from your list of favourite topics
____________________________________________________________
```

<br>

<h3 id="suggest"> Suggesting articles: <code>suggest</code> </h3>

Suggests articles from your favorite topics.


Format: `suggest`  

#### Example of usage:  
 Assuming we have Science and Technology as our favourite topics.

```
What do you want from me?
____________________________________________________________

suggest
____________________________________________________________

1. Suggesting an article from your favorite topic: Science
   Title: "Rare Lunar Phenomenon Captivates Skygazers Worldwide"
   URL: https://www.example.com/science/lunar-phenomenon
2. Suggesting an article from your favorite topic: Technology
   Title: "Technology Company Faces Backlash Over Data Privacy Concerns"
   URL: https://www.example.com/technology/data-privacy-backlash

____________________________________________________________
```

<br>

<h3 id="filter"> Searching for articles on a particular topic: <code>filter</code> </h3>
Find articles based on the topic input from the user.

Format: `filter {TOPIC}`

#### Example of usage:
- `filter Science`
- `filter Sports`

```
What do you want from me?
____________________________________________________________

filter technology
____________________________________________________________

Here are the news articles related to Technology:
1. "Tech Giants Announce Partnership to Combat Online Misinformation"
2. "Artificial Intelligence Breakthrough Promises Revolution in Healthcare"
3. "Technology Expo Showcases Latest Innovations in Robotics"
4. "Major Tech Company Announces Plans for Sustainable Data Centers"
5. "Technology Company Faces Backlash Over Data Privacy Concerns"
____________________________________________________________

You are currently in access to the list of articles in Technology,
use command 'BACK' to return to main list of articles.
____________________________________________________________
```

<br>

### After using the `filter` command, you are able to use the `save`, `source` and `back` commands.

<br>

<h3 id="save"> Saving articles from the filter command: <code>save</code> </h3>

Saves the article title from the `filter` command into a text file.

> [NOTE!] 
> Do not be confused with the other `save` function in `daily`.  

Format: `save {INDEX OF ARTICLE ON DISPLAYED LIST}`

#### Example of usage: 
- `save 1`
  - saves the first item in the current article list
- `save 5`  
  - saves the fifth item in the current article list

Assuming we have the used `filter technology` from before.  

```
What do you want from me?
____________________________________________________________

save 2
____________________________________________________________

Successfully saved "Artificial Intelligence Breakthrough Promises Revolution in Healthcare"
find your saved articles at user_data\saved_news.txt
____________________________________________________________
```

<br>

<h3 id="source"> Get source of article: <code>source</code> </h3>
Displays the source of the article and its APA citation.

#### Format: `source {INDEX OF ARTICLE ON DISPLAYED LIST}`

### Example of usage:
- `source 1`
- `source 5`

```
What do you want from me?
____________________________________________________________

source 4
____________________________________________________________

"Major Tech Company Announces Plans for Sustainable Data Centers"
URL: https://www.example.com/technology/sustainable-data-centers
By: Jonathan Taylor    On: April 8, 2024
Tech News Network

APA Citation: Taylor, J. (2024, April 8). "Major Tech Company Announces Plans for Sustainable Data Centers". Tech News Network. https://www.example.com/technology/sustainable-data-centers
____________________________________________________________
```

<br>

<h3 id="back"> Returning to the previous menu or list: <code>back</code> </h3>
Returns from a filtered list of news or the saved news window to the main list of articles.

#### Format: `back`

* Only applicable if you have filtered the list of news using the `filter` command or used the `save` command to
  save an article.

#### Example of usage:

```
What do you want from me?
____________________________________________________________

back
____________________________________________________________

You have exited the list of articles in Technology
Currently in access to the main list of articles
____________________________________________________________
```

<br>

<h3 id="url"> Star a topic: <code>url</code> </h3>

Retrieves the URL of a specific article.

Format: `url {INDEX OF ARTICLE}`

#### Example of usage:
- `url 1`
- `url 2`

```
What do you want from me?
____________________________________________________________

url 2
____________________________________________________________

Article URL: https://www.example.com/finance/stock-market-record
____________________________________________________________
```

<br>

<h3 id="headlines"> Star a topic: <code>headlines</code> </h3>

Retrieves the headlines of articles from the list of articles until the index specified by the user.

Format: `headlines {INDEX OF ARTICLE}`

#### Example of usage:
- `headlines 2`
- `headlines 10`

```
What do you want from me?
____________________________________________________________

headlines 10
Displaying the first 10 article headlines:
1. "Scientists Discover New Species of Butterfly in the Amazon"
2. "Stock Market Surges to Record Highs Amid Economic Recovery"
3. "Political Tensions Rise in Region X Following Border Dispute"
4. "Breakthrough in Cancer Research Offers Hope for New Treatment"
5. "Tech Giants Announce Partnership to Combat Online Misinformation"
6. "Local Community Comes Together to Support Homeless Shelter"
7. "Climate Change Summit Ends with Disappointing Agreement"
8. "New Study Reveals Alarming Decline in Bee Populations"
9. "Celebrity Couple's Divorce Shocks Fans Worldwide"
10. "Space Agency Plans Mission to Explore Jupiter's Moons"
    What do you want from me?
____________________________________________________________
```

<br>

<h3 id="get"> Star a topic: <code>get</code> </h3>

Retrieves the details of a specific article.

Format: `get {INDEX OF ARTICLE}`

#### Example of usage:
- `get 3`
- `get 5`

```
get 3
____________________________________________________________

"Political Tensions Rise in Region X Following Border Dispute"
URL: https://www.example.com/politics/border-dispute
By: Samantha Lee    On: March 12, 2024
The Guardian

____________________________________________________________

```
<br>

<h3 id="load"> Loading saved news: <code>load</code> </h3>
Displays all saved news articles from the saved articles text file.

Format: `load`  

#### Example of usage:

```
What do you want from me?
____________________________________________________________

load
____________________________________________________________

Displaying saved news articles:

    "Scientists Discover New Species of Butterfly in the Amazon"
        URL: https://www.example.com/science/butterfly-discovery
        By: John Smith    On: March 10, 2024
        Nature News
    
    "Artificial Intelligence Breakthrough Promises Revolution in Healthcare"
        URL: https://www.example.com/technology/ai-healthcare-revolution
        By: Daniel Brown    On: March 21, 2024
        Technology Today

____________________________________________________________
```

<br>

<h3 id="clear"> Clear articles: <code>clear</code> </h3>

Clears the list of articles saved.

Format: `clear`

#### Example of usage:

```
What do you want from me?
____________________________________________________________

clear
____________________________________________________________

File cleared successfully!
____________________________________________________________
```

<br>

<h3 id="clear"> Print a quote: <code>quote</code> </h3>

Prints a random inspirational quote each time the command is called

Format: `quote`

#### Example of usage:

```
What do you want from me?
______________________________________________________________________

quote
_______________________________________________________________________

Some words of inpiration today: 

         (-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~)
         | Be yourself; everyone else is already taken. - Oscar Wilde |
         (-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~)
________________________________________________________________________
```

<br>

<h3 id="bye"> Exiting the program: <code>bye</code> </h3>

Exits the application.

Format: `bye`

#### Example of usage:

```
What do you want from me?
____________________________________________________________

bye
Bye. Hope to see you again soon!
____________________________________________________________
```

<br>

<h2 id="known-issues"> Known Issues </h2>
1. When keying in floating point values for the date in `daily` function, the parser is unable to parse in the daily 
function. Take note by keying in integers only. Make sure the integer do not exceed the size of an integer too.

<br>

<h2 id="command-summary"> Command Summary </h2>

| Commands      | Format, Examples                            |
|---------------|---------------------------------------------|
| **HELP**      | `help`                                      |
| **DAILY**     | `daily {DATE}`<br/>eg.`daily March 10 2024` |
| **TOPICS**    | `topics`                                    |
| **STAR**      | `star {TOPIC}`<br/>eg.`star Technology`     |
| **STARRED**   | `starred`                                   |
| **REMOVE**    | `remove {TOPIC}`<br/>eg.`remove Technology` |
| **SUGGEST**   | `suggest`                                   |
| **FILTER**    | `filter {TOPIC}`<br/>eg.`filter health`     |
| **SAVE**      | `save {INDEX}`<br/>eg.`save 1`              |
| **SOURCE**    | `source {INDEX}`<br/>eg.`source 1`          |
| **BACK**      | `back`                                      |
| **URL**       | `url {INDEX}`<br/>eg.`url 2`                |
| **HEADLINES** | `headlines {INDEX}`<br/>eg.`headlines 10`   |
| **GET**       | `get {INDEX}`<br/>eg.`get 3`                |
| **LOAD**      | `load`                                      |
| **CLEAR**     | `clear`                                     |
| **QUOTE**     | `quote`                                     |
| **BYE**       | `bye`                                       |
