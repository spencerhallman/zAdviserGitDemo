package com.adviser.businessrules.model;

/**
 * Represents the result of processing a single employee record.
 * It stores the calculated compensation and any relevant comments.
 *
 * @author Advisor
 * @version 1.0
 * @since 2024-07-17
 */
public class ProcessingResult {
    private final String employeeName;
    private final int region;
    private final String employeeType;
    private final double compensation;
    private String comment;

    /**
     * Constructs a ProcessingResult object.
     *
     * @param employeeName The name of the employee.
     * @param region The region of the employee.
     * @param employeeType The type of the employee.
     * @param compensation The calculated compensation for the employee.
     */
    public ProcessingResult(String employeeName, int region, String employeeType, double compensation) {
        this.employeeName = employeeName;
        this.region = region;
        this.employeeType = employeeType;
        this.compensation = compensation;
    }

    // Getters

    /**
     * @return The employee's name.
     */
    public String getEmployeeName() { return employeeName; }

    /**
     * @return The employee's region.
     */
    public int getRegion() { return region; }

    /**
     * @return The employee's type.
     */
    public String getEmployeeType() { return employeeType; }

    /**
     * @return The calculated compensation.
     */
    public double getCompensation() { return compensation; }

    /**
     * @return A comment associated with the processing, such as "UH-OH".
     */
    public String getComment() { return comment; }

    // Setters

    /**
     * Sets a comment for the processing result.
     *
     * @param comment The comment to set.
     */
    public void setComment(String comment) { this.comment = comment; }
}
