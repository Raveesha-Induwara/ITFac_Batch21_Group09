@api @category
Feature: Category API Authorization Validation

  Scenario: Verify non-admin cannot create category
    When Non-admin user attempts to create a category with name "Unauthorized Category"
    Then the category API should return status 403
    And the category response message should be "Access denied"
