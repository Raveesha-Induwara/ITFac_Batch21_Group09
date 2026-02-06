@api
Feature: Get Category By ID API Verification - Non-Admin Access

  Scenario Outline: Verify non-admin user can retrieve an existing category by ID
    When dNon-admin retrieves category with ID <categoryId>
    Then dthe category API should return status <expectedStatus>
    And dthe response should contain category with name "<expectedName>"

    Examples:
      | categoryId | expectedStatus | expectedName |
      | 17         | 200            | one          |

  Scenario Outline: Verify non-admin user receives appropriate error for non-existent category
    When dNon-admin retrieves category with ID <categoryId>
    Then dthe category API should return status <expectedStatus>
    And dthe error message should indicate category not found

    Examples:
      | categoryId | expectedStatus |
      | 999        | 404            |
