package com.adviser.businessrules.report;

import com.adviser.businessrules.engine.CWXTSUBCRulesEngine;
import com.adviser.businessrules.model.ProcessingResult;
import com.adviser.businessrules.model.ReportData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportFormatter {

    public static String formatReport(ReportData reportData, LocalDate runDate, boolean isEOM) {
        StringBuilder report = new StringBuilder();
        CWXTSUBCRulesEngine subcEngine = new CWXTSUBCRulesEngine();

        // Header
        report.append("============================================================\n");
        report.append("              Business Rules Processing Report\n");
        report.append("============================================================\n");
        report.append("Run Date/Time: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        report.append("Application Version: 1.0.0\n");
        report.append("End of Month: ").append(isEOM ? "Yes" : "No").append("\n");
        report.append("\n");

        // Input Data Summary
        report.append("------------------------------------------------------------\n");
        report.append("                     Input Data Summary\n");
        report.append("------------------------------------------------------------\n");
        report.append("Total Records Processed: ").append(reportData.getResults().size() + reportData.getValidationMessages().size()).append("\n");
        report.append("\n");

        // Processing Results
        report.append("------------------------------------------------------------\n");
        report.append("                   Processing Results\n");
        report.append("------------------------------------------------------------\n");
        for (ProcessingResult result : reportData.getResults()) {
            report.append(String.format("Employee: %-15s | Region: %d | Type: %s | Compensation: $%.2f | Comment: %s\n",
                    result.getEmployeeName(), result.getRegion(), result.getEmployeeType(), result.getCompensation(), result.getComment() != null ? result.getComment() : ""));
        }
        report.append("\n");

        // Validation Messages
        if (!reportData.getValidationMessages().isEmpty()) {
            report.append("------------------------------------------------------------\n");
            report.append("                  Validation Messages\n");
            report.append("------------------------------------------------------------\n");
            for (String message : reportData.getValidationMessages()) {
                report.append("WARNING: ").append(message).append("\n");
            }
            report.append("\n");
        }

        // Regional Sales Report
        if (isEOM) {
            report.append("------------------------------------------------------------\n");
            report.append("                  Regional Sales Report\n");
            report.append("------------------------------------------------------------\n");

            // North
            double northCommission = subcEngine.calculateManagementCommission(reportData.getTotalSalesNorth());
            report.append(String.format("Region: North | Manager: %-15s | Total Sales: $%.2f | Manager Commission: $%.2f | Manager Total Comp: $%.2f\n",
                    reportData.getManagerNorth(), reportData.getTotalSalesNorth(), northCommission, reportData.getManagerSalaryNorth() + northCommission));

            // South
            double southCommission = subcEngine.calculateManagementCommission(reportData.getTotalSalesSouth());
            report.append(String.format("Region: South | Manager: %-15s | Total Sales: $%.2f | Manager Commission: $%.2f | Manager Total Comp: $%.2f\n",
                    reportData.getManagerSouth(), reportData.getTotalSalesSouth(), southCommission, reportData.getManagerSalarySouth() + southCommission));

            // East
            double eastCommission = subcEngine.calculateManagementCommission(reportData.getTotalSalesEast());
            report.append(String.format("Region: East  | Manager: %-15s | Total Sales: $%.2f | Manager Commission: $%.2f | Manager Total Comp: $%.2f\n",
                    reportData.getManagerEast(), reportData.getTotalSalesEast(), eastCommission, reportData.getManagerSalaryEast() + eastCommission));

            // West
            double westCommission = subcEngine.calculateManagementCommission(reportData.getTotalSalesWest());
            report.append(String.format("Region: West  | Manager: %-15s | Total Sales: $%.2f | Manager Commission: $%.2f | Manager Total Comp: $%.2f\n",
                    reportData.getManagerWest(), reportData.getTotalSalesWest(), westCommission, reportData.getManagerSalaryWest() + westCommission));

            report.append("\n");
        }


        // Footer
        report.append("============================================================\n");
        report.append("              Processing Complete\n");
        report.append("============================================================\n");

        return report.toString();
    }
}
