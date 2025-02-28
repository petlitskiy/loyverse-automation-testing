package com.backoffice.pageobjects;

/**
 * This interface represents the Accounts functionality.
 * Author: Neha Kharbanda
 */
public interface IAccounts {
    void enterBusinessName(String name);

    void enterEmail(String emailID);

    void enterPassword(String password);

    void selectCurrency(String currency, String cents);

    void selectTimezone(String timezone);

    void selectLanguage(String language);

    void clickSaveButton();

}
