package com.prokopovich.projectmanagement.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ObjectFormat {

    public static String formattingDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("\ndd.LL.uuuu HH:mm:ss"));
    }
}
