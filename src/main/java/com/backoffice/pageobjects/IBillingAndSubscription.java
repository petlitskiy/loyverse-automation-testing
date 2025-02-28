package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface IBillingAndSubscription {

    void clickBillingAndSubscriptionOption();

    void startTrialSubscriptions(String subscriptionType);

    void clickAddEmployeeBtn();

    void clickStartFreeTrialBtn();

    void clickAgreeAllTermsCheckbox();

    void clickAddPaymentMethod();

    void enterCardNumber(String cardNumber);

    void enterCardExpiryDate(String expiryDate);

    void enterCardCVV(String cardCVV);

    void clickSavePaymentBtn();

    void clickUpdatePaymentMethod();

    void activateSubscriptions(String planType, String subscriptionType) throws InterruptedException;

    void enableInventoryPricingPlan(String planType);

    void clickActivateButton();

    void clickConfirmInvoicePreviewBtn();

    void enableIntegrationPricingPlan(String planType);

    void clickCancelInvoicePreviewBtn();

    void unsubscribeSubscriptions(String subscriptionType);

    void clickUnSubscribeIntegrationBtn();

    void verifySuccessPaidInvoiceGenerated(String expectedServiceType, String expectedServicePrice);
}
