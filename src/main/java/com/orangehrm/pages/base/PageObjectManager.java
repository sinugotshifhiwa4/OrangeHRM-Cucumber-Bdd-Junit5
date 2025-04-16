package com.orangehrm.pages.base;

import com.orangehrm.pages.orangeHrmPages.LoginPage;
import com.orangehrm.pages.orangeHrmPages.SideMenuPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private final WebDriver driver;
    private LoginPage loginPage;
    private SideMenuPage sideMenuPage;



    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public SideMenuPage getSideMenuPage() {
        return (sideMenuPage == null) ? sideMenuPage = new SideMenuPage(driver) : sideMenuPage;
    }
}
