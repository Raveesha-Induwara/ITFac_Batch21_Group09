package com.example.stepdefinitions.sales;

import io.cucumber.java.en.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import static org.junit.Assert.assertTrue;

import com.example.pages.login.api.AuthService;
import com.example.pages.sales.SalesPage;
import com.example.pages.sales.api.SalesSeedService;

public class SalesPaginationStepDefinitions {

    private SalesPage salesPage;
    private SalesSeedService salesSeedService;
    private Response response;

    public SalesPaginationStepDefinitions() {
        this.salesPage = new SalesPage(
                com.example.stepdefinitions.Hooks.getDriver());
    }

    @Given("there are more than 10 sales records in the system")
    public void seedSalesData() {
        if (salesSeedService.getSalesCount() > 10) {
            return; 
            
        }
        // create 12 sales records
        for (int i = 0; i < 12; i++) {
            response = salesSeedService.createSaleAsAdmin(3, 1); 
        }
    }

    @When("a minimum of 11 sales records are displayed per page")
    public void minimumOf11SalesRecordsDisplayed() {
        // assertTrue(
        // salesPage.areSalesRecordsWithinPageLimit());
        int count = salesSeedService.getSalesCount();
        assertTrue(count > 11);
    }

    @Then("pagination controls should be displayed when total sales records are greater than 10")
    public void paginationControlsShouldBeDisplayed() {
        assertTrue(
                salesPage.hasPaginationControls());
    }

    @Then("the user can navigate between pages using the pagination controls")
    public void userCanNavigateBetweenPages() {
        assertTrue(
                salesPage.canNavigateBetweenPages());
    }

}
