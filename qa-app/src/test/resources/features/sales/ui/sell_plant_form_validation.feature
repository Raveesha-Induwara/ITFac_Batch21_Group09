Feature: Sell Plant Form Validation

  Background:
    Given the user is logged in as an admin

  @sell-plant-validation
  Scenario: Show error when Plant is not selected
    When the user clicks on "Sales" in the side navigation
    Then the "Sales" section should be highlighted in the side navigation
    And the user should be navigated to the "Sales" page
    When the user clicks on "Sell Plant"
    Then the Sell Plant page should load successfully
    When the user leaves the Plant dropdown unselected
    And fills in the other required fields
    And clicks on "Sell"
    Then an error message should be displayed near the Plant dropdown
    # And the form should not submit
    And the URL should remain "/ui/sales/new"

  @sell-plant-validation
  Scenario Outline: Show error when Quantity is invalid
    When the user clicks on "Sales" in the side navigation
    Then the "Sales" section should be highlighted in the side navigation
    And the user should be navigated to the "Sales" page
    When the user clicks on "Sell Plant"
    Then the Sell Plant page should load successfully
    When the user selects a plant from the Plant dropdown
    And enters quantity "<quantity>"
    And clicks on "Sell"
    Then an error message should be displayed near the Quantity field
    And the quantity error message should indicate invalid quantity
    And the URL should remain "/ui/sales/new"

    Examples:
      | quantity |
      | 0        |
      | -1       |
