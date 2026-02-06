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
    }

    @Then("the error message should contain {string}")
    public void verifyErrorMessage(String expectedMsg) {
        String actualMsg = plantPage.getValidationMessage();

        if (lastInputName.isEmpty() && expectedMsg.contains("Plant name")) {
            Assert.assertTrue("Missing 'required' error. Found: " + actualMsg,
                    actualMsg.contains("Plant name is required"));
            Assert.assertTrue("Missing 'length' error. Found: " + actualMsg,
                    actualMsg.contains("Plant name must be between 3 and 25 characters"));
        } else {
            Assert.assertTrue("Expected error missing. Found: " + actualMsg,
                    actualMsg.contains(expectedMsg));
        }
    }

    @And("the plant form should not be submitted")
    public void verifyFormStatus() {
        Assert.assertTrue("Form closed unexpectedly!", plantPage.isFormStillDisplayed());
    }
}
