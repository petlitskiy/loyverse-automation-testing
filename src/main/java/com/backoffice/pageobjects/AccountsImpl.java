package com.backoffice.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Neha Kharbanda
 */

/**
 * Implementation of the IAccounts interface.
 * Represents the page object for Accounts page.
 */
public class AccountsImpl implements IAccounts {
    WebDriver localDriver;
    private Logger log = LogManager.getLogger(AccountsImpl.class);
    @FindBy(how = How.ID, using = "input_177")
    private WebElement businessNameInput;
    @FindBy(how = How.ID, using = "input_178")
    private WebElement emailInput;
    @FindBy(how = How.ID, using = "input_179")
    private WebElement passwordInput;
    @FindBy(how = How.XPATH, using = "//div[4]/div/select")
    private WebElement currencyDropdown;

    @FindBy(how = How.XPATH, using = "//div[5]/div[2]/md-switch")
    private WebElement centsIcon;
    @FindBy(how = How.XPATH, using = "//div[5]/div/select")
    private WebElement timezoneDropdown;
    @FindBy(how = How.XPATH, using = "//div[6]/div/select")
    private WebElement languageDropdown;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Save')]")
    private WebElement saveButton;

    public AccountsImpl(WebDriver driver) // constructor - remote webdriver
    {
        this.localDriver = driver;
        PageFactory.initElements(localDriver, this); // call method
    }

    //enter businessName
    @Override
    public void enterBusinessName(String name) {
        businessNameInput.sendKeys(name);
    }

    //enter email
    @Override
    public void enterEmail(String emailID) {
        emailInput.sendKeys(emailID);
    }

    //enter password
    @Override
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    @Override
    //select_currency and cents
    public void selectCurrency(String currency, String cents) {
        Select currDropdown = new Select(currencyDropdown);
        currDropdown.selectByVisibleText(currency);
        log.info("The currency is selected as {} ", currency);
        if (cents.equals("enabled")) {
            centsIcon.click();
            log.info("Cents is enabled");
        } else {
            log.info("Use cents is disabled by default");
        }
    }

    //select timezone
    @Override
    public void selectTimezone(String timezone) {
        Select timeZone = new Select(timezoneDropdown);
        timeZone.selectByVisibleText(timezone);
    }

    @Override
    public void selectLanguage(String language) {
        Select lang = new Select(languageDropdown);
        lang.selectByVisibleText(language);
    }

    @Override
    public void clickSaveButton() {
        try {
            JavascriptExecutor js2 = (JavascriptExecutor) localDriver;
            js2.executeScript("arguments[0].click();", saveButton);
            log.info("Save button is clicked on accounts page");
        } catch (Exception exception) {
            log.error("An exception occurred while clicking the Save button.", exception);
            exception.printStackTrace();
        }
    }

}
