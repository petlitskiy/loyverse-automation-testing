package com.backoffice.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Neha Kharbanda
 */

/**
 * Implementation of the IAccessRights interface.
 * Represents the page object for Access Rights.
 */
public class AccessRightsImpl implements IAccessRights {
    //  WebDriver object
    private final WebDriver localDriver;
    private Logger log = LogManager.getLogger(AccessRightsImpl.class);
    @FindBy(xpath = "//*[contains(text(),'Cashier')]")
    private WebElement verifyAccessScren;
    @FindBy(xpath = "//*[@id=\"43770\"]/td[3]/div/span")
    private WebElement accessSection;
    @FindBy(xpath = "//*[contains(text(),'View cost of items')]")
    private WebElement costOfItemBtn;
    @FindBy(xpath = "//*[contains(text(),'Save')]")
    private WebElement saveBtn;

    /**
     * Constructor to initialize the AccessRightsImpl.
     * Initializes the WebDriver object and sets up the page factory.
     *
     * @param driver The WebDriver instance.
     */
    public AccessRightsImpl(WebDriver driver) {
        this.localDriver = driver;
        PageFactory.initElements(localDriver, this); // Initializing page elements
    }

    @Override
    public void verifyAccessRightScreen() {
        if (verifyAccessScren.isDisplayed()) {
            log.info("Access Right screen is displayed");
        } else {
            log.info("Access Right screen is not displayed");
        }
    }

    @Override
    public void clickAccessSection() {
        accessSection.click();
    }

    @Override
    public void clickCostOfItemBtn() {
        costOfItemBtn.click();
        log.info("Cost of item button selected");
    }

    @Override
    public void saveItemButton() {
        saveBtn.click();
        log.info("Save button is clicked");
    }


}
