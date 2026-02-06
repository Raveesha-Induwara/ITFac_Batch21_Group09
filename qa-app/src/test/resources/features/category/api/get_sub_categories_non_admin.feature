@api
Feature: Get Sub Categories API Verification - Non-Admin Access

  Scenario: Verify non-admin user can retrieve sub category list
    When dNon-admin retrieves sub categories
    Then dthe category API should return status 200
    And dthe response should contain sub categories list
