package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface ICustomer {
    void clickAddCustomer();

    void enterOrUpdateCustomerName(String customerName);

    void enterOrUpdateCustomerEmail(String customerEmail);

    void enterOrUpdateCustomerPhone(String customerPhone);

    void enterOrUpdateCustomerAddress(String customerAddress);

    void enterOrUpdateCustomerCity(String customerCity);

    void enterOrUpdateCustomerState(String customerState);

    void enterOrUpdateCustomerZipCode(String customerZipcode);

    void selectCustomerCountry(String customerCountry);

    void enterOrUpdateCustomerCode(String customerCode);

    void enterOrUpdateCustomerNote(String customerNote);

    void clickCustomerCancel();

    void clickCustomerSave();

    void clickDeleteCustomer();

    void clickDeleteOnDialogue();

    void clickCustomerBaseOnCustomerProfile();

    public void verifyCustomerDeletionMessage(String expectedDeletionMessage);
    void clickMoreButtonOnCustomerProfile();
    void clickEditPointsBalanceBtn();
    void enterOrUpdateEditPoints(String editPointsValue);
    void clickSavePointsBtn();
    void verifyPointsOnCustomerProfile(String editPointsValue);
    void clickEditProfileBtn();
    void clickAddCustomerWhenCustomerPresent();
    void clickOKOnCodeExistsDialogue();
    void clickDeleteOnDialogueOnEditProfilePage();
    void clickDiscardChangesBtn();
    void clickCustomerToOpenEditCard(String customerNameToOpen);
    void clickSearchIcon();
    void enterOrUpdateSearchInput(String customerNameToOpen);
    void clickSearchButton();
}
