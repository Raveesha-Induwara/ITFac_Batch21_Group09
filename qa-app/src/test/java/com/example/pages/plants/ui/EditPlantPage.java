package com.example.pages.plants.ui;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.utils.DriverFactory;

public class EditPlantPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for edit functionality
    private By editButtonFirstRow = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr[1]/td[5]/a");
    private By plantNameField = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[1]/input");
    private By categoryDropdown = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[2]/select");
    private By priceField = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[3]/input");
    private By quantityField = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[4]/input");
    private By saveButton = By.xpath("//button[contains(text(),'Save')]");
    private By cancelButton = By.linkText("Cancel");

    // Error message locators
    private By plantNameError = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[1]/div");
    private By categoryError = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[2]/div");
    private By priceError = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[3]/div");
    private By quantityError = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[4]/div");

    // Success message
    private By successMessage = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div");

    // Flexible error locator
    private By anyErrorMsg = By.xpath("//div[contains(@class, 'text-danger')]");

    public EditPlantPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToPlantListPage() {
        String targetUrl = DriverFactory.getBaseUrl() + "/ui/plants";
        try {
            if (!driver.getCurrentUrl().equals(targetUrl)) {
                driver.get(targetUrl);
            }
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
        } catch (TimeoutException e) {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
        }
    }

    public boolean isOnPlantListPage() {
        return wait.until(ExpectedConditions.urlContains("/ui/plants"));
    }

    public void clickEditButtonForFirstPlant() {
        wait.until(ExpectedConditions.elementToBeClickable(editButtonFirstRow)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(plantNameField));
    }

    public void clickEditButtonForPlantByName(String plantName) {
        By editButton = By.xpath("//td[contains(text(),'" + plantName + "')]/following-sibling::td//a[contains(@href,'/edit/')]");
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(plantNameField));
    }

    public boolean isOnEditPlantPage() {
        return wait.until(ExpectedConditions.urlContains("/ui/plants/edit/"));
    }

    public String getPlantName() {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(plantNameField));
        return nameField.getAttribute("value");
    }

    public String getPrice() {
        WebElement pField = wait.until(ExpectedConditions.visibilityOfElementLocated(priceField));
        return pField.getAttribute("value");
    }

    public String getQuantity() {
        WebElement qField = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityField));
        return qField.getAttribute("value");
    }

    public String getSelectedCategory() {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryDropdown));
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public void clearAndEnterPlantName(String name) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(plantNameField));
        nameField.clear();
        if (name != null && !name.isEmpty()) {
            nameField.sendKeys(name);
        }
    }

    public void selectCategory(String categoryName) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
        Select select = new Select(dropdown);
        try {
            select.selectByVisibleText(categoryName);
        } catch (Exception e) {
            // Fallback to selecting by index if exact match fails
            select.selectByIndex(0);
        }
    }

    public void clearAndEnterPrice(String price) {
        WebElement pField = wait.until(ExpectedConditions.visibilityOfElementLocated(priceField));
        pField.clear();
        if (price != null && !price.isEmpty()) {
            pField.sendKeys(price);
        }
    }

    public void clearAndEnterQuantity(String quantity) {
        WebElement qField = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityField));
        qField.clear();
        if (quantity != null && !quantity.isEmpty()) {
            qField.sendKeys(quantity);
        }
    }

    public void clickSave() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        String currentUrl = driver.getCurrentUrl();

        try {
            saveBtn.click();
        } catch (Exception e) {
            // Use JavaScript click as fallback
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        }

        // Wait for either navigation or error message to appear
        wait.until(driver ->
            !driver.getCurrentUrl().equals(currentUrl) ||
            !driver.findElements(anyErrorMsg).isEmpty()
        );
    }

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }

    public String getPlantNameErrorMessage() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(plantNameError));
            return errorElement.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getCategoryErrorMessage() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryError));
            return errorElement.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getPriceErrorMessage() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(priceError));
            return errorElement.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getQuantityErrorMessage() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityError));
            return errorElement.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getAllErrorMessages() {
        try {
            StringBuilder combinedErrors = new StringBuilder();
            wait.until(ExpectedConditions.presenceOfElementLocated(anyErrorMsg));
            for (WebElement error : driver.findElements(anyErrorMsg)) {
                if (error.isDisplayed()) {
                    combinedErrors.append(error.getText().trim()).append(" ");
                }
            }
            return combinedErrors.toString().replace("\n", " ").trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(anyErrorMsg));
            return driver.findElements(anyErrorMsg).stream()
                    .anyMatch(WebElement::isDisplayed);
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessage() {
        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return messageElement.getText().trim();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public boolean isFormStillDisplayed() {
        try {
            return driver.findElement(saveButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isPlantNameVisibleInTable(String plantName) {
        By plantRow = By.xpath("//tbody//tr//td[contains(text(), '" + plantName + "')]");
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(plantRow));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void navigateToEditPageDirectly(String plantId) {
        String editUrl = DriverFactory.getBaseUrl() + "/ui/plants/edit/" + plantId;
        driver.get(editUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(plantNameField));
    }

    /**
     * Get plant details from the plant list table by plant name
     * @param plantName The name of the plant to find
     * @return String array with [name, category, price, quantity] or null if not found
     */
    public String[] getPlantDetailsFromList(String plantName) {
        try {
            // Wait for the table to be visible
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));

            // Find the row containing the plant name
            By plantRowLocator = By.xpath("//tbody/tr[td[contains(text(), '" + plantName + "')]]");
            WebElement plantRow = wait.until(ExpectedConditions.presenceOfElementLocated(plantRowLocator));

            // Extract details from the row (assuming columns: Name, Category, Price, Quantity, Actions)
            String name = plantRow.findElement(By.xpath(".//td[1]")).getText().trim();
            String category = plantRow.findElement(By.xpath(".//td[2]")).getText().trim();
            String price = plantRow.findElement(By.xpath(".//td[3]")).getText().trim();
            String quantity = plantRow.findElement(By.xpath(".//td[4]")).getText().trim();

            return new String[]{name, category, price, quantity};
        } catch (Exception e) {
            System.out.println("Could not find plant details for: " + plantName + ". Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get the price of a specific plant from the list
     * @param plantName The name of the plant
     * @return The price as a string (may include currency symbol)
     */
    public String getPlantPriceFromList(String plantName) {
        String[] details = getPlantDetailsFromList(plantName);
        return details != null ? details[2] : null;
    }

    /**
     * Get the quantity of a specific plant from the list
     * @param plantName The name of the plant
     * @return The quantity as a string
     */
    public String getPlantQuantityFromList(String plantName) {
        String[] details = getPlantDetailsFromList(plantName);
        return details != null ? details[3] : null;
    }

    /**
     * Get the category of a specific plant from the list
     * @param plantName The name of the plant
     * @return The category as a string
     */
    public String getPlantCategoryFromList(String plantName) {
        String[] details = getPlantDetailsFromList(plantName);
        return details != null ? details[1] : null;
    }
}
