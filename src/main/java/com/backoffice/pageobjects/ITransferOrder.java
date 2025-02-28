package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface ITransferOrder {

    void selectTransferOrderOption();

    void clickAddTransferOrderButton();

    void enterSourceStore(String sourceStore);

    void enterDestinationStore(String destinationStore);

    void clickDialogueBoxReceiveButton();

    void redirectToTransferOrderListScreen();

    void selectItemForTransferOrder(String transferOrderItem) throws InterruptedException;

    void enterItemQuantity(String orderItemQuantity);

    void clickCreateTransferOrderButton();

    void validateTransferOrderStatus(String expectedTransferOrderStatus);

    void clickReceiveTransferOrderButton();

    void verifyTransferOrderCreated(String expectedTransferOrderStatus);
}
