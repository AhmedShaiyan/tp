package newsonthego.commands;

import newsonthego.NewsTopic;


import static newsonthego.NewsOnTheGo.newsTopics;

public class TopicShowCommand {

    public static void showTopics() {
        System.out.println("Here are the list of topics for your viewing:");
        for (NewsTopic topic : newsTopics) {
            System.out.println(" - " + topic.getTopicName());
        }
    }
}
