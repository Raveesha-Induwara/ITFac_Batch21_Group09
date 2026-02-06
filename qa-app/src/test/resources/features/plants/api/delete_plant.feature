@214009F @delete_plant
Feature: API: Plant Deletion API Validation

  Scenario Outline: Verify successful deletion of plant by ID
    When I delete plant with ID <plantId> using "<user>" credentials
    Then the delete response status should be <statusCode>

    Examples:
      | plantId | user     | statusCode |
      | 47      | admin    | 204        |
      | 45      | testuser  | 403       |

  Scenario: Verify unauthorized deletion of plant by ID
    When I delete plant with ID 8 without authentication
    Then the delete response status should be 401

  Scenario: Verify delete plant with invalid ID
    When I delete plant with ID 99999 using "admin" credentials
    Then the delete response status should be 404
