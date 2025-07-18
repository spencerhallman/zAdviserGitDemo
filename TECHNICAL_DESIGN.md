# Technical Design Document

## 1. Architecture Overview

The Java Batch Application is designed as a console-based batch processor built with Maven. It follows a modular architecture, separating concerns into distinct packages for the engine, data models, report generation, and utilities. The application reads a predefined set of sample data, processes it according to a series of business rules, and generates a comprehensive report to the console and a text file.

## 2. Class Descriptions and Responsibilities

### `com.adviser.businessrules.BusinessRulesProcessor`
- **Responsibility**: Main entry point of the application.
- **Details**:
    - Initializes the data, engines, and report generator.
    - Orchestrates the overall flow of the application.
    - Contains hardcoded sample data for demonstration purposes.

### `com.adviser.businessrules.engine`
- **`CWXTCOBRulesEngine.java`**: Implements the primary business logic for processing different types of employees (Hourly, Sales, Management). It calculates compensation and updates regional data.
- **`CWXTSUBCRulesEngine.java`**: A sub-engine responsible for calculating sales and management commissions based on different rate structures.
- **`CWXTDATERulesEngine.java`**: Handles date-related calculations, including determining if a run date is the end of the month and calculating years of service.

### `com.adviser.businessrules.model`
- **`InputData.java`**: A POJO representing a single employee record to be processed.
- **`ProcessingResult.java`**: A POJO that stores the outcome of processing a single employee record, including their calculated compensation.
- **`ReportData.java`**: A data container that aggregates all processing results, validation messages, and regional summaries needed for the final report.

### `com.adviser.businessrules.report`
- **`ReportGenerator.java`**: Responsible for generating the final report. It calls the `ReportFormatter` and writes the output to the console and a text file.
- **`ReportFormatter.java`**: Formats the raw `ReportData` into a human-readable string, including headers, summaries, and footers.

### `com.adviser.businessrules.util`
- **`DateUtils.java`**: Provides static utility methods for common date operations, such as checking for the end of the month.
- **`ValidationUtils.java`**: Provides static utility methods for input validation, such as checking for valid employee types and regions.

## 3. Business Rules Implementation Approach

The business rules from the markdown files (`CWXTCOB.md`, `CWXTSUBC.md`, `CWXTDATE.md`) are implemented in the `engine` package. Each engine class corresponds to one of the COBOL programs described in the markdown files, mirroring the original logic as closely as possible in a modern Java context.

- **Employee Processing**: The `CWXTCOBRulesEngine` uses a `switch` statement to delegate processing to different methods based on the employee type.
- **Commission Calculation**: The `CWXTSUBCRulesEngine` uses a series of `if-else if` statements to determine the correct commission rate based on sales amounts.
- **Date Logic**: The `CWXTDATERulesEngine` uses Java's `java.time` package for reliable date calculations.

## 4. Data Flow Diagram (Text-based)

```
[Start] -> [BusinessRulesProcessor]
              |
              V
        [Create Sample Data]
              |
              V
[Loop through InputData] -> [CWXTCOBRulesEngine] -> [CWXTSUBCRulesEngine (for S/M types)]
              |                   |
              |                   V
              |             [Update ReportData]
              |                   |
              <-------------------
              |
              V
[CWXTDATERulesEngine (to check for EOM)]
              |
              V
[ReportGenerator] -> [ReportFormatter]
              |
              |----------------------> [Console Output]
              |
              `----------------------> [business_rules_report.txt]
                                          |
                                          V
                                        [End]
```

## 5. Error Handling Strategy

- **Input Validation**: The `CWXTCOBRulesEngine` performs upfront validation on employee type and region. Invalid records are skipped, and a warning message is added to the `ReportData` object.
- **File I/O**: The `ReportGenerator` uses a `try-with-resources` block to ensure the `FileWriter` is properly closed, and it catches `IOException` to print an error message to `System.err` if the report file cannot be written.
- **Zero Sales**: A sales amount of zero is handled as a special case, generating a "UH-OH" comment in the report, as per the business rules.
