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
    private By cancelBtn = By.xpath("/html/body/div/div/div[2]/div[2]/form/a");
    private By SubmissionAlertMsg = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div");
    private By parentCategoryDropdown = By.name("parentId");

    // Validation error message locator
    private By validationErrorMsg = By.xpath("/html/body/div/div/div[2]/div[2]/form/div[1]/div");
    
    // Edit button in the first row of the table
    private By firstRowEditBtn = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr[1]/td[4]/a");
    
    // Delete button in the first row of the table
    private By firstRowDeleteBtn = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr[1]/td[4]/form/button");
    
    // Table column headers for sorting
    private By idColumnHeader = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/thead/tr/th[1]");
    private By nameColumnHeader = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/thead/tr/th[2]");
    private By parentColumnHeader = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/thead/tr/th[3]");
    
    // Table rows and cells for verification
    private By tableRows = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr");
    private By firstRowIdCell = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr[1]/td[1]");
    private By firstRowNameCell = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr[1]/td[2]");
    private By firstRowParentCell = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr[1]/td[3]");

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
        // add wait 10s
        wait.until(ExpectedConditions.elementToBeClickable(categoryNameField));
        if (name != null && !name.isEmpty()) {
            nameEl.sendKeys(name);
        }
    }

    public void selectParentCategory(String parentCategory) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(parentCategoryDropdown));
        Select select = new Select(dropdown);
        try {
            select.selectByVisibleText(parentCategory);
        } catch (Exception e) {
            select.selectByIndex(0);
        }
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public void showSuccessMessage(String expectedMessage) {
        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SubmissionAlertMsg));
            String actualMessage = messageElement.getText().trim();
            if (actualMessage.equals(expectedMessage)) {
                System.out.println("Success message displayed: " + actualMessage);
            } else {
                System.out.println("Unexpected message. Expected: '" + expectedMessage + "', but got: '" + actualMessage + "'");
            }
        } catch (TimeoutException e) {
            System.out.println("No success message displayed within the timeout period.");
        }
    }

    public void verifyCategoryInList(String categoryName) {
        try {
            WebElement categoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//tr//td[contains(text(), '" + categoryName + "')]")));
            if (categoryElement.isDisplayed()) {
                System.out.println("Category '" + categoryName + "' is visible in the list.");
            } else {
                System.out.println("Category '" + categoryName + "' is not visible in the list.");
            }
        } catch (TimeoutException e) {
            System.out.println("Category '" + categoryName + "' was not found in the list within the timeout period.");
        }
    }

    public void deleteFirstCategory() {
        try {
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(firstRowDeleteBtn));
            deleteButton.click();
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            System.out.println("First category deleted successfully.");
        } catch (TimeoutException e) {
            System.out.println("Failed to delete the first category within the timeout period.");
        }
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
        // Wait for navigation back to categories page
        wait.until(ExpectedConditions.urlContains("/ui/categories"));
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
    
    public boolean isEditButtonVisible() {
        try {
            WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowEditBtn));
            return editButton.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    public boolean isEditButtonClickable() {
        try {
            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(firstRowEditBtn));
            return editButton.isEnabled();
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    public boolean isDeleteButtonVisible() {
        try {
            WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowDeleteBtn));
            return deleteButton.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    public boolean isDeleteButtonClickable() {
        try {
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(firstRowDeleteBtn));
            return deleteButton.isEnabled();
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    // Sorting methods
    public void clickIdColumnHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(idColumnHeader)).click();
        try {
            Thread.sleep(500); // Wait for sort to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void clickNameColumnHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(nameColumnHeader)).click();
        try {
            Thread.sleep(500); // Wait for sort to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void clickParentColumnHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(parentColumnHeader)).click();
        try {
            Thread.sleep(500); // Wait for sort to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public java.util.List<String> getCategoryIds() {
        java.util.List<String> ids = new java.util.ArrayList<>();
        java.util.List<WebElement> rows = driver.findElements(tableRows);
        
        for (WebElement row : rows) {
            try {
                WebElement idCell = row.findElement(By.xpath("./td[1]"));
                ids.add(idCell.getText().trim());
            } catch (Exception e) {
                // Skip if cell not found
            }
        }
        return ids;
    }
    
    public java.util.List<String> getCategoryNames() {
        java.util.List<String> names = new java.util.ArrayList<>();
        java.util.List<WebElement> rows = driver.findElements(tableRows);
        
        for (WebElement row : rows) {
            try {
                WebElement nameCell = row.findElement(By.xpath("./td[2]"));
                names.add(nameCell.getText().trim());
            } catch (Exception e) {
                // Skip if cell not found
            }
        }
        return names;
    }
    
    public java.util.List<String> getCategoryParents() {
        java.util.List<String> parents = new java.util.ArrayList<>();
        java.util.List<WebElement> rows = driver.findElements(tableRows);
        
        for (WebElement row : rows) {
            try {
                WebElement parentCell = row.findElement(By.xpath("./td[3]"));
                parents.add(parentCell.getText().trim());
            } catch (Exception e) {
                // Skip if cell not found
            }
        }
        return parents;
    }
   
}

