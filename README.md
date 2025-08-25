# OrangeHRM Automation Framework

This is a test automation framework for the OrangeHRM demo website using Java, Selenium WebDriver, TestNG, and Extent Reports.

## Framework Structure

```
orangehrm-automation/
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── orangehrm/
│                   ├── base/
│                   │   └── BaseTest.java
│                   ├── pages/
│                   │   └── LoginPage.java
│                   └── tests/
│                       └── LoginTest.java
├── test-output/
├── pom.xml
├── testng.xml
└── README.md
```

## Features

- Page Object Model design pattern
- TestNG test framework
- Extent Reports for reporting
- WebDriverManager for browser driver management
- Maven for dependency management
- Base test class with common functionality

## Prerequisites

- Java JDK 11 or higher
- Maven
- Chrome browser

## Running Tests

To run the tests, use the following Maven command:

```bash
mvn clean test
```

The test reports will be generated in the `test-output` directory.

## Test Reports

After test execution, you can find the Extent Report at:
- `test-output/extent-report.html`
