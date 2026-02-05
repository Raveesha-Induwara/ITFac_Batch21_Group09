package com.example.stepdefinitions.category.ui;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.example.pages.category.ui.AddCategoryPage;
import com.example.stepdefinitions.Hooks;

import java.util.List;
import java.util.ArrayList;

public class CategorySortByIdStepDefinitions {

    private WebDriver driver = Hooks.getDriver();
    private AddCategoryPage categoryPage = new AddCategoryPage(driver);

    @When("dthe user clicks on the ID column header to sort")
    public void theUserClicksOnTheIdColumnHeaderToSort() {
        try {
            Thread.sleep(500); // Brief pause before sorting
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        categoryPage.clickIdColumnHeader();
    }

    @Then("dthe categories should be sorted by ID in ascending order")
    public void theCategoriesShouldBeSortedByIdInAscendingOrder() {
        try {
            Thread.sleep(1000); // Wait for sorting to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        List<String> actualIds = categoryPage.getCategoryIds();
        List<Integer> idNumbers = new ArrayList<>();
        
        // Convert string IDs to integers for proper numeric sorting
        for (String id : actualIds) {
            try {
                idNumbers.add(Integer.parseInt(id));
            } catch (NumberFormatException e) {
                // Skip non-numeric IDs
            }
        }
        
        // Check if sorted in ascending order
        for (int i = 0; i < idNumbers.size() - 1; i++) {
            assertTrue(
                "IDs are not sorted in ascending order. ID at position " + i + " (" + idNumbers.get(i) + 
                ") is greater than ID at position " + (i + 1) + " (" + idNumbers.get(i + 1) + ")",
                idNumbers.get(i) <= idNumbers.get(i + 1)
            );
        }
    }

    @Then("dthe categories should be sorted by ID in descending order")
    public void theCategoriesShouldBeSortedByIdInDescendingOrder() {
        try {
            Thread.sleep(1000); // Wait for sorting to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        List<String> actualIds = categoryPage.getCategoryIds();
        List<Integer> idNumbers = new ArrayList<>();
        
        // Convert string IDs to integers for proper numeric sorting
        for (String id : actualIds) {
            try {
                idNumbers.add(Integer.parseInt(id));
            } catch (NumberFormatException e) {
                // Skip non-numeric IDs
            }
        }
        
        // Check if sorted in descending order
        for (int i = 0; i < idNumbers.size() - 1; i++) {
            assertTrue(
                "IDs are not sorted in descending order. ID at position " + i + " (" + idNumbers.get(i) + 
                ") is less than ID at position " + (i + 1) + " (" + idNumbers.get(i + 1) + ")",
                idNumbers.get(i) >= idNumbers.get(i + 1)
            );
        }
    }
}
