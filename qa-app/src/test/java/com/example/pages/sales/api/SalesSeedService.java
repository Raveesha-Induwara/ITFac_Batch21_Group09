package com.example.pages.sales.api;

import java.util.List;

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

}
