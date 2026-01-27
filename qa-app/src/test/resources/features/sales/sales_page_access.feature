Feature: Sales page access from side navigation

  As an admin user
  I want to access the Sales page from the side navigation
  So that I can view sales-related information

  Background:
    Given the user is logged in as an admin

  Scenario: Verify a logged in admin can view Sales page from side navigation
    When the admin clicks on "Sales" in the side navigation
    Then the "Sales" section should be highlighted in the side navigation
    And the admin should be navigated to the "Sales" page
