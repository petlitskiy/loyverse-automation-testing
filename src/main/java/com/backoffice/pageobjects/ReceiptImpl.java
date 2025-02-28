package com.backoffice.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author Neha Kharbanda
 */
public class ReceiptImpl implements IReceipt {

    WebDriver localDriver;
    private Logger log = LogManager.getLogger(ReceiptImpl.class);
    @FindBy(how = How.NAME, using = "reciepts")
    private WebElement receiptOption;

    @FindBy(how = How.ID, using = "settings-receipt-header-textarea")
    private WebElement headerTextArea;

    @FindBy(how = How.ID, using = "settings-receipt-footer-textare")
    private WebElement footerTextArea;


    @FindBy(how = How.ID, using = "settings_receipt__email_upload")
    private WebElement uploadEmailLogo;


    @FindBy(how = How.ID, using = "settings_receipt__print_upload")
    private WebElement uploadPrintLogo;

    @FindBy(how = How.ID, using = "settings-receipt-customer_info-switch")
    private WebElement showCustInfoSwitch;

    @FindBy(how = How.ID, using = "settings-receipt-comments-switch")
    private WebElement showCommentsSwitch;

    @FindBy(how = How.ID, using = "settings-receipt-language-select")
    private WebElement selectReceiptLanguage;
    @FindBy(how = How.ID, using = "settings-receipt-save-button")
    private WebElement saveReceiptButton;
    @FindBy(how = How.ID, using = "settings-receipt-cancel-button")
    private WebElement cancelReceiptButton;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Receipt settings updated')]")
    private WebElement receiptUpdatedMessage;

    public ReceiptImpl(WebDriver driver) // constructor - remote webdriver
    {
        this.localDriver = driver;
        PageFactory.initElements(localDriver, this); // call method
    }

    @Override
    public void selectReceiptOption() {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(80));
        wait.until(ExpectedConditions.elementToBeClickable(receiptOption));
        receiptOption.click();
        log.info("Receipt option is clicked under settings menu");
    }

    @Override
    public void updateHeaderAndFooter(String headerText, String footerText) {
        headerTextArea.sendKeys(headerText);
        footerTextArea.sendKeys(footerText);
        log.info("Header text is updated as: {}", headerText);
        log.info("Footer text is updated as: {}", footerText);
    }

    @Override
    public void uploadPrintedReceipts() {
        uploadEmailLogo.click();
    }

    @Override
    public void uploadEmailedReceipts() {
        uploadPrintLogo.click();
    }

    @Override
    public void clickShowCustInfoIcon() {
        showCustInfoSwitch.click();
        log.info("Show customer info icon is clicked");
    }

    @Override
    public void clickShowCommentsIcon() {
        showCommentsSwitch.click();
        log.info("Show comments icon is clicked");
    }

    @Override
    public void selectReceiptLanguage(String receiptLanguage) {
        try {
            selectReceiptLanguage.click();
            WebElement element = localDriver.findElement(By.xpath("//div[@class='md-virtual-repeat-offsetter']/div[normalize-space()='" + receiptLanguage + "']"));
            element.click();
            log.info("Receipt language is selected as: {}", receiptLanguage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelReceipt() {
        cancelReceiptButton.click();
        log.info("Cancel button is clicked on receipt settings page");
    }

    @Override
    public void saveReceipt() {
        saveReceiptButton.click();
        log.info("Save button is clicked and receipt settings are saved");
    }

    @Override
    public void verifyReceiptUpdatedMessage(String expectedReceiptUpdatedMessage) {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Receipt settings updated')]")));
        String actualReceiptUpdatedMessage = receiptUpdatedMessage.getText();
        log.info("{} message is displayed", actualReceiptUpdatedMessage);
        Assert.assertEquals(expectedReceiptUpdatedMessage, actualReceiptUpdatedMessage);
    }

}

