package com.example.pages.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.utils.DriverFactory;

public class LoginPage {
    
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton   = By.className("btn-primary");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void entersValidCredentials() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys("admin");

        driver.findElement(passwordField).sendKeys("admin123");
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isOnDashboardPage() {
        return driver.getCurrentUrl()
                .equalsIgnoreCase(
                    DriverFactory.getBaseUrl() + "/ui/dashboard"
                ) && driver.getTitle()
                .equalsIgnoreCase("QA Training App | Dashboard");
    }
    
}
