@214013L
Feature: Plant Form Navigation

  Background: 
    Given the user is logged in as an admin
    And the user is on the "plants" page

  Scenario: Verify Cancel button navigation back to List plant page
    When the admin clicks on the "Add Plant" button
    And the admin clicks the Cancel button
    Then the system should navigate back to the "plants" page