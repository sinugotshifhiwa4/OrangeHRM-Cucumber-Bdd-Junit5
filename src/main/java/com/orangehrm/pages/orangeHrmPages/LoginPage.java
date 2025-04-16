package com.orangehrm.pages.orangeHrmPages;

import com.orangehrm.pages.base.BasePage;
import com.orangehrm.utils.ErrorHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {


    private final WebDriver driver;

    By usernameInput = By.cssSelector("input[name='username']");
    By passwordInput = By.cssSelector("input[name='password']");
    By loginButton = By.xpath("//button[@type='submit' and contains(normalize-space(), 'Login')]");
    By errorMessage = By.xpath("//div[contains(@role, 'alert')]//p[contains(normalize-space(), 'Invalid credentials')]");
    By companyLogo = By.cssSelector("img[alt='company-branding']");
    By ForgotYourPasswordLink = By.xpath("//p[contains(., 'Forgot your password?')]");
    By ResetPasswordHeader= By.xpath("//h6[contains(., 'Reset Password')]");
    By cancelButton = By.cssSelector("button[type='button']");
    By submitButton = By.cssSelector("button[type='submit']");
    By resetPasswordSuccessMessage = By.xpath("//h6[contains(., 'Reset Password link sent successfully')]");
    By emailResetLinkConfirmation = By.xpath("(//p[contains(., 'A reset password link has been sent to you via email.')])[2]");



    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillUsername(String username) {
        try {
            sendKeys(driver.findElement(usernameInput), username, "Username");
        } catch (Exception error) {
            ErrorHandler.logError(error, "fillUsername", "Failed to fill username input");
            throw error;
        }
    }

    public void fillPassword(String password) {
        try {
            sendKeys(driver.findElement(passwordInput), password, "Password");
        } catch (Exception error) {
            ErrorHandler.logError(error, "fillPassword", "Failed to fill password input");
            throw error;
        }
    }

    public void clickLoginButton() {
        try {
            clickElement(driver.findElement(loginButton), "Login Button");
        } catch (Exception error) {
            ErrorHandler.logError(error, "clickLoginButton", "Failed to click login button");
            throw error;
        }
    }

    public void isCompanyLogoPresent() {
        try {
            isElementVisible(driver.findElement(companyLogo), "Company Logo");
        } catch (Exception error) {
            ErrorHandler.logError(error, "isCompanyLogoPresent", "Failed to validate presence of company logo");
            throw error;
        }
    }

    public void clickCompanyLogo() {
        try {
            clickElement(driver.findElement(companyLogo), "Company Logo");
        } catch (Exception error) {
            ErrorHandler.logError(error, "clickCompanyLogo", "Failed to click company logo");
            throw error;
        }
    }

    public void isLoginErrorMessageVisible() {
        try {
            isElementVisible(driver.findElement(errorMessage), "Error Message");
        } catch (Exception error) {
            ErrorHandler.logError(error, "isLoginErrorMessageVisible", "Failed to validate presence of error message");
            throw error;
        }
    }

    public void verifyLoginErrorMessageNotVisible() {
        try {
            waitForElementNotVisible(errorMessage, "Error Message");
        } catch (Exception error) {
            ErrorHandler.logError(error, "verifyLoginErrorMessageNotVisible", "Failed to validate absence of error message");
            throw error;
        }
    }

    public void loginToPortal(String username, String password) {
        try {
            fillUsername(username);
            fillPassword(password);
            clickLoginButton();
        } catch (Exception error) {
            ErrorHandler.logError(error, "loginToPortal", "Failed to login to portal");
            throw error;
        }
    }

    public void clickForgotYourPasswordLink() {
        try {
            clickElement(driver.findElement(ForgotYourPasswordLink), "Forgot Your Password Link");
        } catch (Exception error) {
            ErrorHandler.logError(error, "clickForgotYourPasswordLink", "Failed to click forgot your password link");
            throw error;
        }
    }

    public void isResetPasswordHeaderVisible() {
        try {
            isElementVisible(driver.findElement(ResetPasswordHeader), "Reset Password Header");
        } catch (Exception error) {
            ErrorHandler.logError(error, "isResetPasswordHeaderVisible", "Failed to validate presence of reset password header");
            throw error;
        }
    }

    public void isResetPasswordSuccessMessageVisible() {
        try {
            isElementVisible(driver.findElement(resetPasswordSuccessMessage), "Reset Password Success Message");
        } catch (Exception error) {
            ErrorHandler.logError(error, "isResetPasswordSuccessMessageVisible", "Failed to validate presence of reset password success message");
            throw error;
        }
    }

    public void isEmailResetLinkConfirmationVisible() {
        try {
            isElementVisible(driver.findElement(emailResetLinkConfirmation), "Email Reset Link Confirmation");
        } catch (Exception error) {
            ErrorHandler.logError(error, "isEmailResetLinkConfirmationVisible", "Failed to validate presence of email reset link confirmation");
            throw error;
        }
    }

    public void clickCancelButton() {
        try {
            clickElement(driver.findElement(cancelButton), "Cancel Button");
        } catch (Exception error) {
            ErrorHandler.logError(error, "clickCancelButton", "Failed to click cancel button");
            throw error;
        }
    }

    public void clickSubmitButton() {
        try {
            clickElement(driver.findElement(submitButton), "Submit Button");
        } catch (Exception error) {
            ErrorHandler.logError(error, "clickSubmitButton", "Failed to click submit button");
            throw error;
        }
    }

}
