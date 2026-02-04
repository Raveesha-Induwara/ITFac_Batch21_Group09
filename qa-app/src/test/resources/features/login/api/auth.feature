@api
Feature: Authentication API Verification

  Scenario Outline: Verify login for different user roles
    When I attempt to login with username "<username>" and password "<password>"
    Then the API response status should be 200
    And the response includes a valid token

    Examples:
      | username | password |
      | admin    | admin123 |
      | testuser | test123  |