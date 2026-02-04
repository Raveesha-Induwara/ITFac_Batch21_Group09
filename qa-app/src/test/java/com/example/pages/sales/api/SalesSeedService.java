package com.example.pages.sales.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SalesSeedService {

    private RequestSpecification request;

    public SalesSeedService(RequestSpecification request) {
        this.request = request;
    }

    public Response createSale(int plantId, int quantity) {
        return request
                .given() // 🔥 isolates request
                .queryParam("quantity", quantity)
                .pathParam("plantId", plantId)
                .post("/api/sales/plant/{plantId}")
                .then()
                .statusCode(201)
                .extract()
                .response();
    }

    public int getSalesCount() {
        return request
                .get("/api/sales")
                .jsonPath()
                .getList("$")
                .size();
    }
}
