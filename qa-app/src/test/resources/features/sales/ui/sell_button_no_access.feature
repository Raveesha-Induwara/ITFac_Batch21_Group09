Feature: Sell button no access for non-admin

  Background:
    Given the user is logged in as a non-admin

Scenario: Verify "Sell Plant" button is NOT visible for logged-in Non-Admin
  When the user clicks on "Sales" in the side navigation
  Then the "Sales" section should be highlighted in the side navigation
  And the user should be navigated to the "Sales" page
  Then the Sell Plant button should NOT be visible
