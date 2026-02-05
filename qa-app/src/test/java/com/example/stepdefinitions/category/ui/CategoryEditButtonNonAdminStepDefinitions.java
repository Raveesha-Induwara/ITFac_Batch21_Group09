package com.example.stepdefinitions.category.ui;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertFalse;

import org.openqa.selenium.WebDriver;

import com.example.pages.category.ui.AddCategoryPage;
import com.example.stepdefinitions.Hooks;

public class CategoryEditButtonNonAdminStepDefinitions {

    private WebDriver driver;
    private AddCategoryPage categoryPage;

    public CategoryEditButtonNonAdminStepDefinitions() {
        driver = Hooks.getDriver();
        categoryPage = new AddCategoryPage(driver);
    }

    @Then("dthe edit button should not be visible or clickable for non-admin")
    public void theEditButtonShouldNotBeVisibleOrClickableForNonAdmin() {
        try {
            Thread.sleep(500); // Brief pause for page to load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        boolean isVisible = categoryPage.isEditButtonVisible();
        boolean isClickable = categoryPage.isEditButtonClickable();
        
        assertFalse(
                "Edit button should not be visible to non-admin users",
                isVisible
        );
        
        assertFalse(
                "Edit button should not be clickable for non-admin users",
                isClickable
        );
    }
}
