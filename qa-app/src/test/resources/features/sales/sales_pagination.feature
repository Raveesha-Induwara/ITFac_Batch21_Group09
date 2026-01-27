Feature: View Sales Records with Pagination

  As an admin user
  I want to view sales records with pagination
  So that I can navigate through large datasets easily

  Background:
    Given the user is logged in as an admin

  Scenario: Verify sales records are paginated when number of records > 20
    When the admin clicks on "Sales" in the side navigation
    Then the "Sales" section should be highlighted in the side navigation
    And the admin should be navigated to the "Sales" page
    And a maximum of 10 sales records are displayed per page
    Then pagination controls should be displayed when total sales records are greater than 10
    Then the admin can navigate between pages using the pagination controls
