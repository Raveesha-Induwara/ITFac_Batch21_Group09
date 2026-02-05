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

    @When("the user clicks on the {string} sort column")
    public void theUserClicksOnTheSortColumn(String columnName) {
        try {
            plantListPage.clickSortColumn(columnName);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("the plant list should be sorted by {string}")
    public void thePlantListShouldBeSortedBy(String columnName) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isSorted;
        switch (columnName.toLowerCase()) {
            case "name":
                isSorted = plantListPage.isListSortedByName();
                assertTrue("Plant list is not sorted by Name", isSorted);
                System.out.println("Plant list is correctly sorted by Name");
                break;
            case "price":
                isSorted = plantListPage.isListSortedByPrice();
                assertTrue("Plant list is not sorted by Price", isSorted);
                System.out.println("Plant list is correctly sorted by Price");
                break;
            case "quantity":
                isSorted = plantListPage.isListSortedByQuantity();
                assertTrue("Plant list is not sorted by Quantity", isSorted);
                System.out.println("Plant list is correctly sorted by Quantity");
                break;
            default:
                fail("Invalid column name: " + columnName);
        }
    }

    @Then("the {string} column should have a sort header")
    public void theColumnShouldHaveASortHeader(String columnName) {
        assertTrue(
            columnName + " column should have an arrow icon inside span tag",
            plantListPage.hasArrowIcon(columnName)
        );
        System.out.println("✓ " + columnName + " column has arrow icon inside <span> tag");
    }
}
