package com.example.stepdefinitions.category.ui;

import org.junit.Assert;

import com.example.pages.category.ui.SearchCategory;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ParentFilterStepDefinition {

    private SearchCategory searchPage;

    public ParentFilterStepDefinition() {
        searchPage = new SearchCategory(Hooks.getDriver());
    }

    @When("the user selects parent {string} from the filter")
    public void selectParent(String parent) {
        searchPage.selectParent(parent);
    }

    @Then("all results in the table should have the parent {string}")
    public void verifyAllParentsMatch(String expectedParent) {
        try {
            Thread.sleep(1000); // Brief pause for results to load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isMatch = searchPage.areAllParentsMatching(expectedParent);
        Assert.assertTrue("One or more results did not match the parent: " + expectedParent, isMatch);
    }
}
