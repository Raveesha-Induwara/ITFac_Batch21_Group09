Feature: Plant List with Pagination

  Background:
    Given the user is logged in as an admin
    And the user is on the "plants" page

  @pagination @smoke
  Scenario: Verify pagination is visible when plant data exceeds one page
    Then the plant list should be displayed
    And pagination controls should be displayed according to data size
    And the user should be on page 1
    And the Previous button should be "disabled"

  @pagination
  Scenario: Navigate to next page (only if pagination exists)
    Then pagination controls should be displayed according to data size
    When the user goes to the next page
    Then the user should move to the next page
    And the Previous button should be "enabled"

  @pagination
  Scenario: Navigate back to previous page (only if pagination exists)
    Given the user is on page 2
    When the user goes to the previous page
    Then the user should move to the previous page
    And the user should be on page 1

  @pagination
  Scenario: Direct page navigation (only if pagination exists)
    Then pagination controls should be displayed according to data size
    When the user navigates to page 2
    Then the user should be on page 2
    And page 2 should be highlighted as active
