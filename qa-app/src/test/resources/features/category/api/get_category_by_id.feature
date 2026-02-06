@api
Feature: Get Category By ID API Verification

  Scenario Outline: Verify admin can retrieve specific category by ID
    When dAdmin retrieves category with ID <categoryId>
    Then dthe category API should return status <expectedStatus>
    And dthe response should contain category with name "<expectedName>"

    Examples:
      | categoryId | expectedStatus | expectedName |
      | 17         | 200            | one          |
      | 999        | 404            | Not Found    |
