package com.example.stepdefinitions.login.api;

import static org.hamcrest.CoreMatchers.notNullValue;

import com.example.pages.login.api.AuthService;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class AuthApiStepDefinition {
    private AuthService authService = new AuthService();
    private Response response;

    @When("I attempt to login with username {string} and password {string}")
    public void i_attempt_to_login(String user, String pass) {
        response = authService.login(user, pass);
    }

    @Then("the API response status should be {int}")
    public void verify_status(int statusCode) {
        response.then().statusCode(statusCode);
        response.prettyPrint();
    }

    @Then("the response includes a valid token")
    public void verify_token() {
        response.then().body("token", notNullValue());
        response.prettyPrint();
    }
}
