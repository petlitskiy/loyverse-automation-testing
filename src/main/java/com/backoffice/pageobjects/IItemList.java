package com.backoffice.pageobjects;

import io.cucumber.datatable.DataTable;

import java.awt.*;
import java.net.URISyntaxException;

/**
 * @author Neha Kharbanda
 */
public interface IItemList {


    void clickAddItemBtn();

    void verifySaveItemBtnEnabled();

    void clickItemNameInput();

    void toggleTrackStockSwitch(String trackStockSwitchStatus);

    void enterOptionName(String optionName);

    void clickCompositeItemSwitch();

    void clickCompositeItemSearch();

    void updateCompositeItem(String quantity, String compositeItemSearch);

    void verifyTotalCostOfCompositeItem();

    void enterSupplier(String supplierName);

    void enterOptimalStock(String optimalStockValue);

    void enterStoreDetails(DataTable storeDetailsTable);

    void fetchAndValidateInStockValue(String expectedUpdatedInStockValue);

    void enterOptionValues(String variantType1, String variantType2);

    void selectTheStore(String expectedStoreName);

    void validateTrackStock();

    void emptyCostField();

    void enterCostValue(String costValue) throws InterruptedException;

    void verifyCostValue(String expectedCostValue);

    void clickSaveItemBtn() throws InterruptedException;

    void clickCancelItemBtn() throws InterruptedException;


    void enterItemName(String itemName);

    void selectCategory(String categoryName);

    void enterPriceValue(String priceValue);

    void enterSkuValue(String skuValue);

    void enterBarcode(String barcode);

    void enterDescription(String itemDescription);

    void enableTrackStockSwitch();

    void enterInStock(String inStockValue);

    void enterLowStock(String lowStockValue);

    void toggleModifierSwitch(String modifierStatus);

    void createNewItem(DataTable newItemDetails) throws InterruptedException;

    void clickAddVariant();
    
    //void verifyTaxNameAndValue(String expectedIncludedTaxNameAndValue);

    void uploadItemImage() throws AWTException, InterruptedException, URISyntaxException;

}
