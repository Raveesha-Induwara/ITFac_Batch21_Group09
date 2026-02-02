package com.example;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/plants/plant_list_pagination.feature",
    glue = {"com.example.stepdefinitions"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true
)
public class TestRunner {
    
}
