Feature: View Sales Records with Pagination

  As an admin user
  I want to view sales records with pagination
  So that I can navigate through large datasets easily

  Background:
    Given the user is logged in as an admin

  Scenario: Verify sales records are paginated when number of records > 11
    Given there are more than 10 sales records in the system
    When the user clicks on "Sales" in the side navigation
    Then the "Sales" section should be highlighted in the side navigation
    And the user should be navigated to the "Sales" page
    Then pagination controls should be displayed when total sales records are greater than 10
    And the user can navigate between pages using the pagination controls
