package com.example.stepdefinitions.category.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class CategoryApiCommonStepDefinitions {

    @Then("dthe category API should return status {int}")
    public void dTheCategoryApiShouldReturnStatus(int statusCode) {
        Response response = CategoryApiContext.getResponse();
        response.then().statusCode(statusCode);
    }
}
