package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import com.example.pages.category.api.CategoryService;

public class CategoryDeleteNonAdminStepDefinitions {

    private CategoryService categoryService = new CategoryService();

    @When("dNon-admin attempts to delete category {int}")
    public void dNonAdminAttemptsToDeleteCategory(int categoryId) {
        Response response = categoryService.deleteCategoryNonAdmin(categoryId);
        CategoryApiContext.setResponse(response);
    }
}
