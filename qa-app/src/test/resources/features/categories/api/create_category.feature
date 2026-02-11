Feature: Create category API

  @api-category-create
  Scenario: Verify Admin can successfully add a category with valid details
    Given an admin authorization token is available
    When the admin sends a POST request to add main category with "<categoryName>" as the main category name
    Then the response status code should be <statusCode> for category creation
    And the response should contain valid category details

    Examples:
      | categoryName |  statusCode |
      | TestFlower   |  201        |


  @api-category-create
  Scenario: Verify API prevents unauthorized category creation.
    When the user sends a POST request to add main category with "Flower" as the main category name without authentication
    Then the response status code should be 401 for category creation
    And the response should contain an error message indicating unauthorized access

  
  @api-category-create
  Scenario: Verify API prevents duplicate main category creation.
    Given an admin authorization token is available
    When a main category named "Flower" already exists in the system
    When the admin sends a POST request to add main category with "Flower" as the main category name
    Then the response status code should be 400 for category creation
    And the response should contain an error message indicating duplicate category

