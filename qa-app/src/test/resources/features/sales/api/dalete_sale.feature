Feature: Delete sale API

  @api-sale-delete
  Scenario: Verify a logged-in Admin can delete an existing sale
    Given an admin authorization token is available
    And a sale with id 24 exists
    When the admin sends DELETE request to delete sale with id 24
    Then the response status code should be 204
    And the sale should be deleted successfully
    And the response body should be empty

  @api-sale-delete
  Scenario: Verify a logged-in Non-Admin user cannot delete an existing sale
    Given a non-admin authorization token is available
    And a sale with id 25 exists
    When the non-admin sends DELETE request to delete sale with id 25
    Then the response status code should be 403
    And the sale should not be deleted successfully
