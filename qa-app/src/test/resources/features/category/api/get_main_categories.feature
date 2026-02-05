@api
Feature: Get Main Categories API Verification

  Scenario: Verify admin user can retrieve main category list
    When dAdmin retrieves main categories
    Then dthe category API should return status 200
    And dthe response should contain main categories list
