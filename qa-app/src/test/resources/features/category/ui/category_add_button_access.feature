Feature: Add Category Button Access and Navigation

  Background:
    Given the user is logged in as an admin
    And dthe user is on the "Category" page

  Scenario: Verify admin can see Add Category button
    Then dthe admin should see the "Add A Category" button

  Scenario: Verify admin can navigate to Add Category page
    When dthe admin clicks on the "Add A Category" button
    Then dthe admin should be navigated to the Add Category page
