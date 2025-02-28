package com.backoffice.pageobjects;

import com.backoffice.utilities.WebDriverHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
public class CustomerImpl implements ICustomer {

    private final Logger log = LogManager.getLogger(CategoriesImpl.class);
    WebDriver driver;
    private final WebDriverHelper webDriverHelper;
    @FindBy(how = How.ID, using = "clients__add_customer_btn")
    private WebElement addCustomerButton;
    @FindBy(how = How.ID, using = "name")
    private WebElement nameInput;
    @FindBy(how = How.NAME, using = "email")
    private WebElement emailInput;
    @FindBy(how = How.NAME, using = "phone")
    private WebElement phoneInput;
    @FindBy(how = How.NAME, using = "address")
    private WebElement addressInput;
    @FindBy(how = How.NAME, using = "city")
    private WebElement cityInput;
    @FindBy(how = How.NAME, using = "province")
    private WebElement stateInput;
    @FindBy(how = How.NAME, using = "postalCode")
    private WebElement zipCodeInput;
    @FindBy(how = How.XPATH, using = "//md-select[@aria-label='select country']")
    private WebElement countryDropdown;
    @FindBy(how = How.NAME, using = "customerCode")
    private WebElement customerCodeInput;
    @FindBy(how = How.NAME, using = "textarea")
    private WebElement noteTextArea;
    @FindBy(how = How.ID, using = "customer_edit-cancel-button")
    private WebElement customerCancelButton;
    @FindBy(how = How.XPATH, using = "//button[@aria-label='Save']")
    private WebElement customerSaveButton;
    @FindBy(how = How.XPATH, using = "//button[@aria-label='Delete']")
    private WebElement customerDeleteButton;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Delete')]")
    private WebElement customerDeleteDialogueOnListPage;
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Delete']")
    private WebElement customerDeleteDialogueOnEditProfilePage;
    @FindBy(how = How.XPATH, using = "//button[@aria-label='CLIENTS.DB.CLIENTS_BASE']")
    private WebElement customerBaseIcon;
    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Customer deleted')]")
    private WebElement customerDeletedMessage;
    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'More')]")
    private WebElement moreButtonCustomerProfile;
    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Edit points balance')]")
    private WebElement editPointsBalanceBtn;
    @FindBy(how = How.NAME, using = "pointsQuantity")
    private WebElement editPointsBalanceInput;
    @FindBy(how = How.XPATH, using = "//*[(@aria-label)='ON_SAVE']")
    private WebElement saveEditPointsBalanceBtn;
    @FindBy(how = How.XPATH, using = "//*[contains(@aria-label, 'points')]")
    private WebElement pointsOnCustomerProfilePage;
    @FindBy(how = How.XPATH, using = "//*[contains(text(), 'Edit profile')]")
    private WebElement editProfileBtnOnCustomerProfilePage;
    @FindBy(how = How.XPATH, using = "//*[contains(@aria-label,'ADD CUSTOMER')]")
    private WebElement addCustomerBtnWhenCustPresent; // require to update id when BN-2988 is done
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='OK']")
    private WebElement okButtonOnCodeExistPopup; // require to update id when BN-2988 is done
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Discard changes']")
    private WebElement discardChangesBtn; // require to update id when BN-2988 is done
    @FindBy(how = How.ID, using = "enableSearch")
    private WebElement searchIconCustomerListPage;
    @FindBy(how = How.ID, using = "table_search-input")
    private WebElement searchInputCustomerListPage;
    @FindBy(how = How.ID, using = "startSearch")
    private WebElement searchButtonCustomerListPage;


    public CustomerImpl(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        webDriverHelper = new WebDriverHelper(driver);
    }

    @Override
    public void clickAddCustomer() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.elementToBeClickable(addCustomerButton));
            addCustomerButton.click();
            log.info("Add customer button is clicked on customer base page");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enterOrUpdateCustomerName(String customerName) {
        webDriverHelper.enterOrUpdateFieldValue(customerName, nameInput);
    }

    @Override
    public void enterOrUpdateCustomerEmail(String customerEmail) {
        webDriverHelper.enterOrUpdateFieldValue(customerEmail, emailInput);
    }

    @Override
    public void enterOrUpdateCustomerPhone(String customerPhone) {
        webDriverHelper.enterOrUpdateFieldValue(customerPhone, phoneInput);
    }

    @Override
    public void enterOrUpdateCustomerAddress(String customerAddress) {
        webDriverHelper.enterOrUpdateFieldValue(customerAddress, addressInput);
    }

    @Override
    public void enterOrUpdateCustomerCity(String customerCity) {
        webDriverHelper.enterOrUpdateFieldValue(customerCity, cityInput);
    }

    @Override
    public void enterOrUpdateCustomerState(String customerState) {
        webDriverHelper.enterOrUpdateFieldValue(customerState, stateInput);
    }

    @Override
    public void enterOrUpdateCustomerZipCode(String customerZipcode) {
        webDriverHelper.enterOrUpdateFieldValue(customerZipcode, zipCodeInput);
    }

    @Override
    public void selectCustomerCountry(String customerCountry) {
        try {
            webDriverHelper.clickElementWithJavaScript(countryDropdown);
            webDriverHelper.scrollToElement(driver.findElement(By.xpath("//*[text()='" + customerCountry + "']")));
            driver.findElement(By.xpath("//*[text()='" + customerCountry + "']")).click();
            log.info("Customer country is entered as: {}", customerCountry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enterOrUpdateCustomerCode(String customerCode) {
        webDriverHelper.enterOrUpdateFieldValue(customerCode, customerCodeInput);
    }

    @Override
    public void enterOrUpdateCustomerNote(String customerNote) {
        webDriverHelper.enterOrUpdateFieldValue(customerNote, noteTextArea);
    }

    @Override
    public void clickCustomerCancel() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        customerCancelButton.click();
        log.info("Cancel button is clicked at create customer page");
    }

    @Override
    public void clickCustomerSave() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        customerSaveButton.click();
        log.info("Save button is clicked at create customer page");
    }

    @Override
    public void clickDeleteCustomer() {
        webDriverHelper.waitUntilElementIsClickable(customerDeleteButton);
        customerDeleteButton.click();
        log.info("Delete button is clicked at customer base page");
    }

    @Override
    public void clickDeleteOnDialogue() {
        customerDeleteDialogueOnListPage.click();
        log.info("Delete button is clicked on delete customer dialogue box");
    }

    @Override
    public void clickDeleteOnDialogueOnEditProfilePage() {
        customerDeleteDialogueOnEditProfilePage.click();
        log.info("Delete button is clicked on dialogue on Edit Profile Page");
    }


    @Override
    public void clickCustomerBaseOnCustomerProfile() {
        customerBaseIcon.click();
        log.info("Clicked on customer Base button to redirect to customer base page");
    }

    @Override
    public void verifyCustomerDeletionMessage(String expectedDeletionMessage) {
        String actualDeletionMessage = customerDeletedMessage.getText();
        log.info("Expected deletion message is {} | Actual deletion message is {}", expectedDeletionMessage, actualDeletionMessage);
        Assert.assertEquals(expectedDeletionMessage, actualDeletionMessage);
    }

    @Override
    public void clickMoreButtonOnCustomerProfile() {
        webDriverHelper.waitUntilElementIsClickable(moreButtonCustomerProfile);
        webDriverHelper.clickElement(moreButtonCustomerProfile);
        log.info("Clicked on more button on customer profile page");
    }

    @Override
    public void clickEditPointsBalanceBtn() {
        webDriverHelper.waitUntilElementIsClickable(editPointsBalanceBtn);
        webDriverHelper.clickElement(editPointsBalanceBtn);
        log.info("Clicked on Edit Points Balance on customer profile page");
    }

    @Override
    public void enterOrUpdateEditPoints(String editPointsValue) {

        String fetchPointsValue = editPointsBalanceInput.getAttribute("value");
        if (StringUtils.isNotBlank(fetchPointsValue)) {
            editPointsBalanceInput.sendKeys(Keys.END); // Move cursor to the end of the text
            int textLength = editPointsBalanceInput.getAttribute("value").length();
            for (int i = 0; i < textLength; i++) {
                editPointsBalanceInput.sendKeys(Keys.BACK_SPACE);
            }
            log.info("edit Points Value is cleared at run time");
        } else {
            log.info("edit Points Value is already cleared");
        }
        editPointsBalanceInput.click();
        editPointsBalanceInput.sendKeys(editPointsValue);
        log.info("Edit Points value is entered as: {}", editPointsValue);
    }

    @Override
    public void clickSavePointsBtn() {
        saveEditPointsBalanceBtn.click();
        log.info("Clicked on Save Edit Points button on edit point balance button");
    }

    public void verifyPointsOnCustomerProfile(String editPointsValue) {
        String actualPoints = pointsOnCustomerProfilePage.getAttribute("value");
        Assert.assertEquals(editPointsValue, actualPoints);
        log.info("Expected points are {} | Actual points are {}", editPointsValue, actualPoints);
    }

    @Override
    public void clickEditProfileBtn() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        editProfileBtnOnCustomerProfilePage.click();
        log.info("Clicked on Edit Profile button on customer profile screen");
    }

    @Override
    public void clickAddCustomerWhenCustomerPresent() {
        addCustomerBtnWhenCustPresent.click();
        log.info("Clicked on add Customer Btn When Customer is Present");
    }

    @Override
    public void clickOKOnCodeExistsDialogue() {
        okButtonOnCodeExistPopup.click();
        log.info("Clicked on OK Button on  Unable to save customer profile popup");
    }

    @Override
    public void clickDiscardChangesBtn() {
        discardChangesBtn.click();
        log.info("Clicked on Discard Changes on Unsaved changes popup on edit profile screen");
    }

    @Override
    public void clickCustomerToOpenEditCard(String customerNameToOpen){
        driver.findElement(By.xpath("//*[text()='" + customerNameToOpen + "']")).click();
        log.info("Clicked on customer name to open customer profile");
    }

    @Override
    public void clickSearchIcon(){
       searchIconCustomerListPage.click();
    }
    @Override
    public void enterOrUpdateSearchInput(String customerNameToOpen){
       webDriverHelper.enterOrUpdateFieldValue(customerNameToOpen, searchInputCustomerListPage);
    }
    @Override
    public void clickSearchButton(){
     searchButtonCustomerListPage.click();
    }

}
