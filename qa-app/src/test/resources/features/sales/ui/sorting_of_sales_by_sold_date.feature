Feature: View Sales data sorted by Sold Date by default

  As an admin user
  I want to see sales records sorted by Sold Date by default
  So that I can quickly view the most recent sales

  Background:
    Given the user is logged in as an admin

  Scenario: Verify sales records are sorted by Sold Date by default
    When the user clicks on "Sales" in the side navigation
    Then the "Sales" section should be highlighted in the side navigation
    And the user should be navigated to the "Sales" page
    And the sales records should be displayed sorted by Sold Date in descending order
