package com.backoffice.pageobjects;

import com.backoffice.provider.TestContextProvider;
import com.backoffice.utilities.WebDriverHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EmployeeListImpl implements IEmployeeList {
    WebDriver localDriver;
    private final WebDriverHelper webDriverHelper;
    private Logger log = LogManager.getLogger(EmployeeListImpl.class);
    @FindBy(how = How.XPATH, using = "//div[@id='lv_menu_item_employees__employees.list']")
    private WebElement employeeListOption;
    @FindBy(how = How.ID, using = "amployee__add_employee_btn")
    private WebElement addEmployeeButton;
    @FindBy(how = How.ID, using = "name")
    private WebElement empNameInput;
    @FindBy(how = How.NAME, using = "email")
    private WebElement empEmailInput;
    @FindBy(how = How.NAME, using = "phone")
    private WebElement empPhoneInput;
    @FindBy(how = How.NAME, using = "role")
    private WebElement empRoleDropdown;
    @FindBy(how = How.XPATH, using = "//button[@aria-label='Save']")
    private WebElement empSaveButton;
    @FindBy(how = How.XPATH, using = "//button[@aria-label='Cancel']")
    private WebElement empCancelButton;
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Confirm']")
    private WebElement empPinConfirmButton;
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Start free trial']")
    private WebElement empStartFreeTrailButton;
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Employee added')]")
    private WebElement employeeAddedMessage;

    @FindBy(how = How.XPATH, using = "//button[@aria-label='Delete']")
    private WebElement deleteButtonEmpListPage;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Delete')]")
    private WebElement deleteBtnDialogueBox;

    @FindBy(how = How.XPATH, using = "//tbody")
    private WebElement employeeTable;

    public EmployeeListImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.localDriver = driver;
        PageFactory.initElements(localDriver, this); // call method
    }

    @Override
    public void selectEmployeeListOption() {
        WebElement employeeListOption = webDriverHelper.fluentWait(By.xpath("//div[@id='lv_menu_item_employees__employees.list']"));
        webDriverHelper.clickElement(employeeListOption);
        log.info("Employee list option is selected under employee category");
    }

    @Override
    public void clickAddEmployeeButton() {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton));
        addEmployeeButton.click();
        log.info("Add employee button is clicked on employee list page");
    }

    @Override
    public void enterAndSaveAuthPinCode(String authPinCode) {

        for (int i = 1; i <= 4; i++) {
            String authPinDigit = authPinCode.substring(i - 1, i);
            WebElement empAuthPinCodeInput = localDriver.findElement(By.xpath("//lv-pin-editor[@name='pin']/div/input[" + i + "]"));
            empAuthPinCodeInput.click();
            empAuthPinCodeInput.sendKeys(authPinDigit);
        }
        empPinConfirmButton.click();
        log.info("Employee auth pin code is entered and saved as:{}", authPinCode);
    }

    @Override
    public void enterEmployeeName(String empName) {
        empNameInput.sendKeys(empName);
        log.info("Employee name is entered as: {}", empName);
    }

    @Override
    public void enterEmployeeEmail(String empEmail) {
        empEmailInput.click();
        empEmailInput.sendKeys(empEmail);
        log.info("Employee email is entered as: {}", empEmail);
    }

    @Override
    public void enterEmployeePhone(String empPhone) {
        empPhoneInput.sendKeys(empPhone);
        log.info("Employee phone is entered as: {}", empPhone);
    }

    @Override
    public void enterEmployeeRole(String empRole) {
        empRoleDropdown.click();
        WebElement element = localDriver.findElement(By.xpath(" //div[normalize-space()='" + empRole + "']"));
        element.click();
        log.info("Employee role is entered as: {}", empRole);
    }

    @Override
    public void clickEmployeeSave() {
        empSaveButton.click();
        log.info("Save button is clicked on employee creation page");
    }

    @Override
    public void clickEmployeeCancel() {
        empCancelButton.click();
        log.info("Cancel button is clicked on employee creation page");
    }

    @Override
    public void verifyEmployeeCreatedMessage(String expectedEmpAddedMessage) {
        String actualEmpAddedMessage = employeeAddedMessage.getText();
        Assert.assertEquals(expectedEmpAddedMessage, actualEmpAddedMessage);
    }

    @Override
    public void clickStartFreeEmployeeTrialButton() {
        empStartFreeTrailButton.click();
        log.info("Employee free trail is started");
    }

/*    @Override
    public void selectExistingEmployee() {
        String expectedEmpName = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdEmpName");
        log.info("print get context employee name {}" , expectedEmpName);
        List<WebElement>  numOfRow = employeeTable.findElements(By.tagName("tr"));
        for(int iRow=1; iRow<= numOfRow.size(); iRow++) {
            List<WebElement> cells= localDriver.findElements(By.xpath("//tbody/tr["+iRow+"]/td"));
            for (int iCell = 1; iCell <= cells.size(); iCell++) {
                String actualEmpName = localDriver.findElement(By.xpath("//tbody/tr["+iRow+"]/td["+iCell+"]")).getText();
                if(actualEmpName.equals(expectedEmpName)){
                    WebElement mdCheckbox = localDriver.findElement(By.xpath("//tbody/tr[" + iRow + "]/td[1]/div/md-checkbox"));
                    mdCheckbox.click();
                    return;
                }
            }
        }
        }*/

    @Override
    public void selectExistingEmployee() {
        String expectedEmpName = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdEmpName");
        log.info("print get context employee name {}", expectedEmpName);
        List<WebElement> numOfRow = employeeTable.findElements(By.tagName("tr"));
        boolean isExpectedNamePresent = false;
        for (int iRow = 1; iRow <= numOfRow.size(); iRow++) {
            List<WebElement> cells = localDriver.findElements(By.xpath("//tbody/tr[" + iRow + "]/td"));
            for (int iCell = 1; iCell <= cells.size(); iCell++) {
                String actualEmpName = localDriver.findElement(By.xpath("//tbody/tr[" + iRow + "]/td[" + iCell + "]")).getText();
                if (actualEmpName.equals(expectedEmpName)) {
                    WebElement mdCheckbox = localDriver.findElement(By.xpath("//tbody/tr[" + iRow + "]/td[1]/div/md-checkbox"));
                    mdCheckbox.click();
                    isExpectedNamePresent = true; // Set the flag to true when the name is found
                    return;
                }
            }
        }
        // Check the flag and print the log only if the name was not found
        if (!isExpectedNamePresent) {
            log.info("Employee Name '{}' not found in the table.", expectedEmpName);
        }
    }

    @Override
    public void clickDeleteButtonEmpPage() {
        deleteButtonEmpListPage.click();
        deleteBtnDialogueBox.click();
        log.info("Delete Button is clicked on employee list Page");
    }

}
