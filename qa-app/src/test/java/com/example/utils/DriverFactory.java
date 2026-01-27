package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {
            String browser = ConfigReader.get("browser");

            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            }

            driver.manage().window().maximize();
        }

        return driver;
    }

    public static String getBaseUrl() {
        return ConfigReader.get("baseUrl");
    }
}
