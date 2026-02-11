package com.example.stepdefinitions.categories.api;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.example.pages.category.api.CategoryService;
import com.example.utils.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class CategoryCreateAPIStepDefinitions {

    private Response response;
    private CategoryService categoryService;

    public CategoryCreateAPIStepDefinitions() {
        this.categoryService = new CategoryService();
    }

    @When("the admin sends a POST request to add main category with {string} as the main category name")
    public void theAdminAddMainCategoryWithValidDetails(String categoryName) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", categoryName);
        payload.put("parent", null);
        response = categoryService.createCategoryAdmin(payload);
    }

    @When("the user sends a POST request to add main category with {string} as the main category name without authentication")
    public void theUserAddsMainCategoryWithoutAuth(String categoryName) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", categoryName);
        payload.put("parent", null);
        response = categoryService.createCategoryWithoutAuth(payload);
    }

    @Then("the response status code should be {int} for category creation")
    public void theResponseStatusCodeShouldBe(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }
    
    @Then("the response should contain valid category details")
    public void theResponseShouldContainValidCategoryDetails() {
        categoryService.isCategoryCreated(response);
    }

    @Then("the response should contain an error message indicating unauthorized access")
    public void theResponseShouldContainErrorMessageIndicatingUnauthorizedAccess() {
        String error = response.jsonPath().getString("error");
        assertEquals("UNAUTHORIZED", error);
    }

    @When("a main category named {string} already exists in the system")
    public void isMainCategoryAlreadyExists(String categoryName) {
        categoryService.isMainCategoryExist(categoryName);
    }

    @Then("the response should contain an error message indicating duplicate category")
    public void theResponseShouldContainErrorMessageIndicatingDuplicateCategory() {
        String error = response.jsonPath().getString("error");
        assertEquals("DUPLICATE_RESOURCE", error);
    }


    @After
    public void deleteCategory() {
        if (response != null && (response.getStatusCode() == 201 || response.getStatusCode() == 200)) {
            int categoryId = response.jsonPath().getInt("id");
            categoryService.deleteCategory(categoryId);
        }
    }

}
