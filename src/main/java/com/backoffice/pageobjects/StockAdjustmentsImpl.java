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
public class StockAdjustmentsImpl implements IStockAdjustments {

    private final WebDriverHelper webDriverHelper;
    WebDriver driver;
    private Logger log = LogManager.getLogger(StockAdjustmentsImpl.class);
    @FindBy(how = How.ID, using = "lv_menu_item_inventory__inventory.adjustment")
    private WebElement stockAdjustmentOption;

    @FindBy(how = How.ID, using = "stock_adj__add_adj_btn")
    private WebElement addStockAdjustmentButton;

    @FindBy(how = How.ID, using = "inventory-adjustment_create-reason-select")
    private WebElement reasonDropdown;

    @FindBy(how = How.NAME, using = "autocompleteItems")
    private WebElement searchItemInput;

    @FindBy(how = How.ID, using = "inventory-adjustment_create-item_quantity-input-0")
    private WebElement removeStockInput;

    @FindBy(how = How.ID, using = "inventory-adjustment_create-adjust-button")
    private WebElement adjustButton;

    @FindBy(how = How.XPATH, using = "//div[@class='order-number']")
    private WebElement stockAdjustmentNumberText;

    @FindBy(how = How.ID, using = "inventory-adjust_details-back-button")
    private WebElement allStockAdjustmentsButton;

    @FindBy(how = How.XPATH, using = "//tbody")
    private WebElement stockAdjustmentsWebTable;

    @FindBy(how = How.ID, using = "inventory-adjustment_create-item_quantity-input-0")
    private WebElement addStockInput;

    @FindBy(how = How.ID, using = "inventory-adjustment_create-item_cost-input-0")
    private WebElement costInput;

    @FindBy(how = How.ID, using = "inventory-adjustment_create-item_quantity-input-0")
    private WebElement countStockInput;

    public StockAdjustmentsImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // call method
    }


    @Override
    public void selectStockAdjustmentsOption() {
        try {
            webDriverHelper.waitUntilElementIsClickable(stockAdjustmentOption);
            stockAdjustmentOption.click();
            log.info("Stock Adjustments Option under Inventory Management section is clicked");
        } catch (Exception e) {
            log.error("Failed to click on Stock Adjustments Option: {}", e.getMessage());
        }
    }

    @Override
    public void clickAddStockAdjustmentsButton() {
        webDriverHelper.clickElementWithJavaScript(addStockAdjustmentButton);
        log.info("Add Stock Adjustment is clicked on Stock adjustments page");
    }

    @Override
    public void selectReasonForStockAdjustment(String expectedAdjustmentReason) {
        if (expectedAdjustmentReason == null || expectedAdjustmentReason.isEmpty()) {
            log.info("expected Adjustment Reason is not passed");
        } else {
            reasonDropdown.click();
            WebElement reasonText = driver.findElement(By.xpath("//md-option[@role='option']//span[text()='" + expectedAdjustmentReason + "']"));
            webDriverHelper.waitUntilElementIsClickable(reasonText);
            reasonText.click();
            log.info("Reason for stock adjustment is entered as {}", expectedAdjustmentReason);
        }

    }

    @Override
    public void selectItemForStockAdjustment(String itemToBeAdjusted) throws InterruptedException {
        if (itemToBeAdjusted == null || itemToBeAdjusted.isEmpty()) {
            log.info("Item To Be Adjusted is not passed");
        } else {
            if (searchItemInput.isDisplayed()) {
                webDriverHelper.highlightElement(searchItemInput);
                webDriverHelper.clickWithActionsClass(searchItemInput);
                Thread.sleep(500);
                WebElement itemToAdjust = driver.findElement(By.xpath("//*[contains(text(),'" + itemToBeAdjusted + "')]"));
                if (itemToAdjust.isDisplayed()) {
                    webDriverHelper.clickElementWithJavaScript(itemToAdjust);
                    log.info("Item selected for transfer order is {}", itemToBeAdjusted);
                } else {
                    log.error("Item for Stock Adjustment  not present");
                }
            } else {
                log.error("Search Item Input not clicked");
            }
        }
    }


    @Override
    public void enterRemoveStockQuantity(String removeStockQuantity) {
        if (removeStockQuantity == null || removeStockQuantity.isEmpty()) {
            log.info("Remove Stock Quantity is not passed");
        } else {
            removeStockInput.sendKeys(removeStockQuantity);
            log.info("Quantity entered to remove stock is {}", removeStockQuantity);
        }
    }

    @Override
    public void clickAdjustButton() {
        adjustButton.click();
        log.info("Adjust button is clicked");
    }

    @Override
    public void verifyAdjustmentIsCreated(String expectedAdjustmentReason, String expectedRemoveStockQuantity) {
        String createdStockAdjustmentNo = stockAdjustmentNumberText.getText();
        allStockAdjustmentsButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            // Wait until the stock adjustments table is visible
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr")
            ));
            // Get all rows from the stock adjustments table
            List<WebElement> rows = stockAdjustmentsWebTable.findElements(By.tagName("tr"));
            boolean adjustmentFound = false;
            for (int i = 1; i <= rows.size(); i++) {
                String adjustmentNumberFromList = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/div")).getText();
                if (createdStockAdjustmentNo.equals(adjustmentNumberFromList)) {
                    String actualStockAdjustmentReason = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[3]/div")).getText();
                    String actualRemoveStockQuantity = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[4]/div")).getText();

                    // Verify the details
                    Assert.assertEquals(createdStockAdjustmentNo, adjustmentNumberFromList);
                    Assert.assertEquals(expectedAdjustmentReason, actualStockAdjustmentReason);
                    Assert.assertEquals(expectedRemoveStockQuantity, actualRemoveStockQuantity);

                    log.info("Stock Adjustment order {} is created for reason {} for quantity {}", adjustmentNumberFromList, actualStockAdjustmentReason, expectedRemoveStockQuantity);
                    adjustmentFound = true;
                    break;
                }
            }

            if (!adjustmentFound) {
                log.error("Stock Adjustment order number didn't match on stock adjustment list page and details screen");
                throw new RuntimeException("Stock Adjustment order number didn't match on stock adjustment list page and details screen");
            }

        } catch (Exception e) {
            log.error("An error occurred while verifying the stock adjustment: {}", e.getMessage());
            throw new RuntimeException("An error occurred while verifying the stock adjustment", e);
        }
    }


    @Override
    public void enterAddStockQuantity(String expectedAddStockQuantity) {
        if (expectedAddStockQuantity == null || expectedAddStockQuantity.isEmpty()) {
            log.info("Add Stock Quantity is not passed");
        } else {
            addStockInput.sendKeys(expectedAddStockQuantity);
        }
    }

    @Override
    public void enterCostForSelectedItem(String expectedCost) {
        if (expectedCost == null || expectedCost.isEmpty()) {
            log.info("Cost is not passed");
        } else {
            costInput.sendKeys(expectedCost);
        }
    }

    @Override
    public void enterCountedStockValue(String expectedCountedStock) {
        if (expectedCountedStock == null || expectedCountedStock.isEmpty()) {
            log.info("Cost is not passed");
        } else {
            countStockInput.sendKeys(expectedCountedStock);
        }
    }


}
