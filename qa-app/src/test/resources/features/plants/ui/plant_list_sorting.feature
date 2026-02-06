@214009F @plant_list_sorting
Feature: Plant List Sorting

  Background:
    Given the user is logged in as an admin
    And the user is on the "plants" page

  Scenario: Verify plant list can be sorted by Name
    When the user clicks on the "Name" sort column
    Then the plant list should be sorted by "Name"

  Scenario: Verify plant list can be sorted by Price
    When the user clicks on the "Price" sort column
    Then the plant list should be sorted by "Price"

  Scenario: Verify plant list can be sorted by Quantity
    When the user clicks on the "Quantity" sort column
    Then the plant list should be sorted by "Quantity"

  Scenario: Verify plant list sorting by Name, Price, and Quantity sequentially
    When the user clicks on the "Name" sort column
    Then the plant list should be sorted by "Name"
    When the user clicks on the "Price" sort column
    Then the plant list should be sorted by "Price"
    When the user clicks on the "Quantity" sort column
    Then the plant list should be sorted by "Quantity"

  Scenario: Verify Name, Price, and Stock columns have sort headers with arrow icons
    Then the "Name" column should have a sort header
    And the "Price" column should have a sort header
    And the "Stock" column should have a sort header

