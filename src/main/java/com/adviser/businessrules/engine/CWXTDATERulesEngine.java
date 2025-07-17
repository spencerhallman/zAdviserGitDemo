package com.adviser.businessrules.engine;

import com.adviser.businessrules.util.DateUtils;
import java.time.LocalDate;

public class CWXTDATERulesEngine {

    private boolean checkedForEOM = false;
    private boolean isEOM = false;

    public int calculateYearsOfService(LocalDate hireDate, LocalDate runDate) {
        return DateUtils.calculateYearsOfService(hireDate, runDate);
    }

    public boolean isEndOfMonth(LocalDate runDate) {
        if (!checkedForEOM) {
            isEOM = DateUtils.isEndOfMonth(runDate);
            checkedForEOM = true;
        }
        return isEOM;
    }
}
