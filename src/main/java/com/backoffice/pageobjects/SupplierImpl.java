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

/**
 * @author Neha Kharbanda
 */
public class SupplierImpl implements ISupplier {

    private final WebDriverHelper webDriverHelper;
    WebDriver driver;
    private Logger log = LogManager.getLogger(SupplierImpl.class);
    @FindBy(how = How.ID, using = "lv_menu_item_inventory__inventory.supplierlist")
    private WebElement supplierOption;
    @FindBy(how = How.ID, using = "suppliers__add_supplier_btn")
    private WebElement addSupplier;
    @FindBy(how = How.ID, using = "name")
    private WebElement supplierNameInput;
    @FindBy(how = How.ID, using = "inventory-supplier_edit-contact-input")
    private WebElement supplierContactInput;
    @FindBy(how = How.ID, using = "inventory-supplier_edit-email-input")
    private WebElement supplierEmailInput;
    @FindBy(how = How.ID, using = "inventory-supplier_edit-phone-input")
    private WebElement supplierPhoneInput;
    @FindBy(how = How.ID, using = "inventory-supplier_edit-website-input")
    private WebElement supplierWebsiteInput;
    @FindBy(how = How.ID, using = "inventory-supplier_edit-address_1-input")
    private WebElement supplierAddress1Input;
    @FindBy(how = How.ID, using = "inventory-supplier_edit-address_2-input")
    private WebElement supplierAddress2Input;
    @FindBy(how = How.ID, using = "inventory-supplier_edit-city-input")
    private WebElement supplierCityInput;
    @FindBy(how = How.ID, using = "inventory-supplier_edit-zip_code-input")
    private WebElement supplierZipCodeInput;
    @FindBy(how = How.XPATH, using = "//md-select[@id='inventory-supplier_edit-country-select']")
    private WebElement supplierCountryDropDown;
    @FindBy(how = How.ID, using = "inventory-supplier_edit-region-input")
    private WebElement supplierStateInput;
    @FindBy(how = How.ID, using = "inventory-supplier_edit-notes-textarea")
    private WebElement supplierNotesInput;
    @FindBy(how = How.ID, using = "inventory-supplier_edit-save-button")
    private WebElement saveSupplierButton;


    //initialize the page objects of the Supplier  class
    public SupplierImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // call method
    }

    @Override
    public void selectSupplierOption() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
            wait.until(ExpectedConditions.elementToBeClickable(supplierOption));
            supplierOption.click();
            log.info("Supplier option under Inventory Management section is clicked");
        } catch (Exception e) {
            log.error("Failed to click on Supplier option: {}", e.getMessage());
        }
    }

    @Override
    public void clickAddSupplierButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(supplierOption));
        addSupplier.click();
        log.info("Add supplier button is clicked on suppliers page");
    }

    @Override
    public void enterSupplierName(String supplierName) {
        supplierNameInput.sendKeys(supplierName);
        log.info("Supplier Name is entered as {}", supplierName);
    }

    @Override
    public void enterSupplierContact(String supplierContact) {
        supplierContactInput.sendKeys(supplierContact);
        log.info("Supplier Contact is entered as {}", supplierContact);

    }

    @Override
    public void enterSupplierEmail(String supplierEmail) {
        supplierEmailInput.sendKeys(supplierEmail);
        log.info("Supplier Email is entered as {}", supplierEmail);

    }

    @Override
    public void enterSupplierPhone(String supplierPhone) {
        supplierPhoneInput.sendKeys(supplierPhone);
        log.info("Supplier Email is entered as {}", supplierPhone);

    }

    @Override
    public void enterSupplierWebsite(String supplierWebsite) {
        supplierWebsiteInput.sendKeys(supplierWebsite);
        log.info("Supplier Website is entered as {}", supplierWebsite);

    }

    @Override
    public void enterSupplierAddress1(String supplierAddress1) {
        supplierAddress1Input.sendKeys(supplierAddress1);
        log.info("Supplier Address1 is entered as {}", supplierAddress1);

    }

    @Override
    public void enterSupplierAddress2(String supplierAddress2) {
        supplierAddress2Input.sendKeys(supplierAddress2);
        log.info("Supplier Address2 is entered as {}", supplierAddress2);

    }

    @Override
    public void enterSupplierCity(String supplierCity) {
        supplierCityInput.sendKeys(supplierCity);
        log.info("Supplier City is entered as {}", supplierCity);

    }

    @Override
    public void enterSupplierZipCode(String supplierZipCode) {
        supplierZipCodeInput.sendKeys(supplierZipCode);
        log.info("Supplier ZipCode is entered as {}", supplierZipCode);

    }

    @Override
    public void enterSupplierCountry(String supplierCountry) {

        webDriverHelper.clickElementWithJavaScript(supplierCountryDropDown);
        webDriverHelper.scrollToElement(driver.findElement(By.xpath("//*[text()='" + supplierCountry + "']")));
        driver.findElement(By.xpath("//*[text()='" + supplierCountry + "']")).click();
        log.info("Supplier country is entered as {}", supplierCountry);
    }

    @Override
    public void enterSupplierRegion(String supplierRegion) {
        supplierStateInput.sendKeys(supplierRegion);
        log.info("Supplier Region is entered as {}", supplierRegion);

    }

    @Override
    public void enterSupplierNote(String supplierNote) {
        supplierNotesInput.sendKeys(supplierNote);
        log.info("Supplier Note is entered as {}", supplierNote);

    }

    @Override
    public void clickSaveSupplier() {
        webDriverHelper.clickElementWithJavaScript(saveSupplierButton);
        log.info("Save button is clicked at create supplier page");
    }

    @Override
    public void verifySaveSupplierBtnEnabled() {
        webDriverHelper.checkElementIsEnabled(saveSupplierButton);
    }


}
