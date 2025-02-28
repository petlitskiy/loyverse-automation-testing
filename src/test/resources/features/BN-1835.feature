@Regression
Feature:Automate the employee menu functionality of backoffice

  Background:Register the new account and login to the Backoffice to set currency.
    Given new account is registered for country "gb" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And click on menu button

  @Regression #@BN-1836  @BN-1837
  Scenario:Verify the behaviour of save button and error messages for the blank Item and category Name Input
    Given item category is clicked
    When item list option is selected
    And Add Item button is clicked
    Then verify the save button is enabled on item page
    And save is clicked on create item page
    And Validate the message "Unable to save changes. Please fix the errors and try again." is displayed
    And click on item name and lose focus
    And Validate the message "This field cannot be blank" is displayed
    And cancel is clicked on create item page
    And categories option is selected
    And Add Category button is clicked
    And verify the save button is "enabled" on category page
    And click on category name field and lose focus
    And Validate the message "This field cannot be blank" is displayed
    And the category is saved
    And Validate the message "Unable to save changes. Please fix the errors and try again." is displayed
    And Validate the message "This field cannot be blank" is displayed


