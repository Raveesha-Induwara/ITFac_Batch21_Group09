package com.example.pages.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.example.utils.DriverFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SalesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SalesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.className("btn-primary");

    // Updated selectors with better wait conditions
    private By salesTableRows = By.cssSelector("table tbody tr");
    private By salesTable = By.tagName("table");

    private By paginationContainer = By.cssSelector("ul.pagination");
    private By nextPageButton = By.cssSelector("ul.pagination li.next a");

    private By paginationLinks = By.cssSelector("ul.pagination li.page-item a.page-link");
    private By sellPlantButton = By.partialLinkText("Sell Plant");
    private By logoutButton = By.partialLinkText("Logout");

    private By salesRows = By.cssSelector("table tbody tr");
    private By soldDateColumn = By.cssSelector("table tbody tr td:nth-child(4)"); // adjust selector
    private By salesSideNavigation = By.partialLinkText("Sales");

    // Delete action
    private By firstRowDeleteButton = By.cssSelector("table tbody tr:first-child form button.btn-outline-danger");

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
                        DriverFactory.getBaseUrl() + "/ui/" + pageName.toLowerCase());
    }

    public boolean hasSalesData() {
        waitForSalesPageToLoad();
        List<WebElement> rows = driver.findElements(salesRows);
        return !rows.isEmpty();
    }

    private void waitForSalesPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(salesRows));
    }

    public boolean isPaginationVisible() {
        return !driver.findElements(paginationContainer).isEmpty();
    }

    private void scrollToPagination() {
        if (isPaginationVisible()) {
            WebElement pagination = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(paginationContainer));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", pagination);
        } else {
            throw new NoSuchElementException("Pagination container not found.");
        }
    }

    public boolean isSalesSortedBySoldDateDescending() {

        waitForSalesPageToLoad();

        List<WebElement> rows = driver.findElements(salesRows);
        List<LocalDateTime> soldDates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (WebElement row : rows) {
            String dateText = row.findElement(soldDateColumn)
                    .getText()
                    .trim();

            soldDates.add(LocalDateTime.parse(dateText, formatter));
        }

        for (int i = 0; i < soldDates.size() - 1; i++) {
            if (soldDates.get(i).isBefore(soldDates.get(i + 1))) {
                return false;
            }
        }

        return true;
    }

    public boolean hasPaginationControls() {
        waitForSalesPageToLoad();
        scrollToPagination();

        List<WebElement> links = driver.findElements(paginationLinks);
        return !links.isEmpty() && links.get(0).isDisplayed();
    }

    public boolean areSalesRecordsWithinPageLimit() {
        waitForSalesPageToLoad();
        return driver.findElements(salesRows).size() >= 11;
    }

    public boolean canNavigateBetweenPages() {
        waitForSalesPageToLoad();
        scrollToPagination();

        List<WebElement> links = driver.findElements(paginationLinks);
        if (links.size() < 2) {
            return false;
        }

        // Capture first row WebElement before click
        WebElement firstRowBefore = driver.findElements(salesRows).get(0);

        // Click Next
        for (WebElement link : links) {
            if (link.getText().equalsIgnoreCase("Next")) {
                wait.until(ExpectedConditions.elementToBeClickable(link)).click();
                break;
            }
        }

        // Wait for the first row element to become stale (table refreshed)
        wait.until(ExpectedConditions.stalenessOf(firstRowBefore));

        // Optional: wait for new rows to appear
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(salesRows));

        return true;
    }

    public boolean isSellPlantButtonVisible() {
        return !driver.findElements(sellPlantButton).isEmpty()
                && driver.findElements(sellPlantButton).get(0).isDisplayed();
    }

    public int getSalesRecordCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(salesRows));
        return driver.findElements(salesRows).size();
    }

    public void clickDeleteOnFirstSalesRecord() {
        wait.until(ExpectedConditions.elementToBeClickable(firstRowDeleteButton))
                .click();
    }

    public boolean isDeleteConfirmationAlertDisplayed() {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            alertWait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getDeleteConfirmationMessage() {
        return driver.switchTo().alert().getText();
    }

    public void cancelDeleteConfirmation() {
        driver.switchTo().alert().dismiss(); // Clicks "Cancel"
    }

}
