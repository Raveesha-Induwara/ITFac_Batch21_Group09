package com.example.stepdefinitions.dashboard;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;

import com.example.pages.dashboard.DashboardPage;
import com.example.pages.login.ui.LoginPage;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NavigationMenuHighlightStepDefinition {

    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    public NavigationMenuHighlightStepDefinition() {
        driver = Hooks.getDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @When("the user is on the dashboard page")
    public void theUserIsOnTheDashboardPage() {
        assertTrue(
                "User is not on the dashboard page",
                loginPage.isOnDashboardPage()
        );
    }

    @When("the user clicks on {string} in the side navigation menu")
    public void theUserClicksOnInTheSideNavigation(String menuName) {
        dashboardPage.clickMenuInSideNavigation(menuName);
    }

    @Then("the {string} section should be highlighted in the side navigation menu")
    public void theSectionHighlightedInTheSideNavigationMenu(String menuName) {
        assertTrue("Failed to highlight the section: " + menuName, dashboardPage.isMenuHighlighted(menuName));
    }
    
}
