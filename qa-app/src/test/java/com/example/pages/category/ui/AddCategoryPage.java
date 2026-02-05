package com.example.pages.category.ui;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.utils.DriverFactory;

public class AddCategoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Existing locators preserved
    private By addCategoryBtn = By.xpath("/html/body/div[1]/div/div[2]/div[2]/form/div[3]/a[2]");
    private By subCategoryDropdown = By.name("categoryId");
    private By categoryNameField = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[1]/input");
    private By priceField = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[3]/input");
    private By quantityField = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[4]/input");
    private By submitBtn = By.xpath("/html/body/div/div/div[2]/div[2]/form/button");
    private By cancelBtn = By.linkText("Cancel");
    private By SubmissionAlertMsg = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div");

    // Validation error message locator
    private By validationErrorMsg = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[1]/div");

    public AddCategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToCategoryPage() {
        String targetUrl = DriverFactory.getBaseUrl() + "/ui/categories";
        
        try {
            if (!driver.getCurrentUrl().equals(targetUrl)) {
                driver.get(targetUrl);
            }
            
        } catch (TimeoutException e) {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.elementToBeClickable(addCategoryBtn));
        }
    }

    public boolean isOnCategoryPage() {
        return wait.until(ExpectedConditions.urlContains("/ui/categories"));
    }

    public boolean isAddCategoryButtonVisible() {
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(addCategoryBtn));
            return button.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isOnAddCategoryPage() {
        try {
            return wait.until(ExpectedConditions.urlContains("/ui/categories/add"));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickAddCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(addCategoryBtn)).click();
        // Wait for navigation to Add Category page
        wait.until(ExpectedConditions.urlContains("/ui/categories/add"));
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

    public void enterCategoryName(String name) {
        WebElement nameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryNameField));
        nameEl.clear();
        if (name != null && !name.isEmpty()) {
            nameEl.sendKeys(name);
        }
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public String getValidationErrorMessage() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(validationErrorMsg));
            return errorElement.getText().trim();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public boolean isValidationErrorDisplayed() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(validationErrorMsg));
            return errorElement.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isFormStillDisplayed() {
        try {
            return driver.findElement(submitBtn).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String AlertMsg() {

        try {
            // Wait for the message to appear
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SubmissionAlertMsg));
            return messageElement.getText();
        } catch (TimeoutException e) {
            return "No message displayed";
        }
    }
    // Method to click cancel

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
    }

// Method to verify the URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public boolean isCategoryNameVisibleInTable(String categoryName) {
        // This looks for any cell (td) or link (a) inside the tbody that matches the category name
        By categoryRow = By.xpath("//tbody//tr//td[contains(text(), '" + categoryName + "')]");

        try {
            // Wait up to a few seconds for the table to refresh and show the new entry
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryRow));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false; // Category not found on the current page
        }
    }
   
}

