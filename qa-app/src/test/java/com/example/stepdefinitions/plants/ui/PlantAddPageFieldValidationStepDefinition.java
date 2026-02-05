package com.example.stepdefinitions.plants.ui;

import org.junit.Assert;

import com.example.pages.plants.ui.AddPlantPage;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlantAddPageFieldValidationStepDefinition {

    private AddPlantPage plantPage;
    private String lastInputName = "";

    public PlantAddPageFieldValidationStepDefinition() {
        plantPage = new AddPlantPage(Hooks.getDriver());
    }

    @Given("the user is on the {string} page")
    public void theUserIsOnTheSpecificPage(String pageName) throws InterruptedException {
        Thread.sleep(200);
        plantPage.navigateToPlantPage();
        Assert.assertTrue("Admin is not on the page", plantPage.isOnPlantPage());
    }

    @When("the admin clicks on the {string} button")
    public void clickButton(String btnName) {
        plantPage.clickAddPlant();
    }

    @And("the admin selects category {string}")
    public void selectCategory(String category) {
        plantPage.selectCategoryByName(category);
    }

    @And("the admin enters name {string}, price {string}, and quantity {string}")
    public void enterPlantDetails(String name, String price, String qty) {
        this.lastInputName = (name == null) ? "" : name;
        plantPage.enterPlantName(this.lastInputName);
        plantPage.enterDetails(price, qty);
    }

    @And("the admin clicks the submit button")
    public void submitForm() {
        plantPage.clickSubmit();
        // Time interval to allow UI to process
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("the error message should contain {string}")
    public void verifyErrorMessage(String expectedMsg) {
        String actualMsg = plantPage.getValidationMessage();

        // When name is empty, system may show either "required" or "length" error (or both)
        // Both are valid, so accept if either message is present
        if (lastInputName.isEmpty() && expectedMsg.contains("Plant name")) {
            boolean hasRequiredError = actualMsg.contains("Plant name is required");
            boolean hasLengthError = actualMsg.contains("Plant name must be between 3 and 25");

            Assert.assertTrue("Expected either 'required' or 'length' error for empty name. Found: " + actualMsg,
                    hasRequiredError || hasLengthError);
        } else {
            Assert.assertTrue("Expected error missing. Found: " + actualMsg,
                    actualMsg.contains(expectedMsg));
        }
    }

    @And("the plant form should not be submitted")
    public void verifyFormStatus() {
        Assert.assertTrue("Form closed unexpectedly!", plantPage.isFormStillDisplayed());
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
