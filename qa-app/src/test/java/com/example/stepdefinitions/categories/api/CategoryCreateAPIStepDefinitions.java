package com.example.stepdefinitions.categories.api;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import com.example.pages.category.api.CategoryService;

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
        Map<String, Object> payload = categoryService.getMainCategoryPayload(categoryName);
        response = categoryService.createCategoryAdmin(payload);
    }

    @When("the user sends a POST request to add main category with {string} as the main category name without authentication")
    public void theUserAddsMainCategoryWithoutAuth(String categoryName) {
        Map<String, Object> payload = categoryService.getMainCategoryPayload(categoryName);
        response = categoryService.createCategoryWithoutAuth(payload);
    }

    @When("the user sends a POST request to add subcategory without authentication {string} {int}")
    public void theUserAddsSubcategoryWithoutAuth(String categoryName, int parentCategoryId) {
        Map<String, Object> payload = categoryService.getSubCategoryPayload(categoryName, parentCategoryId);
        response = categoryService.createCategoryWithoutAuth(payload);
    }

    @When("the admin sends a POST request to add subcategory with {string} as the subcategory name and {int} as the parent category id")
    public void theAdminAddsSubcategoryWithParent(String subcategoryName, int parentCategoryId) {
        Map<String, Object> payload = categoryService.getSubCategoryPayload(subcategoryName, parentCategoryId);
        response = categoryService.createCategoryAdmin(payload);
    }

    @Then("the response status code should be {int} for category creation")
    public void theResponseStatusCodeShouldBe(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }
    
    @Then("the response should contain valid newly created category details")
    public void theResponseShouldContainValidCategoryDetails() {
        categoryService.isCategoryCreated(response);
    }

    @When("a main category named {string} already exists in the system")
    public void isMainCategoryAlreadyExists(String categoryName) {
        categoryService.isMainCategoryExist(categoryName);
    }

    @Then("the response should contain an error message indicating unauthorized access")
    public void theResponseShouldContainErrorMessageIndicatingUnauthorizedAccess() {
        String error = response.jsonPath().getString("error");

        try {
            assertEquals("UNAUTHORIZED", error);
        } catch (AssertionError e) {
            System.out.println("Expected error message 'UNAUTHORIZED' but got: " + error);
            throw e;
        }
    }

    @Then("the response should contain an error message indicating duplicate category")
    public void theResponseShouldContainErrorMessageIndicatingDuplicateCategory() {
        String error = response.jsonPath().getString("error");
        try {
            assertEquals("DUPLICATE_RESOURCE", error);
        } catch (AssertionError e) {
            System.out.println("Expected error message 'DUPLICATE_RESOURCE' but got: " + error);
            throw e;
        }
    }

    @Then("the response should contain an error message indicating parent category not found")
    public void theResponseShouldContainErrorMessageIndicatingParentCategoryNotFound() {
        String error = response.jsonPath().getString("error");
        try {
            assertEquals("RESOURCE_NOT_FOUND", error);
        } catch (AssertionError e) {
            System.out.println("Expected error message 'RESOURCE_NOT_FOUND' but got: " + error);
            throw e;
        }
    }
    
    @Then("the response should contain an error message indicating bad request")
    public void theResponseShouldContainErrorMessageIndicatingBadRequest() {
        String error = response.jsonPath().getString("error");
        String message = response.jsonPath().getString("details");

        try {
            System.out.println(message);
            assertEquals("BAD_REQUEST", error);
        } catch (AssertionError e) {
            System.out.println("Expected error message 'BAD_REQUEST' but got: " + error);
            throw e;
        }
    }

    @After
    public void deleteCategory() {
        if (response != null && (response.getStatusCode() == 201 || response.getStatusCode() == 200)) {
            int categoryId = response.jsonPath().getInt("id");
            categoryService.deleteCategory(categoryId);
        }
    }

}
