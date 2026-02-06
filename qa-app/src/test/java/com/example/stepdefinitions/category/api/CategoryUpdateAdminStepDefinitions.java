package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import com.example.pages.category.api.CategoryService;

public class CategoryUpdateAdminStepDefinitions {

    private CategoryService categoryService = new CategoryService();

    @When("dAdmin updates category {int} with name {string}")
    public void dAdminUpdatesCategoryWithName(int categoryId, String newName) {
        Map<String, Object> categoryData = new HashMap<>();
        categoryData.put("name", newName);
        // Don't include parentId to preserve existing parent relationship
        
        Response response = categoryService.updateCategory(categoryId, categoryData);
        CategoryApiContext.setResponse(response);
    }

    @When("dAdmin updates category {int} parent to category {int}")
    public void dAdminUpdatesCategoryParentToCategory(int categoryId, int newParentId) {
        Map<String, Object> categoryData = new HashMap<>();
        categoryData.put("name", "updFa"); // Name required with parent update
        categoryData.put("parentId", newParentId);
        
        Response response = categoryService.updateCategory(categoryId, categoryData);
        CategoryApiContext.setResponse(response);
    }

    @Then("dthe response should contain updated category name {string}")
    public void dTheResponseShouldContainUpdatedCategoryName(String expectedName) {
        Response response = CategoryApiContext.getResponse();
        response.then()
                .body("name", equalTo(expectedName));
    }

    @Then("dthe response should contain parent category with ID {int}")
    public void dTheResponseShouldContainParentCategoryWithId(int expectedParentId) {
        Response response = CategoryApiContext.getResponse();
        response.then()
                .body("parentId", equalTo(expectedParentId));
    }
}
