package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import com.example.pages.category.api.CategoryService;

public class CategoryGetByIdStepDefinitions {

    private CategoryService categoryService = new CategoryService();

    @When("dAdmin retrieves category with ID {int}")
    public void dAdminRetrievesCategoryWithId(int categoryId) {
        Response response = categoryService.getCategoryById(categoryId);
        CategoryApiContext.setResponse(response);
    }

    @Then("dthe response should contain category with name {string}")
    public void dTheResponseShouldContainCategoryWithName(String expectedName) {
        Response response = CategoryApiContext.getResponse();
        if (response.statusCode() == 200) {
            response.then().body("name", equalTo(expectedName));
        } else if (response.statusCode() == 404) {
            response.then().body("message", containsString(expectedName));
        }
    }
}
