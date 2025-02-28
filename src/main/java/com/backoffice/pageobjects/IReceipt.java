package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface IReceipt {

    void selectReceiptOption();

    void updateHeaderAndFooter(String headerText, String footerText);

    void saveReceipt();

    void cancelReceipt();

    void verifyReceiptUpdatedMessage(String expectedReceiptUpdatedMessage);

    void selectReceiptLanguage(String receiptLanguage);

    void clickShowCommentsIcon();

    void uploadEmailedReceipts();

    void uploadPrintedReceipts();

    void clickShowCustInfoIcon();

}
