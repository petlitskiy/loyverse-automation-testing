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

/**
 * @author Neha Kharbanda
 */
public class TransferOrderImpl implements ITransferOrder {

    private final WebDriverHelper webDriverHelper;
    private final Logger log = LogManager.getLogger(TransferOrderImpl.class);
    WebDriver driver;
    @FindBy(how = How.ID, using = "lv_menu_item_inventory__inventory.transfer")
    private WebElement transferOrderOption;

    @FindBy(how = How.ID, using = "transfer_orders__add_transfer_btn")
    private WebElement addTransferOrderButton;

    @FindBy(how = How.ID, using = "inventory-transfer_edit-source_store-select")
    private WebElement sourceStoreDropdown;

    @FindBy(how = How.ID, using = "inventory-transfer_edit-destination_store-select")
    private WebElement destinationStoreDropdown;

    @FindBy(how = How.ID, using = "inventory-transfer_edit-item_quantity-input-0")
    private WebElement itemQuantityInput;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Search item']")
    private WebElement searchItemInput;

    @FindBy(how = How.ID, using = "inventory-transfer_edit-create-button")
    private WebElement createTransferOrderButton;

    @FindBy(how = How.XPATH, using = "//span[@translate='INVENTORY.IN_TRANSIT']")
    private WebElement fetchTransferOrderStatusText;

    @FindBy(how = How.XPATH, using = "//div[@class='order-number']")
    private WebElement fetchTransferOrderNumText;

    @FindBy(how = How.ID, using = "inventory-transfer_details-receive-button")
    private WebElement receiveTransferOrderButton;

    @FindBy(how = How.XPATH, using = "//button[@class='md-flat md-button md-green-theme-theme md-ink-ripple primary']")
    private WebElement confirmationReceiveButton;

    @FindBy(how = How.ID, using = "inventory-transfer_details-back-button")
    private WebElement transferOrderButton;


    @FindBy(how = How.XPATH, using = "//tbody")
    private WebElement transferOrderWebTable;

    public TransferOrderImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // call method
    }

    @Override
    public void selectTransferOrderOption() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
            wait.until(ExpectedConditions.elementToBeClickable(transferOrderOption));
            transferOrderOption.click();
            log.info("Transfer Order Option under Inventory Management section is clicked");
        } catch (Exception e) {
            log.error("Failed to click on Transfer Order Option: {}", e.getMessage());
        }
    }

    @Override
    public void clickAddTransferOrderButton() {
        webDriverHelper.clickElementWithJavaScript(addTransferOrderButton);
        log.info("Add Transfer Order is clicked on Transfer orders page");
    }

    @Override
    public void enterSourceStore(String sourceStore) {
        sourceStoreDropdown.click();
        WebElement sourceStoreName = driver.findElement(By.xpath("//md-content[@aria-label='Source store']//div[normalize-space()='" + sourceStore + "']"));
        webDriverHelper.waitUntilElementIsClickable(sourceStoreName);
        sourceStoreName.click();
        log.info("Source Store is entered as {}", sourceStore);
    }

    @Override
    public void enterDestinationStore(String destinationStore) {
        destinationStoreDropdown.click();
        WebElement destinationSourceStore = driver.findElement(By.xpath("//md-content[@aria-label='Destination store']//div[normalize-space()='" + destinationStore + "']"));
        webDriverHelper.waitUntilElementIsClickable(destinationSourceStore);
        destinationSourceStore.click();
        log.info("Destination Store is entered as {}", destinationStore);
    }

    @Override
    public void selectItemForTransferOrder(String transferOrderItem) throws InterruptedException {
        if (searchItemInput.isDisplayed()) {
            webDriverHelper.highlightElement(searchItemInput);
            webDriverHelper.clickWithActionsClass(searchItemInput);
            Thread.sleep(500);
            WebElement item = driver.findElement(By.xpath("//*[contains(text(),'" + transferOrderItem + "')]"));
            if (item.isDisplayed()) {
                webDriverHelper.clickElementWithJavaScript(item);
                log.info("Item selected for transfer order is {}", transferOrderItem);
            } else {
                log.error("Transfer Order Item not present");
            }
        } else {
            log.error("search Item Input not clicked");
        }

    }

    @Override
    public void enterItemQuantity(String orderItemQuantity) {
        itemQuantityInput.sendKeys(orderItemQuantity);
        log.info("Quantity entered for item to transfer is {}", orderItemQuantity);
    }

    @Override
    public void clickCreateTransferOrderButton() {
        webDriverHelper.clickElementWithJavaScript(createTransferOrderButton);
        String fetchTONumber = fetchTransferOrderNumText.getText();
        log.info("Create button is clicked and Transfer order is created as {}", fetchTONumber);
    }

    @Override
    public void validateTransferOrderStatus(String expectedTransferOrderStatus) {
        String actualTransferOrderStatus = fetchTransferOrderStatusText.getText();
        Assert.assertEquals(expectedTransferOrderStatus, actualTransferOrderStatus);
        log.info("TO is created with status {}", actualTransferOrderStatus);
    }

    @Override
    public void clickReceiveTransferOrderButton() {
        receiveTransferOrderButton.click();
        log.info("Receive button is clicked on transfer order details");
    }

    @Override
    public void clickDialogueBoxReceiveButton() {
        webDriverHelper.waitUntilElementIsClickable(confirmationReceiveButton);
        confirmationReceiveButton.click();
        log.info("Transfer Order is received");
    }

    @Override
    public void redirectToTransferOrderListScreen() {
        webDriverHelper.waitUntilElementIsClickable(transferOrderButton);
        webDriverHelper.highlightElement(transferOrderButton);
        webDriverHelper.clickElement(transferOrderButton);
        log.info("Transfer Order button is clicked to redirect to transfer orders list screen");
    }

    @Override
    public void verifyTransferOrderCreated(String expectedTransferOrderStatus) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        try {
            // Wait until at least one row in the transfer orders table is visible
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr"))));
            // Get all rows from the transfer orders table
            List<WebElement> rows = transferOrderWebTable.findElements(By.tagName("tr"));
            boolean statusFound = false;

            for (WebElement row : rows) {
                String actualTransferOrderStatus = row.findElement(By.xpath(".//div[@class='gray-text']")).getText();

                if (expectedTransferOrderStatus.equals(actualTransferOrderStatus)) {
                    statusFound = true;
                    log.info("Transfer order status '{}' matches the expected status '{}'.", actualTransferOrderStatus, expectedTransferOrderStatus);
                    break;
                }
            }

            if (!statusFound) {
                log.error("Expected transfer order status '{}' not found in the list.", expectedTransferOrderStatus);
                throw new RuntimeException("Expected transfer order status '" + expectedTransferOrderStatus + "' not found in the list.");
            }

        } catch (Exception e) {
            log.error("An error occurred while verifying the transfer order status: {}", e.getMessage());
            throw new RuntimeException("An error occurred while verifying the transfer order status", e);
        }
    }

}
