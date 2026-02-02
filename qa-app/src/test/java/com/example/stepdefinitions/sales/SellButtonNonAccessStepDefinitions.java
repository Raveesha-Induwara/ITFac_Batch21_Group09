package com.example.stepdefinitions.sales;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertFalse;

import com.example.pages.Common;
import com.example.pages.sales.SalesPage;
import com.example.stepdefinitions.Hooks;

public class SellButtonNonAccessStepDefinitions {

    private SalesPage salesPage;

    public SellButtonNonAccessStepDefinitions() {
        this.salesPage = new SalesPage(
            com.example.stepdefinitions.Hooks.getDriver()
        );
    }

    @Then("the Sell Plant button should NOT be visible")
    public void theSellPlantButtonShouldNotBeVisible() {
        assertFalse(
            "Sell Plant button should NOT be visible for Non-Admin users",
            salesPage.isSellPlantButtonVisible()
        );
    }
}
