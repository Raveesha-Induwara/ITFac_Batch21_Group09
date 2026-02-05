Feature: Create sale API

  @api-sale-create
  Scenario: Verify Admin can successfully sell a plant with valid quantity
    Given an admin authorization token is available
    And a plant with id 3 exists
    When the admin sends POST request to sell plant with id 3 and quantity 2
    Then the response status code should be 201
    # And the sale record should be created successfully
    And the response should contain valid sale details

  @api-sale-create
  Scenario: Verify Non-Admin cannot sell a plant
    Given a non-admin authorization token is available
    And a plant with id 3 exists
    When the non-admin sends POST request to sell plant with id 3 and quantity 2
    Then the response status code should be 403
    And the response should contain error message "Access denied"
