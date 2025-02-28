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

/**
 * @author Neha Kharbanda
 */
public class PurchaseOrderImpl implements IPurchaseOrder {

    private final WebDriverHelper webDriverHelper;
    WebDriver driver;
    private Logger log = LogManager.getLogger(PurchaseOrderImpl.class);
    @FindBy(how = How.ID, using = "lv_menu_item_inventory__inventory.purchase")
    private WebElement purchaseOrderOption;

    @FindBy(how = How.ID, using = "purchase_orders__add_purchase_btn")
    private WebElement addPurchaseOrderButton;

    @FindBy(how = How.NAME, using = "autocompleteSupplier")
    private WebElement addSupplierInput;

    @FindBy(how = How.XPATH, using = "//*[@id='endDateInput']//md-icon[@aria-label='date-picker']")
    private WebElement expectedOnDateInput;

    @FindBy(how = How.XPATH, using = "//div[@class='btn-forward']//md-icon[@aria-label='calendar']")
    private WebElement calenderForwardIcon;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'06')]")
    private WebElement selectDateIcon;

    @FindBy(how = How.CSS, using = "date-picker-input[id='endDateInput'] div[class='date-picker-label-template']")
    private WebElement fetchExpectedOnDate;

    @FindBy(how = How.ID, using = "inventory-order_create-notes-textarea")
    private WebElement purchaseNoteTextArea;

    @FindBy(how = How.NAME, using = "autocompleteItems")
    private WebElement searchItemInput;

    @FindBy(how = How.XPATH, using = "//span[@class='purchase-order-item-name purchase-order-autocomplete-item-name']")
    private WebElement selectItem;

    @FindBy(how = How.XPATH, using = "//input[@name='itemCount']")
    private WebElement purchaseItemQuantityInput;

    @FindBy(how = How.XPATH, using = "//input[@name='itemCost']")
    private WebElement purchaseItemCostInput;

    @FindBy(how = How.ID, using = "inventory-order_create-total-input")
    private WebElement totalCostForOrder;

    @FindBy(how = How.ID, using = "inventory-order_create-create-button")
    private WebElement createPurchaseOrderButton;
    @FindBy(how = How.ID, using = "inventory-order_detail-receive-button")
    private WebElement receivePurchaseOrderButton;

    @FindBy(how = How.ID, using = "inventory-order_receive-item_to_receive-input-0")
    private WebElement toReceiveQuantityInput;

    @FindBy(how = How.ID, using = "inventory-receive_order-receive-button")
    private WebElement receiveItemsButton;

    @FindBy(how = How.ID, using = "inventory-order_detail-back-button")
    private WebElement purchaseOrderButton;
    @FindBy(how = How.ID, using = "inventory_order_detail__number")
    private WebElement purchaseOrderNumberText;

    @FindBy(how = How.XPATH, using = "//div[@class='purchase-information']/div/div[1]//./div/span")
    private WebElement purchaseOrderStatusText;

    @FindBy(how = How.XPATH, using = "//div[@class='order-detail']/div[3]/div/div[1]/div[1]/div")
    private WebElement creationPODateText;

    @FindBy(how = How.XPATH, using = "//div[@class='order-detail']/div[3]/div/div[1]/div[2]/div")
    private WebElement expectedPODateText;

    @FindBy(how = How.XPATH, using = "//div[@class='order-detail']/div[3]/div/div[2]/div/div[2]/div[1]")
    private WebElement purchaseOrderSupplierText;

    @FindBy(how = How.XPATH, using = "//div[@class='order-detail']/div[3]/div/div[3]/div/div/div[1]")
    private WebElement purchaseOrderStoreText;


    public PurchaseOrderImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // call method
    }

    @Override
    public void selectPurchaseOrderOption() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
            wait.until(ExpectedConditions.elementToBeClickable(purchaseOrderOption));
            purchaseOrderOption.click();
            log.info("Purchase Order Option under Inventory Management section is clicked");
        } catch (Exception e) {
            log.error("Failed to click on Transfer Order Option: {}", e.getMessage());
        }
    }

    @Override
    public void clickAddPurchaseOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(addPurchaseOrderButton));
        webDriverHelper.clickElementWithJavaScript(addPurchaseOrderButton);
        log.info("Add Purchase Order is clicked on purchase order page");
    }

    @Override
    public void enterSupplierName(String supplierName) {
        addSupplierInput.click();
        driver.findElement(By.xpath("//*[text()='" + supplierName + "']")).click();
        log.info("Supplier Name is entered as {}", supplierName);
    }

    @Override
    public void enterExpectedDate() {
        expectedOnDateInput.click();
        calenderForwardIcon.click();
        selectDateIcon.click();
        log.info("Expected Purchase date is entered as {}", fetchExpectedOnDate.getText());
    }

    @Override
    public void enterPurchaseOrderNote(String purchaseOrderNote) {
        purchaseNoteTextArea.sendKeys(purchaseOrderNote);
        log.info("Purchase Order Note is entered as {}", purchaseOrderNote);
    }


    @Override
    public void selectItemForPurchase(String purchaseOrderItem) {
        searchItemInput.click();
        selectItem.click();
        log.info("Item selected for purchase order is {}", purchaseOrderItem);
    }

    @Override
    public void enterOrderItemQuantity(String itemOrderQuantity) {
        purchaseItemQuantityInput.sendKeys(itemOrderQuantity);
        log.info("Quantity entered for item to purchase is {}", itemOrderQuantity);
    }

    @Override
    public void enterOrderItemCost(String itemOrderCost) {
        purchaseItemCostInput.sendKeys(itemOrderCost);
        log.info("Cost entered for item to purchase is {}", itemOrderCost);
    }

    @Override
    public void verifyTotalCostOfPurchaseOrder(String expectedTotalCostOfPurchaseOrder) {
        totalCostForOrder.click();
        String actualTotalCostOfPurchaseOrder = totalCostForOrder.getAttribute("value");
        log.info("actualTotalCostOfPurchaseOrder is {}", actualTotalCostOfPurchaseOrder);
        Assert.assertEquals(expectedTotalCostOfPurchaseOrder, actualTotalCostOfPurchaseOrder);

    }

    @Override
    public void clickCreatePurchaseOrderButton() {
        webDriverHelper.clickElementWithJavaScript(createPurchaseOrderButton);
        String fetchPurchaseOrderNumber = purchaseOrderNumberText.getText();
        log.info("Create button is clicked and Purchase order is created as {}", fetchPurchaseOrderNumber);
    }

    @Override
    public void validatePurchaseOrderStatus(String expectedPurchaseOrderStatus) {
        String actualPurchaseOrderStatus = purchaseOrderStatusText.getText();
        Assert.assertEquals(expectedPurchaseOrderStatus, actualPurchaseOrderStatus);
        log.info("PO is created with status {}", actualPurchaseOrderStatus);
    }

    @Override
    public void clickReceivePurchaseOrderButton() {
        receivePurchaseOrderButton.click();
        log.info("Receive button is clicked on purchase order details screen");
    }

    @Override
    public void enterReceivedQuantity(String itemQuantityToBeReceived) {
        toReceiveQuantityInput.sendKeys(itemQuantityToBeReceived);
        log.info("Item count to be received is {}", itemQuantityToBeReceived);
    }

    @Override
    public void clickReceiveItemButton() {
        receiveItemsButton.click();
        log.info("Receive button is clicked on receive item screen");
    }

    @Override
    public void redirectToPurchaseOrdersScreen() {
        String fetchPOCreationDate = creationPODateText.getText();
        String fetchPOExpectedDate = expectedPODateText.getText();
        String fetchSupplierName = purchaseOrderSupplierText.getText();
        String fetchDestinationStoreName = purchaseOrderStoreText.getText();
        log.info("fetchPOCreationDate is {} ", fetchPOCreationDate);
        log.info("fetchPOExpectedDate is {} ", fetchPOExpectedDate);
        log.info("fetchSupplierName is {} ", fetchSupplierName);
        log.info("fetchDestinationStoreName is {} ", fetchDestinationStoreName);
        purchaseOrderButton.click();
        log.info("Purchase Order button is clicked to redirect to Purchase Orders list screen");
    }


}
