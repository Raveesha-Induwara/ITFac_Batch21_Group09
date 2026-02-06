package com.example.stepdefinitions.sales;

import static org.junit.Assert.assertTrue;

import com.example.pages.sales.SalesPage;
import com.example.pages.sales.SellPlantPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SellPlantPageAccessStepDefinitions {

    private SalesPage salesPage;
    private SellPlantPage sellPlantPage;

    public SellPlantPageAccessStepDefinitions() {
        this.salesPage = new SalesPage(
            com.example.stepdefinitions.Hooks.getDriver()
        );
        this.sellPlantPage = new SellPlantPage(
            com.example.stepdefinitions.Hooks.getDriver()
        );
    }
    
    @When("the user clicks on {string}")
    public void theUserClicksOnButton(String buttonName) {
        salesPage.clickSellButton(buttonName);
    }

    @Then("the Sell Plant page should load successfully")
    public void theSellPlantPageShouldLoad() {
        assertTrue("Sell Plant page did not load", sellPlantPage.isPageLoaded());
    }

    @Then("the page should display all required fields for creating a new sale")
    public void theSellPlantPageShouldHaveRequiredFields() {
        assertTrue("Required fields missing", sellPlantPage.hasAllRequiredFields());
    }
}
