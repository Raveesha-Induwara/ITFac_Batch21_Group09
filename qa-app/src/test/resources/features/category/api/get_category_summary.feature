@api
Feature: Get Category Summary API Verification

  Scenario: Verify admin user can retrieve category summary
    When dAdmin retrieves category summary
    Then dthe category API should return status 200
    And dthe response should contain category summary
