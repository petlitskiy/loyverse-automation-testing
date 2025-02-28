package com.backoffice.stepdefinitions;

import com.backoffice.config.DriverFactory;
import com.backoffice.config.DriverManager;
import com.backoffice.utilities.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.Objects;

/**
 * @author Neha Kharbanda
 */
public class Hooks {
    WebDriver driver;
    private Logger log = LogManager.getLogger(Hooks.class);

    // This method is executed before each test scenario
    @Before
    public void setup(Scenario scenario) throws MalformedURLException {
        log.info("Before hook method called and started the test execution");
        log.info("Test Environment received from environment variable is {}", (Objects.isNull(System.getenv("TEST_ENV")) ? Constants.DEFAULT_TEST_ENV : System.getenv("TEST_ENV")));
        driver = new DriverManager().getDriver();
        DriverFactory.setDriver(driver);
        log.info("Scenario to be executed is {}", scenario.getName());

        // Check if the scenario has a specific tag to skip the Background
        if (scenario.getSourceTagNames().contains("@skipBackground")) {
            // You can add code here to log or perform any other action if needed
            log.info("Skipping Background for scenario: " + scenario.getName());
            // You may also choose to skip the remaining steps by throwing an exception.
            // throw new SkipException("Skipping Background for this scenario");
        }
    }


    // This method is executed after each test scenario
    @After
    public void teardown() {
        driver.quit();
        DriverFactory.removeDriver();
        log.info("Tear down method called and test execution is finished");
    }

}
