# User Guide

This guide provides instructions on how to run the Java Batch Application and interpret its output.

## 1. How to Run the Application

### Prerequisites
- Java 11 or higher must be installed.
- Apache Maven must be installed.

### Steps
1. **Clone the repository** to your local machine.
2. **Open a terminal or command prompt** and navigate to the root directory of the project.
3. **Compile the application** using Maven:
   ```bash
   mvn clean compile
   ```
4. **Run the application** using the Maven exec plugin:
   ```bash
   mvn exec:java -Dexec.mainClass="com.adviser.businessrules.BusinessRulesProcessor"
   ```

## 2. Input Data Format Requirements

The application currently uses hardcoded sample data within the `BusinessRulesProcessor.java` file. To modify the input, you would need to edit the `createSampleData()` method in that file.

The `InputData` object requires the following parameters:
- `employeeName` (String)
- `region` (int, 1-4)
- `employeeType` (String, "H", "S", or "M")
- `hireDate` (LocalDate)
- A variable number of `double` values depending on the employee type:
    - For "H": `hoursWorked`, `hourlyRate`
    - For "S": `salesAmount`, `salesSalary`
    - For "M": `managementSalary`

## 3. Report Interpretation Guide

The application generates a report to both the console and a file named `business_rules_report.txt`.

### Report Sections
- **Header**: Displays the application name, run date/time, version, and whether the run was at the end of the month.
- **Input Data Summary**: Shows the total number of records processed.
- **Processing Results**: Lists each processed employee, their region, type, calculated total compensation, and any comments (e.g., "UH-OH" for sales employees with zero sales).
- **Validation Messages**: Shows any warnings for records that could not be processed due to invalid data (e.g., wrong employee type or region).
- **Regional Sales Report**: This section only appears if the application is run on the last day of a month. It provides a summary for each region, including the manager's name, total regional sales, and the manager's calculated commission and total compensation.
- **Footer**: Indicates that the processing is complete.

## 4. Troubleshooting Common Issues

- **Build Failure**: Ensure that you have Java 11+ and Maven installed correctly and that your `JAVA_HOME` environment variable is set.
- **"End of Month" report not generated**: This report is only generated if the system date on which the application is run is the last day of the month. To test this functionality, you can temporarily change the `runDate` variable in `BusinessRulesProcessor.java`.
- **Incorrect Compensation**: Review the business rules in the markdown files (`CWXTCOB.md`, `CWXTSUBC.md`) to ensure the input data is exercising the rules as expected.
