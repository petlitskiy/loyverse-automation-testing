package com.backoffice.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
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
public class TaxesImpl implements ITaxes {

    private final Logger log = LogManager.getLogger(TaxesImpl.class);
    WebDriver localDriver;
    @FindBy(how = How.ID, using = "idTax")
    private WebElement taxesOption;
    @FindBy(how = How.ID, using = "taxName")
    private WebElement taxNameInput;
    @FindBy(how = How.ID, using = "taxValue")
    private WebElement taxValueInput;
    @FindBy(how = How.ID, using = "tax-add-button")
    private WebElement addTaxButton;
    @FindBy(how = How.ID, using = "tax__add_btn") // clicked when taxes are already present and  we want to add new tax
    private WebElement addTaxBtnOnListPage;
    @FindBy(how = How.ID, using = "tax_create-save-button")
    private WebElement saveTaxButton;
    @FindBy(how = How.XPATH, using = "//*[@class=\"item name\"]/div")
    private WebElement taxItem;
    @FindBy(how = How.XPATH, using = "//*[@class=\"text-right input-disabled ng-pristine ng-untouched ng-valid ng-model md-input ng-not-empty\"]")
    private WebElement taxPercentageOnTaxList;
    @FindBy(how = How.XPATH, using = "//*[@id='tax_create-type-select']")
    private WebElement taxTypeDropdown;
    @FindBy(how = How.XPATH, using = "//*[@value='INCLUDED']")
    private WebElement taxTypeIncludedValue;
    @FindBy(how = How.XPATH, using = "//*[@value='ADDED']")
    private WebElement taxTypeAddedValue;
    @FindBy(how = How.XPATH, using = "//*[@id='tax_create-option-select']")
    private WebElement taxOptiondropdown;
    @FindBy(how = How.XPATH, using = "//*[@value='NEW']")
    private WebElement taxOptionNewItem;
    @FindBy(how = How.XPATH, using = "//*[@value='OLD']")
    private WebElement taxOptionExistingItem;
    @FindBy(how = How.XPATH, using = "//*[normalize-space()='OK']") // cn be used for ok for deletion dialouge
    private WebElement cofirmOKExistingItemOption;
    @FindBy(how = How.XPATH, using = "//*[@value='OLD_NEW']")
    private WebElement taxOptionNewAndExistingItem;
    @FindBy(how = How.XPATH, using = "//*[normalize-space()='Delete']")
    private WebElement deleteTaxButton;



    public TaxesImpl(WebDriver driver) // constructor - remote webdriver
    {
        this.localDriver = driver;
        PageFactory.initElements(localDriver, this); // call method
    }

    @Override
    public void selectTaxesOption() {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(taxesOption));
        taxesOption.click();
        log.info("Taxes option under settings section is clicked");
    }

    @Override
    public void clickAddTaxButton() {
        addTaxButton.click();
        log.info("Add tax  button is clicked");
    }

    @Override
    public void clickAddTaxOnListPage() {
        addTaxBtnOnListPage.click();
        log.info("Add tax  button is clicked when taxes are already present");
    }

    @Override
    public void verifyTaxValue(String expectedTax) {
        taxNameInput.click();
        String actualTaxValue = taxValueInput.getAttribute("value");
        Assert.assertEquals(expectedTax, actualTaxValue);
    }

    @Override
    public void enterOrUpdateTaxName(String newTaxName) {
        String getTaxName = taxNameInput.getAttribute("value");
        if (getTaxName == null || getTaxName.isEmpty()) {
            taxNameInput.sendKeys(newTaxName);
            log.info("Tax name is entered as {}", newTaxName);
        } else {
            taxNameInput.clear();
            taxNameInput.sendKeys(newTaxName);
            log.info("Tax name is  updated as {}", newTaxName);
        }
    }


    @Override
    public void enterOrUpdateTaxValue(String taxValue) {
        try {
            String getTaxRate = taxValueInput.getAttribute("value");
            if (getTaxRate == null || getTaxRate.isEmpty()) {
                JavascriptExecutor js = (JavascriptExecutor) localDriver;
                js.executeScript("arguments[0].value='';", taxValueInput);
                taxValueInput.sendKeys(taxValue);
                log.info("Tax rate is entered as {}" + taxValue);
            } else {
                taxValueInput.click();
                taxValueInput.sendKeys(Keys.END); // Move cursor to the end of the text
                int textLength = taxValueInput.getAttribute("value").length();
                for (int i = 0; i < textLength; i++) {
                    taxValueInput.sendKeys(Keys.BACK_SPACE);
                }
                log.info("Tax rate  is cleared at run time");
                taxValueInput.sendKeys(taxValue);
                log.info("Tax rate is  updated as {}", taxValue);
            }
        } catch (Exception ex) {
            log.error("An error occurred while entering or updating the tax value: {}", taxValue, ex);
            throw new RuntimeException("Failed to enter or update tax value: " + taxValue, ex);
        }
    }

    @Override
    public void saveTax() {
        saveTaxButton.click();
        log.info("Save tax button is clicked");
    }

    @Override
    public void selectTaxType(String taxType) {
        taxTypeDropdown.click();
        if (taxType.equals("Included in the price")) {
            taxTypeIncludedValue.click();
        } else if (taxType.equals("Added to the price")) {
            taxTypeAddedValue.click();
        } else {
            log.error("Invalid tax type: {}", taxType);
            throw new RuntimeException("Invalid tax type: " + taxType);
        }
    }


    @Override
    public void selectTaxOption(String taxOption) {
        taxOptiondropdown.click();
        if (taxOption.equals("Apply the tax to the new items")) {
            taxOptionNewItem.click();
        } else if (taxOption.equals("Apply the tax to existing items")) {
            taxOptionExistingItem.click();
        } else if (taxOption.equals("Apply the tax to all new and existing items")) {
            taxOptionNewAndExistingItem.click();
        }
        else {
            log.error("Invalid tax Option: {}", taxOption);
            throw new RuntimeException("Invalid tax Option: " + taxOption);
        }
    }

    @Override
    public void clickOkForExistingItemOption(){
        cofirmOKExistingItemOption.click();
    }

