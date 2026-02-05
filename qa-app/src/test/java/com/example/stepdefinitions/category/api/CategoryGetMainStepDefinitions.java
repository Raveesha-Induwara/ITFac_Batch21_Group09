package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import com.example.pages.category.api.CategoryService;

public class CategoryGetMainStepDefinitions {

    private CategoryService categoryService = new CategoryService();

    @When("dAdmin retrieves main categories")
    public void dAdminRetrievesMainCategories() {
        Response response = categoryService.getMainCategoriesAdmin();
        CategoryApiContext.setResponse(response);
    }

    @Then("dthe response should contain main categories list")
    public void dTheResponseShouldContainMainCategoriesList() {
        Response response = CategoryApiContext.getResponse();
        response.then()
                .body("$", notNullValue())
                .body("size()", greaterThan(0));
    }
}
