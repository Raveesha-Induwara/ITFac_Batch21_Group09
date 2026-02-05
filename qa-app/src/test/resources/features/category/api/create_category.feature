@api @category
Feature: Category Management API Validation

  Scenario Outline: Create category and verify API response
    When Admin creates a new category with name "<name>"
    Then the category API should return status <expectedStatus>
    And the category response message should be "<expectedMessage>"

    Examples:
      | name          | expectedStatus | expectedMessage                      |
      | Electronics   | 201            | Electronics                          |
      | Electronics   | 400            | Category 'Electronics' already exists|
      | ab            | 400            | Validation failed                    |
      |               | 400            | Validation failed                    |
