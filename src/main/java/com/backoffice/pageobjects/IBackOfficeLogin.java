package com.backoffice.pageobjects;

import io.cucumber.datatable.DataTable;

/**
 * @author Neha Kharbanda
 */
public interface IBackOfficeLogin {
    void navigateToLogin();

    void navigateToApplication(String expectedServiceType);

    void enterRegistrationDetails(DataTable registrationDetails);

    void clickAndRedirectStoreBadge(String badgeType, String expectedPageTitle);

    void enterUsername(String userName);

    void enterPassword(String pssword);

    void clickLoginButton();

    void clickNextOnBoardingDialogue();

    void clickCancelButtonFeatureSettings();

    void clickSaveButtonFeatureSettings();

    void verifyOnBoardingFeaturesDisabled(String featureName, String expectedStatus);

    void verifyOnBoardingFeaturesEnabled(String featureName, String expectedStatus);

    void updateFeatureSettings(String expectedFeatureName, String expectedFeatureStatus);

    void clickConfirmSetPinDialouge();

    void clickCancelSetPinDialouge();

    void setPinForTimeClock(String pinForTimeClock);
}
