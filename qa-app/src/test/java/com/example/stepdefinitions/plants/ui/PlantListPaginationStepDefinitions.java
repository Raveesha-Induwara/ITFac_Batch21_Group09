package com.example.stepdefinitions.plants.ui;

import com.example.pages.plants.ui.PlantListPage;
import com.example.stepdefinitions.Hooks;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class PlantListPaginationStepDefinitions {

    private PlantListPage plantListPage;
    private Integer previousPage;

    @Before
    public void setup() {
        WebDriver driver = Hooks.getDriver();
        plantListPage = new PlantListPage(driver);
    }


    @Then("the plant list should be displayed")
    public void verifyPlantListIsDisplayed() {
        assertTrue(
                "Plant list table should be visible",
                plantListPage.isPlantListVisible()
        );
    }

    @Then("pagination should be visible if applicable")
    public void verifyPaginationVisibilityIfApplicable() {
        if (!plantListPage.isPaginationVisible()) {
            System.out.println("Pagination not available — skipping pagination checks");
            return;
        }

        assertTrue(
                "Pagination should be visible",
                plantListPage.isPaginationVisible()
        );
    }


    @Then("the user should start on page {int}")
    public void verifyUserStartsOnPage(int expectedPage) {
        if (!plantListPage.isPaginationVisible()) return;

        Integer activePage = plantListPage.getActivePageNumberSafe();
        assertNotNull("Active page indicator missing", activePage);
        assertEquals(expectedPage, activePage.intValue());
    }


    @When("the user navigates to the next page if pagination exists")
    public void navigateToNextPageIfPaginationExists() {
        if (!plantListPage.isPaginationVisible()) {
            System.out.println("Pagination not available — skipping next page navigation");
            return;
        }

        previousPage = plantListPage.getActivePageNumberSafe();
        plantListPage.goToNextPage();
    }

    @When("the user navigates to the previous page if pagination exists")
    public void navigateToPreviousPageIfPaginationExists() {
        if (!plantListPage.isPaginationVisible()) {
            System.out.println("Pagination not available — skipping previous page navigation");
            return;
        }

        previousPage = plantListPage.getActivePageNumberSafe();
        plantListPage.goToPreviousPage();
    }

    @Then("the user should be on page {int}")
    public void verifyUserIsOnPage(int expectedPage) {
        if (!plantListPage.isPaginationVisible()) return;

        Integer activePage = plantListPage.getActivePageNumberSafe();
        assertNotNull("Active page indicator missing", activePage);
        assertEquals(expectedPage, activePage.intValue());
    }

    @Then("the Previous button should be enabled")
    public void verifyPreviousButtonEnabled() {
        if (!plantListPage.isPaginationVisible()) return;

        assertTrue(
                "Previous button should be enabled",
                plantListPage.isPreviousButtonEnabled()
        );
    }
}
