package com.example.pages.plants.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import com.example.pages.login.api.AuthService;

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
}
