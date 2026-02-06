@214013L
Feature: Duplicate plant addition Validation

  Background: 
    Given the user is logged in as an admin
    And the user is on the "plants" page

  Scenario: Verify duplicate plant addition outcomes
    When the admin clicks on the "Add Plant" button
    And the admin selects category "rose"
    And the admin enters name "purple rose", price "100", and quantity "10"
    And the admin clicks the submit button
    Then the system should show the message "Plant 'purple rose' already exists in this category"
