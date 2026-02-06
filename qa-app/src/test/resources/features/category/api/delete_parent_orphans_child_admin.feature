@api
Feature: Delete Category API Verification - Parent-Child Integrity

  Scenario: Verify admin cannot delete parent category that has children
    Given dA category with ID 37 exists
    And dA category with ID 38 exists
    When dAdmin retrieves category with ID 37
    Then dthe category API should return status 200
    And dthe parent category should have children
    When dAdmin retrieves category with ID 38
    Then dthe category API should return status 200
    And dthe response should contain parent category with ID 37
    When dAdmin attempts to delete parent category 37 that has children
    Then dthe category API should return status 500
    And dthe error indicates category has children
