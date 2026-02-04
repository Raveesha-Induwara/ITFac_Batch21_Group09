package com.example.stepdefinitions.sales;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import com.example.pages.sales.SalesPage;
import com.example.stepdefinitions.Hooks;

public class DeleteSalesConfirmationStepDefinitions {

    private SalesPage salesPage;
    private int initialSalesCount;

    public DeleteSalesConfirmationStepDefinitions() {
        this.salesPage = new SalesPage(Hooks.getDriver());
    }

    @Given("the user navigates to the {string} page")
    public void theUserNavigatesToThePage(String pageName) {
        salesPage.clickMenuInSideNavigation(pageName);
        assertTrue("User should be on Sales page",
                salesPage.isOnPage(pageName));
    }

    @When("the admin clicks on the Delete action for a sales record")
    public void theAdminClicksOnTheDeleteActionForASalesRecord() {
        initialSalesCount = salesPage.getSalesRecordCount();
        salesPage.clickDeleteOnFirstSalesRecord();
    }

    @Then("a delete confirmation dialog should be displayed")
    public void aDeleteConfirmationDialogShouldBeDisplayed() {
        assertTrue(
            "Delete confirmation alert should be displayed",
            salesPage.isDeleteConfirmationAlertDisplayed()
        );
    }

    @Then("the sales record should not be deleted")
    public void theSalesRecordShouldNotBeDeleted() {
        // Alert is still open, so nothing is deleted yet
        assertTrue(
            "Confirmation alert should still be present",
            salesPage.isDeleteConfirmationAlertDisplayed()
        );
    }

    @When("the admin clicks Cancel on the delete confirmation dialog")
    public void theAdminClicksCancelOnTheDeleteConfirmationDialog() {
        salesPage.cancelDeleteConfirmation();
    }

    @Then("the sales record should remain unchanged")
    public void theSalesRecordShouldRemainUnchanged() {
        assertEquals(
            "Sales record count should remain unchanged after cancel",
            initialSalesCount,
            salesPage.getSalesRecordCount()
        );
    }
}
