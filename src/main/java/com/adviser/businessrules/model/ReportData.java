package com.adviser.businessrules.model;

import java.util.ArrayList;
import java.util.List;

public class ReportData {
    private List<ProcessingResult> results = new ArrayList<>();
    private List<String> validationMessages = new ArrayList<>();
    private double totalSalesNorth = 0;
    private double totalSalesSouth = 0;
    private double totalSalesEast = 0;
    private double totalSalesWest = 0;
    private String managerNorth;
    private String managerSouth;
    private String managerEast;
    private String managerWest;
    private double managerSalaryNorth;
    private double managerSalarySouth;
    private double managerSalaryEast;
    private double managerSalaryWest;

    public void addResult(ProcessingResult result) {
        results.add(result);
    }

    public void addValidationMessage(String message) {
        validationMessages.add(message);
    }

    public List<ProcessingResult> getResults() {
        return results;
    }

    public List<String> getValidationMessages() {
        return validationMessages;
    }

    public void addSales(int region, double sales) {
        switch (region) {
            case 1: totalSalesNorth += sales; break;
            case 2: totalSalesSouth += sales; break;
            case 3: totalSalesEast += sales; break;
            case 4: totalSalesWest += sales; break;
        }
    }

    public void setManager(int region, String name, double salary) {
        switch (region) {
            case 1: managerNorth = name; managerSalaryNorth = salary; break;
            case 2: managerSouth = name; managerSalarySouth = salary; break;
            case 3: managerEast = name; managerSalaryEast = salary; break;
            case 4: managerWest = name; managerSalaryWest = salary; break;
        }
    }

    public double getTotalSalesNorth() { return totalSalesNorth; }
    public double getTotalSalesSouth() { return totalSalesSouth; }
    public double getTotalSalesEast() { return totalSalesEast; }
    public double getTotalSalesWest() { return totalSalesWest; }
    public String getManagerNorth() { return managerNorth; }
    public String getManagerSouth() { return managerSouth; }
    public String getManagerEast() { return managerEast; }
    public String getManagerWest() { return managerWest; }
    public double getManagerSalaryNorth() { return managerSalaryNorth; }
    public double getManagerSalarySouth() { return managerSalarySouth; }
    public double getManagerSalaryEast() { return managerSalaryEast; }
    public double getManagerSalaryWest() { return managerSalaryWest; }
}
