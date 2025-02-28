package com.backoffice.pageobjects;

import com.backoffice.utilities.WebDriverHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Neha Kharbanda
 */
public class PaymentTypeImpl implements IPaymentsType {

    private final Logger log = LogManager.getLogger(PaymentTypeImpl.class);
    private final WebDriverHelper webDriverHelper;
    WebDriver driver;

    @FindBy(how = How.ID, using = "idPayments")
    private WebElement paymentTypesOption;
    @FindBy(how = How.ID, using = "settings_pay_type__add_pay_type_btn")
    private WebElement addPaymentTypeBtn;
    @FindBy(how = How.NAME, using = "paymentTypeName")
    private WebElement paymentNameInput;
    @FindBy(how = How.NAME, using = "paymentType")
    private WebElement paymentTypeDropdown;
    @FindBy(how = How.XPATH, using = "//*[@aria-label='Save']")
    private WebElement savePaymentTypeBtn;
    @FindBy(how = How.XPATH, using = "//*[@aria-label='Cancel']")
    private WebElement cancelPaymentTypeBtn;
    @FindBy(how = How.XPATH, using = "//*[normalize-space()='Continue editing']")
    private WebElement continueEditingBtn;
    @FindBy(how = How.XPATH, using = "//*[normalize-space()='Discard changes']")
    private WebElement discardChangesBtn;


    /*
    payment types dropdown values
    */
    @FindBy(how = How.XPATH, using = "//*[@value='NONINTEGRATEDCARD']")
    private WebElement cardPaymentType;
    @FindBy(how = How.XPATH, using = "//*[@value='CHEQUE']")
    private WebElement checkPaymentType;
    @FindBy(how = How.XPATH, using = "//*[@value='OTHER']")
    private WebElement otherPaymentType;
    @FindBy(how = How.XPATH, using = "//*[@value='SUMUP']")
    private WebElement sumUpPaymentType;
    @FindBy(how = How.XPATH, using = "//*[@value='IZETTLE']")
    private WebElement zettlePaymentType;


    public PaymentTypeImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // call method
    }


    @Override
    public void clickPaymentsTypesOption() {
        paymentTypesOption.click();
        log.info("Payment Types option is selected.");
    }

    @Override
    public void clickAddPaymentTypeButton() {
        addPaymentTypeBtn.click();
        log.info("Add Payment button is selected.");
    }

    @Override
    public void selectPaymentType(String paymentType) {
        paymentTypeDropdown.click();
        switch (paymentType) {
            case "Card":
                cardPaymentType.click();
                break;
            case "Check":
                checkPaymentType.click();
                break;
            case "Other":
                otherPaymentType.click();
                break;
            case "SumUp":
                sumUpPaymentType.click();
                break;
            case "Zettle":
                zettlePaymentType.click();
                break;
            default:
                log.error("Invalid Payment Type {} on Create Payment Type screen on settings page.", paymentType);
        }
    }

    @Override
    public void selectStoreName(String storeName) {

    }



    @Override
    public void enterOrUpdatePaymentTypeName(String paymentTypeName) {
        webDriverHelper.enterOrUpdateFieldValue(paymentTypeName,paymentNameInput );
    }


    @Override
    public void saveOrCancelPaymentType(String expectedAction){
        switch (expectedAction) {
            case "Save":
                savePaymentTypeBtn.click();
                log.info("Save button is clicked on Payment Type screen.");
                break;
            case "Cancel":
                cancelPaymentTypeBtn.click();
                log.info("Cancel button is clicked on Payment Type screen.");
                break;
            case "Continue Editing":
                continueEditingBtn.click();
                log.info("Continue Editing button is clicked on Payment Type screen.");
                break;
            case "Discard Changes":
                discardChangesBtn.click();
                log.info("Discard Changes button is clicked on Payment Type screen.");
                break;
            default:
                log.error("Invalid action {} on Payment Type screen.", expectedAction);
        }
    }


    @Override
    public void clickDeleteOnEditPaymentType() {
    // to be done when ids are updated as no unique locator is there
    }

    @Override
    public void clickDeleteOnDeleteDialouge() {
        // to be done when ids are updated as no unique locator is there
    }
}
