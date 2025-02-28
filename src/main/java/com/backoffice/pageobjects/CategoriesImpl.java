package com.backoffice.pageobjects;

import com.backoffice.provider.TestContextProvider;
import com.backoffice.utilities.WebDriverHelper;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
/**
 * @author Neha Kharbanda
 */

/**
 * Implementation of the Categories page object.
 */
public class CategoriesImpl implements ICategories {
    private final WebDriverHelper webDriverHelper;
    WebDriver localDriver;
    private Logger log = LogManager.getLogger(CategoriesImpl.class);
    @FindBy(how = How.ID, using = "lv_menu_item_goods__goods.categories")
    private WebElement categoriesOption;
    @FindBy(how = How.XPATH, using = "//button[@id='lv_category_list__add']")
    private WebElement addCategoryButton;

    @FindBy(how = How.ID, using = "category_list-add-button")
    private WebElement addMoreCategoriesBtn;

    @FindBy(how = How.XPATH, using = "//input[@id='category-name']")
    private WebElement categoryNameInput;

    @FindBy(how = How.XPATH, using = "//div[@style='background: #F44336']")
    private WebElement categoryColourBtn;

    @FindBy(how = How.ID, using = "category_edit-save-button")
    private WebElement saveCategory;

    @FindBy(how = How.XPATH, using = "//tbody")
    private WebElement categoryTable;

    @FindBy(how = How.XPATH, using = "//button[@aria-label='Delete']")
    private WebElement trashButton;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Delete')]")
    private WebElement dialogueDeleteBtn;
    @FindBy(how = How.ID, using = "category_edit-cancel-button")
    private WebElement cancelCategoryButton;
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Continue editing']")
    private WebElement continueEditingBtn;


    public CategoriesImpl(WebDriver driver) // constructor - remote webdriver
    {
        webDriverHelper = new WebDriverHelper(driver);
        this.localDriver = driver;
        PageFactory.initElements(localDriver, this); // call method
    }

    @Override
    public void selectCategoryOption() {
        webDriverHelper.clickElementWithJavaScript(categoriesOption);
        log.info("Categories option is selected.");
    }

    @Override
    public void clickAddCategory() {
        webDriverHelper.waitUntilElementIsClickable(addCategoryButton);
        addCategoryButton.click();
        log.info("Add Category button is clicked.");
    }

    @Override
    public void verifySaveCategoryBtnEnabled(String expectedStatus) {
        String actualStatus = webDriverHelper.checkElementIsEnabled(saveCategory);
        Assert.assertEquals(expectedStatus, actualStatus);
    }


    @Override
    public void enterOrUpdateCategoryName(String categoryName) {
        TestContextProvider.getContext().getScenarioContext().setContext("createdCategoryName", categoryName);
        String getCategoryName = categoryNameInput.getAttribute("value");
        if (getCategoryName == null || getCategoryName.isEmpty()) {
            categoryNameInput.sendKeys(categoryName);
            log.error("Category name is entered as: {}", categoryName);
        } else {
            categoryNameInput.clear();
            categoryNameInput.sendKeys(categoryName);
            log.info("Category name is updated  as: {}", categoryName);
        }
    }

    @Override
    public void clickCategoryNameInput() {
        categoryNameInput.click();
        categoryColourBtn.click();
        log.info("Category name field was clicked, and focus is lost.");
    }

