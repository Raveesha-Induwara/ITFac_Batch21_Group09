package com.example.pages.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.utils.DriverFactory;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By dashboardButton   = By.linkText("Dashboard");
    private By categoriesButton   = By.linkText("Categories");
    private By plantsButton   = By.linkText("Plants");
    private By salesButton   = By.linkText("Sales");
    private By manageCategoriesButton = By.linkText("Manage Categories");
    private By categoriesSummaryCard = By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div[1]/div/div");
    private By plantsSummaryCard = By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div[2]/div/div");
    private By salesSummaryCard = By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div[3]/div/div");
    
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    public void goToDashboardPage() {
        driver.get(DriverFactory.getBaseUrl() + "/ui/dashboard");
    }

    public void clickMenuInSideNavigation(String menuName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(menuName)));

        switch (menuName) {
            case "Dashboard":
                driver.findElement(dashboardButton).click();
                break;
            case "Categories":
                driver.findElement(categoriesButton).click();
                break;
            case "Plants":
                driver.findElement(plantsButton).click();
                break;
            case "Sales":
                driver.findElement(salesButton).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid menu name: " + menuName);
        }
    }

    public boolean isMenuHighlighted(String menuName) {
        return driver.findElement(By.linkText(menuName)).getAttribute("class").contains("active");
    }

    public boolean isButtonVisible(String buttonName) {
        By button = By.linkText(buttonName);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(button)).isDisplayed();
    }

    public void clickManageCategories() {
        driver.findElement(manageCategoriesButton).click();
    }

    public boolean isOnPage(String pageName) {
        String expectedUrl = DriverFactory.getBaseUrl() + "/ui/" + pageName.toLowerCase().replace(" ", "-");
        return wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    public boolean isSummaryCardVisible(String cardName) {
        switch (cardName) {
            case "Categories":
                return wait.until(ExpectedConditions.visibilityOfElementLocated(categoriesSummaryCard)).isDisplayed();
            case "Plants":
                return wait.until(ExpectedConditions.visibilityOfElementLocated(plantsSummaryCard)).isDisplayed();
            case "Sales":
                return wait.until(ExpectedConditions.visibilityOfElementLocated(salesSummaryCard)).isDisplayed();
            default:
                break;
        }
        throw new IllegalArgumentException("Invalid card name: " + cardName);
    }
}
