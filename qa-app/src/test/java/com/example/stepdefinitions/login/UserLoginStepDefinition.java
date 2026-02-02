package com.example.stepdefinitions.login;

import com.example.pages.Common;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.Given;

public class UserLoginStepDefinition {

    private Common common;

    public UserLoginStepDefinition() {
        common = new Common(Hooks.getDriver());
    }

    @Given("the user is logged in as an admin")
    public void theUserIsLoggedInAndOnTheDashboardPage() {        
        common.launchApplication();
        common.loginAsAdmin();
    }

    @Given("the user is logged in as a non-admin")
    public void theUserIsLoggedInAsNonAdmin() {
        common.logoutIfLoggedIn();
        common.launchApplication();
        common.loginAsNonAdmin();
    }
}
