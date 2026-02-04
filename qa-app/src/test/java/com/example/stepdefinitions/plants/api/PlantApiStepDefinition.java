package com.example.stepdefinitions.plants.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import com.example.pages.plants.api.PlantService;

public class PlantApiStepDefinition {

    private PlantService plantService = new PlantService();
    private Response response;

    @When("Admin create a new plant in category {int} with name {string} with price {int} and quantity {int}")
    public void admin_create_a_new_plant(int catId, String name, int price, int quantity) {
        // Constructing the body with exactly the fields you specified
        Map<String, Object> body = new HashMap<>();
        body.put("id", 10); // Using the ID from your example
        body.put("name", name);
        body.put("price", price);
        body.put("quantity", quantity);

        response = plantService.createPlantAdmin(catId, body);
    }
    
    @When("Non Admin create a new plant in category {int} with name {string} with price {int} and quantity {int}")
    public void non_admin_create_a_new_plant(int catId, String name, int price, int quantity) {
        // Constructing the body with exactly the fields you specified
        Map<String, Object> body = new HashMap<>();
        body.put("id", 10); // Using the ID from your example
        body.put("name", name);
        body.put("price", price);
        body.put("quantity", quantity);

        response = plantService.createPlantNonAdmin(catId, body);
    }

    @Then("the plant API should return status {int}")
    public void verify_status(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response message should be {string}")
    public void verify_message(String expectedValue) {
        if (response.statusCode() == 201) {
            response.then().body("name", equalTo(expectedValue));
        } else {
            response.then().body("message", containsString(expectedValue));
        }
    }

    @Then("the response should match the Swagger documentation")
    public void verify_error_schema() {
        // Ensures the API follows the documentation contract for errors
        response.then()
                .body("status", notNullValue())
                .body("error", notNullValue())
                .body("message", notNullValue())
                .body("timestamp", notNullValue());
    }
}
