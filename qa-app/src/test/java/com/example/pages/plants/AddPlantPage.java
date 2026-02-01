package com.example.pages.plants;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.utils.DriverFactory;

public class AddPlantPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Existing locators preserved
    private By addPlantBtn = By.xpath("//a[contains(@href, 'add')]");
    private By subCategoryDropdown = By.name("categoryId");
    private By plantNameField = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[1]/input");
    private By priceField = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[3]/input");
    private By quantityField = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[4]/input");
    private By submitBtn = By.xpath("/html/body/div/div/div[2]/div[2]/form/button");

    // Flexible locator to find any error message on the page
    private By anyErrorMsg = By.xpath("//div[contains(@class, 'text-danger')]");

    public AddPlantPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToPlantPage() {
        String targetUrl = DriverFactory.getBaseUrl() + "/ui/plants";
        
        try {
            if (!driver.getCurrentUrl().equals(targetUrl)) {
                driver.get(targetUrl);
            }
            wait.until(ExpectedConditions.elementToBeClickable(addPlantBtn));
        } catch (TimeoutException e) {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.elementToBeClickable(addPlantBtn));
        }
    }

    public boolean isOnPlantPage() {
        return wait.until(ExpectedConditions.urlContains("/ui/plants"));
    }

    public void clickAddPlant() {
        wait.until(ExpectedConditions.elementToBeClickable(addPlantBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(subCategoryDropdown));
    }

    public void selectCategoryByName(String categoryName) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(subCategoryDropdown));
        Select select = new Select(dropdown);
        try {
            select.selectByVisibleText(categoryName);
        } catch (Exception e) {
            select.selectByIndex(0);
        }
    }

    public void enterDetails(String price, String quantity) {
        WebElement pField = wait.until(ExpectedConditions.visibilityOfElementLocated(priceField));
        pField.clear();
        pField.sendKeys(price);
        WebElement qField = driver.findElement(quantityField);
        qField.clear();
        qField.sendKeys(quantity);
    }

    public void enterPlantName(String name) {
        WebElement nameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(plantNameField));
        nameEl.clear();
        if (name != null && !name.isEmpty()) {
            nameEl.sendKeys(name);
        }
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public String getValidationMessage() {
        try {
            StringBuilder combinedErrors = new StringBuilder();
            wait.until(ExpectedConditions.presenceOfElementLocated(anyErrorMsg));
            for (WebElement error : driver.findElements(anyErrorMsg)) {
                if (error.isDisplayed()) {
                    combinedErrors.append(error.getText()).append(" ");
                }
            }
            return combinedErrors.toString().replace("\n", " ").trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isFormStillDisplayed() {
        try {
            return driver.findElement(submitBtn).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
