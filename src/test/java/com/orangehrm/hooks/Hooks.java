package com.orangehrm.hooks;

import com.orangehrm.base.TestContext;
import com.orangehrm.drivers.driver.DriverFactory;
import com.orangehrm.pages.base.BasePage;
import com.orangehrm.utils.ErrorHandler;
import com.orangehrm.utils.LoggerUtils;
import io.cucumber.java.*;
import org.apache.logging.log4j.Logger;


public class Hooks {

    private static final Logger logger = LoggerUtils.getLogger(Hooks.class);
    private final TestContext testContext;
    private final BasePage basePage;

    public Hooks(TestContext testContext, BasePage basePage) {
        this.testContext = testContext;
        this.basePage = basePage;
    }

    @Before
    public void scenarioSetup() {
        testContext.initializeBrowserIfRequired();
    }

    //@Before("@login_required")
    public void loginBeforeScenario(Scenario scenario) {
        testContext.login(scenario);
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                logger.info("Capturing failure screenshot for scenario: {}", scenario.getName());
                byte[] screenshot = basePage.captureScreenshotAsBytes(scenario.getName());
                scenario.attach(screenshot, "image/png", "Failure in: " + scenario.getName());
            }

            logger.info("Completing scenario: {} - Status: {}",
                    scenario.getName(), scenario.getStatus());
        } catch (Exception error) {
            ErrorHandler.logError(error, "tearDown", "Error during driver tearDown");
            throw error;
        } finally {
            try {
                DriverFactory.getInstance().quitDriver();
            } catch (Exception error) {
                logger.error("Error during driver tearDown: {}", error.getMessage());
            }
        }
    }
}
