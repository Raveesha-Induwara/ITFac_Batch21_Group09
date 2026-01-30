Feature: Delete sales record confirmation

  As an Admin user
  I want to see a confirmation prompt before deleting a sales record
  So that accidental deletions are prevented

  Background:
    Given the user is logged in as an admin
    And the user navigates to the "Sales" page

  Scenario: Verify confirmation prompt appears before deleting a sales record
    When the admin clicks on the Delete action for a sales record
    Then a delete confirmation dialog should be displayed
    And the sales record should not be deleted
    When the admin clicks Cancel on the delete confirmation dialog
    Then the sales record should remain unchanged
