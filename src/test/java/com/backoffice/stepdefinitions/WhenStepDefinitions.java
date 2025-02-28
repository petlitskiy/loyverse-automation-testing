package com.backoffice.stepdefinitions;

import com.backoffice.config.DriverFactory;
import com.backoffice.pageobjects.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * @author Neha Kharbanda
 */
public class WhenStepDefinitions {
    WebDriver driver;

    IBackOfficeLogin IbackOfficeLogin;
    IAccessRights IAccessRightsPage;
    ICategories ICategoriesPage;
    IModifiers IModifierPage;
    IItemList IItemListPage;
    IReceipt IReceiptPage;
    ICustomer ICustomerPage;
    IEmployeeList IEmployeeListPage;
    IStore IStorePage;
    ISupplier ISupplierPage;
    IMenu IMenuPage;
    ITransferOrder ITransferOrderPage;
    IPurchaseOrder IPurchaseOrderPage;
    IStockAdjustments IStockAdjustmentsPage;
    IDiscounts IDiscountsPage;
    IBillingAndSubscription IBillingAndSubscriptionPage;
    IBackOfficeLogin IBackOfficeLoginPage;
    IPaymentsType IPaymentsTypePage;
    private Logger log = LogManager.getLogger(WhenStepDefinitions.class);


    public WhenStepDefinitions() {
        driver = DriverFactory.getDriver();
        IbackOfficeLogin = new BackOfficeLoginImpl(driver);
        ICategoriesPage = new CategoriesImpl(driver);
        IModifierPage = new ModifiersImpl(driver);
        IItemListPage = new ItemListImpl(driver);
        ICustomerPage = new CustomerImpl(driver);
        IReceiptPage = new ReceiptImpl(driver);
        IEmployeeListPage = new EmployeeListImpl(driver);
        IAccessRightsPage = new AccessRightsImpl(driver);
        IStorePage = new StoreImpl(driver);
        ISupplierPage = new SupplierImpl(driver);
        IMenuPage = new MenuImpl(driver);
        IPurchaseOrderPage = new PurchaseOrderImpl(driver);
        ITransferOrderPage = new TransferOrderImpl(driver);
        IStockAdjustmentsPage = new StockAdjustmentsImpl(driver);
        IDiscountsPage = new DiscountsImpl(driver);
        IBillingAndSubscriptionPage = new BillingAndSubscriptionImpl(driver);
        IBackOfficeLoginPage = new BackOfficeLoginImpl(driver);
        IPaymentsTypePage = new PaymentTypeImpl(driver);
    }

    @When("user navigates to login page")
    public void userNavigatesToLoginPage() {
        IbackOfficeLogin.navigateToLogin();
    }

    @When("user navigates to {string} page")
    public void userNavigatesToPage(String serviceType) {
        IbackOfficeLogin.navigateToApplication(serviceType);
    }

    @When("categories option is selected")
    public void categoriesOptionIsSelected() {
        ICategoriesPage.selectCategoryOption();
    }


    @When("modifiers option is selected")
    public void modifiersOptionIsSelected() {
        try {
            IModifierPage.selectModifiersOption();
        } catch (Exception eMessage) {
            eMessage.printStackTrace();
        }
    }


    @When("add customer button is clicked")
    public void addCustomerButtonIsClicked() {
        ICustomerPage.clickAddCustomer();
    }

    @When("the receipt option is selected")
    public void theReceiptOptionIsSelected() {
        IReceiptPage.selectReceiptOption();
    }

    @When("the Employee List option is selected")
    public void theEmployeeListOptionIsSelected() {
        IEmployeeListPage.selectEmployeeListOption();
    }

    @When("save is clicked on discount page")
    public void saveIsClickedOnDiscountPage() {
        IDiscountsPage.saveDiscountBtn();
    }

    @When("save is clicked on create item page")
    public void saveIsClickedOnCreateItemPage() throws InterruptedException {
        IItemListPage.clickSaveItemBtn();
    }

    @When("save button is clicked on create store page")
    public void saveButtonIsClickedOnCreateStorePage() throws InterruptedException {
        Thread.sleep(1000);
        IStorePage.clickSaveStore();
    }

    @When("save button is clicked on create supplier page")
    public void saveButtonIsClickedOnCreateSupplierPage() {
        ISupplierPage.clickSaveSupplier();
    }

    @When("the purchase order option is selected")
    public void thePurchaseOrderOptionIsSelected() {
        IPurchaseOrderPage.selectPurchaseOrderOption();
    }

    @When("order is received for quantity {string}")
    public void orderIsReceivedForQuantity(String itemQuantityToBeReceived) {
        IPurchaseOrderPage.clickReceivePurchaseOrderButton();
        IPurchaseOrderPage.enterReceivedQuantity(itemQuantityToBeReceived);
        IPurchaseOrderPage.clickReceiveItemButton();

    }

    @When("transfer order is created with status {string}")
    public void transferOrderIsCreatedWithStatus(String expectedTransferOrderStatus) {
        ITransferOrderPage.clickCreateTransferOrderButton();
        ITransferOrderPage.validateTransferOrderStatus(expectedTransferOrderStatus);
    }

    @When("stock adjustments reason is selected as {string}, item as {string}and remove stock quantity as {string}")
    public void stockAdjustmentsReasonIsSelectedAsItemAsAndRemoveStockQuantityAs(String expectedAdjustmentReason, String expectedItemToBeAdjusted, String removeStockQuantity) throws InterruptedException {
        IStockAdjustmentsPage.selectReasonForStockAdjustment(expectedAdjustmentReason);
        IStockAdjustmentsPage.selectItemForStockAdjustment(expectedItemToBeAdjusted);
        IStockAdjustmentsPage.enterRemoveStockQuantity(removeStockQuantity);
    }

    @When("stock adjustments details is selected as {string},item as {string}, AddStock as {string},Cost as {string} and Counted Stock as {string}")
    public void stockAdjustmentsDetailsIsSelectedAsItemAsAddStockAsCostAsAndCountedStockAs(String expectedAdjustmentReason, String expectedItemToBeAdjusted, String expectedAddStockQuantity, String expectedCost, String expectedCountedStock) throws InterruptedException {
        IStockAdjustmentsPage.selectReasonForStockAdjustment(expectedAdjustmentReason);
        IStockAdjustmentsPage.selectItemForStockAdjustment(expectedItemToBeAdjusted);
        IStockAdjustmentsPage.enterAddStockQuantity(expectedAddStockQuantity);
        IStockAdjustmentsPage.enterCostForSelectedItem(expectedCost);
        IStockAdjustmentsPage.enterCountedStockValue(expectedCountedStock);
    }

    @When("billing and subscription option is clicked")
    public void billingAndSubscriptionOptionIsClicked() {
        IBillingAndSubscriptionPage.clickBillingAndSubscriptionOption();

    }

    @When("the user submits the following registration details:")
    public void theUserSubmitsTheFollowingRegistrationDetails(DataTable registrationDetails) {
        IBackOfficeLoginPage.enterRegistrationDetails(registrationDetails);
    }

    @When("payment types option is clicked under settings")
    public void paymentTypesOptionIsClickedUnderSettings() {
        IPaymentsTypePage.clickPaymentsTypesOption();
    }


}

