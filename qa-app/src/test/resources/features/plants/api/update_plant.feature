@214009F @plant_update_validation
Feature: Plant Update API Validation

  Scenario Outline: Verify plant update with different validation cases
    When I update plant <plantId> with name "<name>" price <price> quantity <quantity> subCategory <subCategoryId> using "<user>" credentials
    Then the update response status should be <statusCode>

    Examples:
      | plantId | name            | price | quantity | subCategoryId | user     | statusCode |
      | 48      | Anthurium       | 150   | 25       | 6             | admin    | 200        |
      | 48      |                 | 150   | 25       | 6             | admin    | 400        |
      | 48      | AB              | 150   | 25       | 6             | admin    | 400        |
      | 48      | ThisIsAVeryLongPlantNameThatExceedsTwentyFiveCharactersLimit | 150 | 25 | 6 | admin | 400 |
      | 48      | Orchid          | 0     | 25       | 6             | admin    | 400        |
      | 48      | Orchid          | -10   | 25       | 6             | admin    | 400        |
      | 48      | Lily            | 100   | 0        | 6             | admin    | 200        |
      | 48      | Lily            | 100   | -5       | 6             | admin    | 400        |
      | 48      | Tulip           | 120   | 15       | 6             | testuser | 403        |
