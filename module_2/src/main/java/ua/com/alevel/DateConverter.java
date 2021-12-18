package ua.com.alevel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    String convert(String inputDate) {
        LocalDate parseDate = null;
        try {
            parseDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (parseDate == null) {
            try {
                parseDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
        if (parseDate == null) {
            try {
                parseDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (parseDate != null)
            return parseDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return "";
    }
}