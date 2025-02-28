package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface IDiscounts {

    void enterDiscountItemName(String discountName);

    void saveDiscountBtn();

    void clickAddDiscount();

    void selectDiscountType(String discountType);

    void selectDiscountOption();

    void enterDiscountByPercentage(String valueByPercentage) throws InterruptedException;

    void enterDiscountByAmount(String ValueByAmount);

    void selectRestrictedAccess(String restrictedAccessStatus);

    void verifyDiscounts(String expectedDiscountValue, String expectedDiscountType, String expectedDiscountName, String expectedRestrictedAccessStatus);

    void verifyDiscountValue(String expectedDiscountValue);

    void verifyDiscountErrorMessage(String errorMessageForDiscountValue);
}
