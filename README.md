# ITFac_Batch21_Group09

## QA Automation Testing Project

A comprehensive test automation framework for web application testing using Selenium WebDriver, Cucumber BDD, and Allure reporting.

## Project Overview

This project implements automated end-to-end testing for a web application with focus on:
- User authentication and login functionality
- Sales page navigation and access control
- Sales records pagination and data management

## Technology Stack

- **Java 11** - Programming language
- **Maven** - Build and dependency management
- **Selenium WebDriver 4.40.0** - Browser automation
- **Cucumber 7.14.0** - BDD framework for test scenarios
- **JUnit 4.13.2** - Test execution framework
- **Allure 2.25.0** - Test reporting
- **WebDriverManager 5.6.3** - Automatic browser driver management

## Project Structure

```
qa-app/
├── src/
│   ├── main/java/
│   │   └── com/example/
│   │       └── App.java
│   └── test/
│       ├── java/com/example/
│       │   ├── pages/              # Page Object Model classes
│       │   │   ├── dashboard/
│       │   │   ├── sales/
│       │   │   │   └── SalesPage.java
│       │   │   └── categories/
│       │   ├── stepdefinitions/    # Cucumber step definitions
│       │   │   ├── dashboard/
│       │   │   │   └── LoginPageStepDefinitions.java
│       │   │   ├── sales/
│       │   │   │   ├── SalesPageAccessStepDefinitions.java
│       │   │   │   └── SalesPaginationStepDefinitions.java
│       │   │   ├── categories/
│       │   │   └── Hooks.java
│       │   ├── utils/              # Utility classes
│       │   │   ├── ConfigReader.java
│       │   │   └── DriverFactory.java
│       │   └── TestRunner.java
│       └── resources/
│           ├── features/           # Cucumber feature files
│           │   ├── dashboard/
│           │   │   └── login.feature
│           │   ├── sales/
│           │   │   ├── sales_page_access.feature
│           │   │   └── sales_pagination.feature
│           │   └── categories/
│           └── config.properties
├── allure-results/                 # Allure test results
├── target/                         # Build output
└── pom.xml
```

## Test Scenarios

### Dashboard - Login Feature
- ✅ Successful login with valid credentials
- ✅ Unsuccessful login with invalid credentials
- ✅ Password recovery process

### Sales - Page Access
- ✅ Admin can access Sales page from side navigation
- ✅ Sales section highlighting verification
- ✅ Navigation to Sales page validation

### Sales - Pagination
- ✅ Sales records pagination (max 10 records per page)
- ✅ Pagination controls display
- ✅ Navigation between pages functionality

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven 3.6+
- Chrome browser installed
- Internet connection (for downloading dependencies)

## Configuration

Edit `src/test/resources/config.properties` to configure:

```properties
browser=chrome
baseUrl=http://localhost:8080
```

## Installation & Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd ITFac_Batch21_Group09/qa-app
```

2. Install dependencies:
```bash
mvn clean install
```

## Running Tests

### Run all tests:
```bash
mvn test
```

### Run specific feature:
```bash
mvn test -Dcucumber.options="src/test/resources/features/sales/sales_pagination.feature"
```

### Run with specific tags (if configured):
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

## Test Reports

### Cucumber HTML Report
After test execution, view the report at:
```
target/cucumber-reports.html
```

### Allure Report
Generate and view Allure report:
```bash
mvn allure:serve
```

Or generate static report:
```bash
mvn allure:report
```
View at: `target/site/allure-maven-plugin/index.html`

## Design Patterns

- **Page Object Model (POM)** - Encapsulates page elements and actions
- **Singleton Pattern** - WebDriver instance management
- **Factory Pattern** - Driver initialization
- **BDD (Behavior Driven Development)** - Gherkin syntax for test scenarios

## Key Features

- ✨ Automatic browser driver management with WebDriverManager
- 🔄 Reusable page objects and step definitions
- 📊 Comprehensive test reporting with Allure
- 🎯 BDD approach with Cucumber for readable test scenarios
- ⚙️ Configurable test environment via properties file
- 🧪 Hooks for setup and teardown operations
- 🔍 Explicit waits for stable test execution

## Contributing

1. Create a feature branch
2. Implement tests following existing patterns
3. Ensure all tests pass
4. Submit pull request

## Team

ITFac Batch 21 - Group 09

## License

This project is for educational purposes.
