package com.backoffice.pageobjects;

/**
 * @author Neha Kharbanda
 */
public interface IEmployeeList {

    void selectEmployeeListOption();

    void clickAddEmployeeButton();

    void clickStartFreeEmployeeTrialButton();

    void selectExistingEmployee();

    void verifyEmployeeCreatedMessage(String expectedEmpAddedMessage);

    void clickEmployeeCancel();

    void clickDeleteButtonEmpPage();

    void clickEmployeeSave();

    void enterEmployeeRole(String empRole);

    void enterEmployeePhone(String empPhone);

    void enterEmployeeEmail(String empEmail);

    void enterEmployeeName(String empName);

    void enterAndSaveAuthPinCode(String authPinCode);

}
