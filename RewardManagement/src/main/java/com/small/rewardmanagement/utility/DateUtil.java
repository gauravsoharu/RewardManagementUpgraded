package com.small.rewardmanagement.utility;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    public static DateRange getDateRangeForMonth(String month) {
        Month monthEnum = Month.valueOf(month.toUpperCase());
        LocalDate start = LocalDate.of(2025, monthEnum, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return new DateRange(start, end);
    }

    public static String getMonthName(int month) {
        return Month.of(month).name();
    }

    public static record DateRange(LocalDate startDate, LocalDate endDate) {}
}
