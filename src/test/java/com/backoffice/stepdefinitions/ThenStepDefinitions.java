package com.backoffice.stepdefinitions;

import com.backoffice.config.DriverFactory;
import com.backoffice.pageobjects.*;
import com.backoffice.provider.TestContextProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * @author Neha Kharbanda
 */
public class ThenStepDefinitions {
    private final Logger log = LogManager.getLogger(ThenStepDefinitions.class);
    WebDriver driver;
    IAccessRights IAccessRightsPage;
    ITaxes ITaxesPage;
    IItemList IItemListPage;
    IDiscounts IDiscountsPage;
    ICustomer ICustomerPage;
    IReceipt IReceiptPage;
    ISupplier ISupplierPage;
    IMenu IMenuPage;
    IPurchaseOrder IPurchaseOrderPage;
    ITransferOrder ITransferOrderPage;
    IStockAdjustments IStockAdjustmentsPage;
    IStore IStorePage;
    IPosDevices IPosDevicesPage;

    public ThenStepDefinitions() {
        driver = DriverFactory.getDriver();
        IMenuPage = new MenuImpl(driver);
        IAccessRightsPage = new AccessRightsImpl(driver);
        ITaxesPage = new TaxesImpl(driver);
        IItemListPage = new ItemListImpl(driver);
        IDiscountsPage = new DiscountsImpl(driver);
        ICustomerPage = new CustomerImpl(driver);
        IReceiptPage = new ReceiptImpl(driver);
        ISupplierPage = new SupplierImpl(driver);
        IPurchaseOrderPage = new PurchaseOrderImpl(driver);
        ITransferOrderPage = new TransferOrderImpl(driver);
        IStockAdjustmentsPage = new StockAdjustmentsImpl(driver);
        IStorePage = new StoreImpl(driver);
        IPosDevicesPage = new PosDevicesImpl(driver);
    }

    @Then("Access Right screen must be displayed")
    public void access_right_screen_must_be_displayed() {
        IAccessRightsPage.verifyAccessRightScreen();
    }

    @Then("verify the tax value is populated in tax input as {string}")
    public void verifyTheTaxValueIsPopulatedInTaxInputAs(String expectedTax) {
        ITaxesPage.verifyTaxValue(expectedTax);
    }


