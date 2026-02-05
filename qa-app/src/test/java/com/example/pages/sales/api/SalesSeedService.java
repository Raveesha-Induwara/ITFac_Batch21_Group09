package com.example.pages.sales.api;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.example.pages.login.api.AuthService;

public class SalesSeedService {

    private RequestSpecification request;
    private AuthService authService;

    public SalesSeedService() {
        this.authService = new AuthService();
    }

    public Response createSale(int plantId, int quantity) {
        request = authService.getAuthenticatedRequest("admin", "admin123");
        return request
                .given()
                .queryParam("quantity", quantity)
                .pathParam("plantId", plantId)
                .post("/api/sales/plant/{plantId}")
                .then()
                .statusCode(201)
                .extract()
                .response();
    }

    public Response createSaleAsUser(int plantId, int quantity, String token) {
        request = authService.getAuthenticatedRequest("testuser", "test123");
        return request
                .given()
                .queryParam("quantity", quantity)
                .pathParam("plantId", plantId)
                .post("/api/sales/plant/{plantId}")
                .then()
                .extract()
                .response();
    }

    public Response createSaleWithoutAuth(int plantId, int quantity) {
        return io.restassured.RestAssured.given()
                .queryParam("quantity", quantity)
                .pathParam("plantId", plantId)
                .post("/api/sales/plant/{plantId}")
                .then()
                .extract()
                .response();
    }

    public int getSalesCount() {
        request = authService.getAuthenticatedRequest("admin", "admin123");

        return request
                .get("/api/sales")
                .jsonPath()
                .getList("$")
                .size();
    }

    public boolean isPlantPresentInSales(int plantId) {
        request = authService.getAuthenticatedRequest("admin", "admin123");

        Response response = request
                .get("/api/sales")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Integer> plantIds = response.jsonPath()
                .getList("plant.id");

        return plantIds.contains(plantId);
    }

    public boolean isSalePresent(int saleId) {
        request = authService.getAuthenticatedRequest("admin", "admin123");

        Response response = request.get("/api/sales/" + saleId);

        return response.getStatusCode() == 200;
    }

    // Delete a sale with admin token
    public Response deleteSaleAdmin(int saleId) {
        request = authService.getAuthenticatedRequest("admin", "admin123");

        return request
                .delete("/api/sales/" + saleId)
                .then()
                .extract()
                .response();
    }

    public Response deleteSaleNonAdmin(int saleId) {
        request = authService.getAuthenticatedRequest("testuser", "test123");

        return request
                .delete("/api/sales/" + saleId)
                .then()
                .extract()
                .response();
    }

    public boolean salesExistAdmin() {
        request = authService.getAuthenticatedRequest("admin", "admin123");

        Response response = request.get("/api/sales");

        List<Object> sales = response.jsonPath().getList("$");
        return sales != null && sales.size() > 0;
    }

    public Response getAllSalesAdmin() {
        request = authService.getAuthenticatedRequest("admin", "admin123");
        return request.get("/api/sales");
    }

    public Response getAllSalesNonAdmin() {
        request = authService.getAuthenticatedRequest("testuser", "test123");
        return request.get("/api/sales");
    }

    public Response getSaleByIdAdmin(int saleId) {
        request = authService.getAuthenticatedRequest("admin", "admin123");
        return request
                .pathParam("id", saleId)
                .when()
                .get("/api/sales/{id}")
                .then()
                .extract()
                .response();
    }

    public Response getSaleByIdNonAdmin(int saleId) {
        request = authService.getAuthenticatedRequest("testuser", "test123");
        return request
                .pathParam("id", saleId)
                .when()
                .get("/api/sales/{id}")
                .then()
                .extract()
                .response();
    }

    public int getAnyExistingSaleIdAdmin() {
        request = authService.getAuthenticatedRequest("admin", "admin123");
        Response response = request
                .when()
                .get("/api/sales")
                .then()
                .extract()
                .response();

        return response.jsonPath().get("[0].id");
    }

}
