Feature: Add Category Button Visibility for Non-Admin

  Background:
    Given the user is logged in as a non-admin
    And dthe user is on the "Category" page

  Scenario: Verify non-admin cannot see Add Category button
    Then dthe "Add A Category" button should not be visible to non-admin
