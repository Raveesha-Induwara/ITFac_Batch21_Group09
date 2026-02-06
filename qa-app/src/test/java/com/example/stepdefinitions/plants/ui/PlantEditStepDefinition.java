package com.example.stepdefinitions.plants.ui;

import org.junit.Assert;

import com.example.pages.plants.ui.EditPlantPage;
import com.example.pages.plants.ui.PlantListPage;
import com.example.stepdefinitions.Hooks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class PlantEditStepDefinition {

    private EditPlantPage editPlantPage;
    private PlantListPage plantListPage;

    public PlantEditStepDefinition() {
        editPlantPage = new EditPlantPage(Hooks.getDriver());
        plantListPage = new PlantListPage(Hooks.getDriver());
    }

    // ========== Precondition Steps ==========

    @Given("at least one plant exists in the system")
    public void atLeastOnePlantExists() {
        // This is a precondition check - we assume plants exist
        // In a real scenario, you might want to verify this or create a plant via API
        Assert.assertTrue("Plant list page should be accessible", editPlantPage.isOnPlantListPage());
    }

    // ========== Navigation Steps ==========

    @When("the admin clicks the edit button for the first plant")
    public void adminClicksEditButtonForFirstPlant() {
        editPlantPage.clickEditButtonForFirstPlant();
    }

    @When("the admin clicks the edit button for plant {string}")
    public void adminClicksEditButtonForPlant(String plantName) {
        editPlantPage.clickEditButtonForPlantByName(plantName);
    }

    @And("the admin is on the edit plant page")
    public void adminIsOnEditPlantPage() {
        Assert.assertTrue("User is not on the edit plant page", editPlantPage.isOnEditPlantPage());
    }

    // ========== Form Input Steps ==========

    @And("the admin modifies plant name to {string}")
    public void adminModifiesPlantNameTo(String newName) {
        editPlantPage.clearAndEnterPlantName(newName);
    }

    @And("the admin modifies price to {string}")
    public void adminModifiesPriceTo(String newPrice) {
        editPlantPage.clearAndEnterPrice(newPrice);
    }

    @And("the admin modifies quantity to {string}")
    public void adminModifiesQuantityTo(String newQuantity) {
        editPlantPage.clearAndEnterQuantity(newQuantity);
    }

    @And("the admin modifies category to {string}")
    public void adminModifiesCategoryTo(String newCategory) {
        editPlantPage.selectCategory(newCategory);
    }

    @And("the admin clears and enters plant name {string}")
    public void adminClearsAndEntersPlantName(String name) {
        // Simply enter the name - if it's "DUPLICATE_NAME", assume a plant with that name exists
        editPlantPage.clearAndEnterPlantName(name);
    }

    @And("the admin clears and enters price {string}")
    public void adminClearsAndEntersPrice(String price) {
        editPlantPage.clearAndEnterPrice(price);
    }

    @And("the admin clears and enters quantity {string}")
    public void adminClearsAndEntersQuantity(String quantity) {
        editPlantPage.clearAndEnterQuantity(quantity);
    }

    // ========== Form Action Steps ==========

    @And("the admin clicks the save button")
    public void adminClicksSaveButton() {
        editPlantPage.clickSave();
    }

    @And("the admin clicks the cancel button")
    public void adminClicksCancelButton() {
        editPlantPage.clickCancel();
    }

    // ========== Success Verification Steps ==========

    @Then("the plant should be updated successfully")
    public void plantShouldBeUpdatedSuccessfully() {
        String currentUrl = editPlantPage.getCurrentUrl();

        // Check that we're no longer on the edit page (we should be redirected to list page)
        Assert.assertTrue("Plant was not updated - still on edit page. Current URL: " + currentUrl,
            currentUrl.contains("/ui/plants") && !currentUrl.contains("/edit/"));
    }

    @Then("the user should be navigated back to the plant list page")
    public void userShouldBeNavigatedBackToPlantListPage() {
        String currentUrl = editPlantPage.getCurrentUrl();
        Assert.assertTrue("User was not redirected to plant list page. Current URL: " + currentUrl,
            currentUrl.contains("/ui/plants") && !currentUrl.contains("/edit/"));
    }

    @And("the updated plant {string} should appear in the plant list")
    public void updatedPlantShouldAppearInList(String plantName) {
        boolean isFound = editPlantPage.isPlantNameVisibleInTable(plantName);
        Assert.assertTrue("The updated plant '" + plantName + "' was not found in the plant list!", isFound);
    }

    // Note: "the error message should contain {string}" step is already defined in PlantAddPageFieldValidationStepDefinition
    // Reusing that step definition to avoid duplication

    // ========== Error Validation Steps ==========

    @Then("the error message should display {string}")
    public void errorMessageShouldDisplay(String expectedError) {
        String actualError = editPlantPage.getAllErrorMessages();
        Assert.assertTrue("Expected error message '" + expectedError + "' but got '" + actualError + "'",
            actualError.contains(expectedError));
    }

    @And("the plant should not be updated")
    public void plantShouldNotBeUpdated() {
        // Verify we're still on the edit page (form not submitted)
        Assert.assertTrue("Expected to remain on edit page but was redirected",
            editPlantPage.isFormStillDisplayed());
    }

    @And("the update should be blocked")
    public void updateShouldBeBlocked() {
        // Verify we're still on the edit page and form is still displayed
        Assert.assertTrue("Expected update to be blocked but form was submitted",
            editPlantPage.isFormStillDisplayed());
    }

    @And("the edit form should still be displayed")
    public void editFormShouldStillBeDisplayed() {
        Assert.assertTrue("Edit form is not displayed - it should still be visible",
            editPlantPage.isFormStillDisplayed());
    }

    @Then("the plant name error should display {string}")
    public void plantNameErrorShouldDisplay(String expectedError) {
        String actualError = editPlantPage.getPlantNameErrorMessage();
        Assert.assertEquals("Plant name error message mismatch", expectedError, actualError);
    }

    @Then("the price error should display {string}")
    public void priceErrorShouldDisplay(String expectedError) {
        String actualError = editPlantPage.getPriceErrorMessage();
        Assert.assertEquals("Price error message mismatch", expectedError, actualError);
    }

    @Then("the quantity error should display {string}")
    public void quantityErrorShouldDisplay(String expectedError) {
        String actualError = editPlantPage.getQuantityErrorMessage();
        Assert.assertEquals("Quantity error message mismatch", expectedError, actualError);
    }

    @Then("the category error should display {string}")
    public void categoryErrorShouldDisplay(String expectedError) {
        String actualError = editPlantPage.getCategoryErrorMessage();
        Assert.assertEquals("Category error message mismatch", expectedError, actualError);
    }

    @And("the success message should be {string}")
    public void theSuccessMessageShouldBe(String expectedMessage) {
        String actualMessage = editPlantPage.getSuccessMessage();
        Assert.assertEquals("Success message mismatch", expectedMessage, actualMessage);
    }

    // ========== Plant List Verification Steps ==========

    @And("the updated plant {string} should be visible in the list with price {string}, quantity {string}, and category {string}")
    public void theUpdatedPlantShouldBeVisibleInListWithPriceQuantityAndCategory(String plantName, String expectedPrice, String expectedQuantity, String expectedCategory) {

        // Verify plant exists in the list
        boolean isPlantVisible = editPlantPage.isPlantNameVisibleInTable(plantName);
        Assert.assertTrue("The updated plant '" + plantName + "' was not found in the plant list!", isPlantVisible);

        // Get and verify the price
        String actualPrice = editPlantPage.getPlantPriceFromList(plantName);
        Assert.assertNotNull("Could not retrieve price for plant: " + plantName, actualPrice);

        // Clean up price values for comparison (remove $ and whitespace)
        String cleanActualPrice = actualPrice.replace("$", "").trim();
        String cleanExpectedPrice = expectedPrice.replace("$", "").trim();

        // Convert to doubles for comparison
        double actualPriceValue = Double.parseDouble(cleanActualPrice);
        double expectedPriceValue = Double.parseDouble(cleanExpectedPrice);

        Assert.assertEquals("Price mismatch for plant '" + plantName + "'",
            expectedPriceValue, actualPriceValue, 0.01);

        // Get and verify the quantity
        String actualQuantity = editPlantPage.getPlantQuantityFromList(plantName);
        Assert.assertNotNull("Could not retrieve quantity for plant: " + plantName, actualQuantity);

        // Clean quantity - remove stock indicators like "Low", "High", etc.
        String cleanActualQuantity = actualQuantity.replaceAll("[^0-9]", "").trim();
        String cleanExpectedQuantity = expectedQuantity.trim();

        Assert.assertEquals("Quantity mismatch for plant '" + plantName + "'",
            cleanExpectedQuantity, cleanActualQuantity);

        // Get and verify the category
        String actualCategory = editPlantPage.getPlantCategoryFromList(plantName);
        Assert.assertNotNull("Could not retrieve category for plant: " + plantName, actualCategory);

        Assert.assertEquals("Category mismatch for plant '" + plantName + "'",
            expectedCategory.trim(), actualCategory.trim());
    }

    @And("the plant {string} in the list should have price {string}")
    public void thePlantInListShouldHavePrice(String plantName, String expectedPrice) {
        String actualPrice = editPlantPage.getPlantPriceFromList(plantName);
        Assert.assertNotNull("Could not retrieve price for plant: " + plantName, actualPrice);

        String cleanActualPrice = actualPrice.replace("$", "").trim();
        String cleanExpectedPrice = expectedPrice.replace("$", "").trim();

        double actualPriceValue = Double.parseDouble(cleanActualPrice);
        double expectedPriceValue = Double.parseDouble(cleanExpectedPrice);

        Assert.assertEquals("Price mismatch for plant '" + plantName + "'",
            expectedPriceValue, actualPriceValue, 0.01);
    }

    @And("the plant {string} in the list should have quantity {string}")
    public void thePlantInListShouldHaveQuantity(String plantName, String expectedQuantity) {
        String actualQuantity = editPlantPage.getPlantQuantityFromList(plantName);
        Assert.assertNotNull("Could not retrieve quantity for plant: " + plantName, actualQuantity);

        // Clean quantity - remove stock indicators like "Low", "High", etc.
        String cleanActualQuantity = actualQuantity.replaceAll("[^0-9]", "").trim();
        String cleanExpectedQuantity = expectedQuantity.trim();

        Assert.assertEquals("Quantity mismatch for plant '" + plantName + "'",
            cleanExpectedQuantity, cleanActualQuantity);
    }

    // Note: "the admin selects category {string}" step is already defined in PlantAddPageFieldValidationStepDefinition
    // Reusing that step definition to avoid duplication

    @And("the first plant in the list should have category {string}")
    public void theFirstPlantInListShouldHaveCategory(String expectedCategory) {

        // Get the first plant name
        List<String> plantNames = plantListPage.getPlantNames();
        Assert.assertFalse("No plants found in the list", plantNames.isEmpty());

        String firstPlantName = plantNames.get(0);

        // Get the category for the first plant
        String actualCategory = editPlantPage.getPlantCategoryFromList(firstPlantName);
        Assert.assertNotNull("Could not retrieve category for plant: " + firstPlantName, actualCategory);

        Assert.assertEquals("Category mismatch for plant '" + firstPlantName + "'",
            expectedCategory.trim(), actualCategory.trim());
    }
}
