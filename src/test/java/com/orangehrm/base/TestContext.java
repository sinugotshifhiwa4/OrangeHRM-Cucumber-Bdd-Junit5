package com.orangehrm.base;

import com.orangehrm.configuration.environments.EnvironmentConfigConstants;
import com.orangehrm.configuration.properties.PropertyConfigConstants;
import com.orangehrm.configuration.properties.PropertyFileConfigManager;
import com.orangehrm.crypto.services.EnvironmentCryptoManager;
import com.orangehrm.drivers.browser.BrowserFactory;
import com.orangehrm.drivers.driver.DriverFactory;
import com.orangehrm.pages.base.BasePage;
import com.orangehrm.pages.base.PageObjectManager;
import com.orangehrm.utils.ErrorHandler;
import com.orangehrm.utils.LoggerUtils;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class TestContext {

    private static final Logger logger = LoggerUtils.getLogger(TestContext.class);
    private static final DriverFactory driverFactory = DriverFactory.getInstance();
    private PageObjectManager pageObjectManager;
    private BrowserFactory browserFactory;


    public TestContext(BrowserFactory browserFactory) {
        this.browserFactory = browserFactory;
    }

    public void login(Scenario scenario) {

        // Only perform login if we're NOT in the login feature
        if (!isLoginFeature(scenario)) {
            // Perform login steps here
            System.out.println("Logging in for scenario with @login_required");
            // login logic - navigate to login page, enter credentials, click login
        } else {
            System.out.println("Skipping login for scenario inside login.feature");
        }
    }

    private boolean isLoginFeature(Scenario scenario) {
        String scenarioId = scenario.getId();
        return scenarioId != null && scenarioId.contains("login.feature");
    }

    public void initializeBrowserIfRequired() {
        if (!Boolean.getBoolean("skipBrowserInitialization")) {
            initializeBrowserComponents();
        } else {
            logger.info("Skipping browser initialization for encryption tests.");
        }
    }

    private void initializeBrowserComponents() {
        try {
            browserFactory = new BrowserFactory();

            String CHROME = "CHROME_BROWSER";
            String FIREFOX = "FIREFOX_BROWSER";
            String EDGE = "EDGE_BROWSER";
            String browser = PropertyFileConfigManager.getConfiguration(
                    PropertyConfigConstants.Environment.GLOBAL.getDisplayName(),
                    PropertyConfigConstants.PropertiesFilePath.GLOBAL.getFullPath()
            ).getProperty(CHROME);

            browserFactory.initializeBrowser(browser);

            if (!driverFactory.hasDriver()) {
                String errorMessage = "WebDriver initialization failed for thread: " + Thread.currentThread().threadId();
                logger.error(errorMessage);
                throw new IllegalStateException(errorMessage);
            }

            String PORTAL_BASE_URL = "PORTAL_BASE_URL";
            String url = PropertyFileConfigManager.getConfiguration(
                    PropertyConfigConstants.Environment.UAT.getDisplayName(),
                    PropertyConfigConstants.PropertiesFilePath.UAT.getFullPath()
            ).getProperty(PORTAL_BASE_URL);

            driverFactory.navigateToUrl(url);
        } catch (Exception error) {
            ErrorHandler.logError(error, "initializeBrowserComponents", "Failed to initialize browser components");
            throw error;
        }
    }

    public List<String> decryptCredentials() {
        try {
            // Run Decryption
            String PASSWORD = "PORTAL_PASSWORD";
            String USERNAME = "PORTAL_USERNAME";
            return EnvironmentCryptoManager.decryptEnvironmentVariables(
                    EnvironmentConfigConstants.Environment.UAT.getDisplayName(),
                    EnvironmentConfigConstants.EnvironmentFilePath.UAT.getFilename(),
                    EnvironmentConfigConstants.EnvironmentSecretKey.UAT.getKeyName(),
                    USERNAME, PASSWORD
            );
        } catch (Exception error) {
            ErrorHandler.logError(error, "decryptionCredentials", "Failed to decrypt credentials");
            throw error;
        }
    }

    public PageObjectManager getPageObjectManager() {
        if (pageObjectManager == null) {
            pageObjectManager = new PageObjectManager(driverFactory.getDriver());
        }
        return pageObjectManager;
    }
}
