@api @plants
Feature: Plant Management API Validation

  Scenario Outline: Create plant and verify against Swagger documentation
    When Admin create a new plant in category <catId> with name "<name>" with price <price> and quantity <quantity>
    Then the plant API should return status <expectedStatus>
    And the response message should be "<expectedMessage>"

    Examples:
      | catId | name          | price | quantity | expectedStatus | expectedMessage   |
      | 2     | Anthufvdsrium | 150   | 25       | 201            | Anthufvdsrium     |
      | 2     | Anthufvdsrium | 150   | 25       | 400            | Plant 'Anthufvdsrium' already exists in this category         |
      | 2     | Red Orchid    | -10   | 25       | 400            | Validation failed |
      | 5     | New Plant     | 100   | 25       | 404            | Category not found|
      | 2     | New Plant     | 100   | -5       | 400            | Validation failed |