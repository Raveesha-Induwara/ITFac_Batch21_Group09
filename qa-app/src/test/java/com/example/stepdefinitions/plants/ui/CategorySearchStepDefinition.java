package com.example.stepdefinitions.plants.ui;

import org.junit.Assert;

import com.example.pages.plants.ui.SearchPlant;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CategorySearchStepDefinition {

    private SearchPlant searchPage;

    public CategorySearchStepDefinition() {
        searchPage = new SearchPlant(Hooks.getDriver());
    }

    @When("the user selects category {string} from the filter")
    public void selectCategory(String category) {
        searchPage.selectCategory(category);
    }

    @Then("all results in the table should have the category {string}")
    public void verifyAllCategoriesMatch(String expectedCategory) {
        boolean isMatch = searchPage.areAllResultsMatching(expectedCategory);
        Assert.assertTrue("One or more search results did not match the category: " + expectedCategory, isMatch);
    }
}
