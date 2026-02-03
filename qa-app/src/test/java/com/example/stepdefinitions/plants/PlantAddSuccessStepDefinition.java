package com.example.stepdefinitions.plants;

import org.junit.Assert;

import com.example.pages.plants.AddPlantPage;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.And;

public class PlantAddSuccessStepDefinition {
    private AddPlantPage plantPage;

    public PlantAddSuccessStepDefinition() {
        plantPage = new AddPlantPage(Hooks.getDriver());
    }
    @And("the new plant {string} should appear in the plant list")
    public void verifyNewPlantInList(String plantName) throws InterruptedException {
        Thread.sleep(200);
        boolean isFound = plantPage.isPlantNameVisibleInTable(plantName);

        // This will display the message if isFound is false
        Assert.assertTrue("The plant '" + plantName + "' was not found in the table list!", isFound);
    }
}
