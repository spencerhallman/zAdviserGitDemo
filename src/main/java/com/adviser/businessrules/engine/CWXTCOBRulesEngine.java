package com.adviser.businessrules.engine;

import com.adviser.businessrules.model.InputData;
import com.adviser.businessrules.model.ProcessingResult;
import com.adviser.businessrules.model.ReportData;
import com.adviser.businessrules.util.ValidationUtils;

/**
 * Implements the main business logic for processing employees, mirroring the functionality
 * of the `CWXTCOB.cbl` COBOL program. It handles different employee types and orchestrates
 * calculations for compensation.
 *
 * @author Advisor
 * @version 1.0
 * @since 2024-07-17
 */
public class CWXTCOBRulesEngine {

    private final CWXTSUBCRulesEngine subcEngine = new CWXTSUBCRulesEngine();

    /**
     * Processes a single employee record. It validates the employee type and region,
     * then delegates to the appropriate processing method based on the employee type.
     *
     * @param data The input data for the employee.
     * @param reportData The container for storing results and summary data.
     */
    public void processEmployee(InputData data, ReportData reportData) {
        if (!ValidationUtils.isValidEmployeeType(data.getEmployeeType())) {
            reportData.addValidationMessage("INVALID EMPLOYEE TYPE: " + data.getEmployeeType() + " for " + data.getEmployeeName());
            return;
        }

        if (!ValidationUtils.isValidRegion(data.getRegion())) {
            reportData.addValidationMessage("INVALID REGION: " + data.getRegion() + " for " + data.getEmployeeName());
            return;
        }

        switch (data.getEmployeeType()) {
            case "H":
                processHourlyEmployee(data, reportData);
                break;
            case "S":
                processSalesEmployee(data, reportData);
                break;
            case "M":
                processManagementEmployee(data, reportData);
                break;
        }
    }

    /**
     * Processes an hourly employee, calculating their compensation based on hours worked
     * and handling overtime pay.
     *
     * @param data The input data for the hourly employee.
     * @param reportData The container for storing the processing result.
     */
    private void processHourlyEmployee(InputData data, ReportData reportData) {
        double hours = data.getHoursWorked();
        double rate = data.getHourlyRate();
        double compensation;
        if (hours <= 40) {
            compensation = hours * rate;
        } else {
            double regularPay = 40 * rate;
            double overtimeHours = hours - 40;
            double overtimePay = overtimeHours * (rate * 1.5);
            compensation = regularPay + overtimePay;
        }
        reportData.addResult(new ProcessingResult(data.getEmployeeName(), data.getRegion(), "H", compensation));
    }

    /**
     * Processes a sales employee, calculating their commission based on sales amount
     * and adding it to their base salary. It also handles the "UH-OH" comment for zero sales.
     *
     * @param data The input data for the sales employee.
     * @param reportData The container for storing results and summary data.
     */
    private void processSalesEmployee(InputData data, ReportData reportData) {
        double salesAmount = data.getSalesAmount();
        double commission = 0;
        String comment = null;

        if (salesAmount > 0) {
            commission = subcEngine.calculateSalesCommission(salesAmount);
            reportData.addSales(data.getRegion(), salesAmount);
        } else {
            comment = "UH-OH";
        }

        double totalCompensation = data.getSalesSalary() + commission;
        ProcessingResult result = new ProcessingResult(data.getEmployeeName(), data.getRegion(), "S", totalCompensation);
        if (comment != null) {
            result.setComment(comment);
        }
        reportData.addResult(result);
    }

    /**
     * Processes a management employee by setting them as the manager for their region
     * in the report data.
     *
     * @param data The input data for the management employee.
     * @param reportData The container for storing the manager's details.
     */
    private void processManagementEmployee(InputData data, ReportData reportData) {
        reportData.setManager(data.getRegion(), data.getEmployeeName(), data.getManagementSalary());
    }
}
