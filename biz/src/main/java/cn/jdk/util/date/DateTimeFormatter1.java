package cn.jdk.util.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by leslie on 2020/11/30.
 */
public class DateTimeFormatter1 {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parse(String target) {
        return LocalDate.parse(target, DATE_TIME_FORMATTER);
    }

    public static String format(LocalDate target) {
        return target.format(DATE_TIME_FORMATTER);
    }
}
