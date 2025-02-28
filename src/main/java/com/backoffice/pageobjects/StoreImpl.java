package com.backoffice.pageobjects;

import com.backoffice.utilities.WebDriverHelper;
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
import java.util.List;

import static org.junit.Assert.fail;

/**
 * @author Neha Kharbanda
 */
public class StoreImpl implements IStore {

    private final Logger log = LogManager.getLogger(StoreImpl.class);
    private final WebDriverHelper webDriverHelper;
    WebDriver driver;
    @FindBy(how = How.NAME, using = "outlet")
    private WebElement storesOption;

    @FindBy(how = How.ID, using = "stores-add-button")
    private WebElement addStoreButton;

    @FindBy(how = How.ID, using = "name")
    private WebElement storeNameInput;

    @FindBy(how = How.ID, using = "store_edit-address-input")
    private WebElement storeAddressInput;

    @FindBy(how = How.ID, using = "store_edit-city-input")
    private WebElement storeCityInput;

    @FindBy(how = How.ID, using = "store_edit-state-input")
    private WebElement storeStateInput;

    @FindBy(how = How.ID, using = "store_edit-zip_code-input")
    private WebElement storeZipCodeInput;

    @FindBy(how = How.ID, using = "store_edit-country-select")
    private WebElement storeCountryDropdown;

    @FindBy(how = How.ID, using = "store_edit-phone-input")
    private WebElement storePhoneInput;

    @FindBy(how = How.XPATH, using = "//*[@name='vat']")
    private WebElement storeVatNumberInput;

    @FindBy(how = How.ID, using = "store_edit-description-input")
    private WebElement storeDescriptionInput;

    @FindBy(how = How.ID, using = "store_edit-save-button")
    private WebElement storeSaveButton;

    @FindBy(how = How.XPATH, using = "//*[@aria-label=' ']")
    private WebElement trashButton;

    @FindBy(how = How.XPATH, using = "//*[normalize-space()='Confirm']")
    private WebElement confirmDeleteButton;


