Feature: Category Name Field Validation

  Background:
    Given the user is logged in as an admin
    And dthe user is on the "Category" page
    And dthe admin clicks on the "Add A Category" button

  Scenario: Verify validation error when category name is empty
    When dthe admin enters category name ""
    And dthe admin clicks the save button
    Then dthe validation error "Category name is required" should be displayed below the category name field

  Scenario Outline: Verify validation error when category name length is invalid
    When dthe admin enters category name "<categoryName>"
    And dthe admin clicks the save button
    Then dthe validation error "<errorMessage>" should be displayed below the category name field

    Examples:
      | categoryName | errorMessage                                      |
      | ab           | Category name must be between 3 and 10 characters |
      | a            | Category name must be between 3 and 10 characters |
      | d            | Category name must be between 3 and 10 characters |
