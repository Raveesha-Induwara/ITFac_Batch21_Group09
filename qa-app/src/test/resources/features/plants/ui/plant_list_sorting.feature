@214009F @plant_list_sorting
Feature: UI - Plant list sorting

  Background:
    Given the user is logged in as an admin
    And the user is on the "plants" page

  Scenario: Verify plant list sorting by Name, Price, and Quantity
    Then the plant list should be displayed

    And the "Name" column should have a sort header
    When the user sorts the plant list by "Name"
    Then the plant list should be sorted by "Name"

    And the "Price" column should have a sort header
    When the user sorts the plant list by "Price"
    Then the plant list should be sorted by "Price"

    And the "Quantity" column should have a sort header
    When the user sorts the plant list by "Quantity"
    Then the plant list should be sorted by "Quantity"


