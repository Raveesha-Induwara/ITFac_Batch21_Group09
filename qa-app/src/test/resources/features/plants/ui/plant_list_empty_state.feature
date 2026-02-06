@214009F @plant_list_empty_state
Feature: Plant List Empty State

  Background:
    Given the user is logged in as an admin
    And the user is on the "plants" page

  @214009F @positive
  Scenario: Verify plant list without any published plants
    Given there are no published plants in the system
    When the user views the plant list
    Then the plant list should display "No plants found" message
    And pagination controls should not be displayed
