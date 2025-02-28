package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface IStockAdjustments {
    void selectStockAdjustmentsOption();

    void clickAddStockAdjustmentsButton();

    void selectReasonForStockAdjustment(String expectedReason);

    void selectItemForStockAdjustment(String itemToBeAdjusted) throws InterruptedException;

    void enterRemoveStockQuantity(String removeStockQuantity);

    void clickAdjustButton();

    void enterAddStockQuantity(String expectedAddStockQuantity);

    void enterCostForSelectedItem(String expectedCost);

    void enterCountedStockValue(String expectedCountedStock);

    void verifyAdjustmentIsCreated(String expectedAdjustmentReason, String expectedRemoveStockQuantity);
}
