package com.backoffice.stepdefinitions;

import com.backoffice.config.DriverFactory;
import com.backoffice.pageobjects.*;
import com.backoffice.provider.TestContextProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Map;

/**
 * @author Neha Kharbanda
 */
public class GivenStepDefinitions {
    Map<String, String> credsCache;
    WebDriver driver;
    IMenu IMenuPage;
    ISupplier ISupplierPage;
    // ITestContext textContext = new TestContextImpl();
    IBackOfficeLogin IBackOfficeLogin;
    private Logger log = LogManager.getLogger(GivenStepDefinitions.class);

    public GivenStepDefinitions() {
        driver = DriverFactory.getDriver();
        IBackOfficeLogin = new BackOfficeLoginImpl(driver);
        IMenuPage = new MenuImpl(driver);
        ISupplierPage = new SupplierImpl(driver);
    }


    @Given("new account is registered for country {string} at backoffice application via api request")
    public void newAccountIsRegisteredForCountryAtBackofficeApplicationViaApiRequest(String registrationCountry) throws IOException, ParseException {
        credsCache = TestContextProvider.getContext().getRestServiceManager().cabinetRegistration(registrationCountry);
        log.info("Country of registration is set as {}", registrationCountry);
    }


    @And("login credentials are entered")
    public void loginCredentialsAreEntered() throws InterruptedException {
        IBackOfficeLogin.enterUsername(credsCache.get("email"));
        IBackOfficeLogin.enterPassword(credsCache.get("password"));
        IBackOfficeLogin.clickLoginButton();
        credsCache.clear();
//        IBackOfficeLogin.clickRegistrationDialogue();
    }

    @Given("the customers category is clicked")
    public void theCustomersCategoryIsClicked() {
        IMenuPage.clickCustomersCategory();
    }

    @Given("the Employee Category is selected")
    public void theEmployeeCategoryIsSelected() {
        IMenuPage.clickEmployeesCategory();
    }

    @Given("the suppliers option is selected")
    public void theSuppliersOptionIsSelected() {
        ISupplierPage.selectSupplierOption();
    }
}
