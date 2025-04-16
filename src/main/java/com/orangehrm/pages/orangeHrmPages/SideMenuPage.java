package com.orangehrm.pages.orangeHrmPages;

import com.orangehrm.pages.base.BasePage;
import com.orangehrm.utils.ErrorHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideMenuPage extends BasePage {

    private final WebDriver driver;

    By dashboardMenu = By.xpath("//a[contains(@href, 'dashboard')]");

    public SideMenuPage(WebDriver driver) {
        this.driver = driver;
    }


    public void verifyDashboardMenuIsVisible() {
        try{
            waitForElementToBeVisible(driver.findElement(dashboardMenu), "Dashboard Menu");
        } catch (Exception error){
            ErrorHandler.logError(error, "verifyDashboardMenuIsVisible", "Failed to validate presence of dashboard menu");
            throw error;
        }
    }

}
