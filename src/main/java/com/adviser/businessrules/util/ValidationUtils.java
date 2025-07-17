package com.adviser.businessrules.util;

public class ValidationUtils {

    public static boolean isValidEmployeeType(String type) {
        return "H".equals(type) || "S".equals(type) || "M".equals(type);
    }

    public static boolean isValidRegion(int region) {
        return region >= 1 && region <= 4;
    }
}
