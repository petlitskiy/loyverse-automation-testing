package com.backoffice.config;

import com.backoffice.utilities.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Neha Kharbanda
 */
public class ConfigReader implements IConfigReader {


    // Fetch the test environment
    static final String configProperties =
            "config-" + (Objects.isNull(System.getenv("TEST_ENV")) ? Constants.DEFAULT_TEST_ENV : System.getenv("TEST_ENV")) + ".properties";
    public static Properties prop;
    private final Logger log = LogManager.getLogger(ConfigReader.class);


    //Load the configuration properties file

    public ConfigReader() {
        prop = new Properties();
        try {
            InputStream configFile = getClass().getClassLoader().getResourceAsStream("config/" + configProperties);
            if (configFile != null) {
                prop.load(configFile);
            } else {
                log.error("Properties file not found");
            }
        } catch (IOException e) {
            log.error("An error occurred while loading the configuration properties: {}", e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while loading the configuration properties: {}", e.getMessage());
        }
    }

    // Check if the browser type property is present
    @Override
    public String getBrowserType() {
        String browserType = prop.getProperty("browserType");
        if (!Objects.isNull(browserType)) {
            return browserType;
        } else {
            return StringUtils.EMPTY;
        }
    }

    // Check if the application type property is present
    @Override
    public String getApplicationType() {
        String applicationType = prop.getProperty("applicationType");
        if (!Objects.isNull(applicationType)) {
            return applicationType;
        } else {
            return StringUtils.EMPTY;
        }
    }

    // Check if the auth username property is present
    @Override
    public String getAuthUserName() {
        String authUserName = prop.getProperty("authUserName");
        if (!Objects.isNull(authUserName)) {
            return authUserName;
        } else {
            return StringUtils.EMPTY;
        }
    }

    // Check if the auth password property is present
    @Override
    public String getAuthPassword() {
        String authPassword = prop.getProperty("authPassword");

        if (!Objects.isNull(authPassword)) {
            return authPassword;
        } else {
            return StringUtils.EMPTY;
        }
    }

    // Check if the cabinet registration endpoint property is present
    @Override
    public String getCabinetRegistrationEndpoint() {
        String cabinetRegistrationEndpoint = prop.getProperty("cabinetRegistrationEndpoint");
        if (!Objects.isNull(cabinetRegistrationEndpoint)) {
            return cabinetRegistrationEndpoint;
        } else {
            return StringUtils.EMPTY;
        }
    }

    // Check if the test type property is present
    public String getTestType() {
        String testType = prop.getProperty("testType");
        if (!Objects.isNull(testType)) {
            return testType;
        } else {
            return StringUtils.EMPTY;
        }
    }

    // Check if the BrowserStack Hub URL property is present
    @Override
    public String getBrowserStackHubURL() {
        String browserStackHubUrl = prop.getProperty("browserStackHubUrl");
        if (!Objects.isNull(browserStackHubUrl)) {
            return browserStackHubUrl;
        } else {
            return StringUtils.EMPTY;
        }
    }

    public String getLoginServiceURL() {
        String serviceURL;
        switch (System.getenv("TEST_ENV")) {
            case "qa": {
                serviceURL = prop.getProperty("qa1GuiURL").trim();
                break;
            }
            case "m3": {
                serviceURL = prop.getProperty("m3GuiURL".trim());
                break;
            }
            case "m1": {
                serviceURL = prop.getProperty("m1GuiURL".trim());
                break;
            }
            case "dev": {
                serviceURL = prop.getProperty("devGuiURL").trim();
                break;
            }
            case "local": {
                serviceURL = prop.getProperty("localGuiURL").trim();
                break;
            }
            default: {
                log.error("Test environment is undefined. Please define test environment variable as local or remote");
                throw new RuntimeException("Test environment is undefined. Please define test environment variable as local or remote");
            }
        }
        return serviceURL;
    }


    public String getRegistrationServiceURL() {
        String registrationURL;
        switch (System.getenv("TEST_ENV")) {
            case "qa": {
                registrationURL = prop.getProperty("qaGuiRegistrationURL").trim();
                break;
            }
            case "m3": {
                registrationURL = prop.getProperty("m3GuiRegistrationURL").trim();
                break;
            }
            case "m1": {
                registrationURL = prop.getProperty("m1GuiRegistrationURL").trim();
                break;
            }
            case "dev": {
                registrationURL = prop.getProperty("devGuiRegistrationURL").trim();
                break;
            }
            case "local": {
                registrationURL = prop.getProperty("localGuiRegistrationURL").trim();
                break;
            }
            default: {
                log.error("Test environment is undefined. Please define test environment variable as local or remote");
                throw new RuntimeException("Test environment is undefined. Please define test environment variable as local or remote");
            }
        }
        return registrationURL;
    }

    @Override
    public String getTrailSubscriptionURL() {
        String trailSubscriptionURL = prop.getProperty("setTrailSubscriptionURL");
        if (!Objects.isNull(trailSubscriptionURL)) {
            return trailSubscriptionURL;
        } else {
            return StringUtils.EMPTY;
        }
    }

    @Override
    public String getXManagerLogin() {
        String xManagerLogin = prop.getProperty("X-Manager-Log");
        if (!Objects.isNull(xManagerLogin)) {
            return xManagerLogin;
        } else {
            return StringUtils.EMPTY;
        }
    }

    @Override
    public String getDevStoreAPIURL() {
        String storeAPIURL = prop.getProperty("devStoreURL");
        if (!Objects.isNull(storeAPIURL)) {
            return storeAPIURL;
        } else {
            return StringUtils.EMPTY;
        }
    }

    @Override
    public String getDevSupplierAPIURL() {
        String supplierAPIURL = prop.getProperty("devSupplierAPIURL");
        if (!Objects.isNull(supplierAPIURL)) {
            return supplierAPIURL;
        } else {
            return StringUtils.EMPTY;
        }
    }

    @Override
    public String getCreateWareAPIURL() {
        String createWareAPIURL = prop.getProperty("devCreateWareURL");
        if (!Objects.isNull(createWareAPIURL)) {
            return createWareAPIURL;
        } else {
            return StringUtils.EMPTY;
        }
    }

    @Override
    public String getProfileAPIURL() {
        String getProfileAPIURL = prop.getProperty("devGetProfileAPI");
        if (!Objects.isNull(getProfileAPIURL)) {
            return getProfileAPIURL;
        } else {
            return StringUtils.EMPTY;
        }
    }

    @Override
    public String getInStockUpdateAPIURL() {
        String getInStockUpdateAPIURL = prop.getProperty("devAddInStockValueAPIURL");
        if (!Objects.isNull(getInStockUpdateAPIURL)) {
            return getInStockUpdateAPIURL;
        } else {
            return StringUtils.EMPTY;
        }
    }
}
