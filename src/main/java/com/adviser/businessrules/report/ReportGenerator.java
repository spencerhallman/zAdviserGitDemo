package com.adviser.businessrules.report;

import com.adviser.businessrules.model.ReportData;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class ReportGenerator {

    public void generateReport(ReportData reportData, LocalDate runDate, boolean isEOM) {
        String reportContent = ReportFormatter.formatReport(reportData, runDate, isEOM);

        // Output to console
        System.out.println(reportContent);

        // Output to file
        try (FileWriter writer = new FileWriter("business_rules_report.txt")) {
            writer.write(reportContent);
        } catch (IOException e) {
            System.err.println("Error writing report to file: " + e.getMessage());
        }
    }
}
