package com.example.stepdefinitions.categories.api;

import static org.junit.Assert.assertEquals;

import com.example.pages.category.api.CategoryService;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class CategoryGetAPIStepDefinitions {

    private Response response;
    private CategoryService categoryService;

    public CategoryGetAPIStepDefinitions() {
        this.categoryService = new CategoryService();
    }

    @When("the user sends a GET request to retrieve all categories without filters")
    public void getAllCategoriesByNonAdmin() {
        response = categoryService.getAllCategoriesNonAdmin();
    }

    @Then("the response should contain valid category list")
    public void theResponseShouldContainValidCategoryList() {
        categoryService.isCategoryListValid(response);
    }

    @When("the user sends a GET request to retrieve categories filtered by name {string}")
    public void the_user_sends_a_get_request_to_retrieve_categories_filtered_by_name(String string) {
        response = categoryService.getAllCategoriesByName(string);
    }

    @Then("the response should contain category name {string}")
    public void  theResponseShouldContainCategory(String categoryName) {
        categoryService.isCategoryListValid(response, categoryName);
    }

    @When("the user sends a GET request to retrieve subcategories filtered by parentId {int}")
    public void the_user_sends_a_get_request_to_retrieve_subcategories_filtered_by_parentId(int parentId) {
        response = categoryService.getAllCategoriesByID(parentId);
    }
    
    @When("the user sends a GET request to retrieve categories filtered by name {string} and parentId {int}")
    public void the_user_sends_a_get_request_to_retrieve_categories_filtered_by_name_and_parentId(String categoryName, int parentId) {
        response = categoryService.getAllCategoriesByNameAndID(categoryName, parentId);
    }

    @When("the user sends a GET request with non-numeric value for parentId {string}")
    public void the_user_sends_a_get_request_with_non_numeric_value_for_parentId(String parentId) {
        response = categoryService.getAllCategoriesByNonNumericID("abc");
    }

    @Then("the response status code should be {int} for category retrieval")
    public void the_response_status_code_should_be_for_category_retrieval(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }
}
