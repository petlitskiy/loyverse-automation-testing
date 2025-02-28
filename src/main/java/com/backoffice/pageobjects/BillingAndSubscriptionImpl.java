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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Neha Kharbanda
 */
public class BillingAndSubscriptionImpl implements IBillingAndSubscription {

    private final WebDriverHelper webDriverHelper;
    WebDriver driver;
    private Logger log = LogManager.getLogger(BillingAndSubscriptionImpl.class);
    @FindBy(how = How.NAME, using = "account")
    private WebElement billingAndSubscriptionOption;

    @FindBy(how = How.XPATH, using = "(//button[@aria-label='Try for free'])[1]")
    private WebElement startEmployeeTrailSub;

    @FindBy(how = How.XPATH, using = "(//button[@aria-label='Try for free'])[1]")
    private WebElement startInventoryTrailSub;

    @FindBy(how = How.XPATH, using = "(//button[@aria-label='Try for free'])[2]")
    private WebElement startIntegrationTrailSub;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='ADD EMPLOYEE']")
    private WebElement addEmployeeBtn;

    @FindBy(how = How.ID, using = "subscribe_dialog-start_trial-button")
    private WebElement startFreeTrailBtn;

    @FindBy(how = How.XPATH, using = "//md-checkbox[@aria-label='Agree with terms']")
    private WebElement agreeIntegrationsTermsCheckbox;

    @FindBy(how = How.XPATH, using = "//button[@aria-label='ADD PAYMENT METHOD']") //  //button[@id='btn_main']  //
    private WebElement addPaymentMethod;
    @FindBy(how = How.ID, using = "//div[@id='card-number']//input[@aria-label=' ']")
    private WebElement cardNumberInput;
    @FindBy(how = How.NAME, using = "exp-date")
    private WebElement expiryDateInput;
    @FindBy(how = How.XPATH, using = "//input[@name='cvc']")
    private WebElement cvvInput;
    @FindBy(how = How.ID, using = "btn_main")
    private WebElement savePaymentMethod;

    @FindBy(how = How.XPATH, using = "//div[@class='lv-cell subscriptions'][1]//button[contains(text(),'Activate')]")
    private WebElement activateEmployeeSub;

    @FindBy(how = How.XPATH, using = "//div[@class='lv-cell subscriptions'][2]//button[contains(text(),'Activate')]")
    private WebElement activateInventorySub;

    @FindBy(how = How.XPATH, using = "//div[@class='lv-cell subscriptions'][3]//button[contains(text(),'Activate')]")
    private WebElement activateIntegrationSub;

    @FindBy(how = How.XPATH, using = "//button[@aria-label='Update payment method']")
    private WebElement updatePaymentMethod;

    @FindBy(how = How.XPATH, using = "//md-radio-button[@aria-label='£200/year per store']//div[@class='md-off']")
    private WebElement inventoryAnnualPricingPlan;
    @FindBy(how = How.XPATH, using = "//md-radio-button[@aria-label='£90/year per store']//div[@class='md-off']")
    private WebElement integrationsAnnualPricingPlan;
    @FindBy(how = How.ID, using = "subscribe_activate_dialog-save-button")
    private WebElement activateButton;

    @FindBy(how = How.ID, using = "subscribe_activate_dialog-cancel-button")
    private WebElement cancelBtnForActivateIntegrationScreen;


    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Confirm']")
    private WebElement confirmInvoicePreview;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Cancel']")
    private WebElement cancelInvoicePreview;

    @FindBy(how = How.XPATH, using = "(//button[contains(@aria-label,'Unsubscribe')][normalize-space()='Unsubscribe'])[1]")
    private WebElement unSubscribeEmployeeSub;

    @FindBy(how = How.XPATH, using = "(//button[contains(@aria-label,'Unsubscribe')][normalize-space()='Unsubscribe'])[2]")
    private WebElement unSubscribeInventorySub;

    @FindBy(how = How.XPATH, using = "//tbody")
    private WebElement invoiceTable;

    @FindBy(how = How.XPATH, using = "//div[@class='buttons-block buttons-block-bottom text-right']//button[. = 'Unsubscribe']")
    private WebElement unSubscribeIntegrationBtn;

    @FindBy(how = How.XPATH, using = "//div[@class='lv-cell subscriptions'][3]//button[contains(text(),'Unsubscribe')]")
    private WebElement unSubscribeIntegrationsSub;


    public BillingAndSubscriptionImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // call method
    }

    public static void dummySend(WebDriver driver, String xpathExpression, String word, int delay) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath(xpathExpression));
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            element.sendKeys(Character.toString(c));
            Thread.sleep(delay);
        }
    }

    @Override
    public void clickBillingAndSubscriptionOption() {
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(3));
        wait2.until(ExpectedConditions.elementToBeClickable(billingAndSubscriptionOption));
        webDriverHelper.clickElementWithJavaScript(billingAndSubscriptionOption);
        log.info("Billing And Subscription option is selected.");
    }

    @Override
    public void startTrialSubscriptions(String subscriptionType) {
        switch (subscriptionType) {
            case "EmployeeManagement":
                webDriverHelper.clickElement(startEmployeeTrailSub);
                clickAddEmployeeBtn();
                break;
            case "AdvancedInventory":
                webDriverHelper.clickElement(startInventoryTrailSub);
                clickStartFreeTrialBtn();
                break;
            case "Integrations":
                webDriverHelper.clickElement(startIntegrationTrailSub);
                clickAgreeAllTermsCheckbox();
                clickStartFreeTrialBtn();
                break;
            default:
                log.error("Subscription Type not passed", subscriptionType);
        }
    }

    @Override
    public void clickAddEmployeeBtn() {
        addEmployeeBtn.click();
        log.info("Add Employee button is clicked to enable employee subscription.");
    }

    @Override
    public void clickStartFreeTrialBtn() {
        startFreeTrailBtn.click();
        log.info("Start Free Trail button is clicked to enable subscription.");
    }

    @Override
    public void clickAgreeAllTermsCheckbox() {
        agreeIntegrationsTermsCheckbox.click();
        log.info("Agree All terms checkbox is clicked for Integrations.");
    }

    @Override
    public void clickAddPaymentMethod() {
        addPaymentMethod.click();
        log.info("add Payment Method button is clicked");
    }

    @Override
    public void enterCardNumber(String cardNumber) {
        try {

            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60000));
            boolean isDisplayed2 = driver.findElement(By.xpath("//div[@id='card-number']")).isDisplayed();
            driver.findElement(By.xpath("//div[@id='card-number']")).click();
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(2));
            Thread.sleep(4000);
            //driver.findElement(By.xpath("//div[@id='card-number']//input[@aria-label=' ']")).click();
            dummySend(driver, "//div[@id='card-number']//input[@aria-label=' ']", cardNumber, 1000);
            boolean cardExpiryDate = driver.findElement(By.xpath("//div[@id='card-expiry']")).isDisplayed();
            driver.findElement(By.xpath("//div[@id='card-expiry']")).click();
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofMinutes(2));
            Thread.sleep(4000);

            dummySend(driver, "//div[@id='card-expiry']//input[contains(@aria-label,'')]", "01/26 ", 1000);
            /*
            driver.findElement(By.xpath("//div[@id='card-number']")).click();
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(2));
         Thread.sleep(4000);
           driver.findElement(By.xpath("//div[@id='card-number']//input[@aria-label=' ']")).sendKeys(cardNumber);
          log.info("Card Number is entered as {} ", cardNumber);*/
        } catch (Exception e) {
            log.error("An unexpected error occurred: " + e.getMessage());
            throw new RuntimeException("cardNumber BLA BLA BLA BLA BLA BLA :", e);

        }
    }

    @Override
    public void enterCardExpiryDate(String expiryDate) {
        try {
            boolean cardExpiryDate = driver.findElement(By.xpath("//div[@id='card-expiry']")).isDisplayed();
            driver.findElement(By.xpath("//div[@id='card-expiry']")).click();
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(2));
            Thread.sleep(4000);
            driver.findElement(By.xpath("//div[@id='card-expiry']//input[contains(@aria-label,'')]")).sendKeys(expiryDate);

        } catch (Exception e) {
            log.error("An unexpected error occurred: " + e.getMessage());
            throw new RuntimeException("expiryDate BLA BLA BLA BLA BLA BLA :", e);

        }


    }

    @Override
    public void enterCardCVV(String cardCVV) {
        try {
            boolean cardCdVV = driver.findElement(By.xpath("//div[@id='card-cvc']")).isDisplayed();
            driver.findElement(By.xpath("//div[@id='card-cvc']")).click();
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(2));
            Thread.sleep(4000);
            driver.findElement(By.xpath("//div[@id='card-cvc']//input[@aria-label=' ']")).sendKeys(cardCVV);

        } catch (Exception e) {
            log.error("An unexpected error occurred: " + e.getMessage());
            throw new RuntimeException(" cardCdVV BLA BLA BLA BLA BLA BLA :", e);

        }
    }

    @Override
    public void clickSavePaymentBtn() {
        savePaymentMethod.click();
        log.info("Payment method is saved");
    }

    @Override
    public void clickUpdatePaymentMethod() {
        updatePaymentMethod.click();
        log.info("update Payment Method button is clicked");
    }

    @Override
    public void activateSubscriptions(String planType, String subscriptionType) throws InterruptedException {
        switch (subscriptionType) {
            case "EmployeeManagement":
                webDriverHelper.clickElement(activateEmployeeSub);
                break;
            case "AdvancedInventory":
                Thread.sleep(1000);
                webDriverHelper.clickElement(activateInventorySub);
                Thread.sleep(1000);
                enableInventoryPricingPlan(planType);
                Thread.sleep(1000);
                clickActivateButton();
                Thread.sleep(1000);
                clickConfirmInvoicePreviewBtn();
                Thread.sleep(1000);
                break;
            case "Integrations":
                Thread.sleep(1000);
                webDriverHelper.clickElement(activateIntegrationSub);
                Thread.sleep(1000);
                enableIntegrationPricingPlan(planType);
                Thread.sleep(1000);
                clickActivateButton();
                Thread.sleep(1000);
                clickCancelInvoicePreviewBtn();
                Thread.sleep(1000);
                driver.findElement(By.id("subscribe_activate_dialog-cancel-button")).click();
                break;
            default:
                log.error("Subscription Type not passed", subscriptionType);
        }
    }

    @Override
    public void enableInventoryPricingPlan(String planType) {
        switch (planType) {
            case "annual":
                inventoryAnnualPricingPlan.click();
                break;
            case "monthly":
                break;
            default:
                log.error("Inventory Subscription Pricing Plan not passed", planType);
        }
        log.info("Inventory Pricing Plan is selected as {}", planType);
    }

    @Override
    public void clickActivateButton() {
        activateButton.click();
        log.info("Activate button is clicked");
    }

    @Override
    public void clickConfirmInvoicePreviewBtn() {
        confirmInvoicePreview.click();
        log.info("Confirm is clicked on Invoice Preview dialouge box ");
    }

    @Override
    public void enableIntegrationPricingPlan(String planType) {
        switch (planType) {
            case "annual":
                integrationsAnnualPricingPlan.click();
                break;
            case "monthly":
                break;
            default:
                log.error("Integration Subscription Pricing Plan not passed", planType);
        }
        log.info("Integration Pricing Plan is selected as {}", planType);

    }


    @Override
    public void clickCancelInvoicePreviewBtn() {
        cancelInvoicePreview.click();
        log.info("Cancel is clicked on Invoice Preview dialouge box ");
    }


    @Override
    public void unsubscribeSubscriptions(String subscriptionType) {
        switch (subscriptionType) {
            case "EmployeeManagement":
                webDriverHelper.clickElement(unSubscribeEmployeeSub);
                break;
            case "AdvancedInventory":
                webDriverHelper.clickElement(unSubscribeInventorySub);
                break;
            case "Integrations":
                webDriverHelper.clickElement(unSubscribeIntegrationsSub);
                clickUnSubscribeIntegrationBtn();
                break;
            default:
                log.error("Subscription Type not passed for unsubscription", subscriptionType);
        }
    }

    @Override
    public void clickUnSubscribeIntegrationBtn() {
        unSubscribeIntegrationBtn.click();
        log.info("unSubscribe Integration Btn is clicked on dialog box");
    }

    public void verifySuccessPaidInvoiceGenerated(String expectedServiceType, String expectedServicePrice) {
        LocalDate today = LocalDate.now();
        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        // Format the current date as a string
        String expectedDate = today.format(formatter);

        List<WebElement> rows = invoiceTable.findElements(By.tagName("tr"));
        // boolean isExpectedNamePresent = false;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 3) {
                String actualDate = cells.get(0).findElement(By.tagName("div")).getText();
                String actualServiceType = cells.get(1).findElement(By.tagName("span")).getText();
                String actualPrice = cells.get(2).findElement(By.tagName("span")).getText();

                if (actualServiceType.equals(expectedServiceType) && actualPrice.equals(expectedServicePrice) && actualDate.equals(expectedDate)) {
                    log.info("Success Invoice generated for {}", expectedServiceType);
                    //  isExpectedNamePresent = true;
                    break;  // Exit the loop when the name is found
                } else {
                    log.info("Success Invoice not generated for {}", expectedServiceType);
                }
            }
        }
    }


}
