package com.example.stepdefinitions.category.api;

import io.restassured.response.Response;

public class CategoryApiContext {
    private static Response response;

    public static void setResponse(Response res) {
        response = res;
    }

    public static Response getResponse() {
        return response;
    }
}
