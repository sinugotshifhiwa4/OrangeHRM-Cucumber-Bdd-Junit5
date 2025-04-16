package com.orangehrm.stepDefinitions;

import com.orangehrm.base.TestContext;
import com.orangehrm.pages.base.PageObjectManager;
import com.orangehrm.pages.orangeHrmPages.LoginPage;
import com.orangehrm.pages.orangeHrmPages.SideMenuPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {


    private final TestContext testContext;
    private final LoginPage loginPage;
    private final SideMenuPage sideMenuPage;
    private static final String VALID_USER_TOKEN = "<valid_username>";
    private static final String VALID_PASS_TOKEN = "<valid_password>";
    private static final String INVALID_USER_TOKEN = "<invalid_username>";
    private static final String INVALID_PASS_TOKEN = "<invalid_password>";


    public LoginStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
        PageObjectManager pageObjectManager = testContext.getPageObjectManager();
        this.loginPage = pageObjectManager.getLoginPage();
        this.sideMenuPage = pageObjectManager.getSideMenuPage();
    }

    @Given("the user is on the Orange HRM login page")
    public void theUserIsOnTheOrangeHRMLoginPage() {
        this.loginPage.isCompanyLogoPresent();
    }

    @When("the user enters valid username {string} and password {string}")
    public void theUserEntersValidUsernameAndPassword(String username, String password) {
        if (username.equals(VALID_USER_TOKEN) && password.equals(VALID_PASS_TOKEN)) {
            this.loginPage.fillUsername(testContext.decryptCredentials().get(0));
            this.loginPage.fillPassword(testContext.decryptCredentials().get(1));
        } else {
            this.loginPage.fillUsername(username);
            this.loginPage.fillPassword(password);
        }
    }

    @And("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        this.loginPage.clickLoginButton();
    }

    @Then("the user should login successfully")
    public void theUserShouldLoginSuccessfully() {
        this.loginPage.verifyLoginErrorMessageNotVisible();
    }

    @And("the user should be navigated to the dashboard page")
    public void theUserShouldBeNavigatedToTheDashboardPage() {
        this.sideMenuPage.verifyDashboardMenuIsVisible();
    }

    @When("the user enters username {string} and password {string}")
    public void theUserEntersUsernameAndPassword(String username, String password) {
        if (username.equals(INVALID_USER_TOKEN) && password.equals(INVALID_PASS_TOKEN)) {
            this.loginPage.fillUsername("GeneralUser");
            this.loginPage.fillPassword("Password@123");
        } else {
            this.loginPage.fillUsername(username);
            this.loginPage.fillPassword(password);
        }
    }

    @Then("the login should fail and an appropriate error message should be displayed")
    public void theLoginShouldFailAndAnAppropriateErrorMessageShouldBeDisplayed() {
        this.loginPage.isLoginErrorMessageVisible();
    }

    @When("the user clicks on the forgot your password? link")
    public void theUserClicksOnTheForgotYourPasswordLink() {
        this.loginPage.clickForgotYourPasswordLink();
    }

    @Then("the user should be redirected to the password reset page")
    public void theUserShouldBeRedirectedToThePasswordResetPage() {
        this.loginPage.isResetPasswordHeaderVisible();
    }

    @When("the user enters a valid username")
    public void theUserEntersAValidUsername() {
        this.loginPage.fillUsername(testContext.decryptCredentials().getFirst());
    }

    @And("the user clicks the reset password button")
    public void theUserClicksTheResetPasswordButton() {
        this.loginPage.clickSubmitButton();
    }

    @Then("the user should receive an email with instructions to reset their password")
    public void theUserShouldReceiveAnEmailWithInstructionsToResetTheirPassword() {
        this.loginPage.isResetPasswordSuccessMessageVisible();
        this.loginPage.isEmailResetLinkConfirmationVisible();
    }
}
