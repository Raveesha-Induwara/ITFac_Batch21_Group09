@api
Feature: Create Category API Verification - Non-Admin Access Control

  Scenario: Verify non-admin user is forbidden from creating categories
    When dNon-admin attempts to create a category with name "NewCategory"
    Then dthe category API should return status 403
    And dthe response should contain error message "Forbidden"
