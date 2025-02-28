package com.backoffice.stepdefinitions;

import com.backoffice.config.DriverFactory;
import com.backoffice.config.GsonGenerator;
import com.backoffice.core.model.converters.ToItemConverter;
import com.backoffice.core.model.converters.ToSupplierConverter;
import com.backoffice.datatypes.Item;
import com.backoffice.datatypes.Stores;
import com.backoffice.datatypes.Supplier;
import com.backoffice.pageobjects.*;
import com.backoffice.provider.TestContextProvider;
import com.backoffice.utilities.WebDriverHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * @author Neha Kharbanda
 */
public class AndStepDefinitions {

    private static final Logger log = LogManager.getLogger(AndStepDefinitions.class);
    private final WebDriverHelper webDriverHelper;
    WebDriver driver;
    IMenu IMenuPage;
    IAccounts IAccountPage;
    ITaxes ITaxesPage;
    IAccessRights IAccessRightsPage;
    IItemList IItemListPage;
    IDiscounts IDiscountsPage;
    IModifiers IModifiersPage;
    ICategories ICategoriesPage;
    ICustomer ICustomerPage;
    IReceipt IReceiptPage;
    IEmployeeList IEmployeeListPage;
    IStore IStorePage;
    ISupplier ISupplierPage;
    IPurchaseOrder IPurchaseOrderPage;
    ITransferOrder ITransferOrderPage;
    IStockAdjustments IStockAdjustmentsPage;
    IBackOfficeLogin IBackOfficeLoginPage;
    IBillingAndSubscription IBillingAndSubscriptionPage;
    IIntegrations IIntegrationsPage;
    IPosDevices IPosDevicesPage;
    IPaymentsType IPaymentsTypePage;
    com.backoffice.core.model.Supplier supplier;

    private String accessToken;
    private String storeUUID;

    public AndStepDefinitions() {
        driver = DriverFactory.getDriver();
        webDriverHelper = new WebDriverHelper(driver);
        IMenuPage = new MenuImpl(driver);
        IAccountPage = new AccountsImpl(driver);
        ITaxesPage = new TaxesImpl(driver);
        IAccessRightsPage = new AccessRightsImpl(driver);
        IItemListPage = new ItemListImpl(driver);
        IDiscountsPage = new DiscountsImpl(driver);
        ICategoriesPage = new CategoriesImpl(driver);
        IModifiersPage = new ModifiersImpl(driver);
        IReceiptPage = new ReceiptImpl(driver);
        ICustomerPage = new CustomerImpl(driver);
        IEmployeeListPage = new EmployeeListImpl(driver);
        IStorePage = new StoreImpl(driver);
        ISupplierPage = new SupplierImpl(driver);
        IPurchaseOrderPage = new PurchaseOrderImpl(driver);
        ITransferOrderPage = new TransferOrderImpl(driver);
        IStockAdjustmentsPage = new StockAdjustmentsImpl(driver);
        IBillingAndSubscriptionPage = new BillingAndSubscriptionImpl(driver);
        IBackOfficeLoginPage = new BackOfficeLoginImpl(driver);
        IIntegrationsPage = new IntegrationsImpl(driver);
        IPosDevicesPage = new PosDevicesImpl(driver);
        IPaymentsTypePage=new PaymentTypeImpl(driver);
    }


