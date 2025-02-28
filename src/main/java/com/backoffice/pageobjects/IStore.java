package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface IStore {
    void selectStoresOption();

    void clickAddStoreButton();

    void enterOrUpdateStoreName(String storeName);

    void enterOrUpdateStoreAddress(String storeAddress);

    void enterOrUpdateStoreCity(String storeCity);

    void enterOrUpdateStoreState(String storeState);

    void enterOrUpdateStoreZipcode(String storeZipcode);

    void enterOrUpdateStoreCountry(String storeCountry);

    void enterOrUpdateStorePhone(String storePhone);

    void enterOrUpdateStoreDescription(String storeDescription);

    void clickSaveStore();

    void enterOrUpdateVatNumber(String storeVatNumber);

    void clickTrashButton();

    void clickConfirmDelete();

    void verifyStoreIsCreatedOnListPage(String expectedStoreName, String expectedStoreAddress, String expectedStoreNumberOfPos);

}
