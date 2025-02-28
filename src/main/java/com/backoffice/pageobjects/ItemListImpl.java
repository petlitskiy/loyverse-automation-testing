package com.backoffice.pageobjects;

import com.backoffice.datatypes.Item;
import com.backoffice.provider.TestContextProvider;
import com.backoffice.utilities.WebDriverHelper;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Map;


public class ItemListImpl implements IItemList {

    private final WebDriver localDriver; // create webdriver object
    private final Logger log = LogManager.getLogger(ItemListImpl.class);
    private final WebDriverHelper webDriverHelper;


    // WebElements
    @FindBy(how = How.ID, using = "goods-add-button")
    private WebElement addItemButton;
    @FindBy(how = How.ID, using = "name")
    private WebElement itemNameTextBox;

    @FindBy(how = How.ID, using = "good_edit-category-select")
    private WebElement categoryDropdown;

    @FindBy(how = How.XPATH, using = "//md-radio-button[@id='good_edit-divisible_each-radio']")
    private WebElement soldByEachRadio;
    @FindBy(how = How.XPATH, using = "//md-radio-button[@id='good_edit-divisible_weight-radio']")
    private WebElement soldByWeightRadio;

    @FindBy(how = How.ID, using = "good_edit-track_stock-switch")
    private WebElement trackStockSwitch;

    @FindBy(how = How.ID, using = "good_edit-composite-switch")
    private WebElement compositeItemSwitch;
    @FindBy(how = How.NAME, using = "autocompleteItems")
    private WebElement compositeItemSearchInput;
    @FindBy(how = How.XPATH, using = "//td[@class='checkbox-table-cell padTop']/md-input-container/input")
    private WebElement compositeItemSQuantityInput;

    @FindBy(how = How.XPATH, using = "//div[@class='inline-block']//md-switch")
    private WebElement modifierSwitch;
    @FindBy(how = How.XPATH, using = "//input[@id='good_edit-prime_cost-input']")
    private WebElement costInput;
    @FindBy(how = How.ID, using = "good_edit-prime_cost_complex-input")
    private WebElement compositeItemCostInput;

    @FindBy(how = How.XPATH, using = "//*[@id='edit']/div[2]/div")
    private WebElement descriptionTextArea;
    @FindBy(how = How.XPATH, using = "//input[@title='Price']")
    private WebElement priceInput;
    @FindBy(how = How.ID, using = "good_edit-article-input")
    private WebElement skuInput;
    @FindBy(how = How.ID, using = "good_edit-barcode-input")
    private WebElement barcodeInput;
    @FindBy(how = How.ID, using = "good_edit-in_stock-input-re")
    private WebElement inStockInput;
    @FindBy(how = How.ID, using = "good_edit-low_stock-input-low")
    private WebElement lowStockInput;
    @FindBy(how = How.ID, using = "good_edit-optimal_stock-input")
    private WebElement optimalStockInput;
    @FindBy(how = How.NAME, using = "autocompleteSupplier")
    private WebElement supplierNameInput;
    @FindBy(how = How.ID, using = "variant_edit-add_variant-button")
    private WebElement addVariantButton;
    @FindBy(how = How.ID, using = "variant_dialog-option_name-input")
    private WebElement optionNameInput;
    @FindBy(how = How.ID, using = "variant_dialog-option_value-chips")
    private WebElement optionValueInput;
    @FindBy(how = How.ID, using = "saveButton")
    private WebElement saveOptionButton;
    @FindBy(how = How.ID, using = "good_edit-save-button")
    private WebElement saveItemButton;
    @FindBy(how = How.ID, using = "good_edit-cancel-button")
    private WebElement cancelItemButton;
    @FindBy(how = How.XPATH, using = "//div[@id='goods_edit-tax-0']")
    private WebElement taxNameOnCreatePage;
    @FindBy(how = How.XPATH, using = "//div[@id='goods_edit-tax-0']//following::div[1]")
    private WebElement taxValueOnCreatePage;


