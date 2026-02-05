Feature: Sort Categories by Name

  Background:
    Given the user is logged in as a non-admin
    And dthe user is on the "Category" page

  Scenario: Verify categories can be sorted by Name in descending order
    When dthe user clicks on the Name column header to sort
    Then dthe categories should be sorted by Name in descending order

  Scenario: Verify categories can be sorted by Name in ascending order
    When dthe user clicks on the Name column header to sort
    And dthe user clicks on the Name column header to sort
    Then dthe categories should be sorted by Name in ascending order
