package com.backoffice.config;

import org.openqa.selenium.WebDriver;

/**
 * @author Neha Kharbanda
 */

/**
 * This class manages the WebDriver instances for each thread.
 * It provides methods to get, set, and remove the WebDriver instance associated with the current thread.
 * ThreadLocal is used to ensure thread safety.
 */
public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    /**
     * Returns the WebDriver instance associated with the current thread.
     *
     * @return The WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Sets the WebDriver instance for the current thread.
     *
     * @param driverInstance The WebDriver instance to set
     */
    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    /**
     * Removes the WebDriver instance associated with the current thread.
     * This should be called after the WebDriver usage is completed to clean up resources.
     */
    public static void removeDriver() {
        driver.remove();
    }
}
