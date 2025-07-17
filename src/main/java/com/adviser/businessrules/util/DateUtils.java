package com.adviser.businessrules.util;

import java.time.LocalDate;
import java.time.Period;

public class DateUtils {

    public static int calculateYearsOfService(LocalDate hireDate, LocalDate runDate) {
        return Period.between(hireDate, runDate).getYears();
    }

    public static boolean isEndOfMonth(LocalDate date) {
        return date.getDayOfMonth() == date.lengthOfMonth();
    }
}
