package com.backoffice.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.function.Function;

import java.time.Duration;
import java.util.ArrayList;

/**
 * @author Neha Kharbanda
 */
public class WebDriverHelper {

    private final Logger log = LogManager.getLogger(WebDriverHelper.class);
    private final WebDriver driver;

    public WebDriverHelper(WebDriver driver) // constructor - remote webdriver
    {
        this.driver = driver;
        PageFactory.initElements(driver, this); // call method
    }

    /**
     * Highlights the given WebElement by applying a border around it using JavaScript executor
     *
     * @param element The WebElement to highlight
     */
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='2px solid red'", element);
    }

    /**
     * Clicks the given WebElement using javascript executor
     *
     * @param element The WebElement to click.
     */
    public void clickElementWithJavaScript(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (NullPointerException e) {
            log.error("Failed to invoke clickElementWithJavaScript: " + e.getMessage());
            log.error("Stack trace: ", e);
        }
    }

    /**
     * Clears the given WebElement using javascript executor
     *
     * @param element The WebElement to clear.
     */
    public void clearElementWithJavaScript(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='';", element);
        } catch (Exception e) {
            log.error("{} Element not clicked:", element, e.getMessage());
        }
    }


    /**
     * Gets the text of the WebElement using javascript executor
     *
     * @param element The WebElement of which you want to  get text.
     */
    public String getTextWithJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].textContent", element);
    }

    /**
     * Gets the value from the input and clear the input by using keyboard action
     *
     * @param element The WebElement to clear.
     */
    public void clearInputByKeyboardAction(WebElement element) {
        {
            element.sendKeys(Keys.END); // Move cursor to the end of the text
            int textLength = element.getAttribute("value").length();
            for (int i = 0; i < textLength; i++) {
                element.sendKeys(Keys.BACK_SPACE);
            }
        }
    }

    /**
     * Scrolls to the element on webpage using javascript executor.
     *
     * @param element The WebElement to scroll to.
     */
    public void scrollToElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickWithActionsClass(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void waitUntilElementIsClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(160));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            log.error("TimeoutException");
        }
    }



    /*    To verify if element is not present on the page */

    public void verifyElementNotPresent(By element) {
        int width = driver.findElement(element).getSize().getWidth();
        if (width != 0) {
            log.info("Element is present");
        } else {
            log.info("Element is not found");
        }
    }


    /**
     * To verify if element is present on the page
     */

    public void verifyElementPresent(By element) {
        int width = 0;
        try {
            width = driver.findElement(element).getSize().getWidth();
        } catch (NoSuchElementException e) {
            log.info("Element not found!!");
        }
        if (width != 0) {
            log.info(element.toString() + " is present");
        } else {
            log.info("Element is not found");
        }

    }


    /**
     * To enter the text in the text-box
     */
    public void sendKeys(WebElement element, String text) {
        try {
            element.sendKeys(text);
            log.info("Entered the text: " + text);
        } catch (NoSuchElementException e) {
            log.error("NoSuchElementException");
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException");
        }
    }


    /**
     * To update the text in the text-box
     */
    public void updateText(WebElement element, String text) {
        try {
            clearElementWithJavaScript(element);
            element.sendKeys(text);
            log.info("Text updates as:" + text);
        } catch (NoSuchElementException e) {
            log.error("NoSuchElementException");
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException");
        }
    }

    public void isMessageDisplayed(WebDriver driver, String expectedMessage) throws Exception {
        try {
            By xpathExpression = By.xpath("//*[text()='" + expectedMessage + "']");
            WebElement messageElement = driver.findElement(xpathExpression);
            boolean isDisplayed = messageElement.isDisplayed();
            if (isDisplayed) {
                log.info("{} message is displayed", expectedMessage);
            } else {
                log.info("{} Message is not displayed", expectedMessage);
            }
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException(" Element not found: " + ex);
        } catch (Exception ex) {
            throw new Exception("An unexpected error occurred: " + expectedMessage, ex);
        }
    }

    /**
     * waits until the element becomes click-able
     */

    public void clickElement(WebElement clickElement) {
        try {
            clickElement.click();
            log.info("Element is clicked: {} ", clickElement);
        } catch (NoSuchElementException e) {
            log.error("Element not found: " + clickElement);
        } catch (ElementNotInteractableException e) {
            log.error("Element not interactable: " + clickElement);
        } catch (StaleElementReferenceException e) {
            log.error("Stale element reference: " + clickElement);
        } catch (TimeoutException e) {
            log.error("Timeout waiting for element to be clickable: {}", clickElement);
        }
    }

    // returns true if element is enabled - works mainly for buttons
    public String checkElementIsEnabled(WebElement element) {

        try {
            if (element.isEnabled()) {
                log.info("Element - {} is enabled ", element);
                return "enabled";
            }
        } catch (NoSuchElementException e) {
            log.error("Element not found: " + element);
        } catch (ElementNotInteractableException e) {
            log.error("Element not interactable: " + element);
        } catch (StaleElementReferenceException e) {
            log.error("Stale element reference: " + element);
        }
        return "disabled";
    }

    // returns true if element is enabled - works mainly for toggle/switches/checkboxes
    public String checkToggleIsEnabled(WebElement element) {
        try {
            String getElementStatus = element.getAttribute("aria-checked");
            if (getElementStatus.equals("true")) {
                log.info("Element - {} is enabled ", element);
                return "enabled";
            }
        } catch (NoSuchElementException e) {
            log.error("Element not found: " + element);
        } catch (ElementNotInteractableException e) {
            log.error("Element not interactable: " + element);
        } catch (StaleElementReferenceException e) {
            log.error("Stale element reference: " + element);
        }
        return "disabled";

    }


    //  returns true if element is disabled - works mainly for toggle/switches/checkboxes
    public String verifyElementDisabled(WebElement element) {

        try {
            String getElementStatus = element.getAttribute("aria-checked");
            if (getElementStatus.equals("false")) {
                log.info("Element - {} is disabled by default ", element);
                return "disabled";
            }
        } catch (NoSuchElementException e) {
            log.error("Element not found: " + element);
        } catch (ElementNotInteractableException e) {
            log.error("Element not intractable: " + element);
        } catch (StaleElementReferenceException e) {
            log.error("Stale element reference: " + element);
        }
        return "enabled";
    }

    public void verifyElementFocused(WebElement element) {
        try {
            boolean isFocused = element.equals(driver.switchTo().activeElement());
            if (isFocused) {
                log.info("Element - {} is focused ", element);
            }
        } catch (NoSuchElementException e) {
            log.error("Element not found: " + element);
        } catch (ElementNotInteractableException e) {
            log.error("Element not interactable: " + element);
        } catch (StaleElementReferenceException e) {
            log.error("Stale element reference: " + element);
        }
    }


    /**
     * To refresh the web page
     */
    public void refreshApplication() {
        try {
            driver.navigate().refresh();
            log.info("Application is refreshed successfully");
        } catch (StaleElementReferenceException e) {
            log.error("StaleElementReferenceException: " + e.getMessage());
        } catch (TimeoutException e) {
            log.error("TimeoutException: " + e.getMessage());
        } catch (Exception e) {
            log.error("An unexpected exception occurred: {} " + e);
        }
    }


    /**
     * To switch To New Tab
     */
    public void switchToNewTab() {
        try {
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
        } catch (Exception e) {
            log.error("An unexpected exception occurred while switching to the new tab: {} " + e);
        }
    }

    /**
     * To switch To Original Tab
     */
    public void switchToOriginalTab() {
        try {
            driver.switchTo().window(driver.getWindowHandles().iterator().next());
        } catch (Exception e) {
            log.error("An unexpected exception occurred while switching back to the original tab:{} " + e);
        }
    }

    /**
     * To verify the webPage Title
     */
    public void verifyPageTitle(String expectedPageTitle) {
        try {
            // Get the actual page title
            String actualPageTitle = driver.getTitle();
            Assert.assertEquals(expectedPageTitle, actualPageTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To get the current URL
     */
    public String getTheCurrentURL() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public void openCurrentUrlInNewTab() {

        String currentUrl = getTheCurrentURL();
        if (currentUrl != null) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("window.open(arguments[0], '_blank');", currentUrl);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabs.size() - 1));
        } else {
            log.error("Failed to retrieve the current URL.");
        }
    }

    public void enterOrUpdateFieldValue(String expectedValue, WebElement element) {
        try {
            String fetchValue = element.getAttribute("value");
            if (fetchValue == null || fetchValue.isEmpty()) {
                element.sendKeys(expectedValue);
                log.info("Value is entered as {}", expectedValue);
            } else {
                element.clear();
                element.sendKeys(expectedValue);
                log.info("Value is updated as: {}", expectedValue);
            }
        } catch (NoSuchElementException e) {
            log.error("The WebElement was not found: ", e);
        } catch (StaleElementReferenceException e) {
            log.error("The WebElement is no longer valid: ", e);
        } catch (ElementNotInteractableException e) {
            log.error("The WebElement is not interactable: ", e);
        } catch (InvalidElementStateException e) {
            log.error("Invalid state of the WebElement: ", e);
        } catch (TimeoutException e) {
            log.error("The operation timed out: ", e);
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
        }
    }


    public WebElement fluentWait(By locator) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(120))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    return driver.findElement(locator);
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }
        });
    }

}



