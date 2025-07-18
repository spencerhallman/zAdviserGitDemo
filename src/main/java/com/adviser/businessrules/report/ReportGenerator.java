package com.adviser.businessrules.report;

import com.adviser.businessrules.model.ReportData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Generates the final report and outputs it to the console and a text file.
 * It uses {@link ReportFormatter} to format the report content.
 *
 * @author Advisor
 * @version 1.0
 * @since 2024-07-17
 */
public class ReportGenerator {

    private static final Logger logger = LoggerFactory.getLogger(ReportGenerator.class);

    /**
     * Generates the report, logs it, and writes it to a file.
     *
     * @param reportData The data to be included in the report.
     * @param runDate The date of the batch run.
     * @param isEOM A flag indicating if it is the end of the month.
     */
    public void generateReport(ReportData reportData, LocalDate runDate, boolean isEOM) {
        String reportContent = ReportFormatter.formatReport(reportData, runDate, isEOM);

        // Output to console
        logger.info("--- Report Start ---");
        System.out.println(reportContent);
        logger.info("--- Report End ---");

        // Output to file
        try (FileWriter writer = new FileWriter("business_rules_report.txt")) {
            writer.write(reportContent);
            logger.info("Report successfully written to business_rules_report.txt");
        } catch (IOException e) {
            logger.error("Error writing report to file", e);
        }
    }
}
