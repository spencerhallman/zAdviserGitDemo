package com.adviser.businessrules.engine;

public class CWXTSUBCRulesEngine {

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