    @FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[8]/div//./span")
    private WebElement fetchInStockValue;

    @FindBy(how = How.ID, using = "goods-store-select")
    private WebElement storeDropDown;

    @FindBy(how = How.XPATH, using = "//md-radio-button[@value='img']/div[1]")
    private WebElement imageRadio;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Confirm']")
    private WebElement confirmUploadButton;


    //initialize the page objects of the menu class
    public ItemListImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.localDriver = driver;
        PageFactory.initElements(localDriver, this); // call method
    }

    // Click Save Item Button
    @Override
    public void clickSaveItemBtn() throws InterruptedException {
        ((JavascriptExecutor) localDriver).executeScript("arguments[0].scrollIntoView(true);", saveItemButton);
        saveItemButton.click();
        log.info("Save button is clicked on create Item Page");
    }

    @Override
    public void clickCancelItemBtn() throws InterruptedException {
        ((JavascriptExecutor) localDriver).executeScript("arguments[0].scrollIntoView(true);", cancelItemButton);
        cancelItemButton.click();
        log.info("Cancel button is clicked on create Item Page");
    }


    @Override
    public void verifySaveItemBtnEnabled() {
        ((JavascriptExecutor) localDriver).executeScript("arguments[0].scrollIntoView(true);", saveItemButton);
        webDriverHelper.checkElementIsEnabled(saveItemButton);
        webDriverHelper.verifyElementFocused(saveItemButton);
    }

    @Override
    public void uploadItemImage() throws AWTException, InterruptedException, URISyntaxException {
        Thread.sleep(1000);
        webDriverHelper.clickElement(imageRadio);
        log.info("Image option is selected for representation of item on POS");
        WebElement uploadImageElement = localDriver.findElement(By.xpath("//input[@id='inputFileLogo']"));
        URL resource = ItemListImpl.class.getResource("/testdata/Cake.png");
        String absolutePath = Paths.get(resource.toURI()).toString();
        uploadImageElement.sendKeys(absolutePath);
        webDriverHelper.clickElement(confirmUploadButton);
    }

    // Enter Item Name
    @Override
    public void enterItemName(String itemName) {
        try {
            WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(60000));
            wait.until(ExpectedConditions.elementToBeClickable(itemNameTextBox));
            if (itemNameTextBox.isDisplayed()) {
                itemNameTextBox.sendKeys(itemName);
                log.info("ItemName entered as {}", itemName);
            } else {
                log.info("ItemName textBox not displayed");
            }
        } catch (NoSuchElementException | TimeoutException | ElementNotInteractableException e) {
            log.error("Failed to enter item name: {}", e.getMessage());
        }
    }

    @Override
    public void clickItemNameInput() {
        itemNameTextBox.click();
        costInput.click();
        log.info("Item name field was clicked, and focus is lost.");
    }


    // Click Add Item Button
    @Override
    public void clickAddItemBtn() {
        try {
            WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(80));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.id("goods-add-button"))));
            webDriverHelper.clickElementWithJavaScript(addItemButton);
            log.info("Add Item button is clicked");
        } catch (WebDriverException e) {
            log.error("Failed to click Add Item button: {}", e.getMessage());
        }
    }


    // Validate Track Stock
    @Override
    public void validateTrackStock() {
        try {
            boolean isSelected = trackStockSwitch.isSelected();
            boolean isEnabled = trackStockSwitch.isEnabled();

            if (isSelected) {
                log.info("Track Stock is Selected");
            } else {
                log.info("Track Stock is not Selected");
            }
            if (isEnabled) {
                log.info("Track Stock is Enabled");
            } else {
                log.info("Track Stock is not Enabled");
            }
        } catch (NoSuchElementException e) {
            log.error("Failed to validate Track Stock: {}", e.getMessage());
        }
    }

    // Empty Cost Field
    @Override
    public void emptyCostField() {
        try {
            String costValue = costInput.getAttribute("value");
            if (StringUtils.isNotBlank(costValue)) {
                webDriverHelper.clearInputByKeyboardAction(costInput);
                priceInput.click();
                log.info("The cost field is cleared at run time");
            } else {
                log.info("Cost field is already cleared");
            }
        } catch (NoSuchElementException e) {
            log.error("Failed to empty Cost field: {}", e.getMessage());
        }
    }


    // Enter Cost Value
    @Override
    public void enterCostValue(String costValue) throws InterruptedException {
        costInput.click();
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        costInput.sendKeys(costValue);
        log.info("The cost value entered is: {}", costValue);

    }

    // Verify Cost Value
    @Override
    public void verifyCostValue(String expectedCostValue) {
        priceInput.click();
        String actualCostValue = costInput.getAttribute("value");
        log.info("Actual Cost Value is: {}", actualCostValue);
        log.info("Expected Cost Value is: {}", expectedCostValue);
        Assert.assertEquals(expectedCostValue, actualCostValue);
    }

    // Select Category
    @Override
    public void selectCategory(String categoryName) {
        categoryDropdown.click();
        localDriver.findElement(By.xpath("//*[contains(text(),'" + categoryName + "')]")).click();
    }

    // Enter Price Value
    @Override
    public void enterPriceValue(String priceValue) {
        String getPriceState = priceInput.getAttribute("value");
        if (getPriceState == null || getPriceState.isEmpty()) {
            priceInput.click();
            priceInput.sendKeys(priceValue);
            log.error("Price  is entered as: {}", priceValue);
        } else {
            priceInput.clear();
            priceInput.sendKeys(priceValue);
            log.info("Price is updated  as: {}", priceValue);
        }
    }


    // Enter SKU Value
    @Override
    public void enterSkuValue(String skuValue) {
        skuInput.clear();
        skuInput.sendKeys(skuValue);
        log.info("SKU is entered as: {}", skuValue);
    }

    // Enter Barcode
    @Override
    public void enterBarcode(String barcode) {
        barcodeInput.clear();
        barcodeInput.sendKeys(barcode);
        log.info("Barcode is entered as: {}", barcode);
    }


    // Enter Description
    @Override
    public void enterDescription(String itemDescription) {
        descriptionTextArea.click();
        descriptionTextArea.sendKeys(itemDescription);
        log.info("Item Description is entered as: {}", itemDescription);

    }


    // Enable Track Stock Switch
    @Override
    public void enableTrackStockSwitch() {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.elementToBeClickable(trackStockSwitch));
        webDriverHelper.clickElementWithJavaScript(trackStockSwitch);
        log.info("Track Stock Switch is enabled");

    }

    @Override
    public void toggleTrackStockSwitch(String trackStockSwitchStatus) {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.elementToBeClickable(trackStockSwitch));
        String getTrackStockSwitchStatus = trackStockSwitch.getAttribute("aria-checked");
        if (trackStockSwitchStatus.equals("enabled")) {
            if (getTrackStockSwitchStatus.equals("false")) {
                webDriverHelper.clickElementWithJavaScript(trackStockSwitch);
                log.info("Track Stock Switch is enabled");
            } else {
                log.info("Track Stock Switch is already enabled");
            }
        } else if (trackStockSwitchStatus.equals("disabled")) {
            if (getTrackStockSwitchStatus.equals("true")) {
                webDriverHelper.clickElementWithJavaScript(trackStockSwitch);
                log.info("Track Stock Switch is disabled");
            } else {
                log.info("Track Stock Switch is already disabled");
            }
        }
    }

    // Enter InSTock
    @Override
    public void enterInStock(String inStockValue) {
        inStockInput.clear();
        inStockInput.sendKeys(inStockValue);
        log.info("In Stock Value is entered as {}", inStockValue);
    }

    // Enter LowStock
    @Override
    public void enterLowStock(String lowStockValue) {
        lowStockInput.sendKeys(lowStockValue);
        log.info("Low Stock Value is entered as {}", lowStockValue);
    }

    @Override
    public void enterOptimalStock(String optimalStockValue) {
        optimalStockInput.sendKeys(optimalStockValue);
        log.info("Optimal Stock Value is entered as {}", optimalStockValue);
    }

    @Override
    public void enterSupplier(String supplierName) {
        if (supplierName == null || supplierName.isEmpty()) {
            log.info("Supplier Name is not passed");
        } else {
            webDriverHelper.scrollToElement(supplierNameInput);
            supplierNameInput.click();
            localDriver.findElement(By.xpath("//*[contains(text(),'" + supplierName + "')]")).click();
            log.info("Supplier Name is entered as {}", supplierName);
        }
    }

    @Override
    //Click ModifierSwitch
    public void toggleModifierSwitch(String modifierStatus) {
        if (modifierStatus.equals("enabled")) {
            modifierSwitch.click();
            log.info("Modifier switch is enabled on create new item page");
        } else {
            log.info("Modifier switch is disabled by default on create new item page");
        }

    }


