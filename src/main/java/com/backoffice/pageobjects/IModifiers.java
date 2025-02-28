package com.backoffice.pageobjects;

import io.cucumber.datatable.DataTable;

/**
 * @author Neha Kharbanda
 */
public interface IModifiers {

    void selectModifiersOption();

    void clickAddModifier();

    void enterOrUpdateModifierName(String modifierName);

    void enterModifierName(String modifierName);

    void clickSaveModifierButton();

    void verifyModifierIsUpdated();

    void clickAddOptionBtn();

    void enterFirstOptionName(String optionName);

    void enterFirstOptionPrice(String optionPrice);

    void enterSecondOptionName(String optionName);

    void enterSecondOptionPrice(String optionPrice);

    void enterOptionNameAndPrice(DataTable modifierData);

    void clickTrashIcon();

    void verifyModifierCreated();

    void clickDeleteButtonModifierPage();

    void selectExistingModifier();

    void selectExistingModifierForEdit();

    void clickDeleteEditModifierPage();
}
