package com.backoffice.pageobjects;

import com.backoffice.utilities.WebDriverHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DiscountsImpl implements IDiscounts {
    private final WebDriverHelper webDriverHelper;
    private final Logger log = LogManager.getLogger(DiscountsImpl.class);
    WebDriver driver;
    // WebElements
    @FindBy(how = How.ID, using = "lv_menu_item_goods__goods.discount")
    private WebElement discountOption;
    @FindBy(how = How.XPATH, using = "//input[@id='discount-name']")
    private WebElement discountItemNameText;
    @FindBy(how = How.XPATH, using = "//*[@id=\"discount-percent-radio\"]/div[1]")
    private WebElement typePercentageRadio;
    @FindBy(how = How.XPATH, using = "//*[@id=\"discount-amount-radio\"]/div[1]")
    private WebElement typeAmountRadio;
    @FindBy(how = How.XPATH, using = "//input[@id='discount-value-input']")
    private WebElement discountValuePercentage;
    @FindBy(how = How.XPATH, using = "//input[@name='discountAmount']")
    private WebElement discountValueAmount;
    @FindBy(how = How.ID, using = "discount__add_discount_btn")
    private WebElement addDiscountButton;
    @FindBy(how = How.XPATH, using = "//md-switch[@aria-label='limited access']")
    private WebElement mdSwitchRestrictedAccess;
    @FindBy(how = How.ID, using = "discount-save-button")
    private WebElement saveDiscountBtn;
    @FindBy(how = How.XPATH, using = "//div[@class='md-input-message-animation']")
    private WebElement discountValueErrorMessage;
    @FindBy(how = How.XPATH, using = "//tbody")
    private WebElement discountTable;


    public DiscountsImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // call method
    }

    /**
     * Selects the discount option.
     */
    @Override
    public void selectDiscountOption() {
        webDriverHelper.clickElementWithJavaScript(discountOption);
    }

    /**
     * Enters the discount item name.
     *
     * @param discountName The name of the discount item.
     */
    @Override
    public void enterDiscountItemName(String discountName) {
        webDriverHelper.enterOrUpdateFieldValue(discountName, discountItemNameText);
       /* if (discountName == null || discountName.isEmpty()) {
            log.error("discountType is null", discountName);
        } else {
            discountItemNameText.sendKeys(discountName);
            log.info("Discount name entered as {} ", discountName);
        }
*/
    }


    @Override
    public void selectDiscountType(String discountType) {
        if (discountType == null || discountType.isEmpty()) {
            log.error("discountType is null", discountType);
        } else if (discountType.equals("Percentage")) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", typePercentageRadio);
            log.info("Discount type is selected as percentage");
        } else if (discountType.equals("Amount")) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", typeAmountRadio);
            log.info("Amount radio button selected");
        } else {
            log.error("The discountType value is incorrect");
        }
    }

    @Override
    public void saveDiscountBtn() {
        saveDiscountBtn.click();
        log.info("Discount is saved");
    }


    /**
     * Enters the discount value by percentage.
     *
     * @param valueByPercentage The value by percentage to be entered.
     */
    @Override
    public void enterDiscountByPercentage(String valueByPercentage) {


        String percentageValue = discountValuePercentage.getAttribute("value");
        if (StringUtils.isNotBlank(percentageValue)) {
            discountValuePercentage.sendKeys(Keys.END); // Move cursor to the end of the text
            int textLength = discountValuePercentage.getAttribute("value").length();
            for (int i = 0; i < textLength; i++) {
                discountValuePercentage.sendKeys(Keys.BACK_SPACE);
            }
            log.info("Discount Value by Percentage  is cleared at run time");
        } else {
            log.info("Discount Value by Percentage is already cleared");
        }
        discountValuePercentage.sendKeys(valueByPercentage);
        discountItemNameText.click();
        log.info("Discount value entered by percentage is  {}", valueByPercentage);
    }

    /**
     * Verifies the discount value against the expected value.
     *
     * @param expectedDiscountValue The expected discount value.
     */
    @Override
    public void verifyDiscountValue(String expectedDiscountValue) {
        discountItemNameText.click();
        String actualDiscountValue = discountValuePercentage.getAttribute("value");
        log.info("Actual discount value populated is {}", actualDiscountValue);
        Assert.assertEquals(actualDiscountValue, expectedDiscountValue);
    }

    @Override
    public void verifyDiscountErrorMessage(String errorMessageForDiscountValue) {
        String actualErrorMessage = discountValueErrorMessage.getText();
        if (discountValueErrorMessage.isDisplayed()) {
            Assert.assertEquals("The discount error message is present ", errorMessageForDiscountValue, actualErrorMessage);
        } else {
            log.error("Error message for discount value not displayed");
        }
    }


    /**
     * Enters the discount value by amount.
     *
     * @param valueByAmount The value by amount to be entered.
     */
    @Override
    public void enterDiscountByAmount(String valueByAmount) {
        discountValueAmount.clear();
        discountValueAmount.sendKeys(valueByAmount);
        log.info("Discount entered by amount is {} ", valueByAmount);
    }

    @Override
    public void clickAddDiscount() {
        try {
            webDriverHelper.waitUntilElementIsClickable(addDiscountButton);
            webDriverHelper.highlightElement(addDiscountButton);
            webDriverHelper.clickElementWithJavaScript(addDiscountButton);
            log.info("Add discount button is clicked");
        } catch (Exception e) {
            log.error("Failed to click Add discount button: " + e.getMessage());
            throw new RuntimeException("Failed to click Add discount button", e);
        }
    }

    @Override
    public void selectRestrictedAccess(String restrictedAccessStatus) {
        if (restrictedAccessStatus != null) {
            if (restrictedAccessStatus.equals("enabled")) {
                mdSwitchRestrictedAccess.click();
                log.info("Restricted Access is enabled for discount");
            } else if (restrictedAccessStatus.equals("disabled")) {
                log.info("By Default, Restricted Access is disabled for discount");
            }
        } else {
            log.error("Restricted Access status is null");
        }
    }


    /**
     * Verifies the discounts of type Percentage on discount screen
     */
   /* @Override
    public void verifyDiscounts(String expectedDiscountValue, String expectedDiscountType, String expectedDiscountName, String expectedRestrictedAccessStatus) {
        try {
            String actualDiscountValue = null;
            String discountValue = null;
            List<WebElement> rows = discountTable.findElements(By.tagName("tr"));
            int rowCount = rows.size();


            for (int i = 1; i <= rowCount; i++) {
                String actualDiscountName = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[2]/div/span")).getText();

                if (expectedDiscountType.equals("Percentage")) {
                    discountValue = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[3]/div/span[1]")).getText();
                    String discountSymbol = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[3]/div/span[2]")).getText();
                    actualDiscountValue = discountValue.concat(discountSymbol);

                } else if (expectedDiscountType.equals("Amount")) {
                    actualDiscountValue = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[3]/div")).getText();
                }


                String actualRestrictedAccessStatus = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[4]/div/span")).getText();
                Assert.assertEquals(expectedDiscountName, actualDiscountName);
                Assert.assertEquals(expectedDiscountValue, actualDiscountValue);
                Assert.assertEquals(expectedRestrictedAccessStatus, actualRestrictedAccessStatus);
                log.info("Discount {} with value {} and restricted access {} is present on the discounts screen", actualDiscountName, actualDiscountValue, actualRestrictedAccessStatus);
            }
        } catch (Exception e) {
            log.error("Failed to verify the discount: " + e.getMessage());
            throw new RuntimeException("Failed to verify the discount:", e);
        }
    }
*/
    @Override
    public void verifyDiscounts(String expectedDiscountValue, String expectedDiscountType, String expectedDiscountName, String expectedRestrictedAccessStatus) {
        try {
            List<WebElement> rows = discountTable.findElements(By.tagName("tr"));
            for (WebElement row : rows) {
                String actualDiscountName = row.findElement(By.xpath("./td[2]/div/span")).getText();
                String actualDiscountValue = null;

                if (expectedDiscountType.equals("Percentage")) {
                    WebElement valueElement = row.findElement(By.xpath("./td[3]/div/span[1]"));
                    WebElement symbolElement = row.findElement(By.xpath("./td[3]/div/span[2]"));
                    String discountValue = valueElement.getText();
                    String discountSymbol = symbolElement.getText();
                    actualDiscountValue = discountValue.concat(discountSymbol);
                } else if (expectedDiscountType.equals("Amount")) {
                    actualDiscountValue = row.findElement(By.xpath("./td[3]/div")).getText();
                }

                String actualRestrictedAccessStatus = row.findElement(By.xpath("./td[4]/div/span")).getText();

                Assert.assertEquals(expectedDiscountName, actualDiscountName);
                Assert.assertEquals(expectedDiscountValue, actualDiscountValue);
                Assert.assertEquals(expectedRestrictedAccessStatus, actualRestrictedAccessStatus);

                log.info("Discount {} with value {} and restricted access {} is present on the discounts screen", actualDiscountName, actualDiscountValue, actualRestrictedAccessStatus);
            }
        } catch (Exception e) {
            log.error("Failed to verify the discount: " + e.getMessage());
            throw new RuntimeException("Failed to verify the discount:", e);
        }
    }


}




