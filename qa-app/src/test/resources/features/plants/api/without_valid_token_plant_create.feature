Feature: API Authentication Security Tests

  Scenario: Verify that a request without a token is rejected
    When a request is sent to create a plant in category 2 without any token
    Then the plant API should return status 401
    And the response message should be "Unauthorized"