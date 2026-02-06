@api
Feature: Update Category API Verification - Admin Access

  Scenario: Verify admin can update category name and changes persist across API calls
    Given dA category with ID 33 exists
    When dAdmin updates category 33 with name "UpdatedFa"
    Then dthe category API should return status 200
    And dthe response should contain updated category name "UpdatedFa"
    When dAdmin retrieves category with ID 33
    Then dthe category API should return status 200
    And dthe response should contain category with name "UpdatedFa"

  Scenario: Verify admin can update category parent relationship
    Given dA category with ID 33 exists
    When dAdmin updates category 33 parent to category 32
    Then dthe category API should return status 200
    When dAdmin retrieves category with ID 33
    Then dthe category API should return status 200
    And dthe response should contain parent category with ID 32
