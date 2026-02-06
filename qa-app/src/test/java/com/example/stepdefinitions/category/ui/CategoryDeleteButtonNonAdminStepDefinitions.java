package com.example.stepdefinitions.category.ui;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.example.pages.category.ui.AddCategoryPage;
import com.example.stepdefinitions.Hooks;

public class CategoryDeleteButtonNonAdminStepDefinitions {

    private WebDriver driver;
    private AddCategoryPage categoryPage;

    public CategoryDeleteButtonNonAdminStepDefinitions() {
        driver = Hooks.getDriver();
        categoryPage = new AddCategoryPage(driver);
    }

    @Then("dthe delete button should be visible but disabled for non-admin")
    public void theDeleteButtonShouldBeVisibleButDisabledForNonAdmin() {
        try {
            Thread.sleep(500); // Brief pause for page to load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        boolean isVisible = categoryPage.isDeleteButtonVisible();
        boolean isClickable = categoryPage.isDeleteButtonClickable();
        
        assertTrue(
                "Delete button should be visible to non-admin users",
                isVisible
        );
        
        assertFalse(
                "Delete button should be disabled (not clickable) for non-admin users",
                isClickable
        );
    }
}
