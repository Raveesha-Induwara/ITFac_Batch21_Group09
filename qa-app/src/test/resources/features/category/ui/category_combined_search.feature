Feature: Combined Category Search Validation

  Background:
    Given the user is logged in as an admin
    And dthe user is on the "Category" page

  Scenario Outline: Verify search by both name and parent
    When dthe user enters "<name>" into the search bar
    And the user selects parent "<parent>" from the filter
    And dthe user clicks the search button
    Then donly the category "<name>" should be displayed in the results table
    And all results in the table should have the parent "<parent>"

    Examples:
      | name | parent |
      | two  | one    |
      | thre | one    |
