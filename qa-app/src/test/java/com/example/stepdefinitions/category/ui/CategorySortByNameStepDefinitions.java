package com.example.stepdefinitions.category.ui;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.example.pages.category.ui.AddCategoryPage;
import com.example.stepdefinitions.Hooks;

import java.util.List;
import java.util.ArrayList;

public class CategorySortByNameStepDefinitions {

    private WebDriver driver = Hooks.getDriver();
    private AddCategoryPage categoryPage = new AddCategoryPage(driver);

    @When("dthe user clicks on the Name column header to sort")
    public void theUserClicksOnTheNameColumnHeaderToSort() {
        try {
            Thread.sleep(500); // Brief pause before sorting
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        categoryPage.clickNameColumnHeader();
    }

    @Then("dthe categories should be sorted by Name in ascending order")
    public void theCategoriesShouldBeSortedByNameInAscendingOrder() {
        try {
            Thread.sleep(1000); // Wait for sorting to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        List<String> actualNames = categoryPage.getCategoryNames();
        
        // Check if sorted in ascending order (alphabetically)
        for (int i = 0; i < actualNames.size() - 1; i++) {
            String current = actualNames.get(i).toLowerCase();
            String next = actualNames.get(i + 1).toLowerCase();
            
            assertTrue(
                "Names are not sorted in ascending order. Name at position " + i + " ('" + actualNames.get(i) + 
                "') comes after name at position " + (i + 1) + " ('" + actualNames.get(i + 1) + "') alphabetically",
                current.compareTo(next) <= 0
            );
        }
    }

    @Then("dthe categories should be sorted by Name in descending order")
    public void theCategoriesShouldBeSortedByNameInDescendingOrder() {
        try {
            Thread.sleep(1000); // Wait for sorting to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        List<String> actualNames = categoryPage.getCategoryNames();
        
        // Check if sorted in descending order (reverse alphabetically)
        for (int i = 0; i < actualNames.size() - 1; i++) {
            String current = actualNames.get(i).toLowerCase();
            String next = actualNames.get(i + 1).toLowerCase();
            
            assertTrue(
                "Names are not sorted in descending order. Name at position " + i + " ('" + actualNames.get(i) + 
                "') comes before name at position " + (i + 1) + " ('" + actualNames.get(i + 1) + "') alphabetically",
                current.compareTo(next) >= 0
            );
        }
    }
}
