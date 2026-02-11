package com.example;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/categories",
        glue = {"com.example.stepdefinitions"},
        plugin = {
            "pretty",
            "html:target/cucumber-reports.html",
            "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "@api-category-retrieve",
        monochrome = true
)
public class TestRunner {
    
}
