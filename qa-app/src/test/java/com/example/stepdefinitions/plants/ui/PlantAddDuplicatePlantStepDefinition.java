package com.example.stepdefinitions.plants;

import org.junit.Assert;

import com.example.pages.plants.AddPlantPage;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.Then;


public class PlantAddDuplicatePlantStepDefinition {

    private AddPlantPage plantPage;

    public PlantAddDuplicatePlantStepDefinition() {
        plantPage = new AddPlantPage(Hooks.getDriver());
    }

    @Then("the system should show the message {string}")
    public void verifyDuplicateSubmission(String expectedMessage) throws InterruptedException {
        Thread.sleep(200);
        String actualMessage = plantPage.AlertMsg();
        Assert.assertEquals("The alert message did not match!", expectedMessage, actualMessage);
    }
}
