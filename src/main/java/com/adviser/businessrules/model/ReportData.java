package com.adviser.businessrules.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A data container that aggregates all the necessary information required to generate the final report.
 * This includes processing results, validation messages, and regional summaries.
 *
 * @author Advisor
 * @version 1.0
 * @since 2024-07-17
 */
public class ReportData {
    private final List<ProcessingResult> results = new ArrayList<>();
    private final List<String> validationMessages = new ArrayList<>();
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

    /**
     * Adds a processing result to the list of results.
     * @param result The ProcessingResult to add.
     */
    public void addResult(ProcessingResult result) {
        results.add(result);
    }

    /**
     * Adds a validation message to the list of messages.
     * @param message The validation message to add.
     */
    public void addValidationMessage(String message) {
        validationMessages.add(message);
    }

    /**
     * @return The list of processing results.
     */
    public List<ProcessingResult> getResults() {
        return results;
    }

    /**
     * @return The list of validation messages.
     */
    public List<String> getValidationMessages() {
        return validationMessages;
    }

    /**
     * Adds a sales amount to the total for a specific region.
     * @param region The region to add the sales to.
     * @param sales The sales amount.
     */
    public void addSales(int region, double sales) {
        switch (region) {
            case 1: totalSalesNorth += sales; break;
            case 2: totalSalesSouth += sales; break;
            case 3: totalSalesEast += sales; break;
            case 4: totalSalesWest += sales; break;
        }
    }

    /**
     * Sets the manager's name and salary for a specific region.
     * @param region The region to set the manager for.
     * @param name The manager's name.
     * @param salary The manager's base salary.
     */
    public void setManager(int region, String name, double salary) {
        switch (region) {
            case 1: managerNorth = name; managerSalaryNorth = salary; break;
            case 2: managerSouth = name; managerSalarySouth = salary; break;
            case 3: managerEast = name; managerSalaryEast = salary; break;
            case 4: managerWest = name; managerSalaryWest = salary; break;
        }
    }

    // Getters for regional sales and manager data
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
