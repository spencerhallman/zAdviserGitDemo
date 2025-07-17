package com.adviser.businessrules.engine;

import com.adviser.businessrules.model.InputData;
import com.adviser.businessrules.model.ProcessingResult;
import com.adviser.businessrules.model.ReportData;
import com.adviser.businessrules.util.ValidationUtils;

public class CWXTCOBRulesEngine {

    private final CWXTSUBCRulesEngine subcEngine = new CWXTSUBCRulesEngine();

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

    private void processSalesEmployee(InputData data, ReportData reportData) {
        double salesAmount = data.getSalesAmount();
        double commission = 0;
        ProcessingResult result = new ProcessingResult(data.getEmployeeName(), data.getRegion(), "S", 0);

        if (salesAmount > 0) {
            commission = subcEngine.calculateSalesCommission(salesAmount);
            reportData.addSales(data.getRegion(), salesAmount);
        } else {
            result.setComment("UH-OH");
        }

        double totalCompensation = data.getSalesSalary() + commission;
        result = new ProcessingResult(data.getEmployeeName(), data.getRegion(), "S", totalCompensation);
        if ("UH-OH".equals(result.getComment())) {
            // Preserve the comment
        }
        reportData.addResult(result);
    }

    private void processManagementEmployee(InputData data, ReportData reportData) {
        reportData.setManager(data.getRegion(), data.getEmployeeName(), data.getManagementSalary());
    }
}
