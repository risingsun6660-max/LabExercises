
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateUtils {
    
    // Date format patterns
    private static final String DD_MM_YYYY = "dd-MM-yyyy";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String DD_MM_YY = "dd-MM-yy";
    private static final String MM_DD_YYYY = "MM-dd-yyyy";
    private static final String YYYY_DD_MM = "yyyy-dd-MM";
    
    /**
     * Converts date from dd-MM-yyyy format to yyyy-MM-dd format
     * @param dateString Date in dd-MM-yyyy format (e.g., "25-12-2023")
     * @return Date in yyyy-MM-dd format (e.g., "2023-12-25")
     * @throws IllegalArgumentException if date format is invalid
     */
    public String convertDdMmYyyyToYyyyMmDd(String dateString) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(DD_MM_YYYY);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
            LocalDate date = LocalDate.parse(dateString, inputFormatter);
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected dd-MM-yyyy (e.g., 25-12-2023)");
        }
    }
    
    /**
     * Converts date from yyyy-MM-dd format to dd-MM-yyyy format
     * @param dateString Date in yyyy-MM-dd format (e.g., "2023-12-25")
     * @return Date in dd-MM-yyyy format (e.g., "25-12-2023")
     * @throws IllegalArgumentException if date format is invalid
     */
    public static String convertYyyyMmDdToDdMmYyyy(String dateString) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(DD_MM_YYYY);
            LocalDate date = LocalDate.parse(dateString, inputFormatter);
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd (e.g., 2023-12-25)");
        }
    }
    
    /**
     * Converts date from MM-dd-yyyy format to yyyy-MM-dd format
     * @param dateString Date in MM-dd-yyyy format (e.g., "12-25-2023")
     * @return Date in yyyy-MM-dd format (e.g., "2023-12-25")
     * @throws IllegalArgumentException if date format is invalid
     */
    public static String convertMmDdYyyyToYyyyMmDd(String dateString) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(MM_DD_YYYY);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
            LocalDate date = LocalDate.parse(dateString, inputFormatter);
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected MM-dd-yyyy (e.g., 12-25-2023)");
        }
    }
    
    /**
     * Generic date converter between different formats
     * @param dateString Input date string
     * @param inputFormat Input date format pattern
     * @param outputFormat Output date format pattern
     * @return Converted date string
     * @throws IllegalArgumentException if conversion fails
     */
    public static String convertDateFormat(String dateString, String inputFormat, String outputFormat) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
            LocalDate date = LocalDate.parse(dateString, inputFormatter);
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format or date string");
        }
    }
    
    /**
     * Validates if a date string matches the expected format
     * @param dateString Date string to validate
     * @param format Expected format pattern
     * @return true if valid, false otherwise
     */
    public static boolean isValidDate(String dateString, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /**
     * Gets current date in specified format
     * @param format Date format pattern
     * @return Current date in specified format
     */
    public static String getCurrentDate(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.now().format(formatter);
    }
    
    /**
     * Gets current date in dd-MM-yyyy format
     * @return Current date in dd-MM-yyyy format
     */
    public static String getCurrentDateDdMmYyyy() {
        return getCurrentDate(DD_MM_YYYY);
    }
    
    /**
     * Gets current date in yyyy-MM-dd format
     * @return Current date in yyyy-MM-dd format
     */
    public static String getCurrentDateYyyyMmDd() {
        return getCurrentDate(YYYY_MM_DD);
    }
    
    /**
     * Calculates age based on birth date in dd-MM-yyyy format
     * @param birthDate Birth date in dd-MM-yyyy format
     * @return Age in years
     * @throws IllegalArgumentException if date format is invalid
     */
    public static int calculateAge(String birthDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DD_MM_YYYY);
            LocalDate birth = LocalDate.parse(birthDate, formatter);
            LocalDate now = LocalDate.now();
            
            int age = now.getYear() - birth.getYear();
            if (now.getDayOfYear() < birth.getDayOfYear()) {
                age--;
            }
            return age;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid birth date format. Expected dd-MM-yyyy");
        }
    }
    
    /**
     * Adds days to a date in dd-MM-yyyy format
     * @param dateString Date in dd-MM-yyyy format
     * @param days Number of days to add (can be negative)
     * @return New date in dd-MM-yyyy format
     * @throws IllegalArgumentException if date format is invalid
     */
    public static String addDays(String dateString, int days) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DD_MM_YYYY);
            LocalDate date = LocalDate.parse(dateString, formatter);
            LocalDate newDate = date.plusDays(days);
            return newDate.format(formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected dd-MM-yyyy");
        }
    }
    
    /**
     * Calculates difference in days between two dates in dd-MM-yyyy format
     * @param startDate Start date in dd-MM-yyyy format
     * @param endDate End date in dd-MM-yyyy format
     * @return Number of days between dates (positive if endDate is after startDate)
     * @throws IllegalArgumentException if date format is invalid
     */
    public static long daysBetween(String startDate, String endDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DD_MM_YYYY);
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            return java.time.temporal.ChronoUnit.DAYS.between(start, end);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected dd-MM-yyyy");
        }
    }
}