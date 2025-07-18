package com.adviser.businessrules.util;

/**
 * Provides utility methods for input data validation.
 * This class is a collection of static methods and is not meant to be instantiated.
 *
 * @author Advisor
 * @version 1.0
 * @since 2024-07-17
 */
public final class ValidationUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private ValidationUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Validates the employee type code.
     *
     * @param type The employee type to validate.
     * @return {@code true} if the type is "H", "S", or "M", {@code false} otherwise.
     */
    public static boolean isValidEmployeeType(String type) {
        return "H".equals(type) || "S".equals(type) || "M".equals(type);
    }

    /**
     * Validates the region number.
     *
     * @param region The region number to validate.
     * @return {@code true} if the region is between 1 and 4 (inclusive), {@code false} otherwise.
     */
    public static boolean isValidRegion(int region) {
        return region >= 1 && region <= 4;
    }
}
