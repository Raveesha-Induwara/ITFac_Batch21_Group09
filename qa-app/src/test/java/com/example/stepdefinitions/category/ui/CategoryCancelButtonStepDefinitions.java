package com.example.stepdefinitions.category.ui;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.example.pages.category.ui.AddCategoryPage;
import com.example.stepdefinitions.Hooks;

public class CategoryCancelButtonStepDefinitions {

    private WebDriver driver;
    private AddCategoryPage categoryPage;

    public CategoryCancelButtonStepDefinitions() {
        driver = Hooks.getDriver();
        categoryPage = new AddCategoryPage(driver);
    }

    @When("dthe admin clicks the cancel button on add category page")
    public void theAdminClicksTheCancelButton() {
        categoryPage.clickCancel();
    }

    @Then("dthe admin should be navigated back to the category list page")
    public void theAdminShouldBeNavigatedBackToTheCategoryListPage() {
        assertTrue(
                "Admin is not on the category list page (/ui/categories)",
                categoryPage.isOnCategoryPage()
        );
    }
}
