package com.adviser.businessrules.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    @Test
    void testIsValidEmployeeType() {
        assertTrue(ValidationUtils.isValidEmployeeType("H"));
        assertTrue(ValidationUtils.isValidEmployeeType("S"));
        assertTrue(ValidationUtils.isValidEmployeeType("M"));
        assertFalse(ValidationUtils.isValidEmployeeType("X"));
        assertFalse(ValidationUtils.isValidEmployeeType("h"));
        assertFalse(ValidationUtils.isValidEmployeeType(null));
    }

    @Test
    void testIsValidRegion() {
        assertTrue(ValidationUtils.isValidRegion(1));
        assertTrue(ValidationUtils.isValidRegion(4));
        assertFalse(ValidationUtils.isValidRegion(0));
        assertFalse(ValidationUtils.isValidRegion(5));
    }
}
