package ru.liapkin.springbootsecondappurfu.util;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DateTimeUtil {

    public static SimpleDateFormat getCustomFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public static int getDaysInYear(int year) {
        return new GregorianCalendar().isLeapYear(year) ? 366 : 365;
    }

}
