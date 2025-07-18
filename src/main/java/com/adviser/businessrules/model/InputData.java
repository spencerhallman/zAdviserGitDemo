package com.adviser.businessrules.model;

import java.time.LocalDate;

/**
 * Represents a single input record for an employee, containing all necessary data
 * for processing by the business rules engine.
 *
 * @author Advisor
 * @version 1.0
 * @since 2024-07-17
 */
public class InputData {
    private final String employeeType;
    private double hoursWorked;
    private double hourlyRate;
    private double salesAmount;
    private double salesSalary;
    private double managementSalary;
    private int region;
    private final String employeeName;
    private final LocalDate hireDate;

    /**
     * Constructs an InputData object with the specified details.
     * The constructor uses a variable argument list for numeric values, which are
     * interpreted based on the employee type.
     *
     * @param employeeName The name of the employee.
     * @param region The region number (1-4).
     * @param employeeType The type of employee ("H", "S", or "M").
     * @param hireDate The date the employee was hired.
     * @param values The numeric values associated with the employee type:
     *               - For "H": hours worked, hourly rate
     *               - For "S": sales amount, sales salary
     *               - For "M": management salary
     */
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

    /**
     * @return The employee type.
     */
    public String getEmployeeType() { return employeeType; }

    /**
     * @return The hours worked by an hourly employee.
     */
    public double getHoursWorked() { return hoursWorked; }
    /**
     * @return The hourly rate of an hourly employee.
     */
    public double getHourlyRate() { return hourlyRate; }

    /**
     * @return The sales amount for a sales employee.
     */
    public double getSalesAmount() { return salesAmount; }

    /**
     * @return The base salary for a sales employee.
     */
    public double getSalesSalary() { return salesSalary; }

    /**
     * @return The salary for a management employee.
     */
    public double getManagementSalary() { return managementSalary; }

    /**
     * @return The employee's region.
     */
    public int getRegion() { return region; }

    /**
     * @return The employee's name.
     */
    public String getEmployeeName() { return employeeName; }

    /**
     * @return The employee's hire date.
     */
    public LocalDate getHireDate() { return hireDate; }
}
