@ui-category-delete
Feature: Verify successfully adding a category

  Background: 
    Given the user is logged in as an admin
    And the user is on the category page

  Scenario: Verify main category is not deleted when at least one subcategory is exist
    When the admin clicks on the Add Category button
    Then the admin enters name "Main" for the new category
    Then click on the "Save" button
    Then the system should show the "Category added successfully" message
    And the new category "Main" should appear in the category list

    When the admin clicks on the Add Category button
    Then the admin enters name "Sub" for the new category
    And select "Main" as the parent category
    Then click on the "Save" button
    Then the system should show the "Category added successfully" message
    And the new category "Sub" should appear in the category list

    Then the admin tries to delete "Main" category
    Then the system should show the "Cannot delete category. Please delete sub-categories first." error message
    And Delete the newly added "Sub" category to maintain test data integrity
    And Delete the newly added "Main" category to maintain test data integrity
