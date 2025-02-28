package com.backoffice.pageobjects;

import com.backoffice.managers.ISharedFileManager;
import com.backoffice.managers.SharedFileManager;
import com.backoffice.utilities.Constants;
import com.backoffice.utilities.UniqueCredentialsGenerator;
import com.backoffice.utilities.WebDriverHelper;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * @author Neha Kharbanda
 */

/**
 * Implementation of the BackOfficeLogin interface for login functionality.
 */
public class BackOfficeLoginImpl implements IBackOfficeLogin {
    public static UniqueCredentialsGenerator uniqueCredentialsGenerator;
    private final Logger log = LogManager.getLogger(BackOfficeLoginImpl.class);
    private final WebDriverHelper webDriverHelper;
    public ISharedFileManager sharedFileManager;
    WebDriver driver;
    // Login Form

    @FindBy(how = How.XPATH, using = "//input[@name='login']")
    private WebElement usernameInput;
    @FindBy(how = How.XPATH, using = "//input[@name='pass']")
    private WebElement passwordInput;
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Enter')]")
    private WebElement loginButton;

    //Registration Form
    @FindBy(id = "greeting-close-button")
    private WebElement registrationDialogue;
    @FindBy(how = How.XPATH, using = "//input[@type='email']")
    private WebElement registrationEmailInput;
    @FindBy(how = How.NAME, using = "pass")
    private WebElement pwdInput;
    @FindBy(how = How.NAME, using = "passRepeat")
    private WebElement pwdAgainInput;
    @FindBy(how = How.NAME, using = "business-name")
    private WebElement businessNameInput;
    @FindBy(how = How.XPATH, using = "//md-select[@placeholder='Country']")
    private WebElement countryDropDownIcon;
//    @FindBy(how = How.XPATH, using = "//*[@placeholder='COUNTRY']")  // for qa5 until fixed
//    private WebElement countryDropDownIcon;

    @FindBy(how = How.XPATH, using = "//md-checkbox[@aria-label='license accept']")
    private WebElement policyCheckbox;
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Sign up']")
    private WebElement signUpButton;


    // Onboarding Dialog
    @FindBy(how = How.ID, using = "after_registration_dialog-play_market-link")
    private WebElement googlePlayBadge;
    @FindBy(how = How.ID, using = "after_registration_dialog-app_store-link")
    private WebElement appStoreBadge;

    @FindBy(how = How.XPATH, using = "//*[text() = 'Loyverse POS - Point of Sale']")
    private WebElement storePageDisplayed;

    //Feature settings
    @FindBy(how = How.ID, using = "greeting-close-button")
    private WebElement nextButtonBoardingDialouge;

    @FindBy(how = How.ID, using = "dialog_feature_settings-cancel-button")
    private WebElement cancelButtonFeatureSettings;

    @FindBy(how = How.ID, using = "dialog_feature_settings-confirm-button")
    private WebElement saveButtonFeatureSettings;

    @FindBy(how = How.ID, using = "feature-settings-shifts")
    private WebElement shiftCheckBoxIcon;
    @FindBy(how = How.ID, using = "feature-settings-time")
    private WebElement timeClockIcon;
    @FindBy(how = How.ID, using = "feature-settings-open_tickets")
    private WebElement openTicketsIcon;
    @FindBy(how = How.ID, using = "feature-settings-kitchen_printers")
    private WebElement kitchenPrintersIcon;
    @FindBy(how = How.ID, using = "feature-settings-customer_displays")
    private WebElement customerDisplaysIcon;
    @FindBy(how = How.ID, using = "feature-settings-dining_options")
    private WebElement diningOptionsIcon;
    @FindBy(how = How.ID, using = "feature-settings-low_stock_notification")
    private WebElement lowStockNotificationsIcon;
    @FindBy(how = How.ID, using = "feature-settings-negative_stock_alert")
    private WebElement negativeStockAlertsIcon;
    @FindBy(how = How.ID, using = "feature-settings-weight_embedded_barcodes")
    private WebElement weightEmbeddedBarcodesIcon;


