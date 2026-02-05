package com.example.stepdefinitions.plants;

import com.example.pages.plants.PlantListPage;
import com.example.pages.plants.AddPlantPage;
import com.example.stepdefinitions.Hooks;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class PlantListEmptyStateStepDefinitions {

    private PlantListPage plantListPage;
    private AddPlantPage addPlantPage;

    @Before
    public void setup() {
        WebDriver driver = Hooks.getDriver();
        plantListPage = new PlantListPage(driver);
        addPlantPage = new AddPlantPage(driver);
    }

    @Given("there are no published plants in the system")
    public void ensureNoPlantsExist() {
        addPlantPage.navigateToPlantPage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int currentCount = plantListPage.getPlantCount();
        System.out.println("Current plant count: " + currentCount);

        if (currentCount > 0) {
            System.out.println("WARNING: Expected no plants, but found " + currentCount + " plants in the system");
            System.out.println("This test assumes the environment should be clean before running");
        }
    }

    @When("the user views the plant list")
    public void userViewsPlantList() {
        addPlantPage.navigateToPlantPage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("the plant list should display {string} message")
    public void verifyNoDataMessage(String expectedMessage) {
        assertTrue(
                "No data message should be displayed when there are no plants",
                plantListPage.isNoDataMessageDisplayed()
        );

        String actualMessage = plantListPage.getNoDataMessageText();
        System.out.println("Actual message displayed: '" + actualMessage + "'");

        assertTrue(
                "Expected message containing '" + expectedMessage + "' but got '" + actualMessage + "'",
                actualMessage.contains(expectedMessage)
        );
    }

    @Then("pagination controls should not be displayed")
    public void verifyNoPagination() {
        assertFalse(
                "Pagination should not be visible when there are no plants",
                plantListPage.isPaginationVisible()
        );
    }
}
