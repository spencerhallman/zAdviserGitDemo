package com.adviser.businessrules.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CWXTSUBCRulesEngineTest {

    private CWXTSUBCRulesEngine engine;

    @BeforeEach
    void setUp() {
        engine = new CWXTSUBCRulesEngine();
    }

    @Test
    void testCalculateSalesCommission() {
        assertEquals(400, engine.calculateSalesCommission(20000));     // 2%
        assertEquals(1600, engine.calculateSalesCommission(40000));    // 4%
        assertEquals(3600, engine.calculateSalesCommission(60000));    // 6%
        assertEquals(6400, engine.calculateSalesCommission(80000));    // 8%
        assertEquals(10000, engine.calculateSalesCommission(100000));  // 10%
        assertEquals(12000, engine.calculateSalesCommission(120000));  // 10%
    }

    @Test
    void testCalculateManagementCommission() {
        assertEquals(2000.0, engine.calculateManagementCommission(100000), 0.01);   // 2.0%
        assertEquals(5000.0, engine.calculateManagementCommission(200000), 0.01);   // 2.5%
        assertEquals(9000.0, engine.calculateManagementCommission(300000), 0.01);   // 3.0%
        assertEquals(14000.0, engine.calculateManagementCommission(400000), 0.01);  // 3.5%
        assertEquals(20000.0, engine.calculateManagementCommission(500000), 0.01);  // 4.0%
        assertEquals(24000.0, engine.calculateManagementCommission(600000), 0.01);  // 4.0%
    }
}