//    @Override
//    public void selectTheTaxFromList(String expectedTaxName){
//        localDriver.findElement(By.xpath("//*[normalize-space()='"+ expectedTaxName +"']")).click();
//        log.info("Tax {} is  selected on tax list page", expectedTaxName);
//    }


    @Override
    public void selectTheTaxFromList(String expectedTaxName){
        try {
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody")));
        //find the customer table
        WebElement customerListTable = localDriver.findElement(By.xpath("//tbody"));
        // Locate all rows in the customer table
        List<WebElement> rows = customerListTable.findElements(By.xpath(".//tr"));
        int rowCount = rows.size();
        System.out.println("No of rows in this table : " + rowCount);
        boolean taxNameFound = false;
        for (int i = 1; i <= rowCount; i++) {
            // Check if the Name column matches the expected name
            String actualTaxName = localDriver.findElement(By.xpath(".//tr[" + i + "]/td[2]/div")).getText();
            if (actualTaxName.equals(expectedTaxName)) {
                taxNameFound = true;
                localDriver.findElement(By.xpath(".//tr[" + i + "]/td[2]/div")).click();
                log.info("Tax {} is  selected on tax list page", expectedTaxName);
                break;
            }
        }
            if (!taxNameFound) {
                throw new RuntimeException("Tax " + expectedTaxName + " not found in the list.");
            }
        } catch (Exception e) {
            log.error("An error occurred while selecting the tax from the list: ", e);
            throw new RuntimeException("Test case failed due to exception: " + e.getMessage(), e);
        }
    }
    @Override
    public void verifyTheTaxFromList(String expectedTaxName,String expectedAppliedToItem, String expectedTaxValue) {
        try {
            WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody")));

            // Locate all rows in the customer table
            List<WebElement> rows = localDriver.findElements(By.xpath("//tbody//tr"));
            int rowCount = rows.size();
            System.out.println("No of rows in this table: " + rowCount);

            boolean taxNameFound = false;
            for (int i = 1; i <= rowCount; i++) {
                // Check if the Name column matches the expected name
                String actualTaxName = localDriver.findElement(By.xpath("//tbody//tr[" + i + "]/td[2]/div")).getText();
                if (actualTaxName.equals(expectedTaxName)) {
                    String actualAppliedToItem = localDriver.findElement(By.xpath("//tbody//tr[" + i + "]/td[3]/span")).getText();
                    String getTaxValue = localDriver.findElement(By.xpath("//tbody//tr[" + i + "]/td[4]/md-input-container/input")).getAttribute("value");
                    String getPercentageSign = localDriver.findElement(By.xpath("//tbody//tr[" + i + "]/td[4]/md-input-container/span[1]")).getText();
                    String actualTaxValue =  getTaxValue+getPercentageSign;
                    Assert.assertEquals(actualTaxName, expectedTaxName);
                    Assert.assertEquals(actualTaxValue, expectedTaxValue);
                    Assert.assertEquals(actualAppliedToItem, expectedAppliedToItem);

                    taxNameFound = true;
                    log.info(" {} is present in the list", expectedTaxName);

                    break;
                }
            }

            if (!taxNameFound) {
                throw new RuntimeException("Tax - " + expectedTaxName + " not found in the list.");
            }
        } catch (Exception e) {
            log.error("An error occurred while verifying the tax from the list: ", e);
            throw new RuntimeException("Test case failed due to exception: " + e.getMessage(), e);
        }
    }


    @Override
    public void clickDeleteTaxButton(){
      deleteTaxButton.click();
      cofirmOKExistingItemOption.click();
    }



}










