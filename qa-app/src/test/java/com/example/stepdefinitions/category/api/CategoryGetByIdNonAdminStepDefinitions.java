package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import com.example.pages.category.api.CategoryService;

public class CategoryGetByIdNonAdminStepDefinitions {

    private CategoryService categoryService = new CategoryService();

    @When("dNon-admin retrieves category with ID {int}")
    public void dNonAdminRetrievesCategoryWithId(int categoryId) {
        Response response = categoryService.getCategoryByIdNonAdmin(categoryId);
        CategoryApiContext.setResponse(response);
    }
}
