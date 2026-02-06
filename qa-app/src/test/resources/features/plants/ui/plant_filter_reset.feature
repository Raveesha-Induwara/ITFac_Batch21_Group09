@214009F @plant_filter_reset
Feature: UI: Plant Filter Reset Validation

  Background:
    Given the user is logged in as an admin
    And the user is on the "plants" page

  Scenario: Verify Reset button functionality after category filtering
    When the user selects category "Rose" from the filter
    And the user clicks the search button
    Then all results in the table should have the category "Rose"
    When the user clicks the reset button
    Then the search bar should be cleared
    And the category filter should be reset to default
    And all plants should be displayed

  Scenario: Verify Reset button functionality after name filtering
    Given the new plant "DUPLICATE_NAME" should appear in the plant list
    When the user enters "DUPLICATE_NAME" into the search bar
    And the user clicks the search button
    Then only the plant "DUPLICATE_NAME" should be displayed in the results table
    When the user clicks the reset button
    Then the search bar should be cleared
    And the category filter should be reset to default
    And all plants should be displayed

  Scenario: Verify Reset button functionality after category and name filtering
    When the user selects category "Rose" from the filter
    And the user enters "Anthurium" into the search bar
    And the user clicks the search button
    When the user clicks the reset button
    Then the search bar should be cleared
    And the category filter should be reset to default
    And all plants should be displayed

