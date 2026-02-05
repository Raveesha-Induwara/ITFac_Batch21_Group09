Feature: Retrieve all sales API

  @api-sales-get-all
  Scenario: Verify Admin can retrieve all sales successfully
    Given an admin authorization token is available
    And sales records exist in the system
    When the admin sends GET request to retrieve all sales
    Then the response status code should be 200
    And the response should contain a list of sales
    And each sale should contain valid sale details

  @api-sales-get-all
  Scenario: Verify Non-Admin can retrieve all sales successfully
    Given a non-admin authorization token is available
    And sales records exist in the system
    When the non-admin sends GET request to retrieve all sales
    Then the response status code should be 200
    And the response should contain a list of sales
    And each sale should contain valid sale details

  @api-sales-get-all
  Scenario: Verify Admin can retrieve sale details by valid Sale ID
    Given an admin authorization token is available
    And a valid sale id exists
    When the admin sends a GET request to retrieve sale details with the valid sale id
    Then the response status code should be 200
    And the response body should contain sale details

  @api-sales-get-all
  Scenario: Verify Non-Admin can retrieve sale details by valid Sale ID
    Given a non-admin authorization token is available
    And a valid sale id exists
    When the non-admin sends a GET request to retrieve sale details with the valid sale id
    Then the response status code should be 200
    And the response body should contain sale details
