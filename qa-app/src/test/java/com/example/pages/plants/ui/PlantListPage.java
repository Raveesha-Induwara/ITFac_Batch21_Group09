package com.example.pages.plants.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PlantListPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By plantTable = By.tagName("table");
    private final By plantRows = By.cssSelector("tbody tr");
    private final By noDataMessage = By.xpath("//td[contains(text(),'No plants found')]");
    private final By pagination = By.cssSelector("ul.pagination");
    private final By activePage = By.cssSelector("li.page-item.active");
    private final By nextButton = By.xpath("//a[normalize-space()='Next']");
    private final By previousButton = By.xpath("//a[normalize-space()='Previous']");

    private final By nameSortHeader = By.xpath("//th/a[contains(text(),'Name')]");
    private final By priceSortHeader = By.xpath("//th/a[contains(text(),'Price')]");
    private final By quantitySortHeader = By.xpath("//th/a[contains(text(),'Stock') or contains(text(),'Quantity')]");

    private final By nameArrowIcon = By.xpath("//th/a[contains(text(),'Name')]/span");
    private final By priceArrowIcon = By.xpath("//th/a[contains(text(),'Price')]/span");
    private final By stockArrowIcon = By.xpath("//th/a[contains(text(),'Stock') or contains(text(),'Quantity')]/span");

    private final By nameColumn = By.cssSelector("tbody tr td:nth-child(1)");
    private final By priceColumn = By.cssSelector("tbody tr td:nth-child(3)");
    private final By quantityColumn = By.cssSelector("tbody tr td:nth-child(4)");

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

    public boolean isNoDataMessageDisplayed() {
        try {
            List<WebElement> noDataElements = driver.findElements(noDataMessage);
            return !noDataElements.isEmpty() && noDataElements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNoDataMessageText() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(noDataMessage));
            return element.getText().trim();
        } catch (Exception e) {
            return "";
        }
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

    public void clickSortColumn(String columnName) {
        By columnLocator;
        switch (columnName.toLowerCase()) {
            case "name":
                columnLocator = nameSortHeader;
                break;
            case "price":
                columnLocator = priceSortHeader;
                break;
            case "quantity":
                columnLocator = quantitySortHeader;
                break;
            default:
                throw new IllegalArgumentException("Invalid column name: " + columnName);
        }
        click(columnLocator);
        waitForPageReload();
    }

    public List<String> getPlantNames() {
        List<WebElement> nameElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(nameColumn));
        List<String> names = new ArrayList<>();
        for (WebElement element : nameElements) {
            names.add(element.getText().trim());
        }
        System.out.println("Extracted Names: " + names);
        return names;
    }

    public List<Double> getPlantPrices() {
        List<WebElement> priceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(priceColumn));
        List<Double> prices = new ArrayList<>();
        for (WebElement element : priceElements) {
            String priceText = element.getText().trim().replace("$", "").replace(",", "");
            prices.add(Double.parseDouble(priceText));
        }
        System.out.println("Extracted Prices: " + prices);
        return prices;
    }

    public List<Integer> getPlantQuantities() {
        List<WebElement> quantityElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(quantityColumn));
        List<Integer> quantities = new ArrayList<>();
        for (WebElement element : quantityElements) {
            quantities.add(Integer.parseInt(element.getText().trim()));
        }
        System.out.println("Extracted Quantities: " + quantities);
        return quantities;
    }

    public boolean isListSortedByName() {
        List<String> names = getPlantNames();
        if (names.size() <= 1) return true;

        // Check if sorted in ascending or descending order
        boolean isAscending = true;
        boolean isDescending = true;

        for (int i = 0; i < names.size() - 1; i++) {
            int comparison = names.get(i).compareToIgnoreCase(names.get(i + 1));
            if (comparison > 0) isAscending = false;
            if (comparison < 0) isDescending = false;
        }

        System.out.println("Name sorting - Ascending: " + isAscending + ", Descending: " + isDescending);
        return isAscending || isDescending;
    }

    public boolean isListSortedByPrice() {
        List<Double> prices = getPlantPrices();
        if (prices.size() <= 1) return true;

        boolean isAscending = true;
        boolean isDescending = true;

        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) isAscending = false;
            if (prices.get(i) < prices.get(i + 1)) isDescending = false;
        }

        System.out.println("Price sorting - Ascending: " + isAscending + ", Descending: " + isDescending);
        return isAscending || isDescending;
    }

    public boolean isListSortedByQuantity() {
        List<Integer> quantities = getPlantQuantities();
        if (quantities.size() <= 1) return true;

        boolean isAscending = true;
        boolean isDescending = true;

        for (int i = 0; i < quantities.size() - 1; i++) {
            if (quantities.get(i) > quantities.get(i + 1)) isAscending = false;
            if (quantities.get(i) < quantities.get(i + 1)) isDescending = false;
        }

        System.out.println("Quantity sorting - Ascending: " + isAscending + ", Descending: " + isDescending);
        return isAscending || isDescending;
    }

    public boolean hasArrowIcon(String columnName) {
        By arrowLocator;
        switch (columnName.toLowerCase()) {
            case "name":
                arrowLocator = nameArrowIcon;
                break;
            case "price":
                arrowLocator = priceArrowIcon;
                break;
            case "quantity":
            case "stock":
                arrowLocator = stockArrowIcon;
                break;
            default:
                throw new IllegalArgumentException("Invalid sortable column name: " + columnName);
        }

        try {
            List<WebElement> arrowElements = driver.findElements(arrowLocator);
            if (!arrowElements.isEmpty()) {
                WebElement arrow = arrowElements.get(0);
                boolean isDisplayed = arrow.isDisplayed();
                String arrowText = arrow.getText().trim();
                System.out.println(columnName + " column arrow icon - Present: " + isDisplayed + ", Icon: " + arrowText);
                return isDisplayed;
            }
        } catch (Exception e) {
            System.out.println(columnName + " column arrow icon - Not found: " + e.getMessage());
        }
        return false;
    }
}