    public StoreImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // call method
    }

    @Override
    public void selectStoresOption() {
        webDriverHelper.clickElementWithJavaScript(storesOption);
        log.info("Stores option under settings section is clicked");
    }

    @Override
    public void clickAddStoreButton() {
        addStoreButton.click();
        log.info("Add store button is clicked");
    }


    @Override
    public void enterOrUpdateStoreName(String storeName) {
        String fetchStoreName = storeNameInput.getAttribute("value");
        if (fetchStoreName == null || fetchStoreName.isEmpty()) {
            storeNameInput.sendKeys(storeName);
            log.info("Store name is entered as {}", storeName);
        } else {
            storeNameInput.clear();
            storeNameInput.sendKeys(storeName);
            log.info("Store name is updated  as: {}", storeName);
        }
    }

    @Override
    public void enterOrUpdateStoreAddress(String storeAddress) {
        String fetchStoreAddress = storeAddressInput.getAttribute("value");
        if (fetchStoreAddress == null || fetchStoreAddress.isEmpty()) {
            storeAddressInput.sendKeys(storeAddress);
            log.info("Store address is entered as {}", storeAddress);
        } else {
            storeAddressInput.clear();
            storeAddressInput.sendKeys(storeAddress);
            log.info("Store address is updated  as: {}", storeAddress);
        }

    }

    @Override
    public void enterOrUpdateStoreCity(String storeCity) {
        String fetchStoreCity = storeCityInput.getAttribute("value");
        if (fetchStoreCity == null || fetchStoreCity.isEmpty()) {
            storeCityInput.sendKeys(storeCity);
            log.info("Store city is entered as {}", storeCity);
        } else {
            storeCityInput.clear();
            storeCityInput.sendKeys(storeCity);
            log.info("Store city is updated  as: {}", storeCity);
        }
    }

    @Override
    public void enterOrUpdateStoreState(String storeState) {
        String fetchStoreState = storeStateInput.getAttribute("value");
        if (fetchStoreState == null || fetchStoreState.isEmpty()) {
            storeStateInput.sendKeys(storeState);
            log.info("Store state is entered as {}", storeState);
        } else {
            storeStateInput.clear();
            storeStateInput.sendKeys(storeState);
            log.info("Store state is updated  as: {}", storeState);
        }
    }


    @Override
    public void enterOrUpdateStoreZipcode(String storeZipcode) {
        String fetchStoreZipcode = storeZipCodeInput.getAttribute("value");
        if (fetchStoreZipcode == null || fetchStoreZipcode.isEmpty()) {
            storeZipCodeInput.sendKeys(storeZipcode);
            log.info("Store zipCode is entered as {}", storeZipcode);
        } else {
            storeZipCodeInput.clear();
            storeZipCodeInput.sendKeys(storeZipcode);
            log.info("Store zipCode is updated  as: {}", storeZipcode);
        }
    }


    @Override
    public void enterOrUpdateStoreCountry(String storeCountry) {
        if (driver.findElement(By.xpath("//*[@id='store_edit-country-select']//span/div/div/span")).getText().equals(storeCountry)) {
            log.info("Store country is already selected as {}", storeCountry);
        } else {
            storeCountryDropdown.click();
            driver.findElement(By.xpath("//md-option[@role='option']/div/div/span[contains(text(),' " + storeCountry + "')]")).click();
            log.info("Store country is entered as {}", storeCountry);
        }
    }


    @Override
    public void enterOrUpdateStorePhone(String storePhone) {
        String fetchStorePhone = storePhoneInput.getAttribute("value");
        if (fetchStorePhone == null || fetchStorePhone.isEmpty()) {
            storePhoneInput.sendKeys(storePhone);
            log.info("Store phone is entered as {}", storePhone);
        } else {
            storePhoneInput.clear();
            storePhoneInput.sendKeys(storePhone);
            log.info("Store phone is updated  as: {}", storePhone);
        }
    }

    @Override
    public void enterOrUpdateVatNumber(String storeVatNumber) {
        String fetchStoreVatNumber = storeVatNumberInput.getAttribute("value");
        if (fetchStoreVatNumber == null || fetchStoreVatNumber.isEmpty()) {
            storeVatNumberInput.sendKeys(storeVatNumber);
            log.info("Store Vat Number is entered as {}", storeVatNumber);
        } else {
            storeVatNumberInput.clear();
            storeVatNumberInput.sendKeys(storeVatNumber);
            log.info("Store Vat Number is updated as {}", storeVatNumber);
        }
    }

    @Override
    public void enterOrUpdateStoreDescription(String storeDescription) {
        String fetchStoreDescription = storeDescriptionInput.getAttribute("value");
        if (fetchStoreDescription == null || fetchStoreDescription.isEmpty()) {
            storeDescriptionInput.sendKeys(storeDescription);
            log.info("Store description is entered as {}", storeDescription);
        } else {
            storeDescriptionInput.clear();
            storeDescriptionInput.sendKeys(storeDescription);
            log.info("Store description is updated as {}", storeDescription);
        }
    }

    @Override
    public void clickSaveStore() {
        webDriverHelper.scrollToElement(storeSaveButton);
        storeSaveButton.click();
        log.info("Save button is clicked at create store page");
    }

    @Override
    public void clickTrashButton() {
        trashButton.click();
        log.info("Trash  button is clicked at edit store page");
    }

    @Override
    public void clickConfirmDelete() {
        confirmDeleteButton.click();
        log.info("Confirm Delete button is clicked at edit store page");
    }

    @Override
    public void verifyStoreIsCreatedOnListPage(String expectedStoreName, String expectedStoreAddress, String expectedStoreNumberOfPos) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody")));

        // Locate all rows in the Store table
        List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));
        int rowCount = rows.size();
        System.out.println("No of rows in this table : " + rowCount);
        boolean storeNameFound = false;
        for (int i = 1; i <= rowCount; i++) {
            String actualStoreName = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[1]/div")).getText();
            if (actualStoreName.equals(expectedStoreName)) {
                storeNameFound = true;
                // Verify the entire row data
                String actualStoreAddress = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[2]/div")).getText();
                String actualStoreNumberOfPos = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[3]/div")).getText();
                Assert.assertEquals(actualStoreName, expectedStoreName);
                Assert.assertEquals(actualStoreAddress, expectedStoreAddress);
                Assert.assertEquals(actualStoreNumberOfPos, expectedStoreNumberOfPos);
                break;
            }
        }

        if (!storeNameFound) {
            fail("Store with name '" + expectedStoreName + "' not found in the table");
        }
        } catch (Exception e) {
            log.error("An error occurred while verifying the tax from the list: ", e);
            throw new RuntimeException("Test case failed due to exception: " + e.getMessage(), e);
        }
    }

}
