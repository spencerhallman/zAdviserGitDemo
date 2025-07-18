# Java Batch Application for Business Rules Processing

## Application Overview
This Java batch application is a console-based tool designed to process employee data based on a set of business rules defined in the repository. It calculates employee compensation, aggregates regional sales data, and generates a comprehensive report summarizing the results. The application is built using Java and Maven, and it is designed to be a modern implementation of the logic originally found in a set of COBOL programs.

## Prerequisites
- Java 11 or higher
- Apache Maven

## Installation & Setup
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   ```
2. **Navigate to the project directory:**
   ```bash
   cd <project-directory>
   ```
3. **Compile the project using Maven:**
   ```bash
   mvn clean compile
   ```

## Usage Instructions
Run the application from the command line using the Maven exec plugin:
```bash
mvn exec:java -Dexec.mainClass="com.adviser.businessrules.BusinessRulesProcessor"
```
The application will process a hardcoded set of sample data and generate a report to the console. It will also write the same report to a file named `business_rules_report.txt` in the project's root directory.

## Business Rules Summary
The application implements a variety of business rules, including:
- **Hourly Employee Compensation**: Calculates pay for hourly employees, including overtime at 1.5x the normal rate for hours worked over 40.
- **Sales Employee Compensation**: Calculates commission based on a tiered rate structure and adds it to a base salary. Handles zero sales as a special case.
- **Management Employee Processing**: Assigns managers to regions and calculates their commission based on total regional sales.
- **Date-based Logic**: Determines if the processing date is the end of the month to trigger the generation of a regional sales report.

For a detailed breakdown of the business rules, please refer to:
- `CWXTCOB.md`
- `CWXTSUBC.md`
- `CWXTDATE.md`

## Project Structure
```
src/main/java/com/adviser/businessrules/
├── BusinessRulesProcessor.java (main class)
├── engine/
│   ├── CWXTCOBRulesEngine.java
│   ├── CWXTSUBCRulesEngine.java
│   └── CWXTDATERulesEngine.java
├── model/
│   ├── InputData.java
│   ├── ProcessingResult.java
│   └── ReportData.java
├── report/
│   ├── ReportGenerator.java
│   └── ReportFormatter.java
└── util/
    ├── DateUtils.java
    └── ValidationUtils.java
```

## Sample Output
Here is an example of the report output when run on the last day of the month:
```
============================================================
              Business Rules Processing Report
============================================================
Run Date/Time: 2023-02-28 10:00:00
Application Version: 1.0.0
End of Month: Yes

------------------------------------------------------------
                     Input Data Summary
------------------------------------------------------------
Total Records Processed: 9

------------------------------------------------------------
                   Processing Results
------------------------------------------------------------
Employee: John Doe        | Region: 1 | Type: H | Compensation: $1187.50 | Comment:
Employee: Jane Smith      | Region: 2 | Type: S | Compensation: $33000.00 | Comment:
Employee: Mary Williams   | Region: 3 | Type: S | Compensation: $32000.00 | Comment: UH-OH
Employee: David Brown     | Region: 4 | Type: H | Compensation: $1200.00 | Comment:

------------------------------------------------------------
                  Validation Messages
------------------------------------------------------------
WARNING: INVALID EMPLOYEE TYPE: X for Invalid Emp
WARNING: INVALID REGION: 5 for Invalid Region

------------------------------------------------------------
                  Regional Sales Report
------------------------------------------------------------
Region: North | Manager: Peter Jones     | Total Sales: $0.00 | Manager Commission: $0.00 | Manager Total Comp: $80000.00
Region: South | Manager: Sue Storm       | Total Sales: $50000.00 | Manager Commission: $1000.00 | Manager Total Comp: $86000.00
Region: East  | Manager: Reed Richards   | Total Sales: $0.00 | Manager Commission: $0.00 | Manager Total Comp: $90000.00
Region: West  | Manager: Ben Grimm       | Total Sales: $0.00 | Manager Commission: $0.00 | Manager Total Comp: $95000.00

============================================================
              Processing Complete
============================================================
```

## Development Information
- **Modifying Business Logic**: The core business logic is located in the `src/main/java/com/adviser/businessrules/engine` package. You can modify the classes in this package to alter the calculations.
- **Adding New Business Rules**: To add a new set of rules, you could create a new class in the `engine` package and integrate it into the `BusinessRulesProcessor`.
- **Testing**: The application uses hardcoded sample data in `BusinessRulesProcessor.java`. You can modify the `createSampleData()` method to test different scenarios. For more robust testing, you could add JUnit tests in the `src/test/java` directory.
