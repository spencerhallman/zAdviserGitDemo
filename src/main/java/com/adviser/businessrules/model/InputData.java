package com.adviser.businessrules.model;

import java.time.LocalDate;

public class InputData {
    private String employeeType;
    private double hoursWorked;
    private double hourlyRate;
    private double salesAmount;
    private double salesSalary;
    private double managementSalary;
    private int region;
    private String employeeName;
    private LocalDate hireDate;

    public InputData(String employeeName, int region, String employeeType, LocalDate hireDate, double... values) {
        this.employeeName = employeeName;
        this.region = region;
        this.employeeType = employeeType;
        this.hireDate = hireDate;

        switch (employeeType) {
            case "H":
                this.hoursWorked = values[0];
                this.hourlyRate = values[1];
                break;
            case "S":
                this.salesAmount = values[0];
                this.salesSalary = values[1];
                break;
            case "M":
                this.managementSalary = values[0];
                break;
        }
    }

    // Getters
    public String getEmployeeType() { return employeeType; }
    public double getHoursWorked() { return hoursWorked; }
    public double getHourlyRate() { return hourlyRate; }
    public double getSalesAmount() { return salesAmount; }
    public double getSalesSalary() { return salesSalary; }
    public double getManagementSalary() { return managementSalary; }
    public int getRegion() { return region; }
    public String getEmployeeName() { return employeeName; }
    public LocalDate getHireDate() { return hireDate; }
}
