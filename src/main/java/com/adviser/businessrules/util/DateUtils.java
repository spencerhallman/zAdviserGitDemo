package com.adviser.businessrules.util;

import java.time.LocalDate;
import java.time.Period;

/**
 * Provides utility methods for common date-related operations.
 * This class is a collection of static methods and is not meant to be instantiated.
 *
 * @author Advisor
 * @version 1.0
 * @since 2024-07-17
 */
public final class DateUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private DateUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Calculates the number of full years of service between a hire date and a run date.
     *
     * @param hireDate The employee's hire date.
     * @param runDate The date of the batch run.
     * @return The total number of completed years of service.
     */
    public static int calculateYearsOfService(LocalDate hireDate, LocalDate runDate) {
        return Period.between(hireDate, runDate).getYears();
    }

    /**
     * Checks if a given date is the last day of its month.
     *
     * @param date The date to check.
     * @return {@code true} if the date is the last day of the month, {@code false} otherwise.
     */
    public static boolean isEndOfMonth(LocalDate date) {
        return date.getDayOfMonth() == date.lengthOfMonth();
    }
}
