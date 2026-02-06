package com.example.pages.category.ui;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchCategory {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By searchBar = By.xpath("/html/body/div[1]/div/div[2]/div[2]/form/div[1]/input");
    private By searchBtn = By.xpath("/html/body/div[1]/div/div[2]/div[2]/form/div[3]/button");
    private By tableBody = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody");
    // Parent dropdown in the filter
    private By parentSelect = By.xpath("/html/body/div[1]/div/div[2]/div[2]/form/div[2]/select");
    // This finds the 3rd column (Parent) for ALL rows in the table body
    private By parentColumnCells = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody/tr/td[3]");

    public SearchCategory(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterSearchTerm(String term) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        input.clear();
        input.sendKeys(term);
    }
    public void selectParent(String parentName) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(parentSelect));
        Select select = new Select(dropdown);
        
        // Check if the option exists before trying to select it
        boolean optionExists = select.getOptions().stream()
            .anyMatch(option -> option.getText().equals(parentName));
        
        if (!optionExists) {
            // This is a known UI bug - the option doesn't exist in the dropdown
            throw new AssertionError("UI Bug: The option '" + parentName + "' does not exist in the parent dropdown filter. " +
                "Available options: " + select.getOptions().stream()
                    .map(WebElement::getText)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("none"));
        }
        
        select.selectByVisibleText(parentName);
    }

    public void clickSearch() {
        driver.findElement(searchBtn).click();
    }

    public int getResultCount() {
        try {
            WebElement body = driver.findElement(tableBody);
            List<WebElement> rows = body.findElements(By.tagName("tr"));
            
            // Check if the "No category found" message is displayed
            if (rows.size() == 1) {
                String rowText = rows.get(0).getText().trim();
                if (rowText.equalsIgnoreCase("No category found")) {
                    return 0;
                }
            }
            
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean areAllParentsMatching(String expectedParent) {
        // Wait for results to load
        wait.until(ExpectedConditions.presenceOfElementLocated(parentColumnCells));
        List<WebElement> cells = driver.findElements(parentColumnCells);
        
        if (cells.isEmpty()) return false;

        for (WebElement cell : cells) {
            if (!cell.getText().trim().equalsIgnoreCase(expectedParent)) {
                return false; // Found a row that doesn't match the filter
            }
        }
        return true;
    }
}
