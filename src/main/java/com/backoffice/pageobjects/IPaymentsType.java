package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface IPaymentsType {
    void clickPaymentsTypesOption();

    void clickAddPaymentTypeButton();

    void selectStoreName(String storeName);

    void selectPaymentType(String paymentType);

    void enterOrUpdatePaymentTypeName(String paymentTypeName);

    void saveOrCancelPaymentType(String expectedAction);

    void clickDeleteOnEditPaymentType();

    void clickDeleteOnDeleteDialouge();
}
