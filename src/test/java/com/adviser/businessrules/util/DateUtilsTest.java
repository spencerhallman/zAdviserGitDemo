package com.adviser.businessrules.util;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void testCalculateYearsOfService() {
        LocalDate runDate = LocalDate.of(2023, 10, 26);
        assertEquals(5, DateUtils.calculateYearsOfService(LocalDate.of(2018, 10, 25), runDate));
        assertEquals(4, DateUtils.calculateYearsOfService(LocalDate.of(2018, 10, 27), runDate));
    }

    @Test
    void testIsEndOfMonth() {
        assertTrue(DateUtils.isEndOfMonth(LocalDate.of(2023, 1, 31)));
        assertFalse(DateUtils.isEndOfMonth(LocalDate.of(2023, 2, 27)));
        assertTrue(DateUtils.isEndOfMonth(LocalDate.of(2023, 2, 28)));
        assertTrue(DateUtils.isEndOfMonth(LocalDate.of(2024, 2, 29))); // Leap year
        assertFalse(DateUtils.isEndOfMonth(LocalDate.of(2023, 12, 30)));
        assertTrue(DateUtils.isEndOfMonth(LocalDate.of(2023, 12, 31)));
    }
}
