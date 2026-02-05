package com.example.stepdefinitions.category.ui;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.example.pages.category.ui.AddCategoryPage;
import com.example.stepdefinitions.Hooks;

import java.util.List;
import java.util.ArrayList;

public class CategorySortByParentStepDefinitions {

    private WebDriver driver = Hooks.getDriver();
    private AddCategoryPage categoryPage = new AddCategoryPage(driver);

    @When("dthe user clicks on the Parent column header to sort")
    public void theUserClicksOnTheParentColumnHeaderToSort() {
        try {
            Thread.sleep(500); // Brief pause before sorting
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        categoryPage.clickParentColumnHeader();
    }

    @Then("dthe categories should be sorted by Parent in ascending order")
    public void theCategoriesShouldBeSortedByParentInAscendingOrder() {
        try {
            Thread.sleep(1000); // Wait for sorting to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        List<String> actualParents = categoryPage.getCategoryParents();
        
        // Check if sorted in ascending order (alphabetically, with "-" treated specially)
        for (int i = 0; i < actualParents.size() - 1; i++) {
            String current = actualParents.get(i).toLowerCase().trim();
            String next = actualParents.get(i + 1).toLowerCase().trim();
            
            // Handle "-" which typically represents null/no parent
            if (current.equals("-")) {
                current = ""; // Treat "-" as empty for sorting comparison
            }
            if (next.equals("-")) {
                next = ""; // Treat "-" as empty for sorting comparison
            }
            
            assertTrue(
                "Parents are not sorted in ascending order. Parent at position " + i + " ('" + actualParents.get(i) + 
                "') comes after parent at position " + (i + 1) + " ('" + actualParents.get(i + 1) + "') alphabetically",
                current.compareTo(next) <= 0
            );
        }
    }

    @Then("dthe categories should be sorted by Parent in descending order")
    public void theCategoriesShouldBeSortedByParentInDescendingOrder() {
        try {
            Thread.sleep(1000); // Wait for sorting to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        List<String> actualParents = categoryPage.getCategoryParents();
        
        // Check if sorted in descending order (reverse alphabetically)
        for (int i = 0; i < actualParents.size() - 1; i++) {
            String current = actualParents.get(i).toLowerCase().trim();
            String next = actualParents.get(i + 1).toLowerCase().trim();
            
            // Handle "-" which typically represents null/no parent
            if (current.equals("-")) {
                current = ""; // Treat "-" as empty for sorting comparison
            }
            if (next.equals("-")) {
                next = ""; // Treat "-" as empty for sorting comparison
            }
            
            assertTrue(
                "Parents are not sorted in descending order. Parent at position " + i + " ('" + actualParents.get(i) + 
                "') comes before parent at position " + (i + 1) + " ('" + actualParents.get(i + 1) + "') alphabetically",
                current.compareTo(next) >= 0
            );
        }
    }
}
