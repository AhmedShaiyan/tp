package newsonthego.utilities;

import newsonthego.NewsOnTheGoExceptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class FormatDate {

    private static final String[] inputDateFormats = {
        "MM dd yyyy", // 01 02 2024 -> January 2, 2024
        "MMMM dd yyyy", // January 02 2024 -> January 2, 2024
        "dd MMMM yyyy" // 02 January 2024 -> January 2, 2024
    };

    private static final SimpleDateFormat FILE_FORMAT = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);

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

    private static void checkYearValidity(String inputSplit) throws NewsOnTheGoExceptions{
        if (Integer.parseInt(inputSplit) < 0) {
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
