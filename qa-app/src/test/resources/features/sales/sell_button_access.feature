Feature: Sales page access for Admin

  Background:
    Given the user is logged in as an admin

  Scenario: Verify "Sell Plant" button is visible for logged-in Admin
    When the admin clicks on "Sales" in the side navigation
    Then the "Sales" section should be highlighted in the side navigation
    And the admin should be navigated to the "Sales" page
    Then the Sell Plant button should be visible