@ui-category-add
Feature: Verify successfully adding a category

  Background: 
    Given the user is logged in as an admin
    And the user is on the category page
  
  Scenario: Verify admin can not add duplicate main category with valid data
    When the admin clicks on the Add Category button
    Then the admin enters name "Main" for the new category
    Then click on the "Save" button
    Then the system should show the Category already exists error message
    And the new category "Main" should not appear in the category list