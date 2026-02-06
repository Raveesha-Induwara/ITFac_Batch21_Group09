@api
Feature: Get Category By ID API Verification - Non-Admin Access

  Scenario Outline: Verify non-admin user can retrieve a single category by ID
    When dNon-admin retrieves category with ID <categoryId>
    Then dthe category API should return status <expectedStatus>
    And dthe response should contain category with name "<expectedName>"

    Examples:
      | categoryId | expectedStatus | expectedName |
      | 17         | 200            | one          |
      | 999        | 404            | Not Found    |
