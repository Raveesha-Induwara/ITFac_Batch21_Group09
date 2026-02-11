Feature: Create category API

  @api-category-create
  Scenario: Verify Admin can successfully add a category with valid details
    Given an admin authorization token is available
    When the admin sends a POST request to add main category with "<categoryName>" as the main category name
    Then the response status code should be <statusCode> for category creation
    And the response should contain valid newly created category details

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

  @api-category-create
  Scenario: Verify API prevents subcategory creation with non-existing parent id.
    Given an admin authorization token is available
    When the admin sends a POST request to add subcategory with "<categoryName>" as the subcategory name and <ParentID> as the parent category id
    Then the response status code should be <statusCode> for category creation
    And the response should contain an error message indicating parent category not found

  Examples:
      | categoryName |  ParentID | statusCode |
      | RedRose      |  100      | 404        |

  @api-category-create
  Scenario: Verify Admin can successfully add a subcategory with valid details
    Given an admin authorization token is available
    When the admin sends a POST request to add subcategory with "<categoryName>" as the subcategory name and <ParentID> as the parent category id
    Then the response status code should be <statusCode> for category creation
    And the response should contain valid newly created category details

    Examples:
      | categoryName |  ParentID | statusCode |
      | Rose         |  12      | 201        |

  @api-category-create
  Scenario: Verify API prevents unauthorized subcategory creation.
    When the user sends a POST request to add subcategory without authentication "<categoryName>" <ParentID>
    Then the response status code should be <statusCode> for category creation
    And the response should contain an error message indicating unauthorized access

    Examples:
      | categoryName |  ParentID | statusCode |
      | Rose         |  12       | 401        |


  @api-category-create
  Scenario: Verify API prevents category creation when category name does not meet minimum length or exceeds maximum length.
  Given an admin authorization token is available
  When the admin sends a POST request to add main category with "<categoryName>" as the main category name
  Then the response status code should be <statusCode> for category creation
  And the response should contain an error message indicating bad request

  Examples:
      | categoryName      | statusCode |
      | AnthuriumFlower   | 400        |
      | A                 | 400        |

