package com.example.stepdefinitions.sales.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.junit.Assert.*;

import com.example.pages.login.api.AuthService;
import com.example.pages.sales.api.SalesSeedService;

public class CreateSaleApiStepDefinitions {

    private SalesSeedService salesSeedService;
    private Response response;
    private int plantId;
    private int quantity;
    private AuthService authService;
    private int saleId;

    public CreateSaleApiStepDefinitions() {
        this.authService = new AuthService();
        this.salesSeedService = new SalesSeedService();
    }

    @Given("an admin authorization token is available")
    public void userIsAuthorizedAsAdmin() {
        response = authService.login("admin", "admin123");
        String token = response.jsonPath().getString("token");
        assertNotNull("Failed to obtain admin auth token", token);
    }

    @Given("a plant with id {int} exists")
    public void aPlantWithIdExists(Integer id) {
        assertTrue(salesSeedService.isPlantPresentInSales(id));

    }

    @When("the admin sends POST request to sell plant with id {int} and quantity {int}")
    public void theAdminSendsPostRequest(Integer id, Integer qty) {
        plantId = id;
        quantity = qty;
        response = salesSeedService.createSale(plantId, quantity);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("the response should contain valid sale details")
    public void theResponseShouldContainValidSaleDetails() {
        // Sale level
        assertNotNull(response.jsonPath().get("id"));
        assertEquals(quantity, response.jsonPath().getInt("quantity"));
        assertNotNull(response.jsonPath().get("soldAt"));
        assertTrue(response.jsonPath().getDouble("totalPrice") > 0);

        // Plant details
        assertEquals(plantId, response.jsonPath().getInt("plant.id"));
        assertNotNull(response.jsonPath().getString("plant.name"));
        assertTrue(response.jsonPath().getDouble("plant.price") > 0);

        // Category details
        assertNotNull(response.jsonPath().get("plant.category.id"));
        assertNotNull(response.jsonPath().get("plant.category.name"));
    }

    @Given("a non-admin authorization token is available")
    public void userIsAuthorizedAsNonAdmin() {
        response = authService.login("testuser", "test123");
        String token = response.jsonPath().getString("token");
        assertNotNull("Failed to obtain non-admin auth token", token);
    }

    @When("the non-admin sends POST request to sell plant with id {int} and quantity {int}")
    public void theNonAdminSendsPostRequest(Integer id, Integer qty) {
        plantId = id;
        quantity = qty;

        // Use a method in SalesSeedService to create sale using token
        response = salesSeedService.createSaleAsUser(plantId, quantity, response.jsonPath().getString("token"));
    }

    @Then("the response should contain error message {string}")
    public void theResponseShouldContainErrorMessage(String message) {
        String error = response.jsonPath().getString("error");
        assertEquals(message, error);
    }

    @Given("no authorization token is provided")
    public void noAuthorizationTokenIsProvided() {
        // no token will be used
    }

    @When("the unauthorized user sends POST request to sell plant with id {int} and quantity {int}")
    public void theUserSendsPostRequest(Integer id, Integer qty) {
        plantId = id;
        quantity = qty;

        // Use a method in SalesSeedService for unauthenticated request
        response = salesSeedService.createSaleWithoutAuth(plantId, quantity);
    }

    @Given("a sale with id {int} exists")
    public void aSaleWithIdExists(Integer id) {
        saleId = id;
        assertTrue("Sale does not exist", salesSeedService.isSalePresent(saleId));
    }

    @When("the admin sends DELETE request to delete sale with id {int}")
    public void adminSendsDeleteRequest(Integer id) {
        response = salesSeedService.deleteSaleAdmin(id);
    }

    @Then("the sale should be deleted successfully")
    public void saleShouldBeDeletedSuccessfully() {
        boolean exists = salesSeedService.isSalePresent(saleId);
        assertFalse("Sale was not deleted", exists);
    }

    @Then("the response body should be empty")
    public void responseBodyShouldBeEmpty() {
        assertTrue("Response body is not empty", response.getBody().asString().isEmpty());
    }

    @When("the non-admin sends DELETE request to delete sale with id {int}")
    public void nonAdminSendsDeleteRequest(Integer id) {
        response = salesSeedService.deleteSaleNonAdmin(id);
    }

    @Then("the sale should not be deleted successfully")
    public void saleShouldNotBeDeletedSuccessfully() {
        boolean exists = salesSeedService.isSalePresent(saleId);
        assertTrue("Sale was deleted unexpectedly", exists);
    }

}
