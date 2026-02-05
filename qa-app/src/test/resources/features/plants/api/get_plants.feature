Feature: Plant Retrieval API Validation

  Scenario Outline: Verify successful retrieval of all plants
    When I retrieve all plants using "<user>" credentials
    Then the response status should be 200
    And the response should contain a list of plants

    Examples:
      | user |
      | admin |
      | testuser |

  Scenario: Verify unauthorized access to Get All Plants
    When I retrieve all plants without a token
    Then the response status should be 401
    And the response should include the error message "Unauthorized"

  Scenario Outline: Verify successful retrieval of plants with pagination
    When I retrieve paged plants with page <page> and size <size> using "<user>" credentials
    Then the response status should be 200
    And the response should contain paginated plant data

    Examples:
      | page | size | user |
      | 0    | 10   | admin |
      | 0    | 10   | testuser |

  Scenario Outline: Verify invalid pagination parameters
    When I retrieve paged plants with page <page> and size <size> using "<user>" credentials
    Then the response status should be 400

    Examples:
      | page | size | user |
      | -1   | 0    | admin |
      | -1   | 0    | testuser |

  Scenario: Verify unauthorized access to paginated plants
    When I retrieve paged plants with page 0 and size 10 without a token
    Then the response status should be 401
    And the response should include the error message "Unauthorized"

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