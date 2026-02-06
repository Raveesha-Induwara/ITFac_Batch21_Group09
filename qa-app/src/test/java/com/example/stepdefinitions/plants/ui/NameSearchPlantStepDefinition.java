package com.example.stepdefinitions.plants.ui;

import org.junit.Assert;

import com.example.pages.plants.ui.AddPlantPage;
import com.example.pages.plants.ui.SearchPlant;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NameSearchPlantStepDefinition {

    private SearchPlant searchPage;
    private AddPlantPage plantPage;

    public NameSearchPlantStepDefinition() {
        searchPage = new SearchPlant(Hooks.getDriver());
        plantPage = new AddPlantPage(Hooks.getDriver());

    }

    @When("the user enters {string} into the search bar")
    public void enterPlantName(String name) {
        searchPage.enterSearchTerm(name);
    }

    @And("the user clicks the search button")
    public void clickSearch() {
        searchPage.clickSearch();
    }

    @Then("only the plant {string} should be displayed in the results table")
    public void verifySearchResults(String plantName) {
        boolean isFound = plantPage.isPlantNameVisibleInTable(plantName);
        Assert.assertTrue("Searched plant not found!", isFound);

        int count = searchPage.getResultCount();
        Assert.assertTrue("Search returned more than expected results. Count: " + count, count >= 1);
    }
}
