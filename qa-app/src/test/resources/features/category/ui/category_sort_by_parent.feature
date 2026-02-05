Feature: Sort Categories by Parent

  Background:
    Given the user is logged in as a non-admin
    And dthe user is on the "Category" page

  Scenario: Verify categories can be sorted by Parent in descending order
    When dthe user clicks on the Parent column header to sort
    Then dthe categories should be sorted by Parent in descending order

  Scenario: Verify categories can be sorted by Parent in ascending order
    When dthe user clicks on the Parent column header to sort
    And dthe user clicks on the Parent column header to sort
    Then dthe categories should be sorted by Parent in ascending order
