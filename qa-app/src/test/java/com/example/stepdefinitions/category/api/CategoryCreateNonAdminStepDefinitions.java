package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import com.example.pages.category.api.CategoryService;

public class CategoryCreateNonAdminStepDefinitions {

    private CategoryService categoryService = new CategoryService();

    @When("dNon-admin attempts to create a category with name {string}")
    public void dNonAdminAttemptsToCreateACategoryWithName(String categoryName) {
        Map<String, Object> categoryData = new HashMap<>();
        categoryData.put("name", categoryName);
        categoryData.put("parent", null);
        
        Response response = categoryService.createCategoryNonAdmin(categoryData);
        CategoryApiContext.setResponse(response);
    }
}
