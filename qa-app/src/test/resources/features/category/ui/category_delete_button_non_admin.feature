Feature: Delete Button Visibility for Non-Admin Users

  Background:
    Given the user is logged in as a non-admin
    And dthe user is on the "Category" page

  Scenario: Verify delete button is disabled for non-admin
    Then dthe delete button should be visible but disabled for non-admin
