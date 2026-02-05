package com.example.pages.sales.api;
import com.example.pages.login.api.AuthService;
import com.example.pages.sales.api.SalesSeedService;

import io.restassured.specification.RequestSpecification;

public class SalesService {

    private AuthService authService;
    private SalesSeedService salesSeedService;

    public SalesService() {
        this.authService = new AuthService();
    }

    public void authorizeAsAdmin() {
        RequestSpecification authRequest = authService.getAuthenticatedRequest("admin", "admin123");
    }
    
}
