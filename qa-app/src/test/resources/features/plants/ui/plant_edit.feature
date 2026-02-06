@214009F @plant_edit
Feature: Plant Edit Functionality

  Background:
    Given the user is logged in as an admin
    And the user is on the "plants" page
    And at least one plant exists in the system

  @214009F @positive
  Scenario Outline: Admin successfully edits plant with valid data
    When the admin clicks the edit button for the first plant
    And the admin is on the edit plant page
    And the admin clears and enters plant name "<plantName>"
    And the admin selects category "<category>"
    And the admin clears and enters price "<price>"
    And the admin clears and enters quantity "<quantity>"
    And the admin clicks the save button
    Then the plant should be updated successfully
    And the success message should be "Plant updated successfully"
    And the user should be navigated back to the plant list page
    And the updated plant "<plantName>" should be visible in the list with price "<price>", quantity "<quantity>", and category "<category>"

    Examples:
      | plantName    | category | price  | quantity | test_case_description               |
      | A Test Plant | Rose     | 150.50 | 25       | Update all fields with Rose         |
      | A Test Plant | Lily     | 75.00  | 0        | Update same plant with Lily and 0   |

  @214009F @negative
  Scenario Outline: Verify field validation rules
    When the admin clicks the edit button for the first plant
    And the admin is on the edit plant page
    And the admin clears and enters plant name "<plantName>"
    And the admin clears and enters price "<price>"
    And the admin clears and enters quantity "<quantity>"
    And the admin clicks the save button
    Then the error message should contain "<expectedError>"
    And the plant should not be updated
    And the edit form should still be displayed

    Examples:
      | plantName                     | price  | quantity | expectedError                       | validation_rule              |
      |                               | 100.00 | 10       | Plant name is required              | Name: Required               |
      | AB                            | 100.00 | 10       | Plant name must be between 3 and 25 | Name: Min length (3)         |
      | This name is way too long now | 100.00 | 10       | Plant name must be between 3 and 25 | Name: Max length (25)        |
      | DUPLICATE_NAME                | 100.00 | 10       | Plant name already exists           | Name: Duplicate (BUG)        |
      | Valid Plant Name              |        | 10       | Price is required                   | Price: Required              |
      | Valid Plant Name              | 0      | 10       | Price must be greater than 0        | Price: Must be > 0           |
      | Valid Plant Name              | -50.00 | 10       | Price must be greater than 0        | Price: No negative           |
      | Valid Plant Name              | 100.00 |          | Quantity is required                | Quantity: Required           |
      | Valid Plant Name              | 100.00 | -10      | Quantity cannot be negative         | Quantity: No negative        |
