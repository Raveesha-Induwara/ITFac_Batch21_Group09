@api @category
Feature: Category Deletion API Validation

  Scenario Outline: Delete category and verify API response
    When Admin deletes category with id <categoryId>
    Then the category API should return status <expectedStatus>
    And the category response message should be "<expectedMessage>"

    Examples:
      | categoryId | expectedStatus | expectedMessage           |
      | 1          | 200            | Category deleted successfully |
      | 999        | 404            | Category not found        |
