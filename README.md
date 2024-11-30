
# Test Automation Framework Documentation

## Project Overview
This project automates the login functionality tests for the **OrangeHRM demo** site 
using **Selenium**, **TestNG**, **Java**, and **integrates video screen recording** for each test execution. 
The tests cover valid and invalid login scenarios, verifying the behavior of the application under different conditions.
The tests cover valid and invalid Admin scenarios, verifying the behavior of the application under different conditions.
The tests cover valid  Info scenarios, verifying the behavior of the application under different conditions.
Additionally, the project features logging with **Log4j** and test reporting with **Allure** to track execution results
Recently, a **Directory** Scrolling Functionality was added to **dynamically** load and extract all employee names 
from the directory using JavaScript scrolling techniques. 
The extracted data is then saved to an Excel file using the **ExcelUtilis** class.


## Installation and Prerequisites

Before you start running the automation tests, make sure you have the following software installed:

### Prerequisites:
- **Java** (JDK 8 or higher) - [Download here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Maven** - [Download here](https://maven.apache.org/download.cgi)
- **Selenium** WebDriver - [Download here](https://www.selenium.dev/downloads/)
- **TestNG** - Integrated via Maven dependency
- **Allure Framework** - [Installation Guide](https://docs.qameta.io/allure/)
- **Log4j** - For logging, included as a Maven dependency
- **JavaCV** - For video recording during tests, included as a Maven dependency
- **Browser Drivers** (e.g., ChromeDriver, GeckoDriver) - [ChromeDriver Download](https://sites.google.com/a/chromium.org/chromedriver/)

## Technologies Used:

1. **Selenium**: For web automation, controlling the browser during tests.
2. **TestNG**: For test management and execution.
3. **Java**: Programming language used to write test scripts.
4. **Allure**: For generating beautiful, interactive test reports.
5. **Log4j**: For logging, helping track test execution and debugging.
6. **JavaCV**: For recording videos of the tests in MP4 format (useful for debugging).
7. **Apache POI**: For reading and writing Excel files.
8. **Apache Commons CSV**: For handling CSV file operations.
9. **JavaScriptExecutor**: For executing JavaScript commands to handle dynamic web elements.

## Project Structure

- **LoginTest.java**: Contains test cases for logging in with both valid and invalid credentials.
- **WebDriverFactory.java**: Provides methods to initialize and configure WebDriver instances based on the browser and mode specified.
- **ReuseTests folder**: Includes reusable components like listeners, base classes, configuration loaders, and screen recording utilities.
- **Configuration File** (config.properties): Stores the configuration settings such as URL, browser preferences, and login credentials.
- **OrangeHRMScript.xml**: Defines the suite and test cases to be executed.


### **Environment Variables for Configuration**
If any settings need to be handled by environment variables (e.g., for CI/CD), make sure to mention it explicitly.

#### Example:
```markdown
## Environment Variables

For sensitive information such as credentials or specific configurations, you can override settings using environment variables.

Example:
- `LOGIN_USERNAME`: Override the valid login username.
- `LOGIN_PASSWORD`: Override the valid login password.
```

## Configuration File (config.properties)

The `config.properties` file contains settings for running the tests, such as the application URL, browser preferences, and login credentials.

### Configuration Settings:

- **URL Configuration**
    - `url`: The URL of the OrangeHRM demo site.

- **Browser Settings**
    - `browser`: Specifies the browser for running tests. Options available:
        - Chrome
        - Firefox
        - Edge
    - `mode`: Specifies the browser's operational mode. Options include:
        - headless (No UI, useful for CI/CD environments)
        - incognito (Runs the browser in incognito mode)

- **Credentials**
    - **Valid Credentials**: Used for successful login scenarios.
        - `username`: The valid username.
        - `password`: The valid password.
    - **Invalid Credentials**: Used to simulate unsuccessful login attempts.
        - `Invalidusername`: Invalid username.
        - `Invalidpassword`: Invalid password.

## Test Cases

1. **Login with Invalid Credentials**:
    - This test case simulates an invalid login attempt by using invalid credentials (username and password).
    - It checks if the application correctly handles the failed login scenario.

2. **Login with Valid Credentials**:
    - This test case checks the behavior when valid credentials are provided.
    - It validates that after a successful login, the user is correctly redirected, and the company name and user information are displayed.

## WebDriver Configuration

The `WebDriverFactory.GetWebDriver` class is responsible for launching the browser and setting up the WebDriver based on the browser and operational mode specified in the configuration. It supports multiple browsers (Chrome, Firefox, Edge) and various modes such as **headless** and **incognito**.

## Logging

Logging is managed using **Log4j**. The logs are stored in the `./logs/application.log` file. These logs help track test execution steps and assist in debugging failed tests.

## Allure Integration

The project integrates **Allure** for generating interactive, visually appealing reports. After each test, the **AllureLog4jListener** listens for log events and attaches the logs to the Allure report. This provides detailed insights into the execution results.

## Screen Recording

**ScreenRecorderUtil** captures the screen and records it during the execution of tests. The videos are saved in MP4 format and encoded with the **H.264 codec** using **JavaCV**. This feature is especially useful for debugging and reviewing test executions.

## TestNG Suite Configuration

The `testng.xml` file is used to define the suite and the individual test cases to be executed. The file includes the following:
- **Listeners**: Allure and Log4j listeners are included for reporting and logging.
- **Test Classes**: Contains test classes for login, directory, and admin functionalities.

## CSVDataDriven Class

The `CSVDataDriven` class provides utility methods for reading data from a CSV file. This class is designed to facilitate data-driven testing by allowing tests to be executed with different sets of input data stored in a CSV file. 

### Key Features:
- **Read CSV Data**: Reads data from the provided CSV file and returns it as a list of arrays.
- **Get Specific Line**: Retrieves a specific line of data from the CSV based on the line number.

This class supports flexible handling of CSV files, allowing you to parameterize tests with various input data.

---

## ExcelUtilis Class

The `ExcelUtilis` class offers methods to interact with Excel files. It provides functionality to both write and read data in Excel format, making it useful for managing test data and storing results.

### Key Features:
- **Write Data to Excel**: Adds data from a map of column names and values into a newly created Excel file.
- **Extract Data from Excel**: Reads data from an existing Excel file and returns it in a structured format (map of sheet names and their respective data).

This class is beneficial for test scenarios that involve Excel as a source of test data or where results need to be stored in Excel format for later analysis.

---
## Directory Scrolling and Employee Name Extraction
This newly added functionality dynamically loads and extracts all employee names 
from the `Directory` page on the `OrangeHRM` demo site. 
Since the employee directory is contained within a scrollable container,
the function leverages JavaScriptExecutor to scroll and load all visible names dynamically.

### Key Features:
1. Dynamic Scrolling: Uses JavaScriptExecutor to scroll through the employee directory.
Continues scrolling until all employee names are fully loaded.

2. Employee Name Extraction: Extracts visible employee names from the scrollable container.
Handles duplicates and ensures all names are uniquely collected.

3. Data Export to Excel: Saves all extracted employee names into an Excel file using the ExcelUtilis class.
The file is generated in the specified directory for later analysis.


---
## RetryAnalyzer Class

The `RetryAnalyzer` class helps manage flaky tests by automatically retrying tests that fail. This is particularly useful in situations where tests may intermittently fail due to non-deterministic issues (e.g., network failures).

### Key Features:
- **Test Retry Logic**: Configures the retry logic for failed tests, retrying them a specified number of times.
- **Customizable Retry Count**: Allows you to define the maximum number of retries for any test.

By using this class, tests can be more reliable, minimizing the impact of temporary failures and reducing the need for manual intervention when tests fail intermittently.

---

## Integration in the Project

- **CSVDataDriven**: Useful for implementing data-driven tests, where input data is managed in CSV files. This allows tests to be run with various datasets without altering the test code.
  
- **ExcelUtilis**: Ideal for working with Excel files to read test data or save test results, making it easier to manage and analyze large volumes of test data.
  
- **RetryAnalyzer**: Enhances test stability by ensuring that flaky tests are retried automatically, reducing false negatives and making your test suite more robust.

These classes work together to streamline test data management and improve the reliability and efficiency of your automated tests.

## Running the Tests

Once the setup is complete, you can run the tests using Maven and TestNG.

### Running Tests via Maven:
1. Open a terminal/command prompt.
2. Navigate to the root directory of the project.
3. Run the following command to execute the tests:
   ```bash
   mvn clean test
   ```

### Running Specific Test:
To run a specific test, execute:

**Example**

```bash
mvn -Dtest=LoginTest test
```
## Viewing Allure Reports

After running the tests, you can generate and view the Allure report by following these steps:

1. Install Allure Commandline:
   ```bash
   brew install allure  # macOS
   choco install allure  # Windows
   ```

2. Generate the report using Maven:
   ```bash
   mvn allure:serve
   ```

3. Open the generated report in your browser at the provided URL.
## Troubleshooting

**Issue**: Browser not launching when running tests.
- **Solution**: Ensure that the correct browser driver (e.g., ChromeDriver or GeckoDriver) is installed and available in your PATH.

**Issue**: TestNG not executing tests.
- **Solution**: Verify that your `OrangeHRMScript.xml` file is configured correctly and contains the right test classes.
---
## Summary

This automation testing project covers a variety of key functionalities such as:
- Validating login scenarios with both valid and invalid credentials.
- Configuring WebDriver for different browsers and operational modes.
- Capturing logs for test execution and generating interactive reports using Allure.
- Recording videos of test runs for detailed review and debugging.
- Have the ability to dynamically interact with scrollable containers, extract data, and save it.

The project also utilizes reusable components for test organization, making it scalable and easy to extend with additional functionalities.
It is an ideal template for web-based test automation, particularly for teams using `Selenium`, `TestNG`, and `Java`.