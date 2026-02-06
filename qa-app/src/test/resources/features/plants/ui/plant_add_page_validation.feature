@214013L
Feature: Plant Management Validation

  Background:
    Given the user is logged in as an admin
    And the user is on the "plants" page


  Scenario Outline: Verify plant add form field validations
    When the admin clicks on the "Add Plant" button
    And the admin selects category "<category>"
    And the admin enters name "<plant_name>", price "<price>", and quantity "<quantity>"
    And the admin clicks the submit button
    Then the error message should contain "<expected_error>"
    And the plant form should not be submitted

    Examples:
      | category | plant_name                         | price | quantity | expected_error                       | comment           |
      | Select   | Rose                               | 100   | 10       | Category is required                 | Invalid Category  |
      | rose     |                                    | 100   | 10       | Plant name is required               | Empty Name        |
      | rose     | Ro                                 | 100   | 10       | Plant name must be between 3 and 25  | Short Name        |
      | rose     | Rooooooooooooooooooooooooooooooose | 100   | 10       | Plant name must be between 3 and 25  | Long Name         |
      | rose     | Rose                               | -5    | 10       | Price must be greater than 0         | Negative Price    |
      | rose     | Rose                               |       | 10       | Price is required                    | Non-numeric Price |
      | rose     | Rose                               | 100   | -3       | Quantity cannot be negative          | Negative Quantity |
      | rose     | Tulip                              | 150   |          | Quantity is required                 | Empty Quantity    |