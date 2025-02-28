package com.backoffice.pageobjects;


import com.backoffice.utilities.WebDriverHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenuImpl implements IMenu {

    private final WebDriverHelper webDriverHelper;
    private final Logger log = LogManager.getLogger(MenuImpl.class);
    WebDriver localDriver; // create webdriver object
    @FindBy(how = How.ID, using = "lv_menu_item_clients.database_single_href")
    private WebElement customersCategory;
    @FindBy(how = How.XPATH, using = "//span[normalize-space()='Inventory management']")
    private WebElement inventoryManagement;
    @FindBy(how = How.ID, using = "lv_menu_item_inventory_expanded")
    private WebElement inventoryManagementExpanded;
    @FindBy(how = How.ID, using = "inventory-management-start_trial-button")
    private WebElement startFreeTrailButton;
    @FindBy(how = How.ID, using = "subscribe_dialog-start_trial-button")
    private WebElement startFreeTrailDialogButton;
    @FindBy(how = How.ID, using = "lv_menu_item_employees_expanded")
    private WebElement employeesCategory;
    @FindBy(how = How.ID, using = "lv_menu_item_goods__goods.discount")
    private WebElement discountOption;
    @FindBy(how = How.XPATH, using = "//*[@id=\"lv_menu_item_goods_expanded\"]")
    private WebElement itemsCategory;
    @FindBy(how = How.ID, using = "lv_menu_item_goods__goods.price")
    private WebElement itemListOption;
    //    @FindBy(how = How.XPATH, using = "//md-bottom[@aria-label='Settings']//md-icon[@role='img']")
//    private WebElement menuButton; //three horizontal icon button
    @FindBy(how = How.ID, using = "menu-expand__btn")
    private WebElement menuButton; //three horizontal icon button

    @FindBy(how = How.XPATH, using = "//div[@class=\"iconbutton\"]/md-bottom/md-icon")
    private WebElement ownerDropdown; // dropdown for accounts page
    @FindBy(how = How.XPATH, using = "//button[@id='my_account_btn']")
    private WebElement accountsButton;
    @FindBy(how = How.XPATH, using = "//div[@id='lv_menu_item_settings.profile_single_href']")
    private WebElement settingsCategory;
    @FindBy(how = How.ID, using = "sign_out_btn")
    private WebElement signOutButton;

    // Initialize the page objects of the menu class
    public MenuImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.localDriver = driver;
        PageFactory.initElements(localDriver, this); // call method
    }

    /**
     * Clicks on the items category.
     */
    @Override
    public void clickItemsCategory() {
        try {
            WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(120));
            wait.until(ExpectedConditions.elementToBeClickable(itemsCategory));
            itemsCategory.click();
            log.info("Items category is clicked");
        } catch (TimeoutException e) {
            log.error("Failed to click on item category {}", e.getMessage());
        }
    }


    /**
     * Clicks on the customers category.
     */
    @Override
    public void clickCustomersCategory() {
        customersCategory.click();
        log.info("Customers category is clicked");
    }

    /**
     * Clicks on the employees category.
     */
    @Override
    public void clickEmployeesCategory() {
//        // Wait for the page to be fully loaded
//        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(60));
//        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
//        // Locate the element after ensuring the page is fully loaded
//        employeesCategory = wait.until(ExpectedConditions.elementToBeClickable(By.id("lv_menu_item_employees_expanded")));
        WebElement employeesCategory = webDriverHelper.fluentWait(By.id("lv_menu_item_employees_expanded"));
        employeesCategory.click();
        log.info("Employees category is clicked");
    }

    /**
     * Clicks on the settings category.
     *
     * @throws InterruptedException if interrupted during the wait.
     */
    @Override
    public void clickSettingsCategory() {
        try {
            Thread.sleep(1000);
            webDriverHelper.clickElementWithJavaScript(settingsCategory);
            log.info("Settings category is clicked");
        } catch (Exception e) {
            log.error("Failed to click on settings category {}", e.getMessage());
        }
    }

    /**
     * Selects the item list option.
     */
    @Override
    public void selectItemListOption() {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(200));
        wait.until(ExpectedConditions.elementToBeClickable(itemListOption));
        itemListOption.click();
        log.info("Item List Option is clicked");
    }


    /**
     * Clicks on the menu button.
     */
    @Override
    public void clickMenuButton() {
        try {
            log.info("start Menu button is clicked");
            localDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
            WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(120));
            wait.until(ExpectedConditions.elementToBeClickable(menuButton));
            webDriverHelper.clickElementWithJavaScript(menuButton);
            log.info("end Menu button is clicked");
        } catch (NoSuchElementException | TimeoutException | ElementNotInteractableException e) {
            log.error("Failed to click on menu button: {}", e.getMessage());
        }
    }

    /**
     * Clicks on the owner icon.
     */
    @Override
    public void clickOwnerIcon() {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.elementToBeClickable(ownerDropdown));
        ownerDropdown.click();
        log.info("Owner icon clicked");
    }


    /**
     * Clicks on the accounts button.
     */
    @Override
    public void clickAccountButton() {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.elementToBeClickable(accountsButton));
        accountsButton.click();
        log.info("Accounts button clicked");
    }

    @Override
    public void clickInventoryManagement() throws InterruptedException {
        Thread.sleep(1000);
        webDriverHelper.clickElementWithJavaScript(inventoryManagement);
        log.info("Inventory Management is clicked");
    }

    @Override
    public void clickInventoryManagementExpanded() {
        webDriverHelper.highlightElement(inventoryManagementExpanded);
        webDriverHelper.clickElementWithJavaScript(inventoryManagementExpanded);
        log.info("Inventory Management expanded is clicked");
    }


    @Override
    public void startFreeInventoryTrail() {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(startFreeTrailButton));
        webDriverHelper.clickWithActionsClass(startFreeTrailButton);
        startFreeTrailDialogButton.click();
        wait.until(ExpectedConditions.urlContains("dashboard/#/inventory/purchase"));
        log.info("Advanced Inventory Management 14 days free trial is started");
    }

    @Override
    public void clickSignOutButton() {
        try {
            signOutButton.click();
            log.info("Sign out button is clicked");
        } catch (ElementNotInteractableException e) {
            log.error("Element not interactable: {}", e.getMessage());
        } catch (NoSuchElementException e) {
            log.error("Element not found: {}", e.getMessage());
        } catch (StaleElementReferenceException e) {
            log.error("Stale element reference: {}", e.getMessage());
        }
    }


}
