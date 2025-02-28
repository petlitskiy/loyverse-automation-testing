package com.backoffice.managers;

import com.backoffice.pageobjects.*;

/**
 * @author Neha Kharbanda
 */
public interface IPageObjectManager {
    IBackOfficeLogin getBackOfficeLoginPage();

    IMenu getMenuPage();

    IAccessRights getAccessRightsPage();

    IAccounts getAccountPage();

    ICategories getCategoriesPage();

    IDiscounts getDiscountsPage();

    IEmployeeList getEmployeeListPage();

    IItemList getItemListPage();

    ITaxes getITaxesPage();

    IModifiers getModifiersPage();

    IReceipt getReceiptPage();

    ICustomer getCustomersPage();

    IPosDevices getPosDevicePage();

    IPaymentsType getPaymentsTypePage();
}
