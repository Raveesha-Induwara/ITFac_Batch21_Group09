package com.example.stepdefinitions.plants.api;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;
import java.util.Map;

import com.example.pages.plants.api.PlantService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UpdatePlantApiStepDefinition {

    private PlantService plantService = new PlantService();
    private Response response;
    private Map<String, Object> plantData;

    @When("I update plant {int} with name {string} price {int} quantity {int} subCategory {int} using {string} credentials")
    public void updatePlantById(int plantId, String name, int price, int quantity, int subCategoryId, String role) {
        // Prepare updated plant data - matching actual API structure
        plantData = new HashMap<>();
        plantData.put("name", name);
        plantData.put("price", price);
        plantData.put("quantity", quantity);

        // SubCategory object
        Map<String, Object> subCategory = new HashMap<>();
        subCategory.put("id", subCategoryId);
        plantData.put("subCategory", subCategory);

        if (role.equalsIgnoreCase("admin")) {
            response = plantService.updatePlant(plantId, plantData, "admin", "admin123");
        } else {
            response = plantService.updatePlant(plantId, plantData, "testuser", "test123");
        }
    }

    // ...existing code...

    @Then("the response should contain updated plant details")
    public void verifyUpdatedPlantDetails() {
        response.then()
                .body("id", notNullValue())
                .body("name", notNullValue())
                .body("price", notNullValue())
                .body("quantity", notNullValue());
        response.prettyPrint();
    }

    @Then("the update response status should be {int}")
    public void verifyUpdateStatus(int code) {
        response.then().statusCode(code);
        response.prettyPrint();
    }
}
