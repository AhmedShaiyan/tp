package newsonthego.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowHeadlinesCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(outputStreamCaptor));


    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);

    }

    //        @Test
    //        void printsCorrectHeadlines() {
    //            ShowHeadlinesCommand.showHeadlines("headlines 3");
    //            String output = outputStreamCaptor.toString();
    //            String[] lines = output.split(System.lineSeparator());
    //
    //
    //            assertTrue(output.contains("Displaying the first 3 article headlines:"),
    //                    "Should indicate it's displaying 3 headlines");
    //            assertTrue(output.contains("Kristen Wiig initiated into "SNL" five-timers
    //            club by Ryan Gosling, Matt Damon and? Lorne Michaels | CNN"),
    //                    "Should display the first headline");
    //            assertTrue(output.contains("?The Matrix? has a fifth film in the works and, no,
    //            this is not a simulation | CNN"),
    //                    "Should display the second headline");
    //            assertTrue(output.contains("?Dune: Part Two? may be followed by a third
    //            film, but Timoth?e Chalamet and Zendaya don?t know how it all ends | CNN"),
    //                    "Should display the third headline");
    //        }



    @Test
    void printsTooHighMessage() {
        ShowHeadlinesCommand.showHeadlines("headlines 7000");
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Invalid index, too high."), "Should indicate the index is too high");
    }

    @Test
    void printsFormatErrorMessage() {
        ShowHeadlinesCommand.showHeadlines("headlines quoacamole");
        String output = outputStreamCaptor.toString().trim();
        assertEquals("Please provide a valid number for the number of articles.", output);
    }




}
