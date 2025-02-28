package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface IIntegrations {

    void clickIntegrationCategory();

    void clickAccessTokens();

    void clickAddAccessTokens();

    void enterAccessTokenName(String accessTokenName);

    void disableExpiryDate();

    void clickSaveToken();

    String fetchAccessTokens();
}
