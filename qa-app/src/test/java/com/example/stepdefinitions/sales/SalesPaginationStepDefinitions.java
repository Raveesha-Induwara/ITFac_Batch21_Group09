package com.example.stepdefinitions.sales;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;


import com.example.pages.sales.SalesPage;

public class SalesPaginationStepDefinitions {

    private SalesPage salesPage;

    public SalesPaginationStepDefinitions() {
        this.salesPage = new SalesPage(
            com.example.stepdefinitions.Hooks.getDriver()
        );
    }

    @Then("a maximum of 10 sales records are displayed per page")
    public void maximumOf20SalesRecordsDisplayed() {
        assertTrue(
            "More than 10 sales records are displayed on a single page",
            salesPage.areSalesRecordsWithinPageLimit()
        );
    }

    @Then("pagination controls should be displayed when total sales records are greater than 10")
    public void paginationControlsShouldBeDisplayed() {
        assertTrue(
            "Pagination controls are not visible",
            salesPage.hasPaginationControls()
        );
    }

    @Then("the user can navigate between pages using the pagination controls")
    public void userCanNavigateBetweenPages() {
        assertTrue(
            "Unable to navigate to next page using pagination controls",
            salesPage.canNavigateBetweenPages()
        );
    }
}
