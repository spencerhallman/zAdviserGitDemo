package com.adviser.businessrules.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CWXTDATERulesEngineTest {

    private CWXTDATERulesEngine engine;

    @BeforeEach
    void setUp() {
        engine = new CWXTDATERulesEngine();
    }

    @Test
    void testCalculateYearsOfService() {
        LocalDate runDate = LocalDate.of(2023, 10, 26);
        assertEquals(5, engine.calculateYearsOfService(LocalDate.of(2018, 10, 25), runDate));
        assertEquals(4, engine.calculateYearsOfService(LocalDate.of(2018, 10, 27), runDate));
        assertEquals(5, engine.calculateYearsOfService(LocalDate.of(2018, 9, 1), runDate));
        assertEquals(4, engine.calculateYearsOfService(LocalDate.of(2018, 11, 1), runDate));
    }

    @Test
    void testIsEndOfMonth() {
        assertTrue(new CWXTDATERulesEngine().isEndOfMonth(LocalDate.of(2023, 1, 31)));
        assertFalse(new CWXTDATERulesEngine().isEndOfMonth(LocalDate.of(2023, 2, 27)));
        assertTrue(new CWXTDATERulesEngine().isEndOfMonth(LocalDate.of(2023, 2, 28)));
        assertTrue(new CWXTDATERulesEngine().isEndOfMonth(LocalDate.of(2024, 2, 29))); // Leap year
    }

    @Test
    void testEOMCheckIsOnlyPerformedOnce() {
        assertTrue(engine.isEndOfMonth(LocalDate.of(2023, 1, 31)));
        // This second call should return the cached value and not re-calculate
        assertTrue(engine.isEndOfMonth(LocalDate.of(2023, 5, 5)));
    }
}
