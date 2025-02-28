package com.backoffice.pageobjects;

import com.backoffice.provider.TestContextProvider;
import com.backoffice.utilities.WebDriverHelper;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

/**
 * @author Neha Kharbanda
 */
public class ModifiersImpl implements IModifiers {

    private final Logger log = LogManager.getLogger(ModifiersImpl.class);
    private final WebDriverHelper webDriverHelper;
    WebDriver localDriver;
    @FindBy(how = How.ID, using = "lv_menu_item_goods__goods.modifiers-list")
    private WebElement modifiersOption;
    @FindBy(how = How.NAME, using = "modifierName")
    private WebElement modifierNameInput;

    @FindBy(how = How.XPATH, using = "//button[@aria-label='Delete']")
    private WebElement deleteButton;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Delete']")
    private WebElement deleteBtnEditPage;
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Confirm']")
    private WebElement confirmBtnDialougeBox;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Delete')]")
    private WebElement deleteBtnDialogueBox;

    @FindBy(how = How.XPATH, using = "//tbody")
    private WebElement modifierTable;

    @FindBy(how = How.ID, using = "modifiers__add_modifier_btn")
    private WebElement addModifierButton;

    @FindBy(how = How.ID, using = "option-name")
    private WebElement optionNameText1;
    @FindBy(how = How.XPATH, using = "//input[@id='option-name' and @index=1]")
    private WebElement optionNameText2;

    @FindBy(how = How.ID, using = "option-price")
    private WebElement optionPriceText1;
    @FindBy(how = How.XPATH, using = "(//input[@id='option-price'])[position()=2]")
    private WebElement optionPriceText2;

    @FindBy(xpath = "//*[contains(text(),'Save')]")
    private WebElement saveModifierBtn;

    @FindBy(how = How.ID, using = "create_modifier__add_option")
    private WebElement addOptionBtn;

    @FindBy(how = How.XPATH, using = "(//*[@aria-label='Delete'])[1]")
    private WebElement trashBtn;


