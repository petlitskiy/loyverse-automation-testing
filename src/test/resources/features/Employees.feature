@Regression
Feature:Automate the employee menu functionality of backoffice

  Background:Register the new account and login to the Backoffice to set currency.
    Given new account is registered for country "gb" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And click on menu button

  @Regression
  Scenario:Create the new Employee and verify on employee list page.
    Given the Employee Category is selected
    When the Employee List option is selected
    And the add employee button is clicked
    And the authentication PIN code "6780" is entered
    And the following employee details are entered:
      | Name       | Email               | Phone      | Role          |
      | John Lewis | John@mailinator.com | 6767564534 | Administrator |
    And the Save button is clicked, and the Employee Management for Free is started
    Then verify the employee "John Lewis" is displayed on the Employee List page
    And the existing employee is deleted
    Then Validate the message "Employee deleted" is displayed

