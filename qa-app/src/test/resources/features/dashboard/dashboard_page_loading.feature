@dashboard @ui-dashboard-loading
Feature: Dashboard loads immediately after user successfully login.

  As an admin user
  I want to see Dashboard right after login

  Scenario: Dashboard loads immediately after admin user successfully login.
    Given the user is on the login page
    When the user enters valid credentials
    And clicks the login button
    Then the dashboard should load immediately
