package com.example.pages.category.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.assertNotNull;

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
    
    public Response getCategorySummary() {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        return request.get("/api/categories/summary");
    }
    
    public Response getCategoryByIdNonAdmin(int categoryId) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("testuser", "test123");

        return request.get("/api/categories/{id}", categoryId);
    }
    
    public Response getMainCategoriesNonAdmin() {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("testuser", "test123");

        return request.get("/api/categories/main");
    }

    public Response getSubCategoriesNonAdmin() {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("testuser", "test123");

        return request.get("/api/categories/sub-categories");
    }

    public Response createCategoryAdmin(Map<String, Object> categoryData) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        return request
                .body(categoryData)
                .post("/api/categories");
    }

    public Response createCategoryWithoutAuth(Map<String, Object> categoryData) {
        return RestAssured.given()
                .body(categoryData)
                .post("/api/categories");
    }

    public void isCategoryCreated(Response response) {
        if (response.getStatusCode() == 201 || response.getStatusCode() == 200) {
            System.out.println("Category created successfully with ID: " + response.jsonPath().get("id"));
        } else {
            System.out.println("Failed to create category. Status code: " + response.getStatusCode());
        }
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

    public Response updateCategoryNonAdmin(int categoryId, Map<String, Object> categoryData) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("testuser", "test123");

        return request
                .body(categoryData)
                .put("/api/categories/{id}", categoryId);
    }

    public Response deleteCategory(int categoryId) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");

        return request.delete("/api/categories/{id}", categoryId);
    }

    public Response deleteCategoryNonAdmin(int categoryId) {
        // Obtains the request spec with the Bearer token already set
        RequestSpecification request = authService.getAuthenticatedRequest("testuser", "test123");

        return request.delete("/api/categories/{id}", categoryId);
    }

    public void isMainCategoryExist(String categoryName) {
        RequestSpecification request = authService.getAuthenticatedRequest("admin", "admin123");
        Response response = request.get("/api/categories/main");

        boolean categoryExists = response.jsonPath().getList("name").contains(categoryName);
        if (categoryExists) {
            System.out.println("Main category '" + categoryName + "' already exists.");
        } else {
            System.out.println("Main category '" + categoryName + "' does not exist.");
            assertNotNull("Main category '" + categoryName + "' should exist but was not found.", null);
        }
    }
}
