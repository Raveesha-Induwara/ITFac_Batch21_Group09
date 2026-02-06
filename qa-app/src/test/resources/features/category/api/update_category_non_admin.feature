@api
Feature: Update Category API Verification - Non-Admin Access Control

  Scenario: Verify non-admin user cannot update category information
    Given dA category with ID 17 exists
    When dNon-admin attempts to update category 17 with name "UpdatedName"
    Then dthe category API should return status 403
    And dthe response should contain error message "Forbidden"