    // Time clock
    @FindBy(how = How.ID, using = "//*[@id='dialog_pin-pin-input']//input[1]")
    private WebElement setTimeClockPinInput1;
    @FindBy(how = How.ID, using = "//*[@id='dialog_pin-pin-input']//input[2]")
    private WebElement setTimeClockPinInput2;
    @FindBy(how = How.ID, using = "//*[@id='dialog_pin-pin-input']//input[3]")
    private WebElement setTimeClockPinInput3;
    @FindBy(how = How.ID, using = "//*[@id='dialog_pin-pin-input']//input[4]")
    private WebElement setTimeClockPinInput4;

    @FindBy(how = How.ID, using = "dialog_pin-cancel-button")
    private WebElement cancelBtnSetPinTimeClock;
    @FindBy(how = How.ID, using = "dialog_pin-confirm-button")
    private WebElement confirmBtnSetPinTimeClock;


    /**
     * Constructor to initialize the BackOfficeLoginImpl class.
     *
     * @param driver The WebDriver instance.
     */
    public BackOfficeLoginImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        sharedFileManager = new SharedFileManager();
        this.driver = driver;
        PageFactory.initElements(driver, this); // call method
        uniqueCredentialsGenerator = new UniqueCredentialsGenerator();
    }

    @Override
    public void navigateToLogin() {
        try {
            driver.get(sharedFileManager.getConfigReader().getLoginServiceURL());
            log.info("{} Url is launched", sharedFileManager.getConfigReader().getLoginServiceURL());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Thread.sleep(5000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void navigateToApplication(String expectedServiceType) {

        switch (expectedServiceType) {
            case Constants.LOGIN_SERVICE: {
                driver.get(sharedFileManager.getConfigReader().getLoginServiceURL());
                log.info("{} Url is launched", sharedFileManager.getConfigReader().getLoginServiceURL());
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
                //Thread.sleep(5000);
                break;
            }
            case Constants.REGISTRATION_SERVICE: {
                driver.get(sharedFileManager.getConfigReader().getRegistrationServiceURL());
                log.info("{} Url is launched", sharedFileManager.getConfigReader().getRegistrationServiceURL());
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
                break;
            }
            default:
        }
    }


    @Override
    public void enterUsername(String userName) {
        webDriverHelper.highlightElement(usernameInput);
        usernameInput.sendKeys(userName);
        log.info("UserName is entered as {}", userName);
    }

    @Override
    public void enterPassword(String pssword) {
        webDriverHelper.highlightElement(passwordInput);
        passwordInput.sendKeys(pssword);
        log.info("Password is entered as {}", pssword);
    }

    @Override
    public void clickLoginButton() {
        webDriverHelper.highlightElement(loginButton);
        loginButton.click();
        log.info("Login button is clicked");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
    }

    @Override
    public void clickNextOnBoardingDialogue() {
        nextButtonBoardingDialouge.click();
        log.info("Next is clicked on Thanks for registering dialogue");
    }

    @Override
    public void clickCancelButtonFeatureSettings() {
        cancelButtonFeatureSettings.click();
        log.info("Cancel is clicked on Feature Settings dialogue");
    }

    @Override
    public void clickSaveButtonFeatureSettings() {
        saveButtonFeatureSettings.click();
        log.info("Saved is clicked on Feature Settings dialogue");
    }

    @Override
    public void clickConfirmSetPinDialouge() {
        webDriverHelper.clickElement(confirmBtnSetPinTimeClock);
    }

    @Override
    public void setPinForTimeClock(String pinForTimeClock) {
        for (int i = 1; i <= 4; i++) {
            String timeClockPin = pinForTimeClock.substring(i - 1, i);
            WebElement setTimeClockPinInput = driver.findElement(By.xpath("//*[@id='dialog_pin-pin-input']//input[" + i + "]"));
            setTimeClockPinInput.click();
            webDriverHelper.sendKeys(setTimeClockPinInput, timeClockPin);
        }
    }

    @Override
    public void clickCancelSetPinDialouge() {
        webDriverHelper.clickElement(cancelBtnSetPinTimeClock);
    }

    @Override
    public void enterRegistrationDetails(DataTable registrationDetails) {

        List<Map<String, String>> registrationData = registrationDetails.asMaps(String.class, String.class);
        for (Map<String, String> registrationMap : registrationData) {
            String businessName = registrationMap.get("BusinessName");
            String country = registrationMap.get("Country");

            String emailID = uniqueCredentialsGenerator.generateUniqueEmail();
            String pwd = uniqueCredentialsGenerator.generateUniquePassword();

            webDriverHelper.sendKeys(registrationEmailInput, emailID);
            webDriverHelper.sendKeys(pwdInput, pwd);
            webDriverHelper.sendKeys(pwdAgainInput, pwd);
            webDriverHelper.sendKeys(businessNameInput, businessName);
            webDriverHelper.clickElement(countryDropDownIcon);
            webDriverHelper.scrollToElement(driver.findElement(By.xpath("//div[@class='ng-binding'][normalize-space()='" + country + "']")));
            driver.findElement(By.xpath("//div[@class='ng-binding'][normalize-space()='" + country + "']")).click();
            webDriverHelper.clickElement(policyCheckbox);
            webDriverHelper.clickElement(signUpButton);
            log.info("Registration details are entered");
        }
    }


    @Override
    public void clickAndRedirectStoreBadge(String badgeType, String expectedPageTitle) {
        switch (badgeType) {
            case Constants.GOOGLE_PLAY: {
                webDriverHelper.clickElement(googlePlayBadge);
                webDriverHelper.switchToNewTab();
                storePageDisplayed.isDisplayed();
                webDriverHelper.switchToOriginalTab();
                break;
            }
            case Constants.APPLE_PLAY: {
                webDriverHelper.clickElement(appStoreBadge);
                webDriverHelper.switchToNewTab();
                storePageDisplayed.isDisplayed();
                webDriverHelper.switchToOriginalTab();
                break;
            }
            default:
        }

    }

    @Override
    public void verifyOnBoardingFeaturesDisabled(String featureName, String expectedStatus) {
        // Determine the WebElement to use based on the feature name
        String actualStatus;
        switch (featureName) {
            case "Shifts":
                actualStatus = webDriverHelper.verifyElementDisabled(shiftCheckBoxIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Time clock":
                actualStatus = webDriverHelper.verifyElementDisabled(timeClockIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Open tickets":
                actualStatus = webDriverHelper.verifyElementDisabled(openTicketsIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Kitchen printers":
                actualStatus = webDriverHelper.verifyElementDisabled(kitchenPrintersIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Customer displays":
                actualStatus = webDriverHelper.verifyElementDisabled(customerDisplaysIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Dining options":
                actualStatus = webDriverHelper.verifyElementDisabled(diningOptionsIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Low stock notifications":
                actualStatus = webDriverHelper.verifyElementDisabled(lowStockNotificationsIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Negative stock alerts":
                actualStatus = webDriverHelper.verifyElementDisabled(negativeStockAlertsIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Weight embedded barcodes":
                actualStatus = webDriverHelper.verifyElementDisabled(weightEmbeddedBarcodesIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            default:
                log.error("{} is not correct. Please pass correct feature settings." + featureName);
                break;
            // Add other cases for each feature
        }
    }


    @Override
    public void verifyOnBoardingFeaturesEnabled(String featureName, String expectedStatus) {
        // Determine the WebElement to use based on the feature name
        String actualStatus;
        switch (featureName) {
            case "Shifts":
                actualStatus = webDriverHelper.checkToggleIsEnabled(shiftCheckBoxIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Time clock":
                actualStatus = webDriverHelper.checkToggleIsEnabled(timeClockIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Open tickets":
                actualStatus = webDriverHelper.checkToggleIsEnabled(openTicketsIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Kitchen printers":
                actualStatus = webDriverHelper.checkToggleIsEnabled(kitchenPrintersIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Customer displays":
                actualStatus = webDriverHelper.checkToggleIsEnabled(customerDisplaysIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Dining options":
                actualStatus = webDriverHelper.checkToggleIsEnabled(diningOptionsIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Low stock notifications":
                actualStatus = webDriverHelper.checkToggleIsEnabled(lowStockNotificationsIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Negative stock alerts":
                actualStatus = webDriverHelper.checkToggleIsEnabled(negativeStockAlertsIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            case "Weight embedded barcodes":
                actualStatus = webDriverHelper.checkToggleIsEnabled(weightEmbeddedBarcodesIcon);
                Assert.assertEquals(expectedStatus, actualStatus);
                break;
            default:
                log.error("{} is not correct. Please pass correct feature settings." + featureName);
                break;
            // Add other cases for each feature
        }
    }


    @Override
    public void updateFeatureSettings(String expectedFeatureName, String expectedFeatureStatus) {
        String toggleStatus;
        switch (expectedFeatureName) {
            case "Shifts":
                toggleStatus = webDriverHelper.checkToggleIsEnabled(shiftCheckBoxIcon);
                if ((toggleStatus.equals("disabled") && expectedFeatureStatus.equals("enabled")) ||
                        toggleStatus.equals("enabled") && expectedFeatureStatus.equals("disabled")) {
                    webDriverHelper.clickElement(shiftCheckBoxIcon);
                    break;
                }
            case "Time clock":
                toggleStatus = webDriverHelper.checkToggleIsEnabled(timeClockIcon);
                if ((toggleStatus.equals("disabled") && expectedFeatureStatus.equals("enabled")) ||
                        toggleStatus.equals("enabled") && expectedFeatureStatus.equals("disabled")) {
                    webDriverHelper.clickElement(timeClockIcon);
                    break;
                }
            case "Open tickets":
                toggleStatus = webDriverHelper.checkToggleIsEnabled(openTicketsIcon);
                if ((toggleStatus.equals("disabled") && expectedFeatureStatus.equals("enabled")) ||
                        toggleStatus.equals("enabled") && expectedFeatureStatus.equals("disabled")) {
                    webDriverHelper.clickElement(openTicketsIcon);
                    break;
                }
            case "Kitchen printers":
                toggleStatus = webDriverHelper.checkToggleIsEnabled(kitchenPrintersIcon);
                if ((toggleStatus.equals("disabled") && expectedFeatureStatus.equals("enabled")) ||
                        toggleStatus.equals("enabled") && expectedFeatureStatus.equals("disabled")) {
                    webDriverHelper.clickElement(kitchenPrintersIcon);
                    break;
                }
            case "Customer displays":
                toggleStatus = webDriverHelper.checkToggleIsEnabled(customerDisplaysIcon);
                if ((toggleStatus.equals("disabled") && expectedFeatureStatus.equals("enabled")) ||
                        toggleStatus.equals("enabled") && expectedFeatureStatus.equals("disabled")) {
                    webDriverHelper.clickElement(customerDisplaysIcon);
                    break;
                }
            case "Dining options":
                toggleStatus = webDriverHelper.checkToggleIsEnabled(diningOptionsIcon);
                if ((toggleStatus.equals("disabled") && expectedFeatureStatus.equals("enabled")) ||
                        toggleStatus.equals("enabled") && expectedFeatureStatus.equals("disabled")) {
                    webDriverHelper.clickElement(diningOptionsIcon);
                    break;
                }
            case "Low stock notifications":
                toggleStatus = webDriverHelper.checkToggleIsEnabled(lowStockNotificationsIcon);
                if ((toggleStatus.equals("disabled") && expectedFeatureStatus.equals("enabled")) ||
                        toggleStatus.equals("enabled") && expectedFeatureStatus.equals("disabled")) {
                    webDriverHelper.clickElement(lowStockNotificationsIcon);
                    break;
                }
            case "Negative stock alerts":
                toggleStatus = webDriverHelper.checkToggleIsEnabled(negativeStockAlertsIcon);
                if ((toggleStatus.equals("disabled") && expectedFeatureStatus.equals("enabled")) ||
                        toggleStatus.equals("enabled") && expectedFeatureStatus.equals("disabled")) {
                    webDriverHelper.clickElement(negativeStockAlertsIcon);
                    break;
                }
            case "Weight embedded barcodes":
                toggleStatus = webDriverHelper.checkToggleIsEnabled(weightEmbeddedBarcodesIcon);
                if ((toggleStatus.equals("disabled") && expectedFeatureStatus.equals("enabled")) ||
                        toggleStatus.equals("enabled") && expectedFeatureStatus.equals("disabled")) {
                    webDriverHelper.clickElement(weightEmbeddedBarcodesIcon);
                    break;
                }
            default:
                log.error("{} is not correct. Please pass correct feature settings to update." + expectedFeatureName);
                break;
        }
    }

}

