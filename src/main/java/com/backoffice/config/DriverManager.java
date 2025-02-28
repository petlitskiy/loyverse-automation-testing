package com.backoffice.config;

import com.backoffice.managers.SharedFileManager;
import com.backoffice.utilities.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

/**
 * @author Neha Kharbanda
 */

/**
 * This class manages the creation and configuration of WebDriver instances based on the configuration properties.
 * It supports both local and remote (BrowserStack) execution.
 * The WebDriver instance is created and configured based on the test type and browser specified in the configuration properties.
 */

public class DriverManager {
    private static WebDriver driver;
    SharedFileManager sharedFileManager;
    private WebDriverWait wait;
    private Logger log = LogManager.getLogger(DriverManager.class);

    public DriverManager() throws MalformedURLException {

        sharedFileManager = new SharedFileManager();
        log.info("Test Type is set as {} ", sharedFileManager.getConfigReader().getTestType());
        switch (sharedFileManager.getConfigReader().getTestType()) {

            case Constants.LOCAL: {
                log.info("Test execution browser type is {}", sharedFileManager.getConfigReader().getBrowserType());
                if (sharedFileManager.getConfigReader().getBrowserType().equals(Constants.CHROME)) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--disable-extensions");
                    log.info("--disable extensions--");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    log.info("--disable pop-up blocking--");
                    // chromeOptions.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(chromeOptions);
                    log.info("{} browser is launched.", sharedFileManager.getConfigReader().getBrowserType());
                } else if (sharedFileManager.getConfigReader().getBrowserType().equals(Constants.SAFARI)) {
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    log.info("{} browser is launched.", sharedFileManager.getConfigReader().getBrowserType());
                } else if (sharedFileManager.getConfigReader().getBrowserType().equals(Constants.FIREFOX)) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    log.info("{} browser is launched.", sharedFileManager.getConfigReader().getBrowserType());
                } else {
                    log.info("Browser name is not mentioned in config properties. Please set the browser value.");
                }
                driver.manage().window().maximize();
                log.info("--maximizing window--");
                driver.manage().deleteAllCookies();
                log.info("--deleting all cookies--");
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
               break;
            }

            case Constants.REMOTE: {
                MutableCapabilities capabilities = new MutableCapabilities();
                HashMap<String, String> bStackOptions = new HashMap<>();
                bStackOptions.putIfAbsent("source", "cucumber-java:sample-sdk:v1.0");
                capabilities.setCapability("bstack:options", bStackOptions);
                driver = new RemoteWebDriver(
                        new URL(sharedFileManager.getConfigReader().getBrowserStackHubURL()), capabilities);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
                break;
            }
            default: {
                log.error("Please provide a valid test type in config properties.");
            }
        }
    }


    public WebDriver getDriver() {
        return driver;
    }


    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
        log.info("The {} browser is torn down", sharedFileManager.getConfigReader().getBrowserType());
    }

}

