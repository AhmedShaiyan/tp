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

    @Test
    void PrintsCorrectHeadlines() {
        ShowHeadlinesCommand.showHeadlines("headlines 3");
        String output = outputStreamCaptor.toString();
        String[] lines = output.split(System.lineSeparator());


        assertTrue(output.contains("Displaying the first 3 article headlines:"),
                "Should indicate it's displaying 3 headlines");
        assertTrue(output.contains("Scientists Discover New Species of Butterfly in the Amazon"),
                "Should display the first headline");
        assertTrue(output.contains("Stock Market Surges to Record Highs Amid Economic Recovery"),
                "Should display the second headline");
        assertTrue(output.contains("Political Tensions Rise in Region X Following Border Dispute"),
                "Should display the third headline");
    }



    @Test
    void PrintsTooHighMessage() {
        ShowHeadlinesCommand.showHeadlines("headlines 7000");
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Invalid index, too high."), "Should indicate the index is too high");
    }

    @Test
    void PrintsFormatErrorMessage() {
        ShowHeadlinesCommand.showHeadlines("headlines quoacamole");
        String output = outputStreamCaptor.toString().trim();
        assertEquals("Please provide a valid number for the number of articles.", output);
    }




}
