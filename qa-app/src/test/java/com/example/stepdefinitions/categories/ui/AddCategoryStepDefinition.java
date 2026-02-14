package com.example.stepdefinitions.categories.ui;

import org.openqa.selenium.WebDriver;

import com.example.pages.Common;
import com.example.pages.category.ui.AddCategoryPage;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddCategoryStepDefinition {

    private WebDriver driver;
    private Common common;
    private AddCategoryPage addCategoryPage;
    
    public AddCategoryStepDefinition() {
        driver = Hooks.getDriver(); 
        common = new Common(driver);
        addCategoryPage = new AddCategoryPage(driver);
        common.launchApplication();
    }

    @Given("the user is on the category page")
    public void theUserIsOnTheCategoryPage() {
        addCategoryPage.navigateToCategoryPage();
    }

    @When("the admin clicks on the Add Category button")
    public void theAdminClicksOnTheAddCategoryButton() {
        addCategoryPage.clickAddCategory();
    }

    @Then("the admin enters name {string} for the new category")
    public void theAdminEntersNameForTheNewCategory(String categoryName) {
        addCategoryPage.enterCategoryName(categoryName);
    }

    @When("select {string} as the parent category")
    public void selectAsTheParentCategory(String parentCategory) {
        addCategoryPage.selectParentCategory(parentCategory);
    }

    @Then("click on the {string} button")
    public void theAdminClicksOnTheButton(String buttonName) {
        addCategoryPage.clickSubmit();
    }

    @Then("the system should show the {string} message")
    public void theSystemShouldShowTheMessage(String expectedMessage) {
        addCategoryPage.showSuccessMessage(expectedMessage);
    }

    @Then("the new category {string} should appear in the category list")
    public void theNewCategoryShouldAppearInTheCategoryList(String categoryName) {
        addCategoryPage.verifyCategoryInList(categoryName);
    }

    @Then("Delete the newly added {string} category to maintain test data integrity")
    public void deleteTheNewlyAddedCategoryToMaintainTestDataIntegrity(String categoryName) {
        addCategoryPage.deleteCategory(categoryName);
    }

    @Then("the system should show the {string} error message")
    public void theSystemShouldShowTheErrorMessage(String expectedErrorMessage) {
        addCategoryPage.showDuplicationErrorMessage(expectedErrorMessage);
    }

    @Then("the new category {string} should not appear in the category list")
    public void the_new_category_should_not_appear_in_the_category_list(String string) {
        addCategoryPage.verifyCategoryNotInList(string);
    }

    @Then("the admin tries to delete {string} category")
    public void theAdminTriesToDeleteCategory(String categoryName) {
        addCategoryPage.deleteCategory(categoryName);
    }
}   
