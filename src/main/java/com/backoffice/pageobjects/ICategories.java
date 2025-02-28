package com.backoffice.pageobjects;

import io.cucumber.datatable.DataTable;

/**
 * @author Neha Kharbanda
 */
public interface ICategories {
    void clickAddCategory();

    void selectCategoryOption();

    void selectCategoryColour();

    void enterOrUpdateCategoryName(String categoryName);

    void addMoreCategoriesBtn();

    void verifySaveCategoryBtnEnabled(String expectedStatus);

    void clickCategoryNameInput();

    void verifyCategoryCreated();

    void saveCategory();

    void selectCategoryForDeletion(DataTable categoryNameTable);

    void clickTrashIcon();

    void clickDeleteButton();

    void selectExistingCategoryForEdit();

    void cancelCategoriesBtn();

    void continueEditingBtn();

    void verifyCategoryIsUpdated();

}
