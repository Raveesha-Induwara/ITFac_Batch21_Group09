@214013L
Feature: Plant Search Validation

  Background:
    Given the user is logged in as a non-admin
    And the user is on the "plants" page

  Scenario Outline: Verify search functionality for different plant name formats
    Given the new plant "<plant_name>" should appear in the plant list
    When the user enters "<plant_name>" into the search bar
    And the user clicks the search button
    Then only the plant "<plant_name>" should be displayed in the results table

    Examples:
      | plant_name | description       |
      | black rose | Name with spaces  |
      | Anthurium  | Single word name  |