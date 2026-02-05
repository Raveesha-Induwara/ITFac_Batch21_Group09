package com.example.stepdefinitions.plants.api;

import com.example.pages.plants.api.PlantService;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class DeletePlantApiStepDefinition {

    private PlantService plantService = new PlantService();
    private Response response;

    @When("I delete plant with ID {int} using {string} credentials")
    public void deletePlantById(int plantId, String role) {
        if (role.equalsIgnoreCase("admin")) {
            response = plantService.deletePlant(plantId, "admin", "admin123");
        } else {
            response = plantService.deletePlant(plantId, "testuser", "test123");
        }
    }

    @When("I delete plant with ID {int} without authentication")
    public void deletePlantWithoutAuth(int plantId) {
        response = plantService.deletePlantWithoutToken(plantId);
    }

    @Then("the delete response status should be {int}")
    public void verifyDeleteStatus(int code) {
        response.then().statusCode(code);
        response.prettyPrint();
    }
}
