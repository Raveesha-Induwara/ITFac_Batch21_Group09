@api @category
Feature: Category Update API Validation

  Scenario Outline: Update category and verify API response
    When Admin updates category with id <categoryId> to name "<newName>"
    Then the category API should return status <expectedStatus>
    And the category response message should be "<expectedMessage>"

    Examples:
      | categoryId | newName       | expectedStatus | expectedMessage                   |
      | 1          | Updated Rose  | 200            | Updated Rose                      |
      | 999        | NonExistent   | 404            | Category not found                |
      | 1          | ab            | 400            | Validation failed                 |
