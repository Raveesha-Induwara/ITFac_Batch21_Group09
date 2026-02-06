@214009F @plant_list_pagination
Feature: UI: Plant List with Pagination

  Background:
    Given the user is logged in as an admin
    And the user is on the "plants" page

  Scenario: Verify pagination navigation if pagination is available
    Then the plant list should be displayed
    And pagination should be visible if applicable
    And the user should start on page 1

    When the user navigates to the next page if pagination exists
    Then the user should be on page 2
    And the Previous button should be enabled

    When the user navigates to the previous page if pagination exists
    Then the user should be on page 1
