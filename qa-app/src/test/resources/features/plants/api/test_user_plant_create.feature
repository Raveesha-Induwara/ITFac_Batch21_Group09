Feature: API Authorization Security Tests

  Scenario: Verify that a standard user is forbidden from creating a plant
    When Non Admin create a new plant in category 2 with name "Illegal Plant" with price 100 and quantity 10
    Then the plant API should return status 403