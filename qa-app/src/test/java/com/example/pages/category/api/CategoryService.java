package com.example.pages.category.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import com.example.pages.login.api.AuthService;

public class CategoryService {

    private AuthService authService = new AuthService();

    public Response getMainCategoriesAdmin() {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        return request.get("/api/categories/main");
    }
    
    public Response getCategoryById(int categoryId) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        return request.get("/api/categories/{id}", categoryId);
    }
    
    public Response getMainCategoriesNonAdmin() {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("testuser", "test123");

        return request.get("/api/categories/main");
    }

    public Response createCategoryAdmin(Map<String, Object> categoryData) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        return request
                .body(categoryData)
                .post("/api/categories");
    }

    public Response createCategoryNonAdmin(Map<String, Object> categoryData) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("testuser", "test123");

        return request
                .body(categoryData)
                .post("/api/categories");
    }

    public Response updateCategory(int categoryId, Map<String, Object> categoryData) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        return request
                .body(categoryData)
                .put("/api/categories/{id}", categoryId);
    }

    public Response deleteCategory(int categoryId) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        return request.delete("/api/categories/{id}", categoryId);
    }
}
