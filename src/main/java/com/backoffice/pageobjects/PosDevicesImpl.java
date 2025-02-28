package com.backoffice.pageobjects;

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

/**
 * @author Neha Kharbanda
 */
public class PosDevicesImpl implements IPosDevices {

    private final Logger log = LogManager.getLogger(PosDevicesImpl.class);
    private final WebDriverHelper webDriverHelper;
    WebDriverWait wait;

    WebDriver driver;
    @FindBy(how = How.ID, using = "idCashRegister")
    private WebElement posDevicesOption;

    @FindBy(how = How.XPATH, using = "//*[@aria-label='Add POS']")
    private WebElement addPosButton;

    @FindBy(how = How.XPATH, using = "//lv-select[@aria-haspopup='listbox']")
    //can be used on pos list and create pos screen
    private WebElement storeDropdown;

    @FindBy(how = How.ID, using = "cashRegisterName")
    private WebElement posNameInput;

    @FindBy(how = How.ID, using = "btn_main")
    private WebElement savePosButton;

    @FindBy(how = How.XPATH, using = "//*[@aria-label='Delete']")
    private WebElement trashButtonEditPos;

    @FindBy(how = How.XPATH, using = "//*[normalize-space()='Delete']")
    private WebElement deleteButtonOnDialogue;

    @FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[2]/div")
    private WebElement fetchPosStoreName;


    public PosDevicesImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // call method
        wait = new WebDriverWait(driver, Duration.ofSeconds(160));
    }

    @Override
    public void clickOnPosDevicesOption() {
        webDriverHelper.clickElementWithJavaScript(posDevicesOption);

    }

    @Override
    public void clickOnAddPosButton() {
        addPosButton.click();
    }

    @Override
    public void clickOnSavePosButton() {
        savePosButton.click();
    }

    @Override
    public void enterOrUpdatePosName(String posName) {
        String getPosName = posNameInput.getAttribute("value");
        if (getPosName == null || getPosName.isEmpty()) {
            posNameInput.sendKeys(posName);
            log.info("Pos Name is entered as {}", posName);
        } else {
            posNameInput.clear();
            posNameInput.sendKeys(posName);
            log.info("Pos Name  is updated as {}", posName);
        }
    }


    @Override
    public void selectStoreName(String posStoreName) {
        storeDropdown.click();
        driver.findElement(By.xpath("//*[contains(text(),' " + posStoreName + "')]")).click();
        log.info("Pos Store is selected as {}", posStoreName);

    }

    @Override
    public void clickTrashButton() {
        trashButtonEditPos.click();
        deleteButtonOnDialogue.click();
    }

    public void fetchAndValidatePosStore(String expectedListedPosStoreName) {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr[1]/td[2]/div"))));
        String actualListedPosStoreName = fetchPosStoreName.getText();
        Assert.assertEquals("Expected Pos Store Name Value on pos device list page after search is ", expectedListedPosStoreName, actualListedPosStoreName);
    }


    public void verifyPosIsCreatedOnListPage(String expectedPosName, String expectedPosStoreName, String expectedPosStatus) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody")));
            // Locate all rows in the Pos table
            List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));
            int rowCount = rows.size();
            System.out.println("No of rows in this table: " + rowCount);
            boolean posNameFound = false;
            for (int i = 1; i <= rowCount; i++) {
                // Check if the Name column matches the expected name
                String actualPosName = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[2]/div")).getText();
                if (actualPosName.equals(expectedPosName)) {
                    String actualPosStoreName = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[3]/div")).getText();
                    String actualPosStatus = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[4]/span")).getText();
                    Assert.assertEquals(expectedPosName, actualPosName);
                    Assert.assertEquals(expectedPosStoreName, actualPosStoreName);
                    Assert.assertEquals(expectedPosStatus, actualPosStatus);
                    posNameFound = true;
                    log.info(" {} is present in the list", expectedPosName);
                    break;
                }
            }
            if (!posNameFound) {
                throw new RuntimeException("Tax - " + expectedPosName + " not found in the list.");
            }
        } catch (Exception e) {
            log.error("An error occurred while verifying the Pos device from the list: ", e);
            throw new RuntimeException("Test case failed due to exception: " + e.getMessage(), e);
        }

    }

    @Override
    public void openPosNameFromList(String expectedPos) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody")));
            // Locate all rows in the Pos table
            List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));
            int rowCount = rows.size();
            System.out.println("No of rows in this table: " + rowCount);
            boolean posNameFound = false;
            for (int i = 1; i <= rowCount; i++) {
                // Check if the Name column matches the expected name
                String actualPosName = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[2]/div")).getText();
                if (actualPosName.equals(expectedPos)) {
                    driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[2]/div")).click();
                    posNameFound = true;
                    log.info(" {} is clicked from the list", expectedPos);
                    break;
                }
            }
            if (!posNameFound) {
                throw new RuntimeException("Pos - " + expectedPos + " not found in the list.");
            }
        } catch (Exception e) {
            log.error("An error occurred while clicking the Pos device from the list: ", e);
            throw new RuntimeException("Test case failed due to exception: " + e.getMessage(), e);
        }
    }


}