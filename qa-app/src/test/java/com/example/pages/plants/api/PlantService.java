package com.example.pages.plants.api;

import java.util.Map;

import com.example.pages.login.api.AuthService;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlantService {

    private AuthService authService = new AuthService();

    public Response createPlantAdmin(int categoryId, Map<String, Object> plantData) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        // Based on your preference for Query Parameters
        return request
                .queryParam("categoryId", categoryId)
                .body(plantData)
                .post("/api/plants/category/{categoryId}", categoryId);
    }

    public Response createPlantNonAdmin(int categoryId, Map<String, Object> plantData) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("testuser", "test123");

        // Based on your preference for Query Parameters
        return request
                .queryParam("categoryId", categoryId)
                .body(plantData)
                .post("/api/plants/category/{categoryId}", categoryId);
    }

    public Response createPlantWithoutToken(int categoryId, Map<String, Object> plantData) {
        return io.restassured.RestAssured.given()
                .header("Content-Type", "application/json")
                .pathParam("categoryId", categoryId)
                .body(plantData)
                .post("/api/plants/category/{categoryId}");
    }
    public Response getPlantsByCategory(int categoryId, String username, String password) {
        return authService.getAuthenticatedRequest(username, password)
                .pathParam("catId", categoryId)
                .get("/api/plants/category/{catId}");
    }

    public Response getPlantsWithoutToken(int categoryId) {
        return RestAssured.given()
                .pathParam("catId", categoryId)
                .get("/api/plants/category/{catId}");
    }
}
