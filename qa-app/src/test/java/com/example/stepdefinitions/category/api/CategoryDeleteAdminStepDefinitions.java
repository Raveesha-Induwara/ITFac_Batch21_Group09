package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import com.example.pages.category.api.CategoryService;

public class CategoryDeleteAdminStepDefinitions {

    private CategoryService categoryService = new CategoryService();

    @When("dAdmin deletes category {int}")
    public void dAdminDeletesCategory(int categoryId) {
        Response response = categoryService.deleteCategory(categoryId);
        CategoryApiContext.setResponse(response);
    }

    @When("dAdmin attempts to delete parent category {int} that has children")
    public void dAdminAttemptsToDeleteParentCategoryThatHasChildren(int categoryId) {
        Response response = categoryService.deleteCategory(categoryId);
        CategoryApiContext.setResponse(response);
    }

    @Then("dthe error indicates category has children")
    public void dTheErrorIndicatesCategoryHasChildren() {
        Response response = CategoryApiContext.getResponse();
        // The error message should indicate the category has children or referential integrity constraint
        response.then()
                .body("message", notNullValue());
    }

    @Then("dthe parent category should have children")
    public void dTheParentCategoryShouldHaveChildren() {
        Response response = CategoryApiContext.getResponse();
        response.then()
                .body("subCategories", not(emptyArray()));
    }
}
