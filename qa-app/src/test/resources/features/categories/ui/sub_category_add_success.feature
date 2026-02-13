@ui-category-add
Feature: Verify successfully adding a sub category

  Background: 
    Given the user is logged in as an admin
    And the user is on the category page

    Scenario: Verify success subcategory addition outcomes
    When the admin clicks on the Add Category button
    Then the admin enters name "Apple rose" for the new category
    And select "Flower" as the parent category
    Then click on the "Save" button
    Then the system should show the "Category added successfully" message
    And the new category "Apple rose" should appear in the category list
    And Delete the newly added category to maintain test data integrity
