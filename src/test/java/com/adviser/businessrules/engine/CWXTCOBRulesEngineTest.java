package com.adviser.businessrules.engine;

import com.adviser.businessrules.model.InputData;
import com.adviser.businessrules.model.ReportData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CWXTCOBRulesEngineTest {

    private CWXTCOBRulesEngine engine;
    private ReportData reportData;

    @BeforeEach
    void setUp() {
        engine = new CWXTCOBRulesEngine();
        reportData = new ReportData();
    }

    @Test
    void testProcessHourlyEmployee_RegularHours() {
        InputData data = new InputData("Test Hourly", 1, "H", LocalDate.now(), 40, 20);
        engine.processEmployee(data, reportData);
        assertEquals(1, reportData.getResults().size());
        assertEquals(800, reportData.getResults().get(0).getCompensation());
    }

    @Test
    void testProcessHourlyEmployee_Overtime() {
        InputData data = new InputData("Test Hourly OT", 1, "H", LocalDate.now(), 50, 20);
        engine.processEmployee(data, reportData);
        assertEquals(1, reportData.getResults().size());
        assertEquals(1100, reportData.getResults().get(0).getCompensation()); // 40*20 + 10*30
    }

    @Test
    void testProcessSalesEmployee_WithSales() {
        InputData data = new InputData("Test Sales", 2, "S", LocalDate.now(), 50000, 30000);
        engine.processEmployee(data, reportData);
        assertEquals(1, reportData.getResults().size());
        assertEquals(33000, reportData.getResults().get(0).getCompensation()); // 30000 + 50000 * 0.06
        assertEquals(50000, reportData.getTotalSalesSouth());
    }

    @Test
    void testProcessSalesEmployee_ZeroSales() {
        InputData data = new InputData("Test Sales Zero", 3, "S", LocalDate.now(), 0, 30000);
        engine.processEmployee(data, reportData);
        assertEquals(1, reportData.getResults().size());
        assertEquals(30000, reportData.getResults().get(0).getCompensation());
        assertNotNull(reportData.getResults().get(0).getComment());
        assertEquals("UH-OH", reportData.getResults().get(0).getComment());
    }

    @Test
    void testProcessManagementEmployee() {
        InputData data = new InputData("Test Manager", 4, "M", LocalDate.now(), 90000);
        engine.processEmployee(data, reportData);
        assertEquals(0, reportData.getResults().size()); // Managers don't have compensation results in this part
        assertEquals("Test Manager", reportData.getManagerWest());
        assertEquals(90000, reportData.getManagerSalaryWest());
    }

    @Test
    void testInvalidEmployeeType() {
        InputData data = new InputData("Invalid Type", 1, "X", LocalDate.now(), 1, 1);
        engine.processEmployee(data, reportData);
        assertEquals(0, reportData.getResults().size());
        assertEquals(1, reportData.getValidationMessages().size());
        assertEquals("INVALID EMPLOYEE TYPE: X for Invalid Type", reportData.getValidationMessages().get(0));
    }

    @Test
    void testInvalidRegion() {
        InputData data = new InputData("Invalid Region", 5, "H", LocalDate.now(), 40, 20);
        engine.processEmployee(data, reportData);
        assertEquals(0, reportData.getResults().size());
        assertEquals(1, reportData.getValidationMessages().size());
        assertEquals("INVALID REGION: 5 for Invalid Region", reportData.getValidationMessages().get(0));
    }
}
