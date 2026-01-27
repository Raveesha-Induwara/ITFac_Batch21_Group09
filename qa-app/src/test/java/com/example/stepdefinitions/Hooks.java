package com.example.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import com.example.utils.DriverFactory;

public class Hooks {

    private static WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(DriverFactory.getBaseUrl()); // 🔥 base URL only
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
