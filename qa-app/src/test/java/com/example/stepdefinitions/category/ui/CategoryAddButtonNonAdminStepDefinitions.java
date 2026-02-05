package com.example.stepdefinitions.category.ui;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertFalse;

import org.openqa.selenium.WebDriver;

import com.example.pages.category.ui.AddCategoryPage;
import com.example.stepdefinitions.Hooks;

public class CategoryAddButtonNonAdminStepDefinitions {

    private WebDriver driver;
    private AddCategoryPage categoryPage;

    public CategoryAddButtonNonAdminStepDefinitions() {
        driver = Hooks.getDriver();
        categoryPage = new AddCategoryPage(driver);
    }

    @Then("dthe {string} button should not be visible to non-admin")
    public void theButtonShouldNotBeVisibleToNonAdmin(String buttonName) {
        assertFalse(
                "Add A Category button should not be visible to non-admin users",
                categoryPage.isAddCategoryButtonVisible()
        );
    }
}
