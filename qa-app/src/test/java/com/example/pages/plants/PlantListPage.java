package com.example.pages.plants;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PlantListPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By plantTable = By.tagName("table");
    private final By plantRows = By.cssSelector("tbody tr");
    private final By pagination = By.cssSelector("ul.pagination");
    private final By activePage = By.cssSelector("li.page-item.active");
    private final By nextButton = By.xpath("//a[normalize-space()='Next']");
    private final By previousButton = By.xpath("//a[normalize-space()='Previous']");

    public PlantListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public boolean isPlantListVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(plantTable)).isDisplayed();
    }

    public int getPlantCount() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(plantRows)).size();
    }

    public boolean isPaginationVisible() {
        List<WebElement> pages = driver.findElements(pagination);
        return !pages.isEmpty() && pages.get(0).isDisplayed();
    }

    public Integer getActivePageNumberSafe() {
        List<WebElement> active = driver.findElements(activePage);

        if (active.isEmpty()) {
            return null;
        }

        return Integer.parseInt(active.get(0).getText().trim());
    }

    public boolean isPreviousButtonEnabled() {
        if (!isPaginationVisible()) return false;

        WebElement prevLi = driver.findElement(previousButton)
                .findElement(By.xpath("./parent::li"));

        return !prevLi.getAttribute("class").contains("disabled");
    }




    public void goToNextPage() {
        click(nextButton);
        waitForPageReload();
    }

    public void goToPreviousPage() {
        click(previousButton);
        waitForPageReload();
    }

    public void goToPage(int pageNumber) {
        By pageLink = By.xpath("//a[@class='page-link' and text()='" + pageNumber + "']");
        click(pageLink);
        waitForPageReload();
    }



    private void click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            // Scroll element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            Thread.sleep(300); // Small wait after scroll
            element.click();
        } catch (Exception e) {
            // Fallback to JavaScript click
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
    private void waitForPageReload() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(plantRows));
    }
}
