package com.example.pages.plants.api;

import java.util.HashMap;
import java.util.Map;
import com.example.pages.login.api.AuthService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlantService {

    private AuthService authService = new AuthService();

    // Helper method to keep data construction out of Step Definitions
    private Map<String, Object> createPlantBody(int id, String name, int price, int quantity) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("name", name);
        body.put("price", price);
        body.put("quantity", quantity);
        return body;
    }

    public Response createPlantAdmin(int categoryId, String name, int price, int quantity) {
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");
        Map<String, Object> plantData = createPlantBody(10, name, price, quantity);

        return request
                .queryParam("categoryId", categoryId)
                .body(plantData)
                .post("/api/plants/category/{categoryId}", categoryId);
    }

    public Response createPlantNonAdmin(int categoryId, String name, int price, int quantity) {
        RequestSpecification request = authService.getAuthenticatedRequest("testuser", "test123");
        Map<String, Object> plantData = createPlantBody(10, name, price, quantity);

        return request
                .queryParam("categoryId", categoryId)
                .body(plantData)
                .post("/api/plants/category/{categoryId}", categoryId);
    }

    public Response createPlantWithoutToken(int categoryId, String name, int price, int quantity) {
        Map<String, Object> plantData = createPlantBody(15, name, price, quantity);

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
