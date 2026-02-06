package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import com.example.pages.category.api.CategoryService;

public class CategoryGetSummaryStepDefinitions {

    private CategoryService categoryService = new CategoryService();

    @When("dAdmin retrieves category summary")
    public void dAdminRetrievesCategorySummary() {
        Response response = categoryService.getCategorySummary();
        CategoryApiContext.setResponse(response);
    }

    @Then("dthe response should contain category summary")
    public void dTheResponseShouldContainCategorySummary() {
        Response response = CategoryApiContext.getResponse();
        response.then()
                .body("$", notNullValue());
    }
}
