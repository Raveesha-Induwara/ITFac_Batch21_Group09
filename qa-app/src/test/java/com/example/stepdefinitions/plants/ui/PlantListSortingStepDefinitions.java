package com.example.stepdefinitions.plants.ui;

import com.example.pages.plants.ui.PlantListPage;
import com.example.stepdefinitions.Hooks;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class PlantListSortingStepDefinitions {

    private PlantListPage plantListPage;

    @Before
    public void setup() {
        WebDriver driver = Hooks.getDriver();
        plantListPage = new PlantListPage(driver);
    }

    @When("the user sorts the plant list by {string}")
    public void sortPlantListByColumn(String columnName) {
        plantListPage.clickSortColumn(columnName);
    }

    @Then("the plant list should be sorted by {string}")
    public void verifyPlantListSortedBy(String columnName) {

        boolean sorted;

        switch (columnName.toLowerCase()) {
            case "name":
                sorted = plantListPage.isListSortedByName();
                break;
            case "price":
                sorted = plantListPage.isListSortedByPrice();
                break;
            case "quantity":
                sorted = plantListPage.isListSortedByQuantity();
                break;
            default:
                throw new IllegalArgumentException("Invalid sort column: " + columnName);
        }

        assertTrue(
                "Plant list is not sorted by " + columnName,
                sorted
        );
    }

    @Then("the {string} column should have a sort header")
    public void verifySortHeaderExists(String columnName) {
        assertTrue(
                columnName + " column should have a sort arrow icon",
                plantListPage.hasArrowIcon(columnName)
        );
    }
}
