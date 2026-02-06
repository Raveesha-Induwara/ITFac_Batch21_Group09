package com.example.stepdefinitions.plants.api;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import com.example.pages.plants.api.PlantService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class PlantApiStepDefinition {

    private PlantService plantService = new PlantService();
    private Response response;

    @When("Admin create a new plant in category {int} with name {string} with price {int} and quantity {int}")
    public void admin_create_a_new_plant(int catId, String name, int price, int quantity) {
        response = plantService.createPlantAdmin(catId, name, price, quantity);
    }

    @When("Non Admin create a new plant in category {int} with name {string} with price {int} and quantity {int}")
    public void non_admin_create_a_new_plant(int catId, String name, int price, int quantity) {
        response = plantService.createPlantNonAdmin(catId, name, price, quantity);
    }

    @When("a request is sent to create a plant in category {int} without any token")
    public void create_without_token(int catId) {
        response = plantService.createPlantWithoutToken(catId, "Unauthorized Test", 150, 25);
    }

    @Then("the plant API should return status {int}")
    public void verify_status(int statusCode) {
        response.then().statusCode(statusCode);
        response.prettyPrint();
    }

    @Then("the response message should be {string}")
    public void verify_message(String expectedValue) {
        if (response.statusCode() == 201) {
            response.then().body("name", equalTo(expectedValue));
            response.prettyPrint();
        } else {
            response.then().body("message", containsString(expectedValue));
            response.prettyPrint();
        }
    }

    @Then("the response should match the Swagger documentation")
    public void verify_error_schema() {
        response.then()
                .body("status", notNullValue())
                .body("error", notNullValue())
                .body("message", notNullValue())
                .body("timestamp", notNullValue());
    }
}
