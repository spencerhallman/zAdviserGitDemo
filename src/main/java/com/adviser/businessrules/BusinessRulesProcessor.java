package com.adviser.businessrules;

import com.adviser.businessrules.engine.CWXTCOBRulesEngine;
import com.adviser.businessrules.engine.CWXTDATERulesEngine;
import com.adviser.businessrules.model.InputData;
import com.adviser.businessrules.model.ReportData;
import com.adviser.businessrules.report.ReportGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The main entry point for the Java Batch Application.
 * This class orchestrates the entire process of reading data, applying business rules,
 * and generating the final report.
 *
 * @author Advisor
 * @version 1.0
 * @since 2024-07-17
 */
public class BusinessRulesProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BusinessRulesProcessor.class);

    /**
     * The main method that drives the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        logger.info("Business Rules Processor Started.");

        // 1. Load Data
        List<InputData> sampleData = createSampleData();

        // 2. Initialize Engines and Data Containers
        ReportData reportData = new ReportData();
        CWXTCOBRulesEngine cobolEngine = new CWXTCOBRulesEngine();
        CWXTDATERulesEngine dateEngine = new CWXTDATERulesEngine();
        LocalDate runDate = LocalDate.of(2023, 2, 28); // Using a fixed date for consistent testing

        // 3. Process each record
        for (InputData data : sampleData) {
            cobolEngine.processEmployee(data, reportData);
            // Example of how years of service could be used if needed:
            // int yearsOfService = dateEngine.calculateYearsOfService(data.getHireDate(), runDate);
        }

        // 4. Perform End-of-Month Check
        boolean isEOM = dateEngine.isEndOfMonth(runDate);

        // 5. Generate Report
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateReport(reportData, runDate, isEOM);

        logger.info("Business Rules Processor Finished.");
    }

    /**
     * Creates a hardcoded list of sample data for demonstration purposes.
     * This method would be replaced by a dynamic data loading mechanism in a real-world scenario.
     *
     * @return A list of {@link InputData} objects.
     */
    private static List<InputData> createSampleData() {
        List<InputData> data = new ArrayList<>();
        // Hourly Employees
        data.add(new InputData("John Doe", 1, "H", LocalDate.of(2020, 1, 15), 45, 25.00)); // With overtime
        data.add(new InputData("David Brown", 4, "H", LocalDate.of(2022, 2, 28), 40, 30.00)); // No overtime

        // Sales Employees
        data.add(new InputData("Jane Smith", 2, "S", LocalDate.of(2019, 5, 20), 50000, 30000)); // With sales
        data.add(new InputData("Mary Williams", 3, "S", LocalDate.of(2021, 8, 5), 0, 32000));   // Zero sales

        // Management Employees
        data.add(new InputData("Peter Jones", 1, "M", LocalDate.of(2018, 3, 10), 80000));
        data.add(new InputData("Sue Storm", 2, "M", LocalDate.of(2018, 3, 10), 85000));
        data.add(new InputData("Reed Richards", 3, "M", LocalDate.of(2017, 3, 10), 90000));
        data.add(new InputData("Ben Grimm", 4, "M", LocalDate.of(2016, 3, 10), 95000));

        // Invalid Data for testing validation
        data.add(new InputData("Invalid Emp", 1, "X", LocalDate.of(2021, 1, 1), 40, 20));
        data.add(new InputData("Invalid Region", 5, "H", LocalDate.of(2021, 1, 1), 40, 20));

        return data;
    }
}
