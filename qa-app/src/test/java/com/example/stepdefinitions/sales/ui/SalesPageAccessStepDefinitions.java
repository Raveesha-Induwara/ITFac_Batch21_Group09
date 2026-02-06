package com.example.stepdefinitions.sales.ui;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.example.pages.Common;
import com.example.pages.sales.ui.SalesPage;
import com.example.stepdefinitions.Hooks;

public class SalesPageAccessStepDefinitions {

    private WebDriver driver;
    private SalesPage salesPage;

    public SalesPageAccessStepDefinitions() {
        driver = Hooks.getDriver();
        salesPage = new SalesPage(driver);
    }

    @When("the user clicks on {string} in the side navigation")
    public void theUserClicksOnInTheSideNavigation(String menuName) {
        salesPage.clickMenuInSideNavigation(menuName);
    }

    @Then("the {string} section should be highlighted in the side navigation")
    public void theSectionShouldBeHighlightedInTheSideNavigation(String menuName) {
        assertTrue(
                menuName + " menu is not highlighted",
                salesPage.isMenuHighlighted(menuName)
        );
    }

    @Then("the user should be navigated to the {string} page")
    public void theUserShouldBeNavigatedToThePage(String pageName) {
        assertTrue(
                "User is not navigated to " + pageName + " page",
                salesPage.isOnPage(pageName)
        );
    }
}
