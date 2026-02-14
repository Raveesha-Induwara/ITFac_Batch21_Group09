# QA Training App

> **ITFac Batch 21 - Group 09**

Comprehensive test automation framework for plant nursery management system with UI (Selenium) and API (REST Assured) testing using BDD approach.

## 🚀 Technology Stack

| Category | Technology | Version |
|----------|------------|----------|
| Language | Java | 11 |
| Build Tool | Maven | - |
| UI Testing | Selenium WebDriver | 4.40.0 |
| API Testing | REST Assured | - |
| BDD Framework | Cucumber | 7.14.0 |
| Test Runner | JUnit | 4.13.2 |
| Reporting | Allure | 2.25.0 |
| Driver Manager | WebDriverManager | 5.6.3 |

## 📋 Test Coverage

### UI Testing (Selenium WebDriver)

**Dashboard Module**
- Page loading and navigation
- Summary cards display (Categories, Plants, Sales)
- Navigation menu highlighting
- Role-based access (Admin/Non-Admin)

**Authentication Module**
- User login (Admin/Non-Admin)
- Session management

**Category Module**
- Add/Edit/Delete categories and subcategories
- Search by name, filter by parent
- Sort by ID, name, parent
- Form validation and duplicate handling
- Access control (Add/Edit/Delete buttons for non-admin)
- Cancel button functionality

**Plant Module**
- Add/Edit plants with validation
- Search by name and category
- Combined search and filter reset
- Pagination and sorting
- Duplicate plant handling
- Form cancel functionality

**Sales Module**
- Access sales page
- Sell plant with form validation
- Pagination and sorting by sold date
- Delete confirmation
- Access control (Sell button for non-admin)

### API Testing (REST Assured)

**Authentication API**
- Login with token generation
- Token-based authorization

**Category API**
- Create category (Admin/Non-Admin/Unauthenticated)
- Get all categories, main categories, subcategories
- Get category by ID
- Get category summary
- Update category (Admin/Non-Admin)
- Delete category (Admin/Non-Admin)
- Delete parent category (orphan child handling)

**Plant API**
- Create plant (Admin/Non-Admin/Unauthenticated)
- Get all plants, plants by category
- Get paged plants
- Update plant
- Delete plant
- Validation scenarios (price, quantity, duplicate)

**Sales API**
- Create sale (Admin/Non-Admin/Unauthenticated)
- Get all sales
- Delete sale
- Validation (quantity, stock availability)

## 📁 Project Structure

```
qa-app/
├── src/test/
│   ├── java/com/example/
│   │   ├── pages/                    # Page Object Model
│   │   │   ├── category/
│   │   │   ├── dashboard/            
│   │   │   ├── login/
│   │   │   ├── plants/
│   │   │   ├── sales/
│   │   │   └── Common.java           # Shared utilities
│   │   ├── stepdefinitions/          # Cucumber Step Definitions
│   │   │   ├── category/
│   │   │   ├── dashboard/
│   │   │   ├── login/
│   │   │   ├── plants/
│   │   │   ├── sales/
│   │   │   └── Hooks.java            # Before/After hooks
│   │   ├── utils/
│   │   │   ├── ConfigReader.java     # Properties file reader
│   │   │   └── DriverFactory.java    # WebDriver singleton
│   │   └── TestRunner.java           # Cucumber test runner
│   └── resources/
│       ├── features/                 # Gherkin feature files
│       │   ├── category/
│       │   ├── dashboard/ 
│       │   ├── login/api/
│       │   ├── plants/
│       │   └── sales/
│       └── config.properties         # Configuration file
├── allure-results/                   # Allure test results
├── target/                           # Build output
│   └── cucumber-reports.html         # Cucumber HTML report
└── pom.xml                           # Maven configuration
```

## ⚙️ Configuration

Edit `src/test/resources/config.properties`:

```properties
browser=chrome
baseUrl=http://localhost:8080
```

## 🛠️ Setup & Installation

### Prerequisites
- JDK 11 or higher
- Maven 3.6+
- Chrome browser
- Application running on `http://localhost:8080`

### Installation

```bash
# Clone repository
git clone <repository-url>
cd ITFac_Batch21_Group09/qa-app

# Install dependencies
mvn clean install
```

## ▶️ Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Feature File
```bash
mvn test -Dcucumber.options="src/test/resources/features/plants/ui/plant_add_success.feature"
mvn test -Dcucumber.options="src/test/resources/features/category/api/get_category_by_id.feature"
```

## 📊 Test Reports

### Cucumber HTML Report
After test execution:
```
target/cucumber-reports.html
```

### Allure Report

**Interactive Report:**
```bash
mvn allure:serve
```

**Static Report:**
```bash
mvn allure:report
```
View at: `target/site/allure-maven-plugin/index.html`

## 🏗️ Design Patterns & Best Practices

- **Page Object Model (POM)** - Separate page classes for UI elements and actions
- **Service Layer Pattern** - API service classes (AuthService, CategoryService, PlantService, SalesService)
- **Singleton Pattern** - WebDriver instance management via DriverFactory
- **Factory Pattern** - Driver initialization and configuration
- **BDD (Behavior Driven Development)** - Gherkin syntax for readable test scenarios
- **Hooks Pattern** - Setup and teardown using @Before and @After
- **Configuration Management** - Externalized config via properties file

## 🔑 Key Features

✅ **Dual Testing Approach** - UI (Selenium) + API (REST Assured)  
✅ **Role-Based Testing** - Admin and Non-Admin user scenarios  
✅ **Comprehensive Validation** - Form validation, error handling, access control  
✅ **Automatic Driver Management** - WebDriverManager handles browser drivers  
✅ **Token-Based API Auth** - JWT token management for API tests  
✅ **Reusable Components** - Common utilities and shared page objects  
✅ **Detailed Reporting** - Cucumber HTML + Allure reports  
✅ **Tag-Based Execution** - Flexible test execution with Cucumber tags  

## 👥 Test Users

| Role | Username | Password |
|------|----------|----------|
| Admin | admin | admin123 |
| Non-Admin | testuser | test123 |

## 🤝 Contributing

1. Create a feature branch
2. Follow existing Page Object Model structure
3. Write Gherkin scenarios before implementation
4. Ensure all tests pass before committing
5. Update documentation if needed

---
