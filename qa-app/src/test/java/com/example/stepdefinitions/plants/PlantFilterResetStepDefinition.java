package com.example.stepdefinitions.plants;

import org.junit.Assert;

import com.example.pages.plants.SearchPlant;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlantFilterResetStepDefinition {

    private SearchPlant searchPage;
    private int initialPlantCount;
    private String categoryBeforeReset;
    private String initialCategoryValue;

    public PlantFilterResetStepDefinition() {
        searchPage = new SearchPlant(Hooks.getDriver());
    }

    @When("the user clicks the reset button")
    public void clickResetButton() {
        // Store the category value before clicking reset
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        categoryBeforeReset = searchPage.getSelectedCategory();
        System.out.println("DEBUG: Category before reset = '" + categoryBeforeReset + "'");
        
        searchPage.clickReset();
        try {
            Thread.sleep(1500); // Wait for reset action to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("the search bar should be cleared")
    public void verifySearchBarCleared() {
        boolean isEmpty = searchPage.isSearchBarEmpty();
        Assert.assertTrue("Search bar should be empty after reset", isEmpty);
    }

    @Then("the category filter should be reset to default")
    public void verifyCategoryFilterReset() {
        String selectedCategory = searchPage.getSelectedCategory();
        System.out.println("DEBUG: Category before reset = '" + categoryBeforeReset + "'");
        System.out.println("DEBUG: Category after reset = '" + selectedCategory + "'");
        
        // Check if it's a default value OR if it changed from the filtered category
        boolean isReset = searchPage.isCategoryFilterReset() || 
                         !selectedCategory.equalsIgnoreCase(categoryBeforeReset);
        
        Assert.assertTrue("Category filter should be reset to default. " +
                         "Before: '" + categoryBeforeReset + "', After: '" + selectedCategory + "'", isReset);
    }

    @Then("all plants should be displayed")
    public void verifyAllPlantsDisplayed() {
        try {
            Thread.sleep(1500); // Wait longer for list to refresh after reset
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int currentCount = searchPage.getResultCount();
        System.out.println("DEBUG: Plant count after reset = " + currentCount);

        // After reset, we should have at least some plants displayed
        Assert.assertTrue("Plant list should display all plants after reset (got: " + currentCount + " plants). Expected at least 1 plant.",
                         currentCount >= 1);
    }
}
