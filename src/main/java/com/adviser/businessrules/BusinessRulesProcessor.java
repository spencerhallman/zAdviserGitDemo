package com.adviser.businessrules;

import com.adviser.businessrules.engine.CWXTCOBRulesEngine;
import com.adviser.businessrules.engine.CWXTDATERulesEngine;
import com.adviser.businessrules.model.InputData;
import com.adviser.businessrules.model.ReportData;
import com.adviser.businessrules.report.ReportGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BusinessRulesProcessor {

    public static void main(String[] args) {
        System.out.println("Business Rules Processor Started.");

        List<InputData> sampleData = createSampleData();
        ReportData reportData = new ReportData();
        CWXTCOBRulesEngine cobolEngine = new CWXTCOBRulesEngine();
        CWXTDATERulesEngine dateEngine = new CWXTDATERulesEngine();
        LocalDate runDate = LocalDate.of(2023, 2, 28);

        for (InputData data : sampleData) {
            cobolEngine.processEmployee(data, reportData);
            // We can calculate years of service here if needed for the report
            // int yearsOfService = dateEngine.calculateYearsOfService(data.getHireDate(), runDate);
        }

        boolean isEOM = dateEngine.isEndOfMonth(runDate);

        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateReport(reportData, runDate, isEOM);

        System.out.println("Business Rules Processor Finished.");
    }

    private static List<InputData> createSampleData() {
        List<InputData> data = new ArrayList<>();
        data.add(new InputData("John Doe", 1, "H", LocalDate.of(2020, 1, 15), 45, 25.00));
        data.add(new InputData("Jane Smith", 2, "S", LocalDate.of(2019, 5, 20), 50000, 30000));
        data.add(new InputData("Peter Jones", 1, "M", LocalDate.of(2018, 3, 10), 80000));
        data.add(new InputData("Mary Williams", 3, "S", LocalDate.of(2021, 8, 5), 0, 32000));
        data.add(new InputData("David Brown", 4, "H", LocalDate.of(2022, 2, 28), 40, 30.00));
        data.add(new InputData("Invalid Emp", 1, "X", LocalDate.of(2021, 1, 1), 40, 20));
        data.add(new InputData("Invalid Region", 5, "H", LocalDate.of(2021, 1, 1), 40, 20));
        data.add(new InputData("Sue Storm", 2, "M", LocalDate.of(2018, 3, 10), 85000));
        data.add(new InputData("Reed Richards", 3, "M", LocalDate.of(2017, 3, 10), 90000));
        data.add(new InputData("Ben Grimm", 4, "M", LocalDate.of(2016, 3, 10), 95000));

        return data;
    }
}
