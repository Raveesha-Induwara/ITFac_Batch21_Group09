Feature: Plant Category Search Validation

  Background:
    Given the user is logged in as a non-admin
    And the user is on the "plants" page

  Scenario Outline: Verify category search results match the selected category
    When the user selects category "<category>" from the filter
    And the user clicks the search button
    Then all results in the table should have the category "<category>"

    Examples:
      | category |
      | rose     |
      | spicy    |