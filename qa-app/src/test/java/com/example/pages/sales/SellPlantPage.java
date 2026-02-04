package com.example.pages.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SellPlantPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SellPlantPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }
    
    private By plantSelectDropdown = By.id("plant");
    private By quantityInput = By.id("quantity");
    private By priceInput = By.id("price");
    private By submitButton = By.id("submitSale");

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.urlContains("/ui/sales/new")) != null;
    }

    public boolean hasAllRequiredFields() {
        return !driver.findElements(plantSelectDropdown).isEmpty() &&
               !driver.findElements(quantityInput).isEmpty() &&
               !driver.findElements(priceInput).isEmpty() &&
               !driver.findElements(submitButton).isEmpty();
    }
}
