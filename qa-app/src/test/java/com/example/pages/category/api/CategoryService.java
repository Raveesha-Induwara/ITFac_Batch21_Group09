package com.example.pages.category.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import com.example.pages.login.api.AuthService;

public class CategoryService {

    private AuthService authService = new AuthService();

    public Response createCategoryAdmin(String categoryName) {
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        Map<String, Object> body = new HashMap<>();
        body.put("name", categoryName);

        return request
                .body(body)
                .post("/api/categories");
    }

    public Response createCategoryNonAdmin(String categoryName) {
        RequestSpecification request = authService.getAuthenticatedRequest("testuser", "test123");

        Map<String, Object> body = new HashMap<>();
        body.put("name", categoryName);

        return request
                .body(body)
                .post("/api/categories");
    }

    public Response deleteCategory(int categoryId) {
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        return request.delete("/api/categories/{id}", categoryId);
    }

    public Response updateCategory(int categoryId, String newName) {
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        Map<String, Object> body = new HashMap<>();
        body.put("name", newName);

        return request
                .body(body)
                .put("/api/categories/{id}", categoryId);
    }
}
