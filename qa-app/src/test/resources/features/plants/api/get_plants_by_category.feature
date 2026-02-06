@214013L
Feature: Plant Retrieval API Validation

  Scenario Outline: Verify plants returned match the requested category
    When I retrieve plants for category <catId> using "<user>" credentials
    Then the response status should be 200
    And all returned plants must belong to category ID <catId>

    Examples:
      | catId | user |
      | 2     | admin |
      | 2     | testuser |


  Scenario: Verify unauthenticated users cannot retrieve plant lists
    When I retrieve plants for category 2 without a token
    Then the response status should be 401
    And the response should include the error message "Unauthorized"
@bug
  Scenario: Verify error response for non-existent category
    When I retrieve plants for category 999 using "admin" credentials
    Then the response status should be 404
    And the response should include the error message "Category not found"