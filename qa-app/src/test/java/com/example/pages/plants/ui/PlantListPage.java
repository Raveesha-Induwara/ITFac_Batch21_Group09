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
        captureCurrentTableState();
        click(nextButton);
        waitForTableContentChange();
    }

    public void goToPreviousPage() {
        captureCurrentTableState();
        click(previousButton);
        waitForTableContentChange();
    }

    /* -------------------------------
       HELPERS
       ------------------------------- */

    private String currentTableState = "";

    private void captureCurrentTableState() {
        try {
            List<WebElement> rows = driver.findElements(plantRows);
            if (!rows.isEmpty()) {
                currentTableState = rows.get(0).getText();
            }
        } catch (Exception e) {
            currentTableState = "";
        }
    }

    private void waitForTableContentChange() {
        final String initialState = currentTableState;
        wait.until(driver -> {
            try {
                List<WebElement> rows = driver.findElements(plantRows);
                if (rows.isEmpty()) return false;
                String newState = rows.get(0).getText();
                return !newState.equals(initialState);
            } catch (Exception e) {
                return false;
            }
        });
    }

    private void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollToElement(element);

        try {
            element.click();
        } catch (Exception e) {
            // Fallback to JavaScript click if regular click fails
            jsClick(element);
        }
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
            element
        );
        // Wait for scroll animation to complete
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickSortColumn(String columnName) {
        By columnLocator = getSortColumnLocator(columnName);
        captureCurrentTableState();
        click(columnLocator);
        waitForTableContentChange();
    }

    private By getSortColumnLocator(String columnName) {
        switch (columnName.toLowerCase()) {
            case "name":
                return nameSortHeader;
            case "price":
                return priceSortHeader;
            case "quantity":
                return quantitySortHeader;
            default:
                throw new IllegalArgumentException("Invalid column name: " + columnName);
        }
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
        return isSorted(names, "Name");
    }

    public boolean isListSortedByPrice() {
        List<Double> prices = getPlantPrices();
        return isSorted(prices, "Price");
    }

    public boolean isListSortedByQuantity() {
        List<Integer> quantities = getPlantQuantities();
        return isSorted(quantities, "Quantity");
    }

    private <T extends Comparable<T>> boolean isSorted(List<T> items, String sortType) {
        if (items.size() <= 1) return true;

        boolean isAscending = true;
        boolean isDescending = true;

        for (int i = 0; i < items.size() - 1; i++) {
            int comparison = items.get(i).compareTo(items.get(i + 1));
            if (comparison > 0) isAscending = false;
            if (comparison < 0) isDescending = false;
        }

        System.out.println(sortType + " sorting - Ascending: " + isAscending + ", Descending: " + isDescending);
        return isAscending || isDescending;
    }

    public boolean hasArrowIcon(String columnName) {
        By arrowLocator = getArrowIconLocator(columnName);

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

    private By getArrowIconLocator(String columnName) {
        switch (columnName.toLowerCase()) {
            case "name":
                return nameArrowIcon;
            case "price":
                return priceArrowIcon;
            case "quantity":
            case "stock":
                return stockArrowIcon;
            default:
                throw new IllegalArgumentException("Invalid sortable column name: " + columnName);
        }
    }
}
