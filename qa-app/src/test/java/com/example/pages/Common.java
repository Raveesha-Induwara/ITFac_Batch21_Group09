package com.example.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.utils.DriverFactory;

public class Common {

    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton   = By.className("btn-primary");
    
    public Common(WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void launchApplication() {
        driver.get(DriverFactory.getBaseUrl() + "/ui/login");
    }

    public void loginAsAdmin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys("admin");

        driver.findElement(passwordField).sendKeys("admin123");
        driver.findElement(loginButton).click();
    }

}
