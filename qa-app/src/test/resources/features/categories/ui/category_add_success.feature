@ui-category-add
Feature: Verify successfully adding a category

  Background: 
    Given the user is logged in as an admin
    And the user is on the category page

  Scenario: Verify success main category addition outcomes
    When the admin clicks on the Add Category button
    Then the admin enters name "Main" for the new category
    Then click on the "Save" button
    Then the system should show the "Category added successfully" message
    And the new category "Main" should appear in the category list
    And Delete the newly added "Main" category to maintain test data integrity
