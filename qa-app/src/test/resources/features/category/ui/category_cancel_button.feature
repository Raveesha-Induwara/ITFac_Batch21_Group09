Feature: Cancel Button Navigation on Add Category Page

  Background:
    Given the user is logged in as an admin
    And dthe user is on the "Category" page
    And dthe admin clicks on the "Add A Category" button

  Scenario: Verify cancel button navigates back to category list
    When dthe admin clicks the cancel button on add category page
    Then dthe admin should be navigated back to the category list page
