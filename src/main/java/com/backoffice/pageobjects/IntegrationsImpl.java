package com.backoffice.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Neha Kharbanda
 */
public class IntegrationsImpl implements IIntegrations {

    WebDriver localDriver;
    private Logger log = LogManager.getLogger(IntegrationsImpl.class);

    @FindBy(how = How.ID, using = "lv_menu_item_integrations_expanded")
    private WebElement integrationCategory;
    @FindBy(how = How.ID, using = "lv_menu_item_integrations__integrations.tokens")
    private WebElement accessTokenOption;
    @FindBy(how = How.ID, using = "tokens__add_token_btn")
    private WebElement addAccessTokenButton;
    @FindBy(how = How.NAME, using = "name")
    private WebElement accessTokenNameInput;
    @FindBy(how = How.XPATH, using = "//*[@aria-label='Token has expiration date']")
    private WebElement disabledExpiryDate;
    @FindBy(how = How.XPATH, using = "//*[@aria-label='Save']")
    private WebElement saveAccessTokenButton;
    @FindBy(how = How.XPATH, using = "//*[@class='flex-row-title']")
    private WebElement accessToken;


    public IntegrationsImpl(WebDriver driver) // constructor - remote webdriver
    {
        this.localDriver = driver;
        PageFactory.initElements(localDriver, this); // call method
    }

    @Override
    public void clickIntegrationCategory() {
        integrationCategory.click();
    }

    @Override
    public void clickAccessTokens() {
        accessTokenOption.click();
    }

    @Override
    public void clickAddAccessTokens() {
        addAccessTokenButton.click();
    }

    @Override
    public void enterAccessTokenName(String accessTokenName) {
        accessTokenNameInput.sendKeys(accessTokenName);
    }

    @Override
    public void disableExpiryDate() {
        disabledExpiryDate.click();
    }

    @Override
    public void clickSaveToken() {
        saveAccessTokenButton.click();
    }

    @Override
    public String fetchAccessTokens() {
        return accessToken.getText();
    }
}