/*

    @Override
    //Verify Tax name and Value included on create item page
    public void verifyTaxNameAndValue(String expectedIncludedTaxNameAndValue) {
        String taxName = webDriverHelper.getTextWithJavaScript(taxNameOnCreatePage);
        String taxValue = webDriverHelper.getTextWithJavaScript(taxValueOnCreatePage);
        String actualTaxNameAndValue = taxName + taxValue;
        log.info("Tax is present as {} on create item page", expectedIncludedTaxNameAndValue);
        Assert.assertEquals(expectedIncludedTaxNameAndValue, actualTaxNameAndValue);
    }
*/

    //Click Add variant button
    @Override
    public void clickAddVariant() {
        addVariantButton.click();
        log.info("Add Variant button is clicked");
    }


    //Click Add variant button
    @Override
    public void enterOptionName(String optionName) {
        optionNameInput.sendKeys(optionName);
        log.info("Option Name is entered as {}", optionName);
    }

    @Override
    public void enterOptionValues(String variantType1, String variantType2) {
        optionValueInput.sendKeys(variantType1);
        optionValueInput.sendKeys(Keys.RETURN);
        optionValueInput.sendKeys(variantType2);
        optionValueInput.sendKeys(Keys.RETURN);
        saveOptionButton.click();
        log.info("Option Value is entered as {} and {} and saved", variantType1, variantType2);
    }

    @Override
    public void clickCompositeItemSwitch() {
        compositeItemSwitch.click();
        log.info("Composite item switch is enabled on item list page");
    }

    @Override
    public void clickCompositeItemSearch() {
        compositeItemSearchInput.click();
        log.info("Composite item search is clicked");
    }

    @Override
    public void updateCompositeItem(String quantity, String compositeItemSearch) {
        localDriver.findElement(By.xpath("//span[contains(text(),'" + compositeItemSearch + "')]")).click();
        String quantityValue = compositeItemSQuantityInput.getAttribute("value");
        if (StringUtils.isNotBlank(quantityValue)) {
            webDriverHelper.clearInputByKeyboardAction(compositeItemSQuantityInput);
            log.info("The quantity input for composite search item is cleared at run time");
        } else {
            log.info("The quantity input for composite search item is already cleared");
        }
        compositeItemSQuantityInput.sendKeys(quantity);
        log.info("Composite item details are updated");
    }

    @Override
    public void verifyTotalCostOfCompositeItem() {
        String compositeItemCostVale = compositeItemCostInput.getAttribute("value");
        String totalCostForCompositeComponent = localDriver.findElement(By.xpath("//td//input[@aria-label='total cost']")).getAttribute("value");
        Assert.assertEquals("Total cost for composite item and search component is same ", compositeItemCostVale, totalCostForCompositeComponent);
        log.info("Composite Item Cost and total cost for composite component is {} and {} ", compositeItemCostVale, totalCostForCompositeComponent);
    }

    @Override
    public void enterStoreDetails(DataTable storeDetailsTable) {
        List<Map<String, String>> storeList = storeDetailsTable.asMaps(String.class, String.class);
        // Locate 'store table on create item page' table
        WebElement storeListTable = localDriver.findElement(By.xpath("//table[@class='checkbox-table variations-add-variant-stores-table stores-table-container']//tbody"));
        //Get all web elements by tag name 'tr'
        List<WebElement> getRows = storeListTable.findElements(By.tagName("tr"));
        int totalRowCount = getRows.size();
        for (int i = 0; i < totalRowCount; i++) {
            Map<String, String> tableMap = storeList.get(i);
            //Get each row's column values by tag name
            List<WebElement> getColumns = getRows.get(i).findElements(By.tagName("td"));
            int totalColumnCount = getColumns.size();
            int tmp = i;
            tmp = tmp + 1;

            //Loop through each column
            for (int j = 3; j <= totalColumnCount; j++) {
                WebElement element =
                        localDriver.findElement(By.xpath("//table[@class='checkbox-table variations-add-variant-stores-table stores-table-container']//tbody//tr[" + tmp + "]//td[" + j + "]//.//input"));
                webDriverHelper.clearInputByKeyboardAction(element);
                if (j == 3) {
                    element.sendKeys(tableMap.get("Price"));
                } else if (j == 4) {
                    element.sendKeys(tableMap.get("InStock"));
                } else if (j == 5) {
                    element.sendKeys(tableMap.get("LowStock"));
                }

            }
        }
    }


    @Override
    public void fetchAndValidateInStockValue(String expectedUpdatedInStockValue) {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(160));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr[1]/td[8]/div//./span"))));
        String actualUpdatedInStockValue = fetchInStockValue.getText();
        Assert.assertEquals("Expected InStock Value for store is ", expectedUpdatedInStockValue, actualUpdatedInStockValue);
    }

    @Override
    public void selectTheStore(String expectedStoreName) {
        webDriverHelper.clickElementWithJavaScript(localDriver.findElement(By.id("goods-store-select")));
        localDriver.findElement(By.xpath("//div[@class='lv-select-vr__item' and contains(text(),'" + expectedStoreName + "')]")).click();
        log.info("Store is selected as {} on Item list page", expectedStoreName);
    }

    @Override
    public void createNewItem(DataTable newItemDetails) throws InterruptedException {
        List<Map<String, String>> items = newItemDetails.asMaps(String.class, String.class);
        Item item = new Item();

        for (Map<String, String> itemsMap : items) {

            String price = itemsMap.get("Price");
            String cost = itemsMap.get("Cost");
            String sku = itemsMap.get("SKU");
            String barcode = itemsMap.get("Barcode");
            String name = itemsMap.get("ItemName");
            String category = itemsMap.get("Category");
            String description = itemsMap.get("Description");
            String trackStockSwitch = itemsMap.get("TrackStockSwitch");
            String inStock = itemsMap.get("InStock");
            String lowStock = itemsMap.get("LowStock");
            String modifierIcon = itemsMap.get("ModifierIcon");

            item.setPrice(price);
            item.setCost(cost);
            item.setSku(sku);
            item.setBarCode(barcode);
            item.setName(name);
            item.setCategory(category);
            item.setDescription(description);
            item.setTrackStockSwitch(trackStockSwitch);
            item.setInStock(inStock);
            item.setLowStock(lowStock);
            item.setModifierIcon(modifierIcon);

            TestContextProvider.getContext().scenarioContext.setContext("itemData", item);

            enterItemName(name);
            selectCategory(category);
            enterDescription(description);
            enterPriceValue(price);
            emptyCostField();
            enterCostValue(cost);
            enterSkuValue(sku);
            enterBarcode(barcode);
            toggleTrackStockSwitch(trackStockSwitch);
            enterInStock(inStock);
            enterLowStock(lowStock);
            toggleModifierSwitch(modifierIcon);


        }

    }


}

