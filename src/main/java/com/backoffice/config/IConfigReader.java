package com.backoffice.config;

/**
 * @author Neha Kharbanda
 */
public interface IConfigReader {
    String getBrowserType();

    String getLoginServiceURL();

    public String getRegistrationServiceURL();

    String getApplicationType();

    String getAuthUserName();

    String getAuthPassword();

    String getCabinetRegistrationEndpoint();

    public String getTestType();

    public String getBrowserStackHubURL();

    public String getTrailSubscriptionURL();

    public String getXManagerLogin();

    public String getDevStoreAPIURL();

    public String getCreateWareAPIURL();

    public String getDevSupplierAPIURL();

    public String getInStockUpdateAPIURL();

    public String getProfileAPIURL();


}
