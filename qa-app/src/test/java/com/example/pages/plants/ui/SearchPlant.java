package com.example.pages.plants.ui;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPlant {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By searchBar = By.className("form-control");
    private By searchBtn = By.cssSelector(".btn.btn-primary.me-2");
    private By resetBtn = By.xpath("/html/body/div[1]/div/div[2]/div[2]/form/div[3]/a[1]");
    // Using the table body path you provided
    private By tableBody = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody");
    private By categorySelect = By.className("form-select");
    // This finds the 2nd column (Category) for ALL rows in the table body
    private By categoryColumnCells = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr/td[2]");

    public SearchPlant(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterSearchTerm(String term) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        input.clear();
        input.sendKeys(term);
    }
    public void selectCategory(String categoryName) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(categorySelect));
        Select select = new Select(dropdown);
        select.selectByVisibleText(categoryName);
    }

    public void clickSearch() {
        driver.findElement(searchBtn).click();
    }

    public void clickReset() {
        WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(resetBtn));
        resetButton.click();
    }

    public String getSearchBarValue() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        return input.getAttribute("value");
    }

    public String getSelectedCategory() {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(categorySelect));
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public boolean isSearchBarEmpty() {
        String value = getSearchBarValue();
        return value == null || value.trim().isEmpty();
    }

    public boolean isCategoryFilterReset() {
        String selectedCategory = getSelectedCategory();
        return selectedCategory.equalsIgnoreCase("All Categories");
    }


    public int getResultCount() {
        try {
            Thread.sleep(500); // Wait for table to update
            WebElement body = driver.findElement(tableBody);
            return body.findElements(By.tagName("tr")).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getInitialCategoryValue() {
        return getSelectedCategory();
    }
    public boolean areAllResultsMatching(String expectedCategory) {
        // Wait for the rows to be present
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(categoryColumnCells));

        // Give the table a tiny moment to finish refreshing data
        wait.until(d -> d.findElements(categoryColumnCells).size() > 0);

        List<WebElement> cells = driver.findElements(categoryColumnCells);

        if (cells.isEmpty()) {
            return false;
        }

        for (WebElement cell : cells) {
            String actualText = cell.getText().trim();

            if (!actualText.equals(expectedCategory)) {
                System.err.println("Validation Failed!");
                System.err.println("Expected: [" + expectedCategory + "]");
                System.err.println("Actual (Trimmed): [" + actualText + "]");
                return false;
            }
        }
        return true;
    }
}
