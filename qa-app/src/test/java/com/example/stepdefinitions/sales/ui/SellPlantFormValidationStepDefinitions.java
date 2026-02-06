package com.example.stepdefinitions.sales.ui;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import com.example.pages.sales.ui.SalesPage;
import com.example.pages.sales.ui.SellPlantPage;
import com.example.stepdefinitions.Hooks;

public class SellPlantFormValidationStepDefinitions {

    private WebDriver driver;
    private SellPlantPage sellPlantPage;
    private SalesPage salesPage;

    public SellPlantFormValidationStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.sellPlantPage = new SellPlantPage(driver);
        this.salesPage = new SalesPage(driver);
    }

    @When("the user leaves the Plant dropdown unselected")
    public void theUserLeavesThePlantDropdownUnselected() {
        sellPlantPage.leavePlantUnselected();
    }

    @When("fills in the other required fields")
    public void fillsInOtherRequiredFields() {
        // Fill quantity (example: 5)
        sellPlantPage.fillQuantity(5);
    }

    @When("clicks on {string}")
    public void clicksOnButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Sell")) {
            sellPlantPage.submitForm(); // submit form, not just click
        }
    }

    @Then("an error message should be displayed near the Plant dropdown")
    public void anErrorMessageShouldBeDisplayed() {
        assertTrue("Plant error message is not displayed", sellPlantPage.isPlantErrorMessageDisplayed());
    }

    @Then("the form should not submit")
    public void theFormShouldNotSubmit() {
        assertTrue("Form was submitted unexpectedly", sellPlantPage.isFormStillOnPage());
    }

    @Then("the URL should remain {string}")
    public void urlShouldRemainUnchanged(String expectedUrlEnding) {
        assertTrue("URL changed unexpectedly", driver.getCurrentUrl().contains(expectedUrlEnding));
    }

    // @Then("the error message text should indicate that selection of a plant is
    // required")
    // public void errorMessageTextShouldBeCorrect() {
    // String expectedMessage = "Plant is required"; // matches your HTML text
    // assertEquals("Error message text is incorrect",
    // expectedMessage,
    // sellPlantPage.getPlantErrorMessageText());
    // }

    @When("the user selects a plant from the Plant dropdown")
    public void theUserSelectsAPlantFromTheDropdown() {
        sellPlantPage.selectFirstAvailablePlant();
    }

    @When("enters quantity {string}")
    public void entersQuantity(String quantity) {
        sellPlantPage.fillQuantity(quantity);
    }

    @Then("an error message should be displayed near the Quantity field")
    public void anErrorMessageShouldBeDisplayedNearTheQuantityField() {
        assertTrue(
                "Quantity error message is not displayed",
                sellPlantPage.isQuantityValidationDisplayed());
    }

    @Then("the quantity error message should indicate invalid quantity")
    public void theQuantityErrorMessageShouldIndicateInvalidQuantity() {
        assertTrue(
                sellPlantPage.getQuantityValidationMessage()
                        .contains("greater than or equal to 1"));

    }

}
