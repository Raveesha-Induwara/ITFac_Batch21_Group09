Feature: Sell Plant Page Access for Admin

  Background:
    Given the user is logged in as an admin

  Scenario: Verify logged-in Admin can access Sell Plant page via URL
    When the user clicks on "Sales" in the side navigation
    Then the "Sales" section should be highlighted in the side navigation
    And the user should be navigated to the "Sales" page
    When the user clicks on "Sell Plant"
    Then the Sell Plant page should load successfully
    And the page should display all required fields for creating a new sale
