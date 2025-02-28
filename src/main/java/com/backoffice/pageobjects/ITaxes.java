package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface ITaxes {

    void selectTaxesOption();

    void clickAddTaxButton();

    void  enterOrUpdateTaxName(String newTaxName);

    void clickAddTaxOnListPage();

    void enterOrUpdateTaxValue(String taxValue);

    void saveTax();

    void selectTaxType (String taxType);

    void selectTaxOption(String taxOption);

    void clickOkForExistingItemOption();

    void selectTheTaxFromList(String expectedTaxName);

    void verifyTheTaxFromList(String expectedTaxName,String expectedAppliedToItem, String expectedTaxValue) throws InterruptedException;

    void clickDeleteTaxButton();

    void verifyTaxValue(String expectedTax);
}
