@214009F @get_plants
Feature: API: Plant Retrieval API Validation

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
      | 0    | 5   | admin |
      | 0    | 5   | testuser |

  @214009F @negative
  Scenario: Verify unauthorized access to paginated plants
    When I retrieve paged plants with page 0 and size 10 without a token
    Then the response status should be 401
    And the response should include the error message "Unauthorized"
