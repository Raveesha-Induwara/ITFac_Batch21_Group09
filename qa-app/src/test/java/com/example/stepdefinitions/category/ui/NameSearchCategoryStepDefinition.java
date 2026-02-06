package com.example.stepdefinitions.category.ui;

import org.junit.Assert;

import com.example.pages.category.ui.AddCategoryPage;
import com.example.pages.category.ui.SearchCategory;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NameSearchCategoryStepDefinition {

    private SearchCategory searchPage;
    private AddCategoryPage categoryPage;

    public NameSearchCategoryStepDefinition() {
        searchPage = new SearchCategory(Hooks.getDriver());
        categoryPage = new AddCategoryPage(Hooks.getDriver());

    }

    @When("dthe user enters {string} into the search bar")
    public void enterCategoryName(String name) {
        searchPage.enterSearchTerm(name);
    }

    @And("dthe user clicks the search button")
    public void clickSearch() {
        searchPage.clickSearch();
    }

    @Then("donly the category {string} should be displayed in the results table")
    public void verifySearchResults(String categoryName) {
        // 1. Check if the category is there
        boolean isFound = categoryPage.isCategoryNameVisibleInTable(categoryName);
        Assert.assertTrue("Searched category not found!", isFound);
        // 2. Check that only one result is returned
        int count = searchPage.getResultCount();
        Assert.assertTrue("Search returned more than expected results. Count: " + count, count >= 1);
    }

    @Given("dthe new category {string} should appear in the category list")
    public void verifyNewCategoryInList(String categoryName) throws InterruptedException {
        Thread.sleep(200);
        boolean isFound = categoryPage.isCategoryNameVisibleInTable(categoryName);

        // This will display the message if isFound is false
        Assert.assertTrue("The category '" + categoryName + "' was not found in the table list!", isFound);
    }

    @Given("dthe user is on the {string} page")
    public void theUserIsOnTheSpecificPage(String pageName) throws InterruptedException {
        Thread.sleep(200);
        categoryPage.navigateToCategoryPage();
        Assert.assertTrue("Admin is not on the page", categoryPage.isOnCategoryPage());
    }

    @Then("dno results should be displayed in the table")
    public void verifyNoResults() {
        int count = searchPage.getResultCount();
        Assert.assertEquals("Expected no results, but found " + count + " result(s)", 0, count);
    }
}
