package com.backoffice.managers;

import com.backoffice.pageobjects.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * @author Neha Kharbanda
 */
public class PageObjectManager implements IPageObjectManager {

    private final WebDriver driver;
    private Logger log = LogManager.getLogger(PageObjectManager.class);
    private IBackOfficeLogin backOfficeLogin;
    private IMenu menu;
    private IAccessRights accessRights;
    private IAccounts accounts;
    private IItemList itemList;
    private IDiscounts discounts;
    private ICategories categories;
    private IEmployeeList employeeList;
    private IModifiers modifiers;
    private ITaxes taxes;
    private IReceipt receipts;
    private ICustomer customers;
    private IPosDevices posDevices;
    private IPaymentsType paymentsType;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public IBackOfficeLogin getBackOfficeLoginPage() {

        return backOfficeLogin = new BackOfficeLoginImpl(driver);
    }

    @Override
    public IMenu getMenuPage() {
        return menu = new MenuImpl(driver);
    }

    @Override
    public IAccessRights getAccessRightsPage() {
        return accessRights = new AccessRightsImpl(driver);
    }

    @Override
    public IAccounts getAccountPage() {
        return accounts = new AccountsImpl(driver);
    }

    @Override
    public ICategories getCategoriesPage() {
        return categories = new CategoriesImpl(driver);
    }

    @Override
    public IDiscounts getDiscountsPage() {
        return discounts = new DiscountsImpl(driver);
    }

    @Override
    public IEmployeeList getEmployeeListPage() {
        return employeeList = new EmployeeListImpl(driver);
    }

    @Override
    public IItemList getItemListPage() {
        return itemList = new ItemListImpl(driver);
    }

    @Override
    public ITaxes getITaxesPage() {
        return taxes = new TaxesImpl(driver);
    }

    @Override
    public IModifiers getModifiersPage() {
        return modifiers = new ModifiersImpl(driver);
    }

    @Override
    public IReceipt getReceiptPage() {
        return receipts = new ReceiptImpl(driver);
    }

    @Override
    public ICustomer getCustomersPage() {
        return customers = new CustomerImpl(driver);
    }

    @Override
    public IPosDevices getPosDevicePage() {
        return posDevices = new PosDevicesImpl(driver);
    }

    @Override
    public IPaymentsType getPaymentsTypePage() {
        return paymentsType = new PaymentTypeImpl(driver);
    }


}
