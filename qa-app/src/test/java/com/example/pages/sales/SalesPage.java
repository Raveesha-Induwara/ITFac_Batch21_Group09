package com.example.pages.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.example.utils.DriverFactory;

import java.time.Duration;
import java.util.List;

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

    private By paginationContainer = By.cssSelector("//ul.pagination");
    private By nextPageButton = By.cssSelector("//ul.pagination li.next a");

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

    /** Checks that max 10 records are displayed on the page */
    public boolean areSalesRecordsWithinPageLimit() {
        List<WebElement> rows =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(salesTableRows));

        return rows.size() <= 10;
    }

    /** Checks whether pagination controls are visible */
    public boolean hasPaginationControls() {
        List<WebElement> pagination =
                driver.findElements(paginationContainer);

        return !pagination.isEmpty() && pagination.get(0).isDisplayed();
    }

    /** Verifies navigation between pages using pagination */
    public boolean canNavigateBetweenPages() {

        List<WebElement> firstPageRows =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(salesTableRows));

        if (firstPageRows.isEmpty()) {
            return false;
        }

        String firstRowText = firstPageRows.get(0).getText();

        WebElement nextButton =
                wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
        nextButton.click();

        // Wait for table to refresh
        wait.until(ExpectedConditions.stalenessOf(firstPageRows.get(0)));

        List<WebElement> secondPageRows =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(salesTableRows));

        if (secondPageRows.isEmpty()) {
            return false;
        }

        String secondRowText = secondPageRows.get(0).getText();

        return !firstRowText.equals(secondRowText);
    }
}
