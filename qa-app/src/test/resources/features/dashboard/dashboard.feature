@dashboard-tests
Feature: Dashboard Access for Non-Admin Users

  Background: 
      Given the user is logged in as a non-admin
    
  Scenario: Verify "Manage Categories" button visibility and functionality.
     When the user is on the dashboard page
     Then the "Manage Categories" button should be visible
     When the user clicks on the "Manage Categories" button
     Then the user should be navigated to the "Categories"
    
  Scenario: Verify display Categories summary card for non-admin user.
    When the user is on the dashboard page
    Then the "<cardName>" summary card should be visible

    Examples: 
      | cardName |
      | Categories |
      | Plants   |
      | Sales    |
