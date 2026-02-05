Feature: Retrieve all sales API

  @api-sales-get-all
  Scenario: Verify Admin can retrieve all sales successfully
    Given an admin authorization token is available
    And sales records exist in the system
    When the admin sends GET request to retrieve all sales
    Then the response status code should be 200
    And the response should contain a list of sales
    And each sale should contain valid sale details
