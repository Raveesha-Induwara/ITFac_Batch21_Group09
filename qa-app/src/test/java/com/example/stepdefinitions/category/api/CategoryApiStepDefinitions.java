package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import com.example.pages.category.api.CategoryService;

import static org.hamcrest.Matchers.*;

public class CategoryApiStepDefinitions {

    private CategoryService categoryService = new CategoryService();
    private Response response;

    @When("Admin creates a new category with name {string}")
    public void adminCreatesANewCategoryWithName(String name) {
        response = categoryService.createCategoryAdmin(name);
    }

    @When("Non-admin user attempts to create a category with name {string}")
    public void nonAdminUserAttemptsToCreateACategoryWithName(String name) {
        response = categoryService.createCategoryNonAdmin(name);
    }

    @When("Admin deletes category with id {int}")
    public void adminDeletesCategoryWithId(int categoryId) {
        response = categoryService.deleteCategory(categoryId);
    }

    @When("Admin updates category with id {int} to name {string}")
    public void adminUpdatesCategoryWithIdToName(int categoryId, String newName) {
        response = categoryService.updateCategory(categoryId, newName);
    }

    @Then("the category API should return status {int}")
    public void theCategoryApiShouldReturnStatus(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the category response message should be {string}")
    public void theCategoryResponseMessageShouldBe(String expectedValue) {
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            // For successful responses, check the name field
            response.then().body("name", equalTo(expectedValue));
        } else {
            // For error responses, check the message field
            response.then().body("message", containsString(expectedValue));
        }
    }
}
