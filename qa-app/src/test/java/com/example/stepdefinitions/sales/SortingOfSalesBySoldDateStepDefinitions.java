package com.example.stepdefinitions.sales;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;


import com.example.pages.sales.SalesPage;

public class SortingOfSalesBySoldDateStepDefinitions {

    private SalesPage salesPage;

    public SortingOfSalesBySoldDateStepDefinitions() {
        this.salesPage = new SalesPage(
            com.example.stepdefinitions.Hooks.getDriver()
        );
    }

    @Given("{string} data exists in the system")
    public void salesDataExistsInTheSystem(String menuName) {
        assertTrue("Sales data should exist", salesPage.hasSalesData());
    }

    @Then("the sales records should be displayed sorted by Sold Date in descending order")
    public void theSalesRecordsShouldBeDisplayedSortedBySoldDateInDescendingOrder() {
        assertTrue(
                "Sales records are not sorted by Sold Date (latest to oldest)",
                salesPage.isSalesSortedBySoldDateDescending()
        );
    }
}
