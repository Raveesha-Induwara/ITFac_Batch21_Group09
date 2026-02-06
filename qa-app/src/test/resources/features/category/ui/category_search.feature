Feature: Category Search Validation

  Background:
    Given the user is logged in as an admin
    And dthe user is on the "Category" page

  Scenario Outline: Verify search functionality for different category name formats
    Given dthe new category "<category_name>" should appear in the category list
    When dthe user enters "<category_name>" into the search bar
    And dthe user clicks the search button
    Then donly the category "<category_name>" should be displayed in the results table

    Examples:
      | category_name | description       |
      | one two | Name with spaces  |
      | one  | Single word name  |

  Scenario Outline: Verify search returns no results for non-existent categories
    When dthe user enters "<category_name>" into the search bar
    And dthe user clicks the search button
    Then dno results should be displayed in the table

    Examples:
      | category_name | description       |
      | mis  | Non-existent category  |