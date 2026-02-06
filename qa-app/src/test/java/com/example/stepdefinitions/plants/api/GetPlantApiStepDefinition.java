package com.example.stepdefinitions.plants.api;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

import com.example.pages.plants.api.PlantService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class GetPlantApiStepDefinition {

    private PlantService plantService = new PlantService();
    private Response response;

    @When("I retrieve plants for category {int} using {string} credentials")
    public void retrievePlants(int catId, String role) {
        if (role.equalsIgnoreCase("admin")) {
            response = plantService.getPlantsByCategory(catId, "admin", "admin123");
        } else {
            response = plantService.getPlantsByCategory(catId, "testuser", "test123");
        }
    }

    @When("I retrieve plants for category {int} without a token")
    public void retrieveWithoutToken(int catId) {
        response = plantService.getPlantsWithoutToken(catId);
    }

    @Then("the response status should be {int}")
    public void verifyStatus(int code) {
        response.then().statusCode(code);
        response.prettyPrint();
    }

    @Then("all returned plants must belong to category ID {int}")
    public void verifyCategoryIdMatch(int expectedId) {
        response.then().body("category.id", everyItem(equalTo(expectedId)));
        response.prettyPrint();
    }

    @Then("the response should include the error message {string}")
    public void verifyErrorMessage(String msg) {
        response.then().body("message", containsString(msg));
        response.prettyPrint();
    }
}
