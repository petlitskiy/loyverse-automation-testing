package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface ISupplier {

    void selectSupplierOption();

    void clickAddSupplierButton();

    void enterSupplierName(String supplierName);

    void enterSupplierContact(String supplierContact);

    void enterSupplierEmail(String supplierEmail);

    void enterSupplierPhone(String supplierPhone);

    void enterSupplierWebsite(String supplierWebsite);

    void enterSupplierAddress1(String supplierAddress1);

    void enterSupplierAddress2(String supplierAddress2);

    void enterSupplierCity(String supplierCity);

    void enterSupplierZipCode(String supplierZipCode);

    void enterSupplierCountry(String supplierCountry);

    void enterSupplierRegion(String supplierRegion);

    void enterSupplierNote(String supplierNote);

    void clickSaveSupplier();

    void verifySaveSupplierBtnEnabled();
}
