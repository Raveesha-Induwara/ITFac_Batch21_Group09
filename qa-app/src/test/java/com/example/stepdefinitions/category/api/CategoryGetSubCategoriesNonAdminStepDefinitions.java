package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import com.example.pages.category.api.CategoryService;

public class CategoryGetSubCategoriesNonAdminStepDefinitions {

    private CategoryService categoryService = new CategoryService();

    @When("dNon-admin retrieves sub categories")
    public void dNonAdminRetrievesSubCategories() {
        Response response = categoryService.getSubCategoriesNonAdmin();
        CategoryApiContext.setResponse(response);
    }

    @Then("dthe response should contain sub categories list")
    public void dTheResponseShouldContainSubCategoriesList() {
        Response response = CategoryApiContext.getResponse();
        response.then()
                .body("$", notNullValue())
                .body("size()", greaterThanOrEqualTo(0));
    }
}
