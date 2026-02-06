@214013L
Feature: Success plant addition Validation

  Background: 
    Given the user is logged in as an admin
    And the user is on the "plants" page

  Scenario: Verify success plant addition outcomes
    When the admin clicks on the "Add Plant" button
    And the admin selects category "rose"
    And the admin enters name "Apple rose", price "100", and quantity "10"
    And the admin clicks the submit button
    Then the system should show the message "Plant added successfully"
    And the new plant "Apple rose" should appear in the plant list