    @And("the currency is set to {string} and cents are {string}")
    public void theCurrencyIsSetToAndCentsAre(String currency, String cents) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        /*if (driver.findElement(By.xpath("//translate[normalize-space()='Settings']")).isDisplayed()) {
            IMenuPage.clickMenuButton();
        }*/
        IMenuPage.clickMenuButton();
        IMenuPage.clickOwnerIcon();
        IMenuPage.clickAccountButton();
        IAccountPage.selectCurrency(currency, cents);
        IAccountPage.clickSaveButton();
    }


    @And("settings category is clicked")
    public void settingsCategoryIsClicked() {
        IMenuPage.clickSettingsCategory();
    }

    @And("taxes option is selected")
    public void taxesOptionIsSelected() {
        ITaxesPage.selectTaxesOption();

    }

    @And("update the tax name as {string}")
    @And("create the new tax item as {string}")
    public void createTheNewTaxItemAs(String newTaxName) {
        ITaxesPage.enterOrUpdateTaxName(newTaxName);
    }

    @And("The access right option is selected under menu option")
    public void theAccessRightOptionIsSelectedUnderMenuOption() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // IMenuPage.clickEmployeeImage();
        // IMenuPage.clickAccessRightOption();
    }

    @And("click on cashier access type section")
    public void click_on_cashier_access_type_section() {
        IAccessRightsPage.clickAccessSection();
    }

    @And("select the cost of items button")
    public void select_the_cost_of_items_button() {
        IAccessRightsPage.clickCostOfItemBtn();

    }

    @And("hit save")
    public void hit_save() {
        IAccessRightsPage.saveItemButton();
    }

    @And("item list category is selected under expanded menu")
    public void itemListCategoryIsSelectedUnderExpandedMenu() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        //  IItemListPage.clickItemListExpanded();
    }

    @And("clear the cost field")
    public void clearTheCostField() {
        IItemListPage.emptyCostField();
    }


    @And("enter the cost value as {string}")
    public void enterTheCostValueAs(String costValue) throws InterruptedException {
        IItemListPage.enterCostValue(costValue);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }


    @And("clear the field and update the tax rate as {string}")
    public void clearTheFieldAndUpdateTheTaxRateAs(String taxValue) {
        ITaxesPage.enterOrUpdateTaxValue(taxValue);

    }

    @And("update the discount item name as {string}")
    @And("create the new discount item as {string}")
    public void createTheNewDiscountItemAs(String discountName) {
        IDiscountsPage.enterDiscountItemName(discountName);
    }

    @And("select the discount type by {string}")
    public void selectTheDiscountTypeBy(String discountType) {
        IDiscountsPage.selectDiscountType(discountType);
    }


    @And("clear the field and add discount by {string} as {string}")
    public void clearTheFieldAndAddDiscountByAs(String valueType, String discountValue) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        if (valueType.equals("Percentage")) {
            IDiscountsPage.enterDiscountByPercentage(discountValue);
        } else if (valueType.equals("Amount")) {
            IDiscountsPage.enterDiscountByAmount(discountValue);
        } else {
            log.info("the value type or discount value is wrong");
        }
    }


    @And("category is created as {string}")
    public void categoryIsCreatedAs(String categoryName) {
        ICategoriesPage.clickAddCategory();
        ICategoriesPage.enterOrUpdateCategoryName(categoryName);
    }

    @And("enter the description as {string}")
    public void enterTheDescriptionAs(String itemDescription) {
        IItemListPage.enterDescription(itemDescription);

    }

    @And("enter the price value as {string}")
    public void enterThePriceValueAs(String priceValue) {
        IItemListPage.enterPriceValue(priceValue);

    }

    @And("enter the SKU value as {string}")
    public void enterTheSKUValueAs(String skuValue) {
        IItemListPage.enterSkuValue(skuValue);
    }

    @And("enter the Barcode as {string}")
    public void enterTheBarcodeAs(String barcode) {
        IItemListPage.enterBarcode(barcode);
    }

    @And("modifier is created as {string}")
    public void modifierIsCreatedAs(String modifierName) {
        IModifiersPage.clickAddModifier();
        IModifiersPage.enterOrUpdateModifierName(modifierName);
    }


    @And("select the category from the category dropdown as {string}")
    public void selectTheCategoryFromTheCategoryDropdownAs(String categoryName) {
        IItemListPage.selectCategory(categoryName);
    }

    @And("the category is saved")
    public void theCategoryIsSaved() {
        ICategoriesPage.saveCategory();
    }

    @And("enable the Track Stock switch")
    public void enableTheTrackStockSwitch() {
        IItemListPage.enableTrackStockSwitch();
    }

    @And("enter the In Stock and Low stock as {string} and {string} respectively")
    public void enterTheInStockAndLowStockAsAndRespectively(String inStockValue, String lowStockValue) {
        TestContextProvider.getContext().getScenarioContext().setContext("InStockQuantity", inStockValue);
        IItemListPage.enterInStock(inStockValue);
        IItemListPage.enterLowStock(lowStockValue);

    }


    @And("create the {string},{string} variant for option {string}")
    public void createTheVariantForOption(String variantType1, String variantType2, String optionName) {
        IItemListPage.clickAddVariant();
        IItemListPage.enterOptionName(optionName);
        IItemListPage.enterOptionValues(variantType1, variantType2);
    }

    @And("update details for variants")
    public void updateDetailsForVariants() {


        // And update details for variants
        //       | VariantType | Price | Cost | InStock | LowStock | OptimalStock | SKU      | Barcode |
        //| Regular     |   |  | 50.45   |      | 10.5         | SKU10001 | 10000   |
        //| Medium      |   |  | 50.45   | 12.2     | 10.5         | SKU10001 | 10001   |

        WebElement price1 = driver.findElement(By.id("variant_edit-price-input-0"));
        WebElement price2 = driver.findElement(By.id("variant_edit-price-input-1"));
        String price1value = "8.50";
        String price2value = "4.50";

        WebElement cost1 = driver.findElement(By.id("variant_edit-cost-input-0"));
        WebElement cost2 = driver.findElement(By.id("variant_edit-cost-input-1"));
        String cost1value = "4.25";
        String cost2value = "2.25";

        WebElement instock1 = driver.findElement(By.id("wereedit__variant_in-stock_input_0"));
        WebElement instock2 = driver.findElement(By.id("wereedit__variant_in-stock_input_1"));
        String instock1value = "50.45";
        String instock2value = "50.45";

        WebElement lowStock1 = driver.findElement(By.id("wereedit__variant_low-stock_input_0"));
        WebElement lowStock2 = driver.findElement(By.id("wereedit__variant_low-stock_input_1"));
        String lowStock1value = "12.2";
        String lowStock2value = "12.2";

        WebElement optimalStock1 = driver.findElement(By.id("wereedit__variant_optimal-stock_input_0"));
        WebElement optimalStock2 = driver.findElement(By.id("wereedit__variant_optimal-stock_input_1"));
        String optimalStock1value = "20.24";
        String optimalStock2value = "20.24";

        WebElement sku1 = driver.findElement(By.id("variant_edit-sku-input-0"));
        WebElement sku2 = driver.findElement(By.id("variant_edit-sku-input-1"));
        String sku1value = "SKU10001";
        String sku2value = "SKU10002";

        WebElement barcode1 = driver.findElement(By.id("variant_edit-barcode-input-0"));
        WebElement barcode2 = driver.findElement(By.id("variant_edit-barcode-input-1"));
        String barcode1value = "10000";
        String barcode2value = "100002";

        price1.click();
        price1.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength = price1.getAttribute("value").length();
        for (int ty = 0; ty < textLength; ty++) {
            price1.sendKeys(Keys.BACK_SPACE);
        }
        price1.sendKeys(price1value);
        log.info("VARIANT price is entered as" + price1value);

        price2.click();
        price2.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength2 = price2.getAttribute("value").length();
        for (int ty = 0; ty < textLength2; ty++) {
            price2.sendKeys(Keys.BACK_SPACE);
        }
        price2.sendKeys(price2value);
        log.info("VARIANT 2 price is entered as" + price2value);

        cost1.click();
        cost1.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength3 = cost1.getAttribute("value").length();
        for (int ty = 0; ty < textLength3; ty++) {
            cost1.sendKeys(Keys.BACK_SPACE);
        }
        cost1.sendKeys(cost1value);
        log.info("VARIANT cost is entered as" + cost1value);

        cost2.click();
        cost2.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength4 = cost2.getAttribute("value").length();
        for (int ty = 0; ty < textLength4; ty++) {
            cost2.sendKeys(Keys.BACK_SPACE);
        }
        cost2.sendKeys(cost2value);
        log.info("VARIANT 2 price is entered as" + cost2value);


        instock1.click();
        instock1.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength5 = instock1.getAttribute("value").length();
        for (int ty = 0; ty < textLength5; ty++) {
            instock1.sendKeys(Keys.BACK_SPACE);
        }
        instock1.sendKeys(instock1value);
        log.info("VARIANT instock1value is entered as" + instock1value);

        instock2.click();
        instock2.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength6 = instock2.getAttribute("value").length();
        for (int ty = 0; ty < textLength6; ty++) {
            instock2.sendKeys(Keys.BACK_SPACE);
        }
        instock2.sendKeys(instock2value);
        log.info("VARIANT  instock2 is entered as" + instock2value);


        lowStock1.click();
        lowStock1.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength7 = lowStock1.getAttribute("value").length();
        for (int ty = 0; ty < textLength7; ty++) {
            lowStock1.sendKeys(Keys.BACK_SPACE);
        }
        lowStock1.sendKeys(lowStock1value);
        log.info("VARIANT lowStock1 is entered as" + lowStock1value);

        lowStock2.click();
        lowStock2.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength8 = lowStock2.getAttribute("value").length();
        for (int ty = 0; ty < textLength8; ty++) {
            lowStock2.sendKeys(Keys.BACK_SPACE);
        }
        lowStock2.sendKeys(lowStock2value);
        log.info("VARIANT lowStock2 is entered as" + lowStock2value);


        optimalStock1.click();
        optimalStock1.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength9 = optimalStock1.getAttribute("value").length();
        for (int ty = 0; ty < textLength9; ty++) {
            optimalStock1.sendKeys(Keys.BACK_SPACE);
        }
        optimalStock1.sendKeys(optimalStock1value);
        log.info("VARIANT optimalStock1value is entered as" + optimalStock1value);

        optimalStock2.click();
        optimalStock2.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength10 = optimalStock2.getAttribute("value").length();
        for (int ty = 0; ty < textLength10; ty++) {
            optimalStock2.sendKeys(Keys.BACK_SPACE);
        }
        optimalStock2.sendKeys(optimalStock2value);
        log.info("VARIANT optimalStock1value is entered as" + optimalStock2value);


        sku1.click();
        sku1.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength11 = sku1.getAttribute("value").length();
        for (int ty = 0; ty < textLength11; ty++) {
            sku1.sendKeys(Keys.BACK_SPACE);
        }
        sku1.sendKeys(sku1value);
        log.info("VARIANT sku1value is entered as" + sku1value);

        sku2.click();
        sku2.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength12 = sku2.getAttribute("value").length();
        for (int ty = 0; ty < textLength12; ty++) {
            sku2.sendKeys(Keys.BACK_SPACE);
        }
        sku2.sendKeys(sku2value);
        log.info("VARIANT sku2value is entered as" + sku2value);


        barcode1.click();
        barcode1.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength13 = barcode1.getAttribute("value").length();
        for (int ty = 0; ty < textLength13; ty++) {
            barcode1.sendKeys(Keys.BACK_SPACE);
        }
        barcode1.sendKeys(barcode1value);
        log.info("VARIANT barcode1value is entered as" + barcode1value);

        barcode2.click();
        barcode2.sendKeys(Keys.END); // Move cursor to the end of the text
        int textLength14 = barcode2.getAttribute("value").length();
        for (int ty = 0; ty < textLength14; ty++) {
            barcode2.sendKeys(Keys.BACK_SPACE);
        }
        barcode2.sendKeys(barcode2value);
        log.info("VARIANT barcode2value is entered as" + barcode2value);


    }

    /*public void updateDetailsForVariants(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String variantType = row.get("VariantType");
            String price = row.get("Price");
            String cost = row.get("Cost");
            String inStock = row.get("InStock");
            String lowStock = row.get("LowStock");
            String optimalStock = row.get("OptimalStock");
            String sku = row.get("SKU");
            String barcode = row.get("Barcode");
// Locate the table element
            WebElement variantTable = driver.findElement(By.xpath("//div[@class='tableBodyWrap']//table//tbody"));

// Find all rows in the table
            List<WebElement> tableRows = variantTable.findElements(By.tagName("tr"));
            log.info("variant table rows:"+tableRows);
            // Iterate over each row (skipping the header row)
            for (int i = 1; i < tableRows.size(); i++) {
                WebElement variantTableRows = tableRows.get(i);

                // Find all columns within the row
                List<WebElement> tableColumns = variantTableRows.findElements(By.tagName("td"));
                log.info("variant table column:"+tableColumns);
                int count = 0;
                // Iterate over each column
                for (WebElement variantTableColumn : tableColumns) {
                    boolean isPresent = driver.findElement(By.id("variant_edit-price-input-" + count)).isDisplayed();
                    if (isPresent) {
                        log.info("isPresent");
                    }
                    // Perform actions on each column
                    WebElement priceInput2 = driver.findElement(By.id("variant_edit-price-input-" + count));
                    priceInput2.click();
                    priceInput2.sendKeys(Keys.END); // Move cursor to the end of the text
                    int textLength = priceInput2.getAttribute("value").length();
                    for (int ty = 0; ty < textLength; ty++) {
                        priceInput2.sendKeys(Keys.BACK_SPACE);
                    }
                    priceInput2.sendKeys(price);
                    log.info("VARIANT price is entered as" +price);

                    WebElement CostInput2 = driver.findElement(By.id("variant_edit-cost-input-" + count));
                    CostInput2.click();
                    CostInput2.sendKeys(Keys.END); // Move cursor to the end of the text
                    int textLength2 = CostInput2.getAttribute("value").length();
                    for (int ty = 0; ty < textLength2; ty++) {
                        CostInput2.sendKeys(Keys.BACK_SPACE);
                    }
                    CostInput2.sendKeys(cost);
                    log.info("VARIANT cost is entered as" +cost);

                    WebElement instock2 = driver.findElement(By.id("wereedit__variant_in-stock_input_" + count));
                    instock2.click();
                    instock2.sendKeys(Keys.END); // Move cursor to the end of the text
                    int textLength3 = instock2.getAttribute("value").length();
                    for (int ty = 0; ty < textLength3; ty++) {
                        instock2.sendKeys(Keys.BACK_SPACE);
                    }
                    instock2.sendKeys(inStock);
                    log.info("VARIANT instock is entered as" +cost);


                    WebElement lowstcok2 = driver.findElement(By.id("wereedit__variant_low-stock_input_" + count));
                    instock2.click();
                    instock2.sendKeys(Keys.END); // Move cursor to the end of the text
                    int textLength3 = instock2.getAttribute("value").length();
                    for (int ty = 0; ty < textLength3; ty++) {
                        instock2.sendKeys(Keys.BACK_SPACE);
                    }
                    instock2.sendKeys(inStock);
                    log.info("VARIANT instock is entered as" +cost);

                    count++;
                }
            }

        }
    }*/

    @And("the modifier is {string}")
    public void theModifierIs(String modifierStatus) {
        IItemListPage.toggleModifierSwitch(modifierStatus);
    }

    @And("Emailed , Printed receipt logo is uploaded")
    public void emailedPrintedReceiptLogoIsUploaded() {
        IReceiptPage.uploadEmailedReceipts();
        IReceiptPage.uploadPrintedReceipts();
    }


    @And("item category is clicked")
    public void itemCategoryIsClicked() {
        IMenuPage.clickItemsCategory();
    }

    @And("item list option is selected")
    public void itemListOptionIsSelected() {
        IMenuPage.selectItemListOption();
    }


    @And("create the new item as {string}")
    public void createTheNewItemAs(String itemName) {
        TestContextProvider.getContext().getScenarioContext().setContext("createdItemName", itemName);
        IItemListPage.clickAddItemBtn();
        IItemListPage.enterItemName(itemName);
    }

    @And("discount option is selected")
    public void discountOptionIsSelected() {
        IDiscountsPage.selectDiscountOption();
    }


    @And("enter the item details as")
    public void enterTheItemDetailsAs(DataTable itemDetails) throws InterruptedException {
        List<Map<String, String>> items = itemDetails.asMaps(String.class, String.class);
        Item item = new Item();

        for (Map<String, String> itemsMap : items) {

            String price = itemsMap.get("Price");
            String cost = itemsMap.get("Cost");
            String sku = itemsMap.get("SKU");
            String barcode = itemsMap.get("Barcode");


            item.setPrice(price);
            item.setCost(cost);
            item.setSku(sku);
            item.setBarCode(barcode);
            TestContextProvider.getContext().scenarioContext.setContext("itemData", item);

            IItemListPage.enterPriceValue(price);
            IItemListPage.emptyCostField();
            IItemListPage.enterCostValue(cost);
            IItemListPage.enterSkuValue(sku);
            IItemListPage.enterBarcode(barcode);

        }
    }


    @And("the customer details are updated as")
    public void theCustomerDetailsAreUpdatedAs(DataTable createCustomerDetails) {
        List<Map<String, String>> customerDetails = createCustomerDetails.asMaps(String.class, String.class);

        for (Map<String, String> customerData : customerDetails) {
            String customerName = customerData.get("Name");
            String customerEmail = customerData.get("Email");
            String customerPhone = customerData.get("Phone");
            String customerAddress = customerData.get("Address");
            String customerCity = customerData.get("City");
            String customerState = customerData.get("State");
            String customerZipCode = customerData.get("ZipCode");
            String customerCountry = customerData.get("Country");
            String customerCode = customerData.get("CustomerCode");
            String customerNote = customerData.get("Note");

            ICustomerPage.enterOrUpdateCustomerName(customerName);
            ICustomerPage.enterOrUpdateCustomerEmail(customerEmail);
            ICustomerPage.enterOrUpdateCustomerPhone(customerPhone);
            ICustomerPage.enterOrUpdateCustomerAddress(customerAddress);
            ICustomerPage.enterOrUpdateCustomerCity(customerCity);
            ICustomerPage.enterOrUpdateCustomerState(customerState);
            ICustomerPage.enterOrUpdateCustomerZipCode(customerZipCode);
            ICustomerPage.selectCustomerCountry(customerCountry);
            ICustomerPage.enterOrUpdateCustomerCode(customerCode);
            ICustomerPage.enterOrUpdateCustomerNote(customerNote);
        }
    }

    @And("customer details are saved")
    public void customerDetailsAreSaved() {
        ICustomerPage.clickCustomerSave();
    }


    @And("click on customer base icon to redirect to customer list screen")
    public void clickOnCustomerBaseIconToRedirectToCustomerListScreen() {
        ICustomerPage.clickCustomerBaseOnCustomerProfile();
    }

    @And("hit save on create tax page")
    public void hitSaveOnCreateTaxPage() {
        ITaxesPage.saveTax();
    }

    @And("set the header and footer as {string} and {string}")
    public void setTheHeaderAndFooterAsAnd(String headerText, String footerText) {
        IReceiptPage.updateHeaderAndFooter(headerText, footerText);
    }

    @And("the show customer info and show comments is enabled")
    public void theShowCustomerInfoAndShowCommentsIsEnabled() {
        IReceiptPage.clickShowCustInfoIcon();
        IReceiptPage.clickShowCommentsIcon();
    }

    @And("the languages is selected as {string}")
    public void theLanguagesIsSelectedAs(String receiptLanguage) {
        IReceiptPage.selectReceiptLanguage(receiptLanguage);
    }

    @And("click save on Receipt settings page")
    public void clickSaveOnReceiptSettingsPage() {
        IReceiptPage.saveReceipt();
    }

    @And("the add employee button is clicked")
    public void theAddEmployeeButtonIsClicked() {
        IEmployeeListPage.clickAddEmployeeButton();
    }

    @And("the authentication PIN code {string} is entered")
    public void theAuthenticationPINCodeIsEntered(String authPinCode) {
        IEmployeeListPage.enterAndSaveAuthPinCode(authPinCode);
    }

    @And("the following employee details are entered:")
    public void theFollowingEmployeeDetailsAreEntered(DataTable creatEmployeeDetails) {


        List<Map<String, String>> empDetails = creatEmployeeDetails.asMaps(String.class, String.class);
        for (Map<String, String> empData : empDetails) {

            String empName = empData.get("Name");
            String empEmail = empData.get("Email");
            String empPhone = empData.get("Phone");
            String empRole = empData.get("Role");
            IEmployeeListPage.enterEmployeeName(empName);
            IEmployeeListPage.enterEmployeeEmail(empEmail);
            IEmployeeListPage.enterEmployeePhone(empPhone);
            IEmployeeListPage.enterEmployeeRole(empRole);
            TestContextProvider.getContext().getScenarioContext().setContext("createdEmpName", empName);
        }
    }

    @And("the Save button is clicked, and the Employee Management for Free is started")
    public void theSaveButtonIsClickedAndTheEmployeeManagementForFreeIsStarted() {
        IEmployeeListPage.clickEmployeeSave();
        IEmployeeListPage.clickStartFreeEmployeeTrialButton();
    }

    @And("the Stores option is clicked")
    public void theStoresOptionIsClicked() {
        IStorePage.selectStoresOption();
    }

    @And("the ADD STORE button is clicked")
    public void theADDSTOREButtonIsClicked() {
        IStorePage.clickAddStoreButton();
    }

    @And("edit the store details as")
    @And("the store details are entered as")
    public void theStoreDetailsAreEnteredAs(DataTable storeDetails) {
        List<Map<String, String>> storeData = storeDetails.asMaps(String.class, String.class);
        Stores store = new Stores();
        for (Map<String, String> storesMap : storeData) {
            String storeName = storesMap.get("Name");
            String storeAddress = storesMap.get("Address");
            String storeCity = storesMap.get("City");
            String storeState = storesMap.get("State");
            String storeZipcode = storesMap.get("ZipCode");
            String storeCountry = storesMap.get("Country");
            String storePhone = storesMap.get("Phone");
            String storeVatNumber = storesMap.get("VatNumber");
            String storeDescription = storesMap.get("Description");

            store.setName(storeName);
            store.setAddress(storeAddress);
            store.setCity(storeCity);
            store.setState(storeState);
            store.setZipCode(storeZipcode);
            store.setCountry(storeCountry);
            store.setPhone(storePhone);
            store.setVatNumber(storeVatNumber);
            store.setDescription(storeDescription);
            TestContextProvider.getContext().scenarioContext.setContext("storeData", store);

            IStorePage.enterOrUpdateStoreName(storeName);
            IStorePage.enterOrUpdateStoreAddress(storeAddress);
            IStorePage.enterOrUpdateStoreCity(storeCity);
            IStorePage.enterOrUpdateStoreState(storeState);
            IStorePage.enterOrUpdateStoreZipcode(storeZipcode);
            IStorePage.enterOrUpdateStoreCountry(storeCountry);
            IStorePage.enterOrUpdateStorePhone(storePhone);
            IStorePage.enterOrUpdateVatNumber(storeVatNumber);
            IStorePage.enterOrUpdateStoreDescription(storeDescription);
        }
    }


    @And("enable the composite Item switch and update the quantity as {string} for item {string}")
    public void enableTheCompositeItemSwitchAndUpdateTheQuantityAsForItem(String quantity, String compositeItemSearch) {
        IItemListPage.clickCompositeItemSwitch();
        IItemListPage.clickCompositeItemSearch();
        IItemListPage.updateCompositeItem(quantity, compositeItemSearch);
    }

    @And("assert the total composite component cost populated is equal to cost in cost field")
    public void assertTheTotalCompositeComponentCostPopulatedIsEqualToCostInCostField() {
        IItemListPage.verifyTotalCostOfCompositeItem();
    }

    @And("I enter the store details as")
    public void i_enter_the_store_details_as(DataTable storeDetailsTable) {
        IItemListPage.enterStoreDetails(storeDetailsTable);
    }

    @And("the add supplier button is clicked")
    public void theAddSupplierButtonIsClicked() {
        ISupplierPage.clickAddSupplierButton();
    }

    @And("the supplier's details are entered as")
    public void theSupplierSDetailsAreEnteredAs(DataTable supplierDetails) {
        List<Map<String, String>> supplierData = supplierDetails.asMaps(String.class, String.class);
        Supplier supplier = new Supplier();
        for (Map<String, String> supplierMap : supplierData) {
            String supplierName = supplierMap.get("SupplierName");
            String supplierContact = supplierMap.get("Contact");
            String supplierEmail = supplierMap.get("Email");
            String supplierPhone = supplierMap.get("Phone");
            String supplierWebsite = supplierMap.get("Website");
            String supplierAddress1 = supplierMap.get("Address1");
            String supplierAddress2 = supplierMap.get("Address2");
            String supplierCity = supplierMap.get("City");
            String supplierZipCode = supplierMap.get("Postal/ZipCode");
            String supplierCountry = supplierMap.get("Country");
            String supplierRegion = supplierMap.get("Region");
            String supplierNote = supplierMap.get("Note");

            supplier.setSupplierName(supplierName);
            supplier.setSupplierContact(supplierContact);
            supplier.setSupplierEmail(supplierEmail);
            supplier.setSupplierPhone(supplierPhone);
            TestContextProvider.getContext().scenarioContext.setContext("supplierData", supplier);

            ISupplierPage.enterSupplierName(supplierName);
            ISupplierPage.enterSupplierContact(supplierContact);
            ISupplierPage.enterSupplierEmail(supplierEmail);
            ISupplierPage.enterSupplierPhone(supplierPhone);
            ISupplierPage.enterSupplierWebsite(supplierWebsite);
            ISupplierPage.enterSupplierAddress1(supplierAddress1);
            ISupplierPage.enterSupplierAddress2(supplierAddress2);
            ISupplierPage.enterSupplierCity(supplierCity);
            ISupplierPage.enterSupplierZipCode(supplierZipCode);
            ISupplierPage.enterSupplierCountry(supplierCountry);
            ISupplierPage.enterSupplierRegion(supplierRegion);
            ISupplierPage.enterSupplierNote(supplierNote);
        }

    }


    @And("enable the Track Stock switch and enter the details as")
    public void enableTheTrackStockSwitchAndEnterTheDetailsAs(DataTable trackStockDetailsTable) {
        List<Map<String, String>> trackStockData = trackStockDetailsTable.asMaps(String.class, String.class);
        for (Map<String, String> supplierMap : trackStockData) {
            String inStockValue = supplierMap.get("InStock");
            String lowStockValue = supplierMap.get("LowStock");
            String supplierName = supplierMap.get("Supplier");
            String optimalStock = supplierMap.get("OptimalStock");

            IItemListPage.enableTrackStockSwitch();
            IItemListPage.enterInStock(inStockValue);
            IItemListPage.enterLowStock(lowStockValue);
            IItemListPage.enterOptimalStock(optimalStock);
            IItemListPage.enterSupplier(supplierName);
        }


    }

    @And("add purchase button is clicked")
    public void addPurchaseButtonIsClicked() {
        IPurchaseOrderPage.clickAddPurchaseOrderButton();
    }

    @And("the purchase order details are entered as")
    public void thePurchaseOrderDetailsAreEnteredAs(DataTable purchaseOrderDetails) {

        List<Map<String, String>> purchaseOrderData = purchaseOrderDetails.asMaps(String.class, String.class);
        for (Map<String, String> purchaseOrderMap : purchaseOrderData) {
            String supplierName = purchaseOrderMap.get("Supplier");
            String notes = purchaseOrderMap.get("Notes");
            String item = purchaseOrderMap.get("Item");
            String quantity = purchaseOrderMap.get("Quantity");
            String purchaseCost = purchaseOrderMap.get("Purchase Cost");

            IPurchaseOrderPage.enterSupplierName(supplierName);
            IPurchaseOrderPage.enterExpectedDate();
            IPurchaseOrderPage.enterPurchaseOrderNote(notes);
            IPurchaseOrderPage.selectItemForPurchase(item);
            IPurchaseOrderPage.enterOrderItemQuantity(quantity);
            IPurchaseOrderPage.enterOrderItemCost(purchaseCost);
        }

    }

    @And("verify the total cost is updated as {string}")
    public void verifyTheTotalCostIsUpdatedAs(String expectedTotalCostOfPurchaseOrder) {
        IPurchaseOrderPage.verifyTotalCostOfPurchaseOrder(expectedTotalCostOfPurchaseOrder);
    }

    @And("create button is clicked on create purchase order screen and is marked {string}")
    public void createButtonIsClickedOnCreatePurchaseOrderScreenAndIsMarked(String expectedPurchaseOrderStatus) {
        IPurchaseOrderPage.clickCreatePurchaseOrderButton();
        IPurchaseOrderPage.validatePurchaseOrderStatus(expectedPurchaseOrderStatus);
    }


    @And("click Inventory management")
    public void clickInventoryManagement() throws InterruptedException {
        IMenuPage.clickInventoryManagement();
    }


    @And("redirect to Purchase Orders list screen")
    public void redirectToPurchaseOrdersListScreen() {
        IPurchaseOrderPage.redirectToPurchaseOrdersScreen();
    }

    @And("the transfer orders option is selected")
    public void theTransferOrdersOptionIsSelected() {
        ITransferOrderPage.selectTransferOrderOption();
    }

    @And("the add transfer order button is clicked")
    public void theAddTransferOrderButtonIsClicked() {
        ITransferOrderPage.clickAddTransferOrderButton();
    }

    @And("the transfer order details are entered as:")
    public void theTransferOrderDetailsAreEnteredAs(DataTable transferOrderDetails) throws InterruptedException {

        List<Map<String, String>> transferOrderData = transferOrderDetails.asMaps(String.class, String.class);
        for (Map<String, String> transferOrderMap : transferOrderData) {
            String sourceStore = transferOrderMap.get("Source Store");
            String destinationStore = transferOrderMap.get("Destination Store");
            String item = transferOrderMap.get("Item");
            String quantity = transferOrderMap.get("Quantity");

            ITransferOrderPage.enterSourceStore(sourceStore);
            ITransferOrderPage.enterDestinationStore(destinationStore);
            ITransferOrderPage.selectItemForTransferOrder(item);
            ITransferOrderPage.enterItemQuantity(quantity);
        }
    }


    @And("the transfer order is received")
    public void theTransferOrderIsReceived() {
        ITransferOrderPage.clickReceiveTransferOrderButton();
        ITransferOrderPage.clickDialogueBoxReceiveButton();
        ITransferOrderPage.redirectToTransferOrderListScreen();
    }


    @And("click on store {string}")
    public void clickOnStore(String expectedStoreName) {
        IItemListPage.selectTheStore(expectedStoreName);
    }

    @And("InStock value is updated to {string} on item list page")
    public void inStockValueIsUpdatedToOnItemListPage(String expectedUpdatedInStockValue) {
        IItemListPage.fetchAndValidateInStockValue(expectedUpdatedInStockValue);
    }


    @And("select the existing item to edit")
    public void selectTheExistingItemToEdit() throws InterruptedException {
        Thread.sleep(100);
        driver.findElement(By.xpath("//*[contains(text(),'Chocolate Mousse')]")).click();
        log.info("Existing item is clicked");
    }


    @And("the Stock adjustments option is selected")
    public void theStockAdjustmentsOptionIsSelected() {
        IStockAdjustmentsPage.selectStockAdjustmentsOption();
    }

    @And("add Stock adjustments button is clicked")
    public void addStockAdjustmentsButtonIsClicked() {
        IStockAdjustmentsPage.clickAddStockAdjustmentsButton();
    }

    @And("user clicks on sign out button")
    public void userClicksOnSignOutButton() {
        IMenuPage.clickMenuButton();
        IMenuPage.clickOwnerIcon();
        IMenuPage.clickSignOutButton();
    }

    @And("modifier is saved")
    public void modifierIsSaved() {
        IModifiersPage.clickSaveModifierButton();
    }

    @And("the category colour is selected")
    public void theCategoryColourIsSelected() {
        ICategoriesPage.selectCategoryColour();
    }

    @And("Verify category is displayed on categories page")
    public void verifyCategoryIsDisplayedOnCategoriesPage() {
        ICategoriesPage.verifyCategoryCreated();
    }

    @And("add inventory counts button is clicked")
    public void addInventoryCountsButtonIsClicked() {

    }


    @And("Validate the message {string} is displayed for discount Name")
    @And("the {string} dialog is displayed")
    @And("Validate the message {string} is displayed")
    @Then("the {string} page is displayed")
    @Then("a dialog box with the message {string} is displayed")
    public void validateTheMessageIsDisplayed(String expectedMessage) throws Exception {
        webDriverHelper.isMessageDisplayed(driver, expectedMessage);
    }


    @And("the existing category is deleted")
    public void theExistingCategoryIsDeleted(DataTable categoryNameTable) {
        ICategoriesPage.selectCategoryForDeletion(categoryNameTable);
        ICategoriesPage.clickTrashIcon();
        ICategoriesPage.clickDeleteButton();
    }


    @And("update the details on count stock and confirm count")
    public void updateTheDetailsOnCountStockAndConfirmCount() {

    }

    @And("Select the existing category to edit")
    public void selectTheExistingCategoryToEdit() {
        ICategoriesPage.selectExistingCategoryForEdit();
    }

    @And("update the category name as {string}")
    public void updateTheCategoryNameAs(String categoryName) {
        ICategoriesPage.enterOrUpdateCategoryName(categoryName);
    }

    @And("update the item details as")
    public void updateTheItemDetailsAs(DataTable updateItemDetails) throws InterruptedException {
        List<Map<String, String>> itemsDetails = updateItemDetails.asMaps(String.class, String.class);
        Item item = new Item();

        for (Map<String, String> itemsMap : itemsDetails) {

            String price = itemsMap.get("Price");
            String cost = itemsMap.get("Cost");
            String sku = itemsMap.get("SKU");
            String barcode = itemsMap.get("Barcode");
            String name = itemsMap.get("Name");
            String description = itemsMap.get("description");
            String inStock = itemsMap.get("inStock");
            String lowStock = itemsMap.get("lowStock");

            item.setName(name);
            item.setDescription(description);
            item.setPrice(price);
            item.setCost(cost);
            item.setSku(sku);
            item.setBarCode(barcode);
            item.setInStock(inStock);
            item.setLowStock(lowStock);

            TestContextProvider.getContext().scenarioContext.setContext("updateItemData", item);


            IItemListPage.enterPriceValue(price);
            IItemListPage.emptyCostField();
            IItemListPage.enterCostValue(cost);
            IItemListPage.enterSkuValue(sku);
            IItemListPage.enterBarcode(barcode);

        }

    }

    @And("update the item details from item list page as")
    public void updateTheItemDetailsFromItemListPageAs() {
    }

    @And("Verify updated category is displayed on categories page")
    public void verifyUpdatedCategoryIsDisplayedOnCategoriesPage() {
        ICategoriesPage.verifyCategoryIsUpdated();
    }

    @And("delete the existing category from edit page")
    public void deleteTheExistingCategoryFromEditPage() {
        ICategoriesPage.selectExistingCategoryForEdit();
        ICategoriesPage.clickTrashIcon();
        ICategoriesPage.clickDeleteButton();
    }

    @And("modifier option name and price is entered as")
    public void enterModifierOptionNameAndPrice(DataTable modifierData) {
        IModifiersPage.enterOptionNameAndPrice(modifierData);
    }

    @And("delete one of the modifier on modifier page")
    public void deleteOneOfTheModifierOnModifierPage() {
        IModifiersPage.clickTrashIcon();

    }

    @And("Verify modifier is displayed on modifiers page")
    public void verifyModifierIsDisplayedOnModifiersPage() {
        IModifiersPage.verifyModifierCreated();
    }

    @And("the existing modifier is deleted")
    public void theExistingModifierIsDeleted() {
        IModifiersPage.selectExistingModifier();
        IModifiersPage.clickDeleteButtonModifierPage();

    }


    @And("Select the existing modifier to edit")
    public void selectTheExistingModifierToEdit() {
        IModifiersPage.selectExistingModifierForEdit();
    }

    @And("update the modifier name as {string}")
    public void updateTheModifierNameAs(String modifierName) {
        IModifiersPage.enterOrUpdateModifierName(modifierName);
    }

    @And("the inventory count details and clcik save")
    public void theInventoryCountDetailsAndClcikSave() {

    }


    @Then("verify the details are updated on inventory Counts page")
    public void verifyTheDetailsAreUpdatedOnInventoryCountsPage() {
    }

    @And("Verify updated modifier is displayed on modifiers page")
    public void verifyUpdatedModifierIsDisplayedOnMOdifiersPage() {
        IModifiersPage.verifyModifierIsUpdated();
    }

    @And("delete the existing modifier from edit page")
    public void deleteTheExistingModifierFromEditPage() {
        IModifiersPage.selectExistingModifierForEdit();
        IModifiersPage.clickDeleteEditModifierPage();

    }

    @And("restricted access is {string} for discount")
    public void restrictedAccessIsForDiscount(String restrictedAccessStatus) {
        IDiscountsPage.selectRestrictedAccess(restrictedAccessStatus);
    }

    @And("click Add item button")
    public void clickAddItemButton() {
        IItemListPage.clickAddItemBtn();
    }

    @And("create new item with following details")
    public void createNewItemWithFollowingDetails(DataTable newItemDetails) throws InterruptedException {
        IItemListPage.createNewItem(newItemDetails);
    }

    @And("another category is created as {string}")
    public void anotherCategoryIsCreatedAs(String categoryName) {
        ICategoriesPage.addMoreCategoriesBtn();
        ICategoriesPage.enterOrUpdateCategoryName(categoryName);
    }

    @And("Add Item button is clicked")
    public void addItemButtonIsClicked() {
        IItemListPage.clickAddItemBtn();
    }

    @And("Add Category button is clicked")
    public void addCategoryButtonIsClicked() {
        ICategoriesPage.clickAddCategory();
    }


    @And("click on item name and lose focus")
    public void clickOnItemNameAndLoseFocus() {
        IItemListPage.clickItemNameInput();
    }

    @And("click on category name field and lose focus")
    public void clickOnCategoryNameFieldAndLoseFocus() {
        ICategoriesPage.clickCategoryNameInput();
    }

    @And("cancel is clicked on create item page")
    public void cancelIsClickedOnCreateItemPage() throws InterruptedException {
        IItemListPage.clickCancelItemBtn();
    }

    @And("verify the save supplier button is active")
    public void verifyTheSaveSupplierButtonIsActive() {
        ISupplierPage.verifySaveSupplierBtnEnabled();
    }

    @And("the existing customer is deleted")
    public void theExistingCustomerIsDeleted() {
        ICustomerPage.clickDeleteCustomer();
        ICustomerPage.clickDeleteOnDialogue();
    }


    @And("the customer is selected to delete from the list")
    public void theCustomerIsSelectedToDeleteFromTheList() {
        driver.findElement(By.xpath("//tr[1]/td[1]/div/md-checkbox")).click();
    }

    @And("trial subscription is enabled for {string}")
    public void trialSubscriptionIsEnabledFor(String subscriptionType) {
        IBillingAndSubscriptionPage.startTrialSubscriptions(subscriptionType);
    }

    @And("change the trial subscription to via API Request for below:")
    public void changeTheTrialSubscriptionToViaAPIRequestForBelow(DataTable subscriptionType) throws FileNotFoundException {
        Object ownerId = TestContextProvider.getContext().getScenarioContext().getContext("ownerId");
        log.info("owner ID is", ownerId.toString());
        List<Map<String, String>> subscriptions = subscriptionType.asMaps(String.class, String.class);
        for (Map<String, String> subscriptionsMap : subscriptions) {
            String subscription_Type = subscriptionsMap.get("SubscriptionType");
            String subscription_Status = subscriptionsMap.get("SubscriptionStatus");
            String subscription_State = subscriptionsMap.get("SubscriptionState");
            TestContextProvider.getContext().getRestServiceManager().setTrailSubscription(ownerId.toString(), subscription_Type, subscription_Status);
        }
    }


    @And("the existing employee is deleted")
    public void theExistingEmployeeIsDeleted() {
        IEmployeeListPage.selectExistingEmployee();
        IEmployeeListPage.clickDeleteButtonEmpPage();

    }

    @And("refresh the backoffice application")
    @And("the original browser tab is refreshed")
    public void refreshTheBackofficeApplication() {
        webDriverHelper.refreshApplication();

    }

    @And("update the payment details as")
    @And("add the payment method using below details:")
    public void addThePaymentMethodUsingBelowDetails(DataTable cardDetails) {
        List<Map<String, String>> cardData = cardDetails.asMaps(String.class, String.class);

        for (Map<String, String> cardMap : cardData) {
            String cardNumber = cardMap.get("CardNumber");
            String expiryDate = cardMap.get("ExpiryDate");
            String cardCVV = cardMap.get("CVV");

            IBillingAndSubscriptionPage.enterCardNumber(cardNumber);
            IBillingAndSubscriptionPage.enterCardExpiryDate(expiryDate);
            IBillingAndSubscriptionPage.enterCardCVV(cardCVV);
            IBillingAndSubscriptionPage.clickSavePaymentBtn();
        }
    }


    @And("click on update payment")
    public void clickOnUpdatePayment() {
        IBillingAndSubscriptionPage.clickUpdatePaymentMethod();
    }

    @And("click on menu button")
    public void clickOnMenuButton() {
        IMenuPage.clickMenuButton();
    }

    @And("click on add payment method")
    public void clickOnAddPaymentMethod() {
        IBillingAndSubscriptionPage.clickAddPaymentMethod();
    }


    @And("ACTIVATE the {string} plan for {string}")
    public void activateThePlanFor(String planType, String subscriptionType) throws InterruptedException {
        IBillingAndSubscriptionPage.activateSubscriptions(planType, subscriptionType);
    }

    @And("UNSUBSCRIBE for {string} subscription")
    public void unsubscribeForSubscription(String subscriptionType) {
        IBillingAndSubscriptionPage.unsubscribeSubscriptions(subscriptionType);
    }


    @And("verify the success paid invoice is displayed with below details")
    public void verifyTheSuccessPaidInvoiceIsDisplayedWithBelowDetails(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            String expectedServiceType = row.get("Service");
            String expectedServicePrice = row.get("Amount");
            IBillingAndSubscriptionPage.verifySuccessPaidInvoiceGenerated(expectedServiceType, expectedServicePrice);
        }
    }

    @And("the user clicks on the {string} badge, verify opening links in new browser tab and web Title as {string}")
    public void theUserClicksOnTheBadgeVerifyOpeningLinksInNewBrowserTabAndWebTitleAs(String badgeType, String webPageTitle) {
        IBackOfficeLoginPage.clickAndRedirectStoreBadge(badgeType, webPageTitle);
    }

    @And("the user clicks the Next button on the dialog box")
    public void theUserClicksTheNextButtonOnTheDialogBox() {
        IBackOfficeLoginPage.clickNextOnBoardingDialogue();
    }

    @And("the user clicks the Cancel button on feature settings dialouge")
    public void theUserClicksTheCancelButtonOnFeatureSettingsDialouge() {
        IBackOfficeLoginPage.clickCancelButtonFeatureSettings();
    }

    @And("the user clicks the Save button on feature settings dialouge")
    public void theUserClicksTheSaveButtonOnFeatureSettingsDialouge() {
        IBackOfficeLoginPage.clickSaveButtonFeatureSettings();
    }


    @And("verify below feature setting icons are {string}")
    public void verifyBelowFeatureSettingIconsAre(String expectedStatus, DataTable featureSettings) {
        List<Map<String, String>> featureSettingsList = featureSettings.asMaps(String.class, String.class);

        for (Map<String, String> featureSetting : featureSettingsList) {
            String featureName = featureSetting.get("Feature");
            if (expectedStatus.equals("disabled")) {
                IBackOfficeLoginPage.verifyOnBoardingFeaturesDisabled(featureName, expectedStatus);
            } else {
                IBackOfficeLoginPage.verifyOnBoardingFeaturesEnabled(featureName, expectedStatus);
            }

        }
    }

    @And("update the feature settings")
    public void updateTheFeatureSettings(DataTable featureSettings) {
        List<Map<String, String>> featureSettingsList = featureSettings.asMaps(String.class, String.class);

        for (Map<String, String> featureSetting : featureSettingsList) {
            String expectedFeatureName = featureSetting.get("Feature");
            String expectedFeatureStatus = featureSetting.get("Status");
            IBackOfficeLoginPage.updateFeatureSettings(expectedFeatureName, expectedFeatureStatus);

        }
    }

    @And("click confirm on Set Pin dialouge")
    public void clickConfirmOnSetPinDialouge() {
        IBackOfficeLoginPage.clickConfirmSetPinDialouge();
    }

    @And("click cancel on Set Pin dialouge")
    public void clickCancelOnSetPinDialouge() {
        IBackOfficeLoginPage.clickCancelSetPinDialouge();
    }

    @And("Enter {string} on the set PIN dialogue")
    public void enterOnTheSetPINDialogue(String pinForTimeClock) {
        IBackOfficeLoginPage.setPinForTimeClock(pinForTimeClock);
    }


    @And("verify the save button is {string} on category page")
    public void verifyTheSaveButtonIsOnCategoryPage(String expectedStatus) {
        ICategoriesPage.verifySaveCategoryBtnEnabled(expectedStatus);
    }


    @And("validate the billing status is {string}")
    public void validateTheBillingStatusIs(String billingStatus) {
        Assert.assertEquals("Billing Status is set to Active ", billingStatus, TestContextProvider.getContext().getRestServiceManager().getProfile());
    }

    @And("item image is uploaded")
    public void itemImageIsUploaded() throws AWTException, InterruptedException, URISyntaxException {
        IItemListPage.uploadItemImage();

    }

    @And("click on Cancel button")
    public void clickOnCancelButton() {
        ICategoriesPage.cancelCategoriesBtn();
    }


    @And("click on Continue Editing button")
    public void clickOnContinueEditingButton() {
        ICategoriesPage.continueEditingBtn();
    }

    @And("the existing URL is copied and opened in a new tab")
    public void theExistingURLIsCopiedAndOpenedInANewTab() {
        webDriverHelper.openCurrentUrlInNewTab();
    }

    @And("the original browser tab is switched to")
    public void theOriginalBrowserTabIsSwitchedTo() {
        webDriverHelper.switchToOriginalTab();
    }


    @And("the browser is redirected to the new tab")
    public void theBrowserIsRedirectedToTheNewTab() {
        webDriverHelper.switchToNewTab();
    }

    @And("the access token is generated for the account as {string}")
    public void theAccessTokenIsGeneratedForTheAccountAs(String accessTokenName) {
        IIntegrationsPage.clickIntegrationCategory();
        IIntegrationsPage.clickAccessTokens();
        IIntegrationsPage.clickAddAccessTokens();
        IIntegrationsPage.enterAccessTokenName(accessTokenName);
        IIntegrationsPage.disableExpiryDate();
        IIntegrationsPage.clickSaveToken();
        accessToken = IIntegrationsPage.fetchAccessTokens();
    }

    @And("the store UUID is fetched via API")
    public void theStoreUUIDIsFetchedViaAPI() {
        storeUUID = TestContextProvider.getContext().getRestServiceManager().getStoreUUID(accessToken);
    }

    @And("supplier is created via API")
    public void supplierIsCreatedViaAPI() {
        String request = GsonGenerator.toRequest(ToSupplierConverter.toConvert());
        supplier = TestContextProvider.getContext().getRestServiceManager().createSupplierByAPI(accessToken, request);
    }

    @And("the item is created via API")
    public void theItemIsCreatedViaAPI() throws FileNotFoundException {
        com.backoffice.core.model.Item createWare = ToItemConverter.toConvert(storeUUID, supplier.getId());
        String request = GsonGenerator.toRequest(createWare);
        TestContextProvider.getContext().getRestServiceManager().createItemByAPI(accessToken, request);
    }

    @And("click Inventory management when already expanded")
    public void clickInventoryManagementWhenAlreadyExpanded() {
        IMenuPage.clickInventoryManagementExpanded();
    }


    @And("select the existing {string} store")
    public void selectTheExistingStore(String expectedStoreName) {
        String actualStoreName = driver.findElement(By.xpath("//tr[1][@class='tableBodyBody list pointer']/td[1]/div")).getText();
        if (expectedStoreName.equals(actualStoreName)) {
            driver.findElement(By.xpath("//tr[1][@class='tableBodyBody list pointer']/td[1]/div")).click();
        } else {
            log.error("Store {}  not present", actualStoreName);
        }
    }

    @And("delete the store from edit store page")
    public void deleteTheStoreFromEditStorePage() {
        IStorePage.clickTrashButton();
        IStorePage.clickConfirmDelete();
    }

    @And("update the store name and phone as {string} and {string} respectively")
    public void updateTheStoreNameAndPhoneAsAndRespectively(String updateStoreName, String updateStorePhone) {
        IStorePage.enterOrUpdateStoreName(updateStoreName);
        IStorePage.enterOrUpdateStorePhone(updateStorePhone);
    }

    @And("save button is clicked on create POS page")
    public void saveButtonIsClickedOnCreatePOSPage() {
        IPosDevicesPage.clickOnSavePosButton();
    }

    @And("pos name and store is entered as {string} and {string}")
    public void posNameAndStoreIsEnteredAsAnd(String posName, String posStoreName) {
        IPosDevicesPage.enterOrUpdatePosName(posName);
        IPosDevicesPage.selectStoreName(posStoreName);

    }

    @And("select the store {string} on post list page")
    public void selectTheStoreOnPostListPage(String expectedPosStoreName) {
        IPosDevicesPage.selectStoreName(expectedPosStoreName);
    }

    @And("store is shown as {string} on pos list page")
    public void storeIsShownAsOnPosListPage(String expectedListedPosStoreName) {
        IPosDevicesPage.fetchAndValidatePosStore(expectedListedPosStoreName);
    }

    @And("the ADD POS DEVICE button is clicked")
    public void theADDPOSDEVICEButtonIsClicked() {
        IPosDevicesPage.clickOnAddPosButton();
    }

    @And("Pos device option is selected")
    public void posDeviceOptionIsSelected() {
        IPosDevicesPage.clickOnPosDevicesOption();
    }

    @And("select the tax type as {string}")
    public void selectTheTaxTypeAs(String taxType) {
        ITaxesPage.selectTaxType(taxType);
    }

    @And("Add tax button is clicked")
    public void addTaxButtonIsClicked() {
        ITaxesPage.clickAddTaxButton();
    }

    @And("Add tax button is clicked when taxes are already present")
    public void addTaxButtonIsClickedWhenTaxesAreAlreadyPresent() {
        ITaxesPage.clickAddTaxOnListPage();
    }


    @And("select the tax option as {string}")
    public void selectTheTaxOptionAs(String expectedTaxOption) {
        ITaxesPage.selectTaxOption(expectedTaxOption);
    }

    @And("click on ok for Applying tax to existing items")
    public void clickOnOkForApplyingTaxToExistingItems() {
        ITaxesPage.clickOkForExistingItemOption();
    }

    @And("select the existing tax {string}")
    public void selectTheExistingTax(String expectedTaxName) {
        ITaxesPage.selectTheTaxFromList(expectedTaxName);
    }


    @And("verify the tax created is shown as {string} with {string} as Apply to new items and tax rate {string}")
    public void verifyTheTaxCreatedIsShownAsWithAsApplyToNewItemsAndTaxRate(String expectedTaxName, String expectedAppliedToItems, String expectedTaxValue) throws InterruptedException {
        ITaxesPage.verifyTheTaxFromList(expectedTaxName,expectedAppliedToItems,expectedTaxValue);
    }

    @And("delete the tax from edit tax page")
    public void deleteTheTaxFromEditTaxPage() {
        ITaxesPage.clickDeleteTaxButton();
    }


    @And("verify the customer details on profile screen")
    public void verifyTheCustomerDetailsOnProfileScreen(DataTable addedCustomerDetails) {
        // Convert DataTable to List<Map<String, String>>
        List<Map<String, String>> customerDetails = addedCustomerDetails.asMaps(String.class, String.class);

        // Initialize WebDriverWait with a timeout of 20 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Iterate over each customer's details
        for (Map<String, String> customer : customerDetails) {
            for (Map.Entry<String, String> entry : customer.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                // Construct XPath expression dynamically
                String xpathExpression = "//*[contains(text(), '" + value + "')]";

                try {
                    // Wait until the element is visible on the page
                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));

                    // Check if the element is displayed
                    if (element == null || !element.isDisplayed()) {
                        fail("Element with key '" + key + "' and value '" + value + "' not found on profile screen");
                    }
                } catch (Exception e) {
                    fail("Element with key '" + key + "' and value '" + value + "' not found on profile screen");
                }
            }
        }
    }

    @And("validate the customer details on customer list page")
    public void validateTheCustomerDetailsOnCustomerListPage(DataTable customerListDetails) {
        List<Map<String, String>> customerDetails = customerListDetails.asMaps(String.class, String.class);

        for (Map<String, String> customer : customerDetails) {
            String expectedName = customer.get("Name");
            String expectedCustomerNote = customer.get("Note");
            String expectedEmail = customer.get("Email");
            String expectedPhone = customer.get("Phone");
            String expectedFirstVisit = customer.get("First visit");
            String expectedLastVisit = customer.get("Last visit");
            String expectedTotalVisit = customer.get("Total visits");
            String expectedTotalSpent = customer.get("Total spent");
            String expectedPointsBalance = customer.get("Points balance");

            //find the customer table
            WebElement customerListTable = driver.findElement(By.xpath("//tbody"));
            // Locate all rows in the customer table
            List<WebElement> rows = customerListTable.findElements(By.xpath(".//tr"));
            int rowCount = rows.size();
            System.out.println("No of rows in this table : " + rowCount);

            boolean customerFound = false;
            for (int i = 1; i <= rowCount; i++) {
                // Check if the Name column matches the expected name
                String actualName = driver.findElement(By.xpath(".//tr[" + i + "]/td[2]/div/div/div/div[1]/span")).getText();
                if (actualName.equals(expectedName)) {
                    customerFound = true;
                    // Verify the entire row data
                    String actualCustomerNote = driver.findElement(By.xpath(".//tr[" + i + "]/td[2]/div/div/div/div[2]/span")).getText();
                    String actualEmail = driver.findElement(By.xpath(".//tr[" + i + "]/td[3]/div[1]")).getText();
                    String actualPhone = driver.findElement(By.xpath(".//tr[" + i + "]/td[3]/div[2]")).getText();
                    String actualFirstVisit = driver.findElement(By.xpath(".//tr[" + i + "]/td[4]/div/div/span")).getText();
                    String actualLastVisit = driver.findElement(By.xpath(".//tr[" + i + "]/td[5]/span")).getText();
                    String actualTotalVisit = driver.findElement(By.xpath(".//tr[" + i + "]/td[6]/span")).getText();
                    String actualTotalSpent = driver.findElement(By.xpath(".//tr[" + i + "]/td[7]/span")).getText();
                    String actualPointsBalance = driver.findElement(By.xpath(".//tr[" + i + "]/td[8]/span")).getText();

                    Assert.assertEquals(actualName, expectedName);
                    Assert.assertEquals(actualCustomerNote, expectedCustomerNote);
                    Assert.assertEquals(actualEmail, expectedEmail);
                    Assert.assertEquals(actualPhone, expectedPhone);
                    Assert.assertEquals(actualFirstVisit, expectedFirstVisit);
                    Assert.assertEquals(actualLastVisit, expectedLastVisit);
                    Assert.assertEquals(actualTotalVisit, expectedTotalVisit);
                    Assert.assertEquals(actualTotalSpent, expectedTotalSpent);
                    Assert.assertEquals(actualPointsBalance, expectedPointsBalance);

                    break;
                }
            }

            if (!customerFound) {
                fail("Customer with name '" + expectedName + "' not found in the table");
            }
        }
    }


    @And("update and verify the edit points balance to {string}")
    public void updateAndVerifyTheEditPointsBalanceTo(String editPointsValue) {
        ICustomerPage.clickMoreButtonOnCustomerProfile();
        ICustomerPage.clickEditPointsBalanceBtn();
        ICustomerPage.enterOrUpdateEditPoints(editPointsValue);
        ICustomerPage.clickSavePointsBtn();
        ICustomerPage.verifyPointsOnCustomerProfile(editPointsValue);

    }

    @And("click on edit profile button")
    public void clickOnEditProfileButton() {
        ICustomerPage.clickEditProfileBtn();
    }

    @And("click on Cancel button on edit customer screen")
    public void clickOnCancelButtonOnEditCustomerScreen() {
        ICustomerPage.clickCustomerCancel();
    }

    @And("add customer button is clicked when customer is already present")
    public void addCustomerButtonIsClickedWhenCustomerIsAlreadyPresent() {
        ICustomerPage.clickAddCustomerWhenCustomerPresent();
    }


    @And("update the customer email as {string}")
    public void updateTheCustomerEmailAs(String updateCustomerEmail) {
        ICustomerPage.enterOrUpdateCustomerEmail(updateCustomerEmail);
    }

    @And("Click ok on unable to save customer profile popup")
    public void clickOkOnUnableToSaveCustomerProfilePopup() {
        ICustomerPage.clickOKOnCodeExistsDialogue();
    }

    @And("update the customer code as {string}")
    public void updateTheCustomerCodeAs(String updateCustomerCode) {
        ICustomerPage.enterOrUpdateCustomerCode(updateCustomerCode);
    }

    @And("delete the customer from customer profile screen")
    public void deleteTheCustomerFromCustomerProfileScreen() {
        ICustomerPage.clickMoreButtonOnCustomerProfile();
        ICustomerPage.clickDeleteCustomer();
        ICustomerPage.clickDeleteOnDialogueOnEditProfilePage();
    }

    @And("click on  Discard changes on edit customer screen")
    public void clickOnDiscardChangesOnEditCustomerScreen() {
        ICustomerPage.clickDiscardChangesBtn();
    }

    @And("click on existing customer {string} to edit")
    public void clickOnExistingCustomerToEdit(String customerNameToOpen) {
        ICustomerPage.clickCustomerToOpenEditCard(customerNameToOpen);
    }


    @And("verify {string} is displayed on top when {string} is searched")
    public void verifyIsDisplayedOnTopWhenIsSearched(String expectedSearchedName, String enterSearchName) {
        ICustomerPage.clickSearchIcon();
        ICustomerPage.enterOrUpdateSearchInput(enterSearchName);
        ICustomerPage.clickSearchButton();
        String actualName= driver.findElement(By.xpath(".//tbody//tr[1]/td[2]/div/div/div/div[1]/span")).getText();
        Assert.assertEquals("Customer name is displayed on top",expectedSearchedName,actualName);
    }

    @And("add discount button is clicked")
    public void addDiscountButtonIsClicked() {
        IDiscountsPage.clickAddDiscount();
    }

    @And("update the pos name as {string}")
    public void updateThePosNameAs(String expectedPosNameToBeUpdated) {
        IPosDevicesPage.enterOrUpdatePosName(expectedPosNameToBeUpdated);
    }

    @And("open the pos {string} from the list page")
    public void openThePosFromTheListPage(String expectedPos) {
        IPosDevicesPage.openPosNameFromList(expectedPos);
    }

    @And("delete the pos from edit pos page")
    public void deleteThePosFromEditPosPage() {
        IPosDevicesPage.clickTrashButton();
    }

    @And("Add payment type button is clicked")
    public void addPaymentTypeButtonIsClicked() {
        IPaymentsTypePage.clickAddPaymentTypeButton();
    }


    @And("payment type and name is entered as {string} and {string}")
    public void paymentTypeAndNameIsEnteredAsAnd(String expectedPaymentType, String expectedPaymentName) {
            IPaymentsTypePage.selectPaymentType(expectedPaymentType);
            IPaymentsTypePage.enterOrUpdatePaymentTypeName(expectedPaymentName);
    }

    @And("click on {string} on unsaved changed popup")
    @And("click {string} on Payments type page")
    public void clickOnPaymentsTypePage(String expectedAction) {
        IPaymentsTypePage.saveOrCancelPaymentType(expectedAction);
    }


}
