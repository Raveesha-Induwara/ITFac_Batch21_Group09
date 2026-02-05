package com.example.stepdefinitions.plants.ui;

import org.junit.Assert;

import com.example.pages.plants.ui.AddPlantPage;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class PlantAddFormCancel {
    private AddPlantPage addPlantPage;

    public PlantAddFormCancel() {
        addPlantPage = new AddPlantPage(Hooks.getDriver());
    }
    @And("the admin clicks the Cancel button")
public void clickCancelButton() {
        addPlantPage.clickCancel();
}

@Then("the system should navigate back to the {string} page")
public void verifyNavigation(String pageName) {
    // We check if the URL contains the expected path (e.g., /ui/plants)
    String expectedPath = "/ui/" + pageName;
    String actualUrl = addPlantPage.getCurrentUrl();
    
    Assert.assertTrue("Navigation failed! Expected URL to contain: " + expectedPath + " but was: " + actualUrl,
            actualUrl.contains(expectedPath));
}
}
