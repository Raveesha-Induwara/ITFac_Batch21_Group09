package com.example.stepdefinitions.sales;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;


import com.example.pages.sales.SalesPage;

public class SellButtonAccessStepDefinitions {

    private SalesPage salesPage;

    public SellButtonAccessStepDefinitions() {
        this.salesPage = new SalesPage(
            com.example.stepdefinitions.Hooks.getDriver()
        );
    }

    @Then("the Sell Plant button should be visible")
    public void theSellPlantButtonShouldBeVisible() {
        assertTrue(
            "Sell Plant button should be visible for Admin",
            salesPage.isSellPlantButtonVisible()
        );
    }
}
