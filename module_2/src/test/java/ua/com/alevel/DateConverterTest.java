package ua.com.alevel;

import ua.com.alevel.DateConverter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateConverterTest {

    @org.junit.jupiter.api.Test
    void convertTest() {
        final ua.com.alevel.DateConverter dateConverter = new ua.com.alevel.DateConverter();
        var dates = List.of("2020/04/05", "05/04/2020", "04-05-2020");
        var result = "20200405";
        dates.forEach(date -> {
            final String convert = dateConverter.convert(date);
            assertEquals(result, convert);
        });
    }
}