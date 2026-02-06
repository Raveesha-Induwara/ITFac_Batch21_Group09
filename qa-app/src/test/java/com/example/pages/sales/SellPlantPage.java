package com.example.pages.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SellPlantPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SellPlantPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    private By plantSelectDropdown = By.id("plantId");
    private By quantityInput = By.id("quantity");
    private By submitButton = By.cssSelector("button.btn-primary");
    private By plantErrorMessage = By.cssSelector("#plantId + .text-danger");
    private By quantityErrorMessage = By.cssSelector("input#quantity + div.text-danger");
    private By plantOptions = By.cssSelector("#plantId option[value]:not([value=''])");

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.urlContains("/ui/sales/new")) != null;
    }

    public boolean hasAllRequiredFields() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(plantSelectDropdown));
            wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
            wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void leavePlantUnselected() {
        // Ensure dropdown exists, then leave it unselected
        wait.until(ExpectedConditions.visibilityOfElementLocated(plantSelectDropdown));
        driver.findElement(plantSelectDropdown).sendKeys(""); // do nothing / keep empty
    }

    public void fillQuantity(int quantity) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        driver.findElement(quantityInput).clear();
        driver.findElement(quantityInput).sendKeys(String.valueOf(quantity));
    }

    public boolean isPlantErrorMessageDisplayed() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(plantErrorMessage))
                    .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getPlantErrorMessageText() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(plantErrorMessage))
                    .getText().trim();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public boolean isFormStillOnPage() {
        return driver.getCurrentUrl().endsWith("/ui/sales/new");
    }

    public void submitForm() {
        driver.findElement(By.cssSelector("form")).submit(); // submit form properly
    }

    public void fillQuantity(String quantity) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        driver.findElement(quantityInput).clear();
        driver.findElement(quantityInput).sendKeys(quantity);
    }

    public void selectFirstAvailablePlant() {
        WebElement dropdownElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(plantSelectDropdown));

        Select dropdown = new Select(dropdownElement);

        // Select the first option that has a non-empty value
        for (WebElement option : dropdown.getOptions()) {
            String value = option.getAttribute("value");
            if (value != null && !value.isEmpty()) {
                dropdown.selectByValue(value);
                break;
            }
        }
    }

    public boolean isQuantityValidationDisplayed() {
        return !getQuantityValidationMessage().isEmpty();
    }

    public String getQuantityValidationMessage() {
        WebElement qty = wait.until(
                ExpectedConditions.visibilityOfElementLocated(quantityInput));

        return qty.getAttribute("validationMessage");
    }

}
