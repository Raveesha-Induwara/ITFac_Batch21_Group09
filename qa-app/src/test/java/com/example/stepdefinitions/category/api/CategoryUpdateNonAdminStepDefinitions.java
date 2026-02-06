package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import com.example.pages.category.api.CategoryService;

public class CategoryUpdateNonAdminStepDefinitions {

    private CategoryService categoryService = new CategoryService();

    @Given("dA category with ID {int} exists")
    public void dACategoryWithIdExists(int categoryId) {
        // This is a precondition step - we assume the category exists
        // In a real scenario, you might want to verify it exists first
    }

    @When("dNon-admin attempts to update category {int} with name {string}")
    public void dNonAdminAttemptsToUpdateCategoryWithName(int categoryId, String newName) {
        Map<String, Object> categoryData = new HashMap<>();
        categoryData.put("name", newName);
        categoryData.put("parentId", null);
        
        Response response = categoryService.updateCategoryNonAdmin(categoryId, categoryData);
        CategoryApiContext.setResponse(response);
    }

    @Then("dthe response should contain error message {string}")
    public void dTheResponseShouldContainErrorMessage(String errorMessage) {
        Response response = CategoryApiContext.getResponse();
        response.then()
                .body("error", containsString(errorMessage));
    }
}