    @Override
    public void saveCategory() {
        saveCategory.click();
        WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(10));
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.id("category_list-add-button")));
        log.info("Category is saved");
    }

    @Override
    public void selectCategoryColour() {
        categoryColourBtn.click();
        log.info("Category colour is selected");
    }

    @Override
    public void verifyCategoryCreated() {
        String expectedCategoryName = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdCategoryName");
        List<WebElement> rows = categoryTable.findElements(By.tagName("tr"));
        int rowCount = rows.size();
        for (int i = 1; i <= rowCount; i++) {
            String xpathExpression = "//tbody/tr[" + i + "]/td[3]/div[1]";
            WebElement element = localDriver.findElement(By.xpath(xpathExpression));
            String actualCategoryName = element.getText();
            Assert.assertEquals(expectedCategoryName, actualCategoryName);
            log.info(" {} category is present on the category list page ", actualCategoryName);
        }
    }

    @Override
    public void verifyCategoryIsUpdated() {
        String expectedUpdateCategoryName = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdCategoryName");
        List<WebElement> rows = categoryTable.findElements(By.tagName("tr"));
        int rowCount = rows.size();
        for (int i = 1; i <= rowCount; i++) {
            String xpathExpression = "//tbody/tr[" + i + "]/td[3]/div[1]";
            WebElement element = localDriver.findElement(By.xpath(xpathExpression));
            String actualUpdateCategoryName = element.getText();
            Assert.assertEquals(expectedUpdateCategoryName, actualUpdateCategoryName);
            log.info(" {} category is updated on the category list page ", actualUpdateCategoryName);
        }

    }


    @Override
    public void selectExistingCategoryForEdit() {
        String expectedCategory = (String) TestContextProvider.getContext().getScenarioContext().getContext("createdCategoryName");
        List<WebElement> rows = categoryTable.findElements(By.tagName("tr"));
        int rowCount = rows.size();
        for (int i = 1; i <= rowCount; i++) {
            String xpathExpression = "//tbody/tr[" + i + "]/td[3]/div[1]";
            WebElement element = localDriver.findElement(By.xpath(xpathExpression));
            String actualCategory = element.getText();
            if (expectedCategory.equals(actualCategory)) {
                element.click();
            }
            log.info(" {} category is selected to edit ", actualCategory);
        }
    }

    @Override
    public void selectCategoryForDeletion(DataTable categoryNameTable) {
        List<Map<String, String>> categoryList = categoryNameTable.asMaps(String.class, String.class);
        int numberOfCategoriesToDelete = categoryList.size();
        // Locate the category table on the page
        WebElement categoryTable = localDriver.findElement(By.xpath("//tbody"));
        // Loop through each row in the DataTable and select the corresponding category
        for (int i = 0; i < numberOfCategoriesToDelete; i++) {
            String categoryName = categoryList.get(i).get("Category Name");
            String xpathExpression = "//tbody/tr[" + (i + 1) + "]/td[1]/md-checkbox";
            try {
                WebElement element = localDriver.findElement(By.xpath(xpathExpression));
                element.click();
                log.info("Category '{}' selected for deletion.", categoryName);
            } catch (NoSuchElementException e) {
                log.error("Checkbox element not found for category '{}' ", categoryName);
            } catch (StaleElementReferenceException e) {
                log.error("Stale element reference encountered for category '{}'", categoryName);
            }
        }
    }


//    @Override
//    public void selectCategoryForDeletion(DataTable categoryNameTable) {
//        List<WebElement> rows = categoryTable.findElements(By.tagName("tr"));
//        int rowCount = rows.size();
//        for (int i = 1; i <= rowCount; i++) {
//            String xpathExpression = "//tbody/tr[" + i + "]/td[1]/md-checkbox";
//            try {
//                WebElement element = localDriver.findElement(By.xpath(xpathExpression));
//                element.click();
//            } catch (NoSuchElementException e) {
//                log.info("Checkbox element not found for row: {} ", i);
//            } catch (StaleElementReferenceException e) {
//                log.info("Stale element reference encountered for row: {} " + i);
//            }
//        }
//    }


    @Override
    public void clickTrashIcon() {
        trashButton.click();
    }

    @Override
    public void clickDeleteButton() {
        dialogueDeleteBtn.click();
    }

    @Override
    public void addMoreCategoriesBtn() {
        addMoreCategoriesBtn.click();
        log.info("Add categories button is clicked");
    }

    @Override
    public void cancelCategoriesBtn() {
        cancelCategoryButton.click();
        log.info("cancel Category Button is clicked");
    }

    @Override
    public void continueEditingBtn() {
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        continueEditingBtn.click();
        log.info("continue Editing Btn is clicked");
    }
}
