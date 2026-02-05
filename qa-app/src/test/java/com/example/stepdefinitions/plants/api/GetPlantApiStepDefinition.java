package com.example.stepdefinitions.plants.api;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

import com.example.pages.plants.api.PlantService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class GetPlantApiStepDefinition {

    private PlantService plantService = new PlantService();
    private Response response;

    @When("I retrieve all plants using {string} credentials")
    public void retrieveAllPlantsWithAuth(String role) {
        if (role.equalsIgnoreCase("admin")) {
            response = plantService.getAllPlants("admin", "admin123");
        } else {
            response = plantService.getAllPlants("testuser", "test123");
        }
    }

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

    @When("I retrieve all plants without a token")
    public void retrieveAllPlantsWithoutToken() {
        response = plantService.getAllPlantsWithoutToken();
    }

    @When("I retrieve paged plants with page {int} and size {int} using {string} credentials")
    public void retrievePagedPlants(int page, int size, String role) {
        if (role.equalsIgnoreCase("admin")) {
            response = plantService.getPagedPlants("admin", "admin123", page, size);
        } else {
            response = plantService.getPagedPlants("testuser", "test123", page, size);
        }
    }

    @When("I retrieve paged plants with page {int} and size {int} without a token")
    public void retrievePagedPlantsWithoutToken(int page, int size) {
        response = plantService.getPagedPlantsWithoutToken(page, size);
    }

    @Then("the response status should be {int}")
    public void verifyStatus(int code) {
        response.then().statusCode(code);
        response.prettyPrint();
    }

    @Then("the response should contain a list of plants")
    public void verifyPlantsList() {
        response.then().body("$", notNullValue());
        response.prettyPrint();
    }

    @Then("the response should contain paginated plant data")
    public void verifyPaginatedResponse() {
        response.then()
                .body("content", notNullValue())
                .body("pageable", notNullValue());
        response.prettyPrint();
    }

    @Then("all returned plants must belong to category ID {int}")
    public void verifyCategoryIdMatch(int expectedId) {
        // looks into the 'category' object of every item in the array
        response.then().body("category.id", everyItem(equalTo(expectedId)));
        response.prettyPrint();
    }

    @Then("the response should include the error message {string}")
    public void verifyErrorMessage(String msg) {
        response.then().body("message", containsString(msg));
        response.prettyPrint();
    }
}
