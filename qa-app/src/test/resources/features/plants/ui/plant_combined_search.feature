@214013L
Feature: Combined Plant Search Validation

  Background:
    Given the user is logged in as a non-admin
    And the user is on the "plants" page

  Scenario Outline: Verify search by both name and category
    When the user enters "<name>" into the search bar
    And the user selects category "<category>" from the filter
    And the user clicks the search button
    Then only the plant "<name>" should be displayed in the results table
    And all results in the table should have the category "<category>"

    Examples:
      | name      | category |
      | rdtfjgh   | spicy    |
      | Anthurium | rose     |