    @Then("verify the monetary value \\(Cost) is reflected as {string}")
    public void verifyTheMonetaryValueCostIsReflectedAs(String expectedCostValue) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        IItemListPage.verifyCostValue(expectedCostValue);
    }

    @Then("verify the system has populated zero and value is {string}")
    public void verifyTheSystemHasPopulatedZeroAndValueIs(String expectedCost) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        IItemListPage.verifyCostValue(expectedCost);
    }

    @Then("verify the cost input accepts only the accepted length as {string}")
    public void verifyTheCostInputAcceptsOnlyTheAcceptedLengthAs(String expectedCostLength) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        IItemListPage.verifyCostValue(expectedCostLength);
    }

    @Then("verify the discount is populated as {string}")
    public void verifyTheDiscountIsPopulatedAs(String expectedDiscountValue) {
        IDiscountsPage.verifyDiscountValue(expectedDiscountValue);
    }

    @Then("verify error message is displayed as  {string}")
    public void verifyErrorMessageIsDisplayedAs(String errorMessageForDiscountValue) {
        IDiscountsPage.verifyDiscountErrorMessage(errorMessageForDiscountValue);
    }

    @Then("verify the discount {string} of type {string} is displayed with value {string} and restricted access {string} on discounts page")
    public void verifyTheDiscountOfTypeIsDisplayedWithValueAndRestrictedAccessOnDiscountsPage(String expectedDiscount, String expectedDiscountType, String expectedDiscountValue, String expectedRestrictedAccessStatus) {
        IDiscountsPage.verifyDiscounts(expectedDiscountValue, expectedDiscountType, expectedDiscount, expectedRestrictedAccessStatus);
    }

    @Then("verify category is created as {string}")
    public void verifyCategoryIsCreatedAs(String expectedCategoryName) {
        WebElement htmlTable = driver.findElement(By.xpath("//table[@class='table-list table-list-goods']/tbody"));
        List<WebElement> rows = htmlTable.findElements(By.tagName("tr"));
        for (int rNum = 0; rNum < rows.size(); rNum++) {
            List<WebElement> columns = rows.get(rNum).findElements(By.tagName("td"));
            for (int cNum = 1; cNum <= columns.size(); cNum++) {
                String fetchColumnText = driver.findElement(By.xpath("//table[@class='table-list table-list-goods']/tbody/tr/td[" + cNum + "]")).getText();
                if (fetchColumnText.equals("")) {
                    log.info("continue execution");
                } else {
                    String actualCategoryName = driver.findElement(By.xpath("//table[@class='table-list table-list-goods']/tbody/tr/td[" + cNum + "]/div")).getText();
                    Assert.assertEquals(expectedCategoryName, actualCategoryName);
                    log.info("Category is present on category list page as : " + expectedCategoryName);
                }

            }
        }

    }


    @Then("verify modifier is created as {string}")
    public void verifyModifierIsCreatedAs(String expectedModifierName) {
        String actualModifierName = driver.findElement(By.xpath("//table[@class='table table-list table-hover']/tbody/tr/td/div[2]/div[1]")).getText();
        Assert.assertEquals(expectedModifierName, actualModifierName);
    }


    @Then("verify the item is created on item list page")
    public void verifyTheItemIsCreatedOnItemListPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr[1]/td[6]/div/div/div/div/span")));
        JSONObject expectedItemDetails = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(TestContextProvider.getContext().getScenarioContext().getContext("itemData")));
        String expectedCreatedItemName = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdItemName");
        String expectedCategory = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdCategoryName");
        String expectedItemInStock = (String) TestContextProvider.getContext().getScenarioContext().getContext("InStockQuantity");
        String expectedItemPrice = (String) expectedItemDetails.get("price");
        String expectedItemCost = (String) expectedItemDetails.get("cost");

        String actualCreatedItemName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[7]/div/div/ui-view/div/div[2]/div[2]/div/table/tbody/tr/td[3]/div"))).getText();

        WebElement category = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div/lv-select/div/div[1]"));
        String actualCategory = category.getText();
        String actualItemPrice = driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div/div")).getText().substring(1);
        String actualItemCost = driver.findElement(By.xpath("//tbody/tr[1]/td[6]/div/div/div/div/span")).getText().substring(1);
        String actualItemInStock = driver.findElement(By.xpath(" //tbody/tr[1]/td[8]/div/div/div/div/div/span")).getText();
        Assert.assertEquals(expectedCreatedItemName, actualCreatedItemName);
        Assert.assertEquals(expectedCategory, actualCategory);
        Assert.assertEquals(expectedItemPrice, actualItemPrice);
        Assert.assertEquals(expectedItemCost, actualItemCost);
        Assert.assertEquals(expectedItemInStock, actualItemInStock);
        log.info("Created item {} is present on the item list page", actualCreatedItemName);
    }

   /* @Then("verify the included tax {string} is present and {string} by default.")
    public void verifyTheIncludedTaxIsPresentAndByDefault(String expectedIncludedTaxNameAndValue, String expectedTaxStatusIncluded) {
        IItemListPage.verifyTaxNameAndValue(expectedIncludedTaxNameAndValue);
        IItemListPage.verifyTaxMdSwitchStatus(expectedTaxStatusIncluded);
    }*/




    @Then("I should see the message {string} is displayed")
    public void iShouldSeeTheMessageIsDisplayed(String expectedReceiptSavedMessage) {
        IReceiptPage.verifyReceiptUpdatedMessage(expectedReceiptSavedMessage);
    }

    @Then("free trial to advanced inventory is opted")
    public void freeTrialToAdvancedInventoryIsOpted() {
        IMenuPage.startFreeInventoryTrail();
    }


    @Then("verify {string} store is created with address details as {string} with number of pos as {string}")
    public void verifyStoreIsCreatedWithAddressDetailsAsWithNumberOfPosAs(String expectedStoreName, String expectedStoreAddress, String expectedStoreNumberOfPos) {
        IStorePage.verifyStoreIsCreatedOnListPage(expectedStoreName, expectedStoreAddress, expectedStoreNumberOfPos);
    }

    @Then("verify the employee {string} is displayed on the Employee List page")
    public void verifyTheEmployeeIsDisplayedOnTheEmployeeListPage(String expectedEmployName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String actualEmployeeName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[2]/td[2]//span"))).getText();
        Assert.assertEquals(expectedEmployName, actualEmployeeName);
        log.info("Employee {} is present on employee list page", expectedEmployName);
    }

    @Then("verify the item and variant details are populated on item list page.")
    public void verifyTheItemAndVariantDetailsArePopulatedOnItemListPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        String actualCreatedItemName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[7]/div/div/ui-view/div/div[2]/div[2]/div/table/tbody/tr/td[3]/div"))).getText();
        String expectedName = "Supreme Pizza";
        Assert.assertEquals(expectedName, actualCreatedItemName);
    }



    @Then("verify the supplier and its details are listed on the supplier page")
    public void verifyTheSupplierAndItsDetailsAreListedOnTheSupplierPage() {
        JSONObject expectedSupplierDetails = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(TestContextProvider.getContext().getScenarioContext().getContext("supplierData")));
        String expectedSupplierName = (String) expectedSupplierDetails.get("supplierName");
        String expectedSupplierContact = (String) expectedSupplierDetails.get("supplierContact");
        String expectedSupplierPhoneNumber = (String) expectedSupplierDetails.get("supplierPhone");
        String expectedSupplierEmail = (String) expectedSupplierDetails.get("supplierEmail");
        WebElement supplierListTable = driver.findElement(By.xpath("//tbody"));
        List<WebElement> getRows = supplierListTable.findElements(By.tagName("tr"));
        int totalRowCount = getRows.size();
        for (int i = 0; i < totalRowCount; i++) {
            String actualSupplierName = driver.findElement(By.xpath("//tbody/tr[" + (i + 1) + "]/td[2]//./div")).getText();
            if (actualSupplierName.equals(expectedSupplierName)) {
                String actualSupplierContact = driver.findElement(By.xpath("//tbody/tr[" + (i + 1) + "]/td[3]//./span")).getText();
                String actualSupplierPhone = driver.findElement(By.xpath("//tbody/tr[" + (i + 1) + "]/td[4]//./span")).getText();
                String actualSupplierEmail = driver.findElement(By.xpath("//tbody/tr[" + (i + 1) + "]/td[5]//./span")).getText();
                Assert.assertEquals(expectedSupplierName, actualSupplierName);
                Assert.assertEquals(expectedSupplierContact, actualSupplierContact);
                Assert.assertEquals(expectedSupplierPhoneNumber, actualSupplierPhone);
                Assert.assertEquals(expectedSupplierEmail, actualSupplierEmail);
                log.info("Supplier is created as {} and details are verified on supplier list page", expectedSupplierName);
            }
        }
    }

    @Then("verify the PO is added to the Purchase Orders screen with status {string}")
    public void verifyThePOIsAddedToThePurchaseOrdersScreenWithStatus(String expectedPurchaseOrderStatus) {

        WebElement purchaseOrderTable = driver.findElement(By.xpath("//tbody"));
        List<WebElement> getRows = purchaseOrderTable.findElements(By.tagName("tr"));
        int totalRowCount = getRows.size();
        for (int i = 0; i < totalRowCount; i++) {
            String actualOrderStatus = driver.findElement(By.xpath("//tbody/tr[1]/td[5]/div")).getText();
            Assert.assertEquals(expectedPurchaseOrderStatus, actualOrderStatus);
        }
    }

    @Then("verify the TO is listed on Transfer Orders screen with status {string}")
    public void verifyTheTOIsListedOnTransferOrdersScreenWithStatus(String expectedTOStatus) {
        ITransferOrderPage.verifyTransferOrderCreated(expectedTOStatus);
    }


    @Then("the stock adjustment for {string} with adjusted quantity as {string} is created")
    public void theStockAdjustmentForWithAdjustedQuantityAsIsCreated(String expectedAdjustmentReason, String expectedRemoveStockQuantity) {
        IStockAdjustmentsPage.clickAdjustButton();
        IStockAdjustmentsPage.verifyAdjustmentIsCreated(expectedAdjustmentReason, expectedRemoveStockQuantity);
    }


    @Then("verify the save button is enabled on item page")
    public void verifyTheSaveButtonIsEnabledOnItemPage() {
        IItemListPage.verifySaveItemBtnEnabled();

    }


    @Then("verify Backoffice is active")
    public void verifyBackofficeIsActive() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000)); // Adjust the timeout as needed

        boolean isClickable = wait.until(ExpectedConditions.elementToBeClickable(By.id("lv_menu_item_report_expanded"))).isEnabled();

        // Check if the element is not visible (greyed out or inactive)
        boolean isInactive = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("lv_menu_item_report_expanded")));

        if (isClickable) {
            System.out.println("Back Office is Active.");
        } else {
            System.out.println("Back Office is blocked.");
        }

        if (isInactive) {
            System.out.println("Back Office is blocked.");
        } else {
            System.out.println("Back Office is Active.");
        }

    }


    //to be updated after adding th id's
    @Then("assert error message is displayed {string} for Tax Name and Tax Rate")
    public void assertErrorMessageIsDisplayedForTaxNameAndTaxRate(String expectedErrorMessage) {

        try {
            String nameXpathExpression = "//*[@id='taxName']/ancestor::div[1]/following-sibling::div//div[text()='" + expectedErrorMessage + "']";
            By nameXpath = By.xpath(nameXpathExpression);
            WebElement nameElement = driver.findElement(nameXpath);
            String actualNameErrorMessage = nameElement.getText();
            assert expectedErrorMessage.equals(actualNameErrorMessage) : "Expected name error message: " + expectedErrorMessage + ", but found: " + actualNameErrorMessage;

            String rateXpathExpression = "//*[@id='taxValue']/following-sibling::div//div[text()='" + expectedErrorMessage + "']";
            By taxRateXpath = By.xpath(rateXpathExpression);
            WebElement rateElement = driver.findElement(taxRateXpath);
            String actualRateErrorMessage = rateElement.getText();
            assert expectedErrorMessage.equals(actualRateErrorMessage) : "Expected tax rate error message: " + expectedErrorMessage + ", but found: " + actualRateErrorMessage;

        } catch (Exception ex) {
            log.error("Assertion doesnt match for name and tax rate error");
            throw new RuntimeException("An unexpected error occurred for name and tax rate error");
        }
    }

    @Then("verify the pos {string} is created with store {string} on pos list page with status {string}")
    public void verifyThePosIsCreatedWithStoreOnPosListPageWithStatus(String expectedPosDeviceName, String expectedPosStore, String expectedPosStatus) {
        IPosDevicesPage.verifyPosIsCreatedOnListPage(expectedPosDeviceName, expectedPosStore, expectedPosStatus);
    }
}