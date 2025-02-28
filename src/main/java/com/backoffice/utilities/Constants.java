package com.backoffice.utilities;

/**
 * @author Neha Kharbanda
 */
public class Constants {

    // api status results
    public static final String DEFAULT_TEST_ENV = "dev";
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;

    // set chrome driver
    public static final String LOCAL = "local";
    public static final String REMOTE = "remote";
    public static final String CHROME = "chrome";
    public static final String SAFARI = "safari";
    public static final String FIREFOX = "firefox";

    // navigate to login
    public static final String LOGIN_SERVICE = "login";
    public static final String REGISTRATION_SERVICE = "registration";
    //selenium wait
    public static final long PAGE_LOAD = 60;
    public static final long IMPLICIT_WAIT = 20;
    public static final long EXPLICIT_WAIT = 20;

    // trail subscription change api request
    public static final String OWNER_ID = "ownerId";
    public static final String TRAIL_START = "trialStart";
    public static final String TRIAL_END = "trialEnd";
    public static final String SUBSCRIPTION_TYPE = "type";
    public static final String SUBSCRIPTION_STATUS = "subscription";

    // badge options
    public static final String GOOGLE_PLAY = "Google Play";
    public static final String APPLE_PLAY = "Apple Play";

    //Api response Constants
    public static final String STORE_UUID = "stores[0].id";
    public static final String SUPPLIER_ID = "suppliers[0].id";

    //API Item Constants
    public static final String STORE_ID = "enterStoreUUID";
    public static final String PRICING_TYPE = "FIXED";
    public static final double ITEM_PRICE = 10.00;
    public static final double OPTIMAL_STOCK = 10.00;
    public static final double LOW_STOCK = 5.00;
    public static final String OPTION_2_VALUE = "MEDIUM";
    public static final double VARIANT_COST = 18.00;
    public static final double VARIANT_DEFAULT_PRICE = 20.00;
    public static final String ITEM_NAME = "Test Item";
    public static final String ITEM_OPTION2_NAME = "Size";
    public static final String MODIFIERS_ID_1 = "3771";

    //API Supplier Constants
    public static final String SUPPLIER_NAME = "Happy And Co";
    public static final String SUPPLIER_CONTACT = "Rodrigo";
    public static final String SUPPLIER_EMAIL = "Mark@mailinator.com";
    public static final String SUPPLIER_PHONE_NUMBER = "7878989979";
    public static final String SUPPLIER_WEBSITE = "marksHomeFoods.com";
    public static final String SUPPLIER_ADDRESS_1 = "Lane 2";
    public static final String SUPPLIER_ADDRESS_2 = "Victoria Road";
    public static final String SUPPLIER_CITY = "London";
    public static final String SUPPLIER_REGION = "Zone 5";
    public static final String SUPPLIER_POSTAL_CODE = "WM3 T6Q";
    public static final String SUPPLIER_COUNTRY_CODE = "gb";
    public static final String SUPPLIER_NOTE = "Supplier deals with home manufactured products";


    //request header Constants
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String COOKIE_HEADER = "Cookie";
    public static final String JSESSION_ID_HEADER = "JSESSIONID";
    public static final String APPLICATION_JSON_HEADER = "application/json";


}
