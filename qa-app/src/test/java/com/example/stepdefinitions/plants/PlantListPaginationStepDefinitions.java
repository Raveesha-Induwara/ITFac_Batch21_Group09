package com.example.stepdefinitions.plants;

import com.example.pages.plants.PlantListPage;
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

    @Then("pagination controls should be displayed according to data size")
    public void paginationConditional() {
        int plantCount = plantListPage.getPlantCount();

        if (plantCount <= 10) {
            System.out.println("Pagination not expected (plant count = " + plantCount + ")");
            return; // skip safely, no failure, no error
        }

        assertTrue(
                "Pagination should be visible when plants > 10",
                plantListPage.isPaginationVisible()
        );
    }

    @When("the user goes to the next page")
    public void nextPage() {
        if (!plantListPage.isPaginationVisible()) {
            System.out.println("Skipping next page navigation (pagination not available or next is disabled)");
            return;
        }

        previousPage = plantListPage.getActivePageNumberSafe();
        plantListPage.goToNextPage();
    }

    @When("the user goes to the previous page")
    public void previousPage() {
        if (!plantListPage.isPaginationVisible()) {
            System.out.println("Skipping previous page navigation (pagination not available)");
            return;
        }
        previousPage = plantListPage.getActivePageNumberSafe();
        plantListPage.goToPreviousPage();
    }

    @When("the user navigates to page {int}")
    public void navigateToPage(int page) {
        if (!plantListPage.isPaginationVisible()) {
            System.out.println("Skipping navigation to page " + page + " (pagination not available or page does not exist)");
            return;
        }

        plantListPage.goToPage(page);
    }

    @Given("the user is on page {int}")
    public void userIsOnPage(int page) {
        if (!plantListPage.isPaginationVisible()) {
            System.out.println("Cannot go to page " + page + " (pagination not available)");
            return;
        }

        plantListPage.goToPage(page);
    }

    /* ---------- ASSERTIONS ---------- */

    @Then("the plant list should be displayed")
    public void plantListDisplayed() {
        assertTrue("Plant table should be visible", plantListPage.isPlantListVisible());
        assertTrue("Plant list should not be empty", plantListPage.getPlantCount() > 0);
    }

    @Then("the user should move to the next page")
    public void movedNext() {
        Integer current = plantListPage.getActivePageNumberSafe();
        assertNotNull("Active page indicator missing", current);
        assertTrue(current > previousPage);
    }

    @Then("the user should move to the previous page")
    public void movedPrevious() {
        Integer current = plantListPage.getActivePageNumberSafe();
        assertNotNull("Active page indicator missing", current);
        assertTrue(current < previousPage);
    }

    @Then("the user should be on page {int}")
    public void verifyPage(int expected) {
        Integer actual = plantListPage.getActivePageNumberSafe();
        assertNotNull("Active page indicator missing", actual);
        assertEquals(expected, actual.intValue());
    }

    @Then("the Previous button should be {string}")
    public void previousButtonState(String state) {
        boolean expected = state.equalsIgnoreCase("enabled");
        assertEquals(expected, plantListPage.isPreviousButtonEnabled());
    }

    @Then("page {int} should be highlighted as active")
    public void pageHighlighted(int page) {
        Integer active = plantListPage.getActivePageNumberSafe();
        assertNotNull("Active page indicator missing", active);
        assertEquals(page, active.intValue());
    }
}
