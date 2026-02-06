package com.example.stepdefinitions.category.ui;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.example.pages.category.ui.AddCategoryPage;
import com.example.stepdefinitions.Hooks;

public class CategoryAddButtonAccessStepDefinitions {

    private WebDriver driver;
    private AddCategoryPage categoryPage;

    public CategoryAddButtonAccessStepDefinitions() {
        driver = Hooks.getDriver();
        categoryPage = new AddCategoryPage(driver);
    }

    @Then("dthe admin should see the {string} button")
    public void theAdminShouldSeeTheButton(String buttonName) {
        assertTrue(
                "Add A Category button is not visible",
                categoryPage.isAddCategoryButtonVisible()
        );
    }

    @When("dthe admin clicks on the {string} button")
    public void theAdminClicksOnTheButton(String buttonName) {
        categoryPage.clickAddCategory();
    }

    @Then("dthe admin should be navigated to the Add Category page")
    public void theAdminShouldBeNavigatedToTheAddCategoryPage() {
        assertTrue(
                "Admin is not on the Add Category page (/ui/categories/add)",
                categoryPage.isOnAddCategoryPage()
        );
    }
}
