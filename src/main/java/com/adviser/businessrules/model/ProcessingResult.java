package com.adviser.businessrules.model;

public class ProcessingResult {
    private String employeeName;
    private int region;
    private String employeeType;
    private double compensation;
    private String comment;

    public ProcessingResult(String employeeName, int region, String employeeType, double compensation) {
        this.employeeName = employeeName;
        this.region = region;
        this.employeeType = employeeType;
        this.compensation = compensation;
    }

    // Getters
    public String getEmployeeName() { return employeeName; }
    public int getRegion() { return region; }
    public String getEmployeeType() { return employeeType; }
    public double getCompensation() { return compensation; }
    public String getComment() { return comment; }

    // Setters
    public void setComment(String comment) { this.comment = comment; }
}
