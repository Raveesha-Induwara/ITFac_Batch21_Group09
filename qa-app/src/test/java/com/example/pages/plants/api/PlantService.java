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
        RequestSpecification request = authService.getAuthenticatedRequest("testuser", "test123");

        return request
                .queryParam("categoryId", categoryId)
                .body(plantData)
                .post("/api/plants/category/{categoryId}", categoryId);
    }

    public Response createPlantWithoutToken(int categoryId, Map<String, Object> plantData) {
        return RestAssured.given()
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

    public Response getAllPlants(String username, String password) {
        return authService.getAuthenticatedRequest(username, password)
                .get("/api/plants");
    }

    public Response getAllPlantsWithoutToken() {
        return RestAssured.given()
                .get("/api/plants");
    }

    public Response getPagedPlants(String username, String password, int page, int size) {
        return authService.getAuthenticatedRequest(username, password)
                .queryParam("page", page)
                .queryParam("size", size)
                .get("/api/plants/paged");
    }

    public Response getPagedPlantsWithoutToken(int page, int size) {
        return RestAssured.given()
                .queryParam("page", page)
                .queryParam("size", size)
                .get("/api/plants/paged");
    }

    public Response updatePlant(int plantId, Map<String, Object> plantData, String username, String password) {
        return authService.getAuthenticatedRequest(username, password)
                .pathParam("id", plantId)
                .body(plantData)
                .put("/api/plants/{id}");
    }

    public Response deletePlant(int plantId, String username, String password) {
        return authService.getAuthenticatedRequest(username, password)
                .pathParam("id", plantId)
                .delete("/api/plants/{id}");
    }

    public Response deletePlantWithoutToken(int plantId) {
        return RestAssured.given()
                .pathParam("id", plantId)
                .delete("/api/plants/{id}");
    }
}
