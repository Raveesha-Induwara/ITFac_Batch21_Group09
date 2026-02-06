package com.example.stepdefinitions.plants.ui;

import org.junit.Assert;

import com.example.pages.plants.ui.AddPlantPage;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.Then;


public class PlantAddDuplicatePlantStepDefinition {

    private AddPlantPage plantPage;

    public PlantAddDuplicatePlantStepDefinition() {
        plantPage = new AddPlantPage(Hooks.getDriver());
    }

    @Then("the system should show the message {string}")
    public void verifyDuplicateSubmission(String expectedMessage) throws InterruptedException {
        String actualMessage = plantPage.AlertMsg();
        Assert.assertEquals("The alert message did not match!", expectedMessage, actualMessage);
    }
}
