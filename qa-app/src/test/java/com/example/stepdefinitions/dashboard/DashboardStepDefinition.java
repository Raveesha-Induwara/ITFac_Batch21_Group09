package com.example.stepdefinitions.dashboard;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.example.pages.dashboard.DashboardPage;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DashboardStepDefinition {

    private WebDriver driver;
    private DashboardPage dashboardPage;

    public DashboardStepDefinition() {
        driver = Hooks.getDriver();
        dashboardPage = new DashboardPage(driver);
    }
    
    @Then("the {string} button should be visible")
    public void the_button_should_be_visible(String buttonName) {
        assertTrue("Failed to find the button: " + buttonName, dashboardPage.isButtonVisible(buttonName));
    }
    
    @When("the user clicks on the {string} button")
    public void the_user_clicks_on_the_button(String buttonName) {
        dashboardPage.clickManageCategories();
    }

    @Then("the user should be navigated to the {string}")
    public void theUserShouldBeNavigatedToThePage(String pageName) {
        assertTrue(
                "User is not navigated to " + pageName + " page",
                dashboardPage.isOnPage(pageName)
        );
    }

    @Then("the {string} summary card should be visible")
    public void theSummaryCardShouldBeVisible(String cardName) {
        assertTrue("Failed to find the summary card: " + cardName, dashboardPage.isSummaryCardVisible(cardName));
    }
    
}
