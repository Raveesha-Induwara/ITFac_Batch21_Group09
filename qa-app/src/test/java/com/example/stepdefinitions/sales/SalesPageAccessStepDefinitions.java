package com.example.stepdefinitions.sales;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.example.pages.sales.SalesPage;
import com.example.stepdefinitions.Hooks;

public class SalesPageAccessStepDefinitions {

    private WebDriver driver;
    private SalesPage salesPage;

    @Given("the user is logged in as an admin")
    public void theUserIsLoggedInAsAnAdmin() {
        driver = Hooks.getDriver();          //get driver from Hooks
        salesPage = new SalesPage(driver);  

        salesPage.goToLoginPage();         
        salesPage.loginAsAdmin();
    }

    @When("the admin clicks on {string} in the side navigation")
    public void theAdminClicksOnInTheSideNavigation(String menuName) {
        salesPage.clickMenuInSideNavigation(menuName);
    }

    @Then("the {string} section should be highlighted in the side navigation")
    public void theSectionShouldBeHighlightedInTheSideNavigation(String menuName) {
        assertTrue(
                menuName + " menu is not highlighted",
                salesPage.isMenuHighlighted(menuName)
        );
    }

    @Then("the admin should be navigated to the {string} page")
    public void theAdminShouldBeNavigatedToThePage(String pageName) {
        assertTrue(
                "Admin is not navigated to " + pageName + " page",
                salesPage.isOnPage(pageName)
        );
    }
}
