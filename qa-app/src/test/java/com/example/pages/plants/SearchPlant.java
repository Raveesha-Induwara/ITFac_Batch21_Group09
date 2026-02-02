package com.example.pages.plants;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPlant {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By searchBar = By.xpath("/html/body/div[1]/div/div[2]/div[2]/form/div[1]/input");
    private By searchBtn = By.xpath("//button[text()='Search']");
    // Using the table body path you provided
    private By tableBody = By.xpath("/html/body/div[1]/div/div[2]/div[2]/table/tbody");

    public SearchPlant(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterSearchTerm(String term) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        input.clear();
        input.sendKeys(term);
    }

    public void clickSearch() {
        driver.findElement(searchBtn).click();
    }


    public int getResultCount() {
        try {
            WebElement body = driver.findElement(tableBody);
            return body.findElements(By.tagName("tr")).size();
        } catch (Exception e) {
            return 0;
        }
    }
}
