Feature: Category Filter Validation

  Background:
    Given the user is logged in as an admin
    And dthe user is on the "Category" page

  Scenario Outline: Verify filter by parent category
    When the user selects parent "<parent>" from the filter
    And dthe user clicks the search button
    Then all results in the table should have the parent "<parent>"

    Examples:
      | parent |
      | one    |
      | Main Category    |