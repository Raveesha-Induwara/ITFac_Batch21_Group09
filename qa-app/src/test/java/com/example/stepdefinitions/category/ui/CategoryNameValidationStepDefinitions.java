package com.example.stepdefinitions.category.ui;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import com.example.pages.category.ui.AddCategoryPage;
import com.example.stepdefinitions.Hooks;

public class CategoryNameValidationStepDefinitions {

    private WebDriver driver;
    private AddCategoryPage categoryPage;

    public CategoryNameValidationStepDefinitions() {
        driver = Hooks.getDriver();
        categoryPage = new AddCategoryPage(driver);
    }

    @When("dthe admin enters category name {string}")
    public void theAdminEntersCategoryName(String categoryName) {
        categoryPage.enterCategoryName(categoryName);
    }

    @When("dthe admin clicks the save button")
    public void theAdminClicksTheSaveButton() {
        categoryPage.clickSubmit();
    }

    @Then("dthe validation error {string} should be displayed below the category name field")
    public void theValidationErrorShouldBeDisplayedBelowTheCategoryNameField(String expectedError) {
        try {
            Thread.sleep(500); // Brief pause for error to appear
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        assertTrue(
                "Validation error is not displayed below the category name field",
                categoryPage.isValidationErrorDisplayed()
        );
        
        String actualError = categoryPage.getValidationErrorMessage();
        assertTrue(
                "Expected error: '" + expectedError + "' but got: '" + actualError + "'",
                actualError.contains(expectedError)
        );
    }
}
