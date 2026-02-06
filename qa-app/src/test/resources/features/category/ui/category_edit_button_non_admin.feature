Feature: Edit Button Visibility for Non-Admin Users

  Background:
    Given the user is logged in as a non-admin
    And dthe user is on the "Category" page

  Scenario: Verify non-admin cannot see or access edit button
    Then dthe edit button should not be visible or clickable for non-admin