    public ModifiersImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.localDriver = driver;
        PageFactory.initElements(localDriver, this); // call method
    }

    @Override
    public void selectModifiersOption() {
        webDriverHelper.scrollToElement(modifiersOption);
        webDriverHelper.clickElementWithJavaScript(modifiersOption);
        log.info("Modifier Option is clicked");
    }

    @Override
    public void clickAddModifier() {
        webDriverHelper.clickElementWithJavaScript(addModifierButton);
    }

    @Override
    public void enterModifierName(String modifierName) {
        TestContextProvider.getContext().getScenarioContext().setContext("createdModifierName", modifierName);
        modifierNameInput.sendKeys(modifierName);
    }

    @Override
    public void enterOrUpdateModifierName(String modifierName) {
        TestContextProvider.getContext().getScenarioContext().setContext("createdModifierName", modifierName);
        String getModifierName = modifierNameInput.getAttribute("value");
        if (getModifierName == null || getModifierName.isEmpty()) {
            modifierNameInput.sendKeys(modifierName);
            log.info("Modifier name is entered as: {}", modifierName);
        } else {
            modifierNameInput.clear();
            modifierNameInput.sendKeys(modifierName);
            log.info("Modifier name is updated  as: {}", modifierName);
        }
    }

    @Override
    public void enterFirstOptionName(String optionName) {
        optionNameText1.sendKeys(optionName);
        log.info("Option Name is entered as {}", optionName);
    }

    @Override
    public void enterFirstOptionPrice(String optionPrice) {
        webDriverHelper.clearElementWithJavaScript(optionPriceText1);
        optionPriceText1.sendKeys(optionPrice);
        log.info("Option Price is entered as {}", optionPrice);
    }


    @Override
    public void enterSecondOptionName(String optionName) {
        optionNameText2.sendKeys(optionName);
        log.info("Option Name is entered as {}", optionName);
    }

    @Override
    public void enterSecondOptionPrice(String optionPrice) {
        webDriverHelper.clearElementWithJavaScript(optionPriceText2);
        optionPriceText2.sendKeys(optionPrice);
        log.info("Option Price is entered as {}", optionPrice);
    }

    @Override
    public void clickSaveModifierButton() {
        saveModifierBtn.click();
        log.info("Save button is clicked on create modifier Page");
    }

    @Override
    public void clickAddOptionBtn() {
        addOptionBtn.click();
        log.info("Add Modifier button is clicked on create modifier Page");
    }

    @Override
    public void clickTrashIcon() {
        trashBtn.click();
        log.info("Option is deleted from create modifier Page");
    }

    @Override
    public void clickDeleteButtonModifierPage() {
        deleteButton.click();
        deleteBtnDialogueBox.click();
        log.info("Delete Button is clicked on modifier Page");
    }

    @Override
    public void clickDeleteEditModifierPage() {
        deleteBtnEditPage.click();
        confirmBtnDialougeBox.click();
        log.info("Modifier is deleted form edit page");
    }

    @Override
    public void enterOptionNameAndPrice(DataTable modifierData) {
        List<Map<String, String>> optionData = modifierData.asMaps(String.class, String.class);

        for (int i = 0; i < optionData.size(); i++) {
            String optionName = optionData.get(i).get("OptionName");
            String optionPrice = optionData.get(i).get("Price");


            if (i == 0) {
                enterFirstOptionName(optionName);
                enterFirstOptionPrice(optionPrice);
                TestContextProvider.getContext().getScenarioContext().setContext("createdOptionName", optionName);
                TestContextProvider.getContext().getScenarioContext().setContext("createdOptionPrice", optionPrice);
            } else {
                clickAddOptionBtn();
                enterSecondOptionName(optionName);
                enterSecondOptionPrice(optionPrice);
                TestContextProvider.getContext().getScenarioContext().setContext("createdOptionName", optionName);
                TestContextProvider.getContext().getScenarioContext().setContext("createdOptionPrice", optionPrice);
            }
        }

    }

    @Override
    public void verifyModifierCreated() {
        String expectedModifierName = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdModifierName");
        String expectedOptionName = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdOptionName");
        List<WebElement> rows = modifierTable.findElements(By.tagName("tr"));
        int rowCount = rows.size();
        int column = 2;
        for (int i = 1; i <= rowCount; i++) {
            String modNameElement = "//tbody/tr[" + i + "]/td[" + column + "]/div[2]/div[1]";
            WebElement modName = localDriver.findElement(By.xpath(modNameElement));
            String actualModifierName = modName.getText();
            String optionNameElement = "//tbody/tr[" + i + "]/td[" + column + "]/div[2]/div[2]/span";
            WebElement optionName = localDriver.findElement(By.xpath(optionNameElement));
            String actualOptionName = optionName.getText();
            Assert.assertEquals(expectedModifierName, actualModifierName);
            Assert.assertEquals(expectedOptionName, actualOptionName);
            log.info("  Modifier - {}  and  option- {} is present on the modifier list page ", actualModifierName, actualOptionName);
        }
    }

    @Override
    public void selectExistingModifier() {
        String expectedModifierName = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdModifierName");
        List<WebElement> rows = modifierTable.findElements(By.tagName("tr"));
        int rowCount = rows.size();
        int column = 2;
        for (int i = 1; i <= rowCount; i++) {
            String modNameElement = "//tbody/tr[" + i + "]/td[" + column + "]/div[2]/div[1]";
            WebElement modName = localDriver.findElement(By.xpath(modNameElement));
            String actualModifierName = modName.getText();
            if (expectedModifierName.equals(actualModifierName)) {
                WebElement mdCheckbox = localDriver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/md-checkbox"));
                mdCheckbox.click();
            } else {
                log.info("Modifier Name not present to select for deletion");
            }

        }
    }


    @Override
    public void selectExistingModifierForEdit() {
        String expectedModifierName = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdModifierName");
        List<WebElement> rows = modifierTable.findElements(By.tagName("tr"));
        int rowCount = rows.size();
        int column = 2;
        for (int i = 1; i <= rowCount; i++) {
            String modNameElement = "//tbody/tr[" + i + "]/td[" + column + "]/div[2]/div[1]";
            WebElement modName = localDriver.findElement(By.xpath(modNameElement));
            String actualModifierName = modName.getText();
            if (expectedModifierName.equals(actualModifierName)) {
                modName.click();
            }
            log.info(" {} modifier is selected to edit ", actualModifierName);
        }
    }

    @Override
    public void verifyModifierIsUpdated() {
        String expectedUpdateModifierName = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdModifierName");
        List<WebElement> rows = modifierTable.findElements(By.tagName("tr"));
        int rowCount = rows.size();
        int column = 2;
        for (int i = 1; i <= rowCount; i++) {
            String modNameElement = "//tbody/tr[" + i + "]/td[" + column + "]/div[2]/div[1]";
            WebElement modName = localDriver.findElement(By.xpath(modNameElement));
            String actualUpdatedModifierName = modName.getText();
            Assert.assertEquals(expectedUpdateModifierName, actualUpdatedModifierName);
            log.info(" Modifier is updated on the modifier list page as {} ", actualUpdatedModifierName);
        }

    }


}
