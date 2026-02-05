package com.example.pages.login.api;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthService {


    public AuthService() {
        RestAssured.baseURI = "http://localhost:8081";
    }

    public Response login(String username, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/api/auth/login");
    }
    public RequestSpecification getAuthenticatedRequest(String username, String password) {
        Response response = login(username, password);
        String token = response.jsonPath().getString("token");

        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");
    }
}
