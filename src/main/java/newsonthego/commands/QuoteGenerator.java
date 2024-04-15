package newsonthego.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuoteGenerator {

    public final List<String> quotes;
    private final Random random;

    public QuoteGenerator() {
        quotes = new ArrayList<>();
        random = new Random();

        quotes.add("The only way to do great work is to love what you do. - Steve Jobs");
        quotes.add("In the end, we will remember not the words of our enemies, " +
                "but the silence of our friends. - Martin Luther King Jr.");
        quotes.add("The journey of a thousand miles begins with one step. - Lao Tzu");
        quotes.add("Success is not final, failure is not fatal: It is the courage to " +
                "continue that counts. - Winston Churchill");
        quotes.add("Happiness is not something ready-made. It comes from your own actions. - Dalai Lama");
        quotes.add("You miss 100% of the shots you don't take. - Wayne Gretzky");
        quotes.add("The only limit to our realization of tomorrow is our doubts of today." +
                " - Franklin D. Roosevelt");
        quotes.add("Believe you can and you're halfway there. - Theodore Roosevelt");
        quotes.add("Be yourself; everyone else is already taken. - Oscar Wilde");
        quotes.add("It is never too late to be what you might have been. - George Eliot");
        quotes.add("Do not dwell in the past, do not dream of the future, concentrate the" +
                " mind on the present moment. - Buddha");
        quotes.add("The only impossible journey is the one you never begin. - Tony Robbins");
        quotes.add("I have not failed. I've just found 10,000 ways that won't work. - Thomas Edison");
        quotes.add("A person who never made a mistake never tried anything new. - Albert Einstein");
        quotes.add("The only true wisdom is in knowing you know nothing. - Socrates");
        quotes.add("In the middle of every difficulty lies opportunity. - Albert Einstein");
        quotes.add("Act as if what you do makes a difference. It does. - William James");
        quotes.add("The greatest glory in living lies not in never falling, but in rising " +
                "every time we fall. - Nelson Mandela");
        quotes.add("The only thing we have to fear is fear itself. - Franklin D. Roosevelt");
        quotes.add("The greatest wealth is to live content with little. - Plato");
    }

    /**
     * Randomly selects and returns a quote from the list of quotes.
     *
     * @return A randomly selected quote.
     */
    public String getRandomQuote() {
        // Add a harmless assertion to ensure quotes list is not empty
        assert !quotes.isEmpty() : "Quotes list should not be empty.";

        // Generate a random index between 0 and the size of the quotes list
        int randomIndex = random.nextInt(quotes.size());
        // Return the quote at the random index
        return quotes.get(randomIndex);
    }
}
