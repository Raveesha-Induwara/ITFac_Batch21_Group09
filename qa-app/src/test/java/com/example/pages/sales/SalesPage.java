package com.example.pages.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.example.utils.DriverFactory;

import java.time.Duration;

public class SalesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SalesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton   = By.className("btn-primary");
    private By salesTableRows = By.cssSelector("table tbody tr");

    public void goToLoginPage() {
        driver.get(DriverFactory.getBaseUrl() + "/ui/login");
    }

    public void loginAsAdmin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys("admin");

        driver.findElement(passwordField).sendKeys("admin123");
        driver.findElement(loginButton).click();
    }

    public void clickMenuInSideNavigation(String menuName) {
        By menuLocator = By.partialLinkText(menuName);

        wait.until(ExpectedConditions.elementToBeClickable(menuLocator)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(salesTableRows));
    }

    public boolean isMenuHighlighted(String menuName) {
        WebElement menu = driver.findElement(By.partialLinkText(menuName));
        return menu.getAttribute("class").contains("active");
    }

    public boolean isOnPage(String pageName) {
        return driver.getCurrentUrl()
                .equalsIgnoreCase(
                    DriverFactory.getBaseUrl() + "/ui/" + pageName.toLowerCase()
                );
    }
}
