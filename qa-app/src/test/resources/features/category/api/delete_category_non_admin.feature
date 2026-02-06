@api
Feature: Delete Category API Verification - Non-Admin Access Control

  Scenario: Verify non-admin user cannot delete categories
    Given dA category with ID 17 exists
    When dNon-admin attempts to delete category 17
    Then dthe category API should return status 403
    And dthe response should contain error message "Forbidden"
