@dashboard @navigation-menu
Feature: Side Navigation menu highlights the active page for admin users.

  As an admin user
  I want to see Dashboard right after login

  Background:
    Given the user is logged in as an admin

  Scenario: Verify navigation menu highlights the active page for admin users.
    When the user is on the dashboard page
    Then the "Dashboard" section should be highlighted in the side navigation menu
    And the user clicks on "Sales" in the side navigation menu
    Then the "Sales" section should be highlighted in the side navigation menu
    And the user clicks on "Categories" in the side navigation menu
    Then the "Categories" section should be highlighted in the side navigation menu
    And the user clicks on "Plants" in the side navigation menu
    Then the "Plants" section should be highlighted in the side navigation menu