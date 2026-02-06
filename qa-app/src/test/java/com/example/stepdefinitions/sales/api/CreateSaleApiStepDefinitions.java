package com.example.stepdefinitions.sales.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import com.example.pages.login.api.AuthService;
import com.example.pages.sales.api.SalesSeedService;

public class CreateSaleApiStepDefinitions {

    private SalesSeedService salesSeedService;
    private Response response;
    private int plantId;
    private int quantity;
    private AuthService authService;
    private int saleId;
    private int stockBefore;
    private int stockAfter;
    private int sellQty;

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

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("the response should contain valid sale details")
    public void theResponseShouldContainValidSaleDetails() {
        // Sale level
        assertNotNull(response.jsonPath().get("id"));
        assertEquals(quantity+1, response.jsonPath().getInt("quantity"));
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

    @When("the non-admin sends a POST request to sell plant with quantity {int}")
    public void theNonAdminSendsPostRequest(int qty) {
        response = salesSeedService.createSaleAsNonAdmin(plantId, qty);
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

    @When("the unauthorized user sends a POST request to sell plant with quantity {int}")
    public void theUnauthorizedUserSendsPostRequest(int qty) {
        quantity = qty;
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

    @Given("sales records exist in the system")
    public void salesRecordsExist() {
        assertTrue("No sales exist in system", salesSeedService.salesExistAdmin());
    }

    @When("the admin sends GET request to retrieve all sales")
    public void adminSendsGetRequest() {
        response = salesSeedService.getAllSalesAdmin();
    }

    @Then("the response should contain a list of sales")
    public void responseShouldContainSalesList() {
        List<Object> sales = response.jsonPath().getList("$");
        assertNotNull(sales);
        assertTrue(sales.size() > 0);
    }

    @Then("each sale should contain valid sale details")
    public void eachSaleShouldContainValidDetails() {

        List<Map<String, Object>> sales = response.jsonPath().getList("$");

        for (Map<String, Object> sale : sales) {

            assertNotNull(sale.get("id"));
            assertNotNull(sale.get("quantity"));
            assertNotNull(sale.get("soldAt"));
            assertNotNull(sale.get("totalPrice"));

            Map<String, Object> plant = (Map<String, Object>) sale.get("plant");
            assertNotNull(plant);
            assertNotNull(plant.get("id"));
            assertNotNull(plant.get("name"));
            assertNotNull(plant.get("price"));
        }
    }

    @When("the non-admin sends GET request to retrieve all sales")
    public void nonAdminSendsGetRequest() {
        response = salesSeedService.getAllSalesNonAdmin();
    }

    @Given("a valid sale id exists")
    public void validSaleIdExists() {
        // You can fetch an existing sale id from DB or create one
        saleId = salesSeedService.getAnyExistingSaleIdAdmin();
        assertTrue(saleId > 0);
    }

    @When("the admin sends a GET request to retrieve sale details with the valid sale id")
    public void sendGetSaleByIdRequest() {
        response = salesSeedService.getSaleByIdAdmin(saleId);
    }

    @Then("the response body should contain sale details")
    public void verifySaleDetails() {
        assertNotNull(response.jsonPath().get("id"));
        assertNotNull(response.jsonPath().get("plant"));
        assertNotNull(response.jsonPath().get("quantity"));
        assertNotNull(response.jsonPath().get("totalPrice"));
        assertNotNull(response.jsonPath().get("soldAt"));
    }

    @When("the non-admin sends a GET request to retrieve sale details with the valid sale id")
    public void sendGetSaleByIdRequestNonAdmin() {
        response = salesSeedService.getSaleByIdNonAdmin(saleId);
    }

    @Given("a valid plant id exists")
    public void validPlantIdExists() {
        plantId = salesSeedService.getAnyPlantIdWithStock();
        stockBefore = salesSeedService.getPlantStock(plantId);
    }

    @When("the admin sends a POST request to sell plant with quantity {int}")
    public void sendInvalidSaleRequest(int qty) {
        response = salesSeedService.createSaleAsAdmin(plantId, qty);
    }

    @Then("the error response should contain validation details")
    public void verifyValidationError() {
        assertNotNull(response.jsonPath().get("status"));
        assertNotNull(response.jsonPath().get("error"));
        assertNotNull(response.jsonPath().get("message"));
        assertNotNull(response.jsonPath().get("timestamp"));
    }

    @Then("no sale should be created for the plant")
    public void verifyNoSaleCreated() {
        stockAfter = salesSeedService.getPlantStock(plantId);
        assertEquals(stockBefore, stockAfter);
    }

    @Given("a plant exists with limited stock")
    public void plantExistsWithLimitedStock() {
        plantId = salesSeedService.getAnyPlantIdWithStock();
        stockBefore = salesSeedService.getPlantStock(plantId);

        sellQty = stockBefore + 10; // intentionally exceeding stock
    }

    @When("the admin sends a POST request to sell quantity greater than available stock")
    public void sendSaleExceedStockRequest() {
        response = salesSeedService.createSaleAsAdmin(plantId, sellQty);
    }

    @When("the admin sends POST request to sell plant with id {string} and quantity {int}")
    public void theAdminSendsPostRequestWithStringId(String id, Integer qty) {

        quantity = qty;
        response = salesSeedService.createSaleWithStringId(id, quantity);
    }

}
