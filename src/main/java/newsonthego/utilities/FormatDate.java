package newsonthego.utilities;

import newsonthego.NewsOnTheGoExceptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class FormatDate {

    public static final SimpleDateFormat FILE_FORMAT = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);

    // Formats of the dates that the user can pass in
    private static final String[] inputDateFormats = {
        "MM dd yyyy", // 01 02 2024 -> January 2, 2024
        "MMMM dd yyyy", // January 02 2024 -> January 2, 2024
        "dd MMMM yyyy" // 02 January 2024 -> January 2, 2024
    };

    /**
     * Takes in the date input from the user and formats it to be the same as the date format of our articles
     * Returns null if the date format received does not match the ones specified
     *
     * @param date the input from the user
     * @return formated date input from the user
     */
    public static String formatFromUser(String date) {
        String result = null;
        for (String dateFormat : inputDateFormats) {
            SimpleDateFormat targetFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
            try {
                Date formattedDate = targetFormat.parse(date);
                result = FILE_FORMAT.format(formattedDate);
                checkResult(date, result, dateFormat);
                return result;
            } catch (ParseException ignored) {
                // Try another format
            } catch (NewsOnTheGoExceptions e) {
                UI.printError(e.getMessage());
                return null;
            }
        }
        return null;
    }

    /**
     * Check the edge cases for date formatting to ensure that the date input from the user is the valid.
     * Throws an exception if the date formatting is one of the edge cases.
     *
     * @param date is the original date input from the user.
     * @param result is the result after formatting the date input from the user.
     * @param dateFormat is the date format that the user supposedly passed in.
     * @throws NewsOnTheGoExceptions if any part of the input date is not equal to the result.
     */
    private static void checkResult(String date, String result, String dateFormat) throws NewsOnTheGoExceptions{
        String[] dateInputSplit = date.split(" ");
        String[] resultSplit = result.split(" ");
        if (Objects.equals(dateFormat, "dd MMMM yyyy")) {
            checkDayValidity(dateInputSplit[0], resultSplit[1]);
            checkMonthValidity(dateInputSplit[1], dateFormat);
            checkYearValidity(dateInputSplit[2]);
        } else {
            checkMonthValidity(dateInputSplit[0], dateFormat);
            checkDayValidity(dateInputSplit[1], resultSplit[1]);
            checkYearValidity(dateInputSplit[2]);
        }
    }

    /**
     * Checks whether the year input from the user is correct.
     * Throws an exception if it is a negative number or pass 2024.
     *
     * @param inputSplit the year input from the user.
     * @throws NewsOnTheGoExceptions when the year is less than 0 or more than 2024
     */
    private static void checkYearValidity(String inputSplit) throws NewsOnTheGoExceptions{
        if (Integer.parseInt(inputSplit) <= 0) {
            throw new NewsOnTheGoExceptions("Unfortunately, articles did not exist in " + inputSplit);
        }
        if (Integer.parseInt(inputSplit) > 2024) {
            throw new NewsOnTheGoExceptions("Sorry, but we are not time travellers.");
        }
    }

    private static void checkMonthValidity(String inputSplit, String dateFormat)
            throws NewsOnTheGoExceptions{
        if (Objects.equals(dateFormat, "MM dd yyyy")) {
            if (Integer.parseInt(inputSplit) > 12) {
                throw new NewsOnTheGoExceptions("You can't have more than 12 months!");
            }
            if (Integer.parseInt(inputSplit) <= 0 ) {
                throw new NewsOnTheGoExceptions("You can't have less than 1 month!");
            }
        }
    }

    private static void checkDayValidity(String inputSplit, String resultSplit) throws NewsOnTheGoExceptions{
        String resultDay = resultSplit.replace(",", "");
        if (!inputSplit.equals(resultDay)) {
            throw new NewsOnTheGoExceptions("Your day format is incorrect!");
        }
    }
}
