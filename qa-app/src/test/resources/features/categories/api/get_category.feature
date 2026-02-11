Feature: Retrieve category API

    @api-category-retrieve
    Scenario: Verify non-admin user can retrieve all categories without filters.
        Given a non-admin authorization token is available
        When the user sends a GET request to retrieve all categories without filters
        Then the response status code should be 200
        And the response should contain valid category list

    @api-category-retrieve
    Scenario: Verify non-admin user can filter categories using name.
        Given a non-admin authorization token is available
        When the user sends a GET request to retrieve categories filtered by name "<category_name>"
        Then the response status code should be 200
        And the response should contain category name "<category_name>"

        Examples:
            | category_name  |
            | Flower         |
            | Banana         |
            | Tree           |


    @api-category-retrieve
    Scenario: Verify non-admin user can retrieve sub-categories using parentId.
        Given a non-admin authorization token is available
        When the user sends a GET request to retrieve subcategories filtered by parentId <parent_id>
        Then the response status code should be 200
        And the response should contain valid category list

        Examples:
            | parent_id  |
            | 12         |

    @api-category-retrieve
    Scenario: Verify non-admin user can retrieve categories using both name and parentId together.
        Given a non-admin authorization token is available
        When the user sends a GET request to retrieve categories filtered by name "<category_name>" and parentId <parent_id>
        Then the response status code should be 200
        And the response should contain valid category list

        Examples:
            | category_name  | parent_id |
            | Anthurium      | 12        |
            | Fruit          | 3         |
            | Tree           | 12        |

    @api-category-retrieve
    Scenario: Verify non-admin user can not retrieve categories with invalid data type for parentId.
        Given a non-admin authorization token is available
        When the user sends a GET request with non-numeric value for parentId "<parent_id>"
        Then the response status code should be 400

        Examples:
            | parent_id  |
            | abc        |
            | !@#        |
            | 12.34      |
