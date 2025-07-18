package com.adviser.businessrules.engine;

/**
 * Implements the commission calculation logic, mirroring the functionality of the
 * `CWXTSUBC.cbl` COBOL subprogram. It provides methods to calculate commission for
 * both sales and management employees based on different rate structures.
 *
 * @author Advisor
 * @version 1.0
 * @since 2024-07-17
 */
public class CWXTSUBCRulesEngine {

    /**
     * Calculates the commission for a sales employee based on their individual sales amount.
     * The commission rate is determined by a tiered structure.
     *
     * @param salesAmount The individual sales amount of the employee.
     * @return The calculated commission.
     */
    public double calculateSalesCommission(double salesAmount) {
        double rate;
        if (salesAmount <= 20000.00) {
            rate = 0.02;
        } else if (salesAmount <= 40000.00) {
            rate = 0.04;
        } else if (salesAmount <= 60000.00) {
            rate = 0.06;
        } else if (salesAmount <= 80000.00) {
            rate = 0.08;
        } else {
            rate = 0.10;
        }
        return salesAmount * rate;
    }

    /**
     * Calculates the commission for a management employee based on the total regional sales amount.
     * The commission rate is determined by a tiered structure different from the sales commission.
     *
     * @param regionalSalesAmount The total sales amount for the manager's region.
     * @return The calculated commission for the manager.
     */
    public double calculateManagementCommission(double regionalSalesAmount) {
        double rate;
        if (regionalSalesAmount <= 100000.00) {
            rate = 0.020;
        } else if (regionalSalesAmount <= 200000.00) {
            rate = 0.025;
        } else if (regionalSalesAmount <= 300000.00) {
            rate = 0.030;
        } else if (regionalSalesAmount <= 400000.00) {
            rate = 0.035;
        } else {
            rate = 0.040;
        }
        return regionalSalesAmount * rate;
    }
}
