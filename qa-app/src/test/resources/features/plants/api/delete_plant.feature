@214009F @delete_plant
Feature: Plant Deletion API Validation

  @214009F @positive
  Scenario Outline: Verify successful deletion of plant by ID
    When I delete plant with ID <plantId> using "<user>" credentials
    Then the delete response status should be <statusCode>

    Examples:
      | plantId | user     | statusCode |
      | 47      | admin    | 204        |
      | 45      | testuser  | 403       |

  @214009F @negative
  Scenario: Verify unauthorized deletion of plant by ID
    When I delete plant with ID 8 without authentication
    Then the delete response status should be 401

  @214009F @negative
  Scenario: Verify delete plant with invalid ID
    When I delete plant with ID 99999 using "admin" credentials
    Then the delete response status should be 404
