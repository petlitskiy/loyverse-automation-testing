package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface IPurchaseOrder {

    void selectPurchaseOrderOption();

    void clickAddPurchaseOrderButton();

    void enterSupplierName(String supplierName);

    void enterExpectedDate();

    void enterPurchaseOrderNote(String purchaseOrderNote);

    void enterOrderItemQuantity(String itemOrderQuantity);

    void selectItemForPurchase(String purchaseOrderItem);

    void enterOrderItemCost(String itemOrderCost);

    void verifyTotalCostOfPurchaseOrder(String actualTotalCostOfPurchaseOrder);

    void clickCreatePurchaseOrderButton();

    void validatePurchaseOrderStatus(String expectedPurchaseOrderStatus);

    void clickReceivePurchaseOrderButton();

    void enterReceivedQuantity(String itemQuantityToBeReceived);

    void clickReceiveItemButton();

    void redirectToPurchaseOrdersScreen();
}
