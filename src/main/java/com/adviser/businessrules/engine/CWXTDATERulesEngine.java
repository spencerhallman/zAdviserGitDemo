package com.adviser.businessrules.engine;

import com.adviser.businessrules.util.DateUtils;
import java.time.LocalDate;

/**
 * Implements date-related business logic, mirroring the functionality of the
 * `CWXTDATE.cbl` COBOL program. It handles the calculation of years of service
 * and determines if a given run date is the end of the month.
 *
 * @author Advisor
 * @version 1.0
 * @since 2024-07-17
 */
public class CWXTDATERulesEngine {

    private boolean checkedForEOM = false;
    private boolean isEOM = false;

    /**
     * Calculates the years of service for an employee based on their hire date and the run date.
     * This method delegates the calculation to the {@link DateUtils} class.
     *
     * @param hireDate The employee's hire date.
     * @param runDate The date of the batch run.
     * @return The number of completed years of service.
     */
    public int calculateYearsOfService(LocalDate hireDate, LocalDate runDate) {
        return DateUtils.calculateYearsOfService(hireDate, runDate);
    }

    /**
     * Determines if the given run date is the end of the month. To replicate the
     * COBOL program's behavior, this check is performed only once per instance
     * of the engine.
     *
     * @param runDate The date of the batch run.
     * @return {@code true} if the run date is the end of the month, {@code false} otherwise.
     */
    public boolean isEndOfMonth(LocalDate runDate) {
        if (!checkedForEOM) {
            isEOM = DateUtils.isEndOfMonth(runDate);
            checkedForEOM = true;
        }
        return isEOM;
    }
}
