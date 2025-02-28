@Regression
Feature:Automate the category feature and its operations

  Background:Register the new account and login to the Backoffice to set currency.
    Given new account is registered for country "gb" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And click on menu button


  @Regression @Category
  Scenario:Create and delete the single and multiple Categories from category screen And Edit and delete the  category from edit page and perform validations
    Given item category is clicked
    When categories option is selected
    And category is created as "Desserts"
    And the category colour is selected
    And the category is saved
    And Validate the message "Category created" is displayed
    And Verify category is displayed on categories page
    And the existing category is deleted
      | Category Name |
      | Desserts      |
    Then Validate the message "Category deleted" is displayed
    And category is created as "Beverages"
    And the category colour is selected
    And the category is saved
    And another category is created as "Main Course"
    And the category colour is selected
    And the category is saved
    And the existing category is deleted
      | Category Name |
      | Beverages     |
      | Main Course   |
    Then Validate the message "Categories deleted" is displayed
    And category is created as "Desserts"
    And the category colour is selected
    And the category is saved
    And Verify category is displayed on categories page
    And Select the existing category to edit
    And update the category name as ""
    And the category is saved
    Then Validate the message "This field cannot be blank" is displayed
    And click on Cancel button
    And click on Continue Editing button
    And update the category name as "Sweets"
    And the category is saved
    And Verify updated category is displayed on categories page
    And delete the existing category from edit page
    Then Validate the message "Category deleted" is displayed

  @Regression @Category
  Scenario:Category Session test case - Editing and Deleting the category in different browser session
    Given item category is clicked
    When categories option is selected
    And category is created as "Desserts"
    And the category colour is selected
    And the category is saved
    And Verify category is displayed on categories page
    And the existing URL is copied and opened in a new tab
    And Select the existing category to edit
    And update the category name as "MainCourse"
    And the category is saved
    And the original browser tab is switched to
    And the original browser tab is refreshed
    And Verify updated category is displayed on categories page
    And the existing category is deleted
      | Category Name |
      | MainCourse    |
    And the browser is redirected to the new tab
    And Select the existing category to edit
    Then Validate the message "This object has been deleted earlier. Please refresh the page." is displayed




