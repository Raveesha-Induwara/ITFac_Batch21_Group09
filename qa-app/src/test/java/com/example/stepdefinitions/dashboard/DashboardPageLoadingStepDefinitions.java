package com.example.stepdefinitions.dashboard;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.example.pages.Common;
import com.example.pages.login.LoginPage;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DashboardPageLoadingStepDefinitions {

    private WebDriver driver;
    private Common common;
    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        driver = Hooks.getDriver(); 
        common = new Common(driver);
        loginPage = new LoginPage(driver);
        
        common.launchApplication();
    }

    @When("the user enters valid credentials")
    public void theUserEntersValidCredentials() {
        loginPage.entersValidCredentials();
    }

    @Then("clicks the login button")
    public void clicksTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("the dashboard should load immediately")
    public void theUserShouldBeRedirectedToTheDashboard() {
        assertTrue(
                "Admin is not navigated to dashboard page",
                loginPage.isOnDashboardPage()
        );
    }
    
}
