package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface IPosDevices {
    void enterOrUpdatePosName(String posName);

    void clickOnSavePosButton();

    void clickOnAddPosButton();

    void clickOnPosDevicesOption();

    void selectStoreName(String posStoreName);

    void clickTrashButton();

    void fetchAndValidatePosStore(String expectedListedPosStoreName);

    void verifyPosIsCreatedOnListPage(String expectedPosName, String expectedPosStoreName, String expectedPosStatus);

    void openPosNameFromList(String expectedPos);
}
