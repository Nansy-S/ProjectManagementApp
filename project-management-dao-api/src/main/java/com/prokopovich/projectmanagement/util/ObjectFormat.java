package com.prokopovich.projectmanagement.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ObjectFormat {

    public static String formattingDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd.LL.uuuu HH:mm:ss"));
    }

    public static LocalDateTime formattingInputDateTime(String dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", Locale.UK);
        LocalDateTime newDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
        return newDateTime;
    }
}
