Feature:Automate the modifiers feature and its operations.

  Background:Register the new account and login to the Backoffice to set currency.
    Given new account is registered for country "gb" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And click on menu button

  @Regression @Modifiers
  Scenario:Create and delete the Modifier from modifiers list page
    Given item category is clicked
    When modifiers option is selected
    And modifier is created as "Extras"
    And modifier option name and price is entered as
      | OptionName    | Price |
      | Double Cheese | 3.50  |
      | Ring Onions   | 5.50  |
    And delete one of the modifier on modifier page
    And modifier is saved
    And Validate the message "Modifier created" is displayed
    And Verify modifier is displayed on modifiers page
    And the existing modifier is deleted
    Then Validate the message "Modifier deleted" is displayed

  @Regression @Modifiers
  Scenario:Edit the Modifier and delete from edit modifier page
    Given item category is clicked
    When modifiers option is selected
    And modifier is created as "Extras"
    And modifier option name and price is entered as
      | OptionName    | Price |
      | Double Cheese | 3.50  |
    And modifier is saved
    And Verify modifier is displayed on modifiers page
    And Select the existing modifier to edit
    And update the modifier name as "Toppings"
    And modifier is saved
    And Verify updated modifier is displayed on modifiers page
    And delete the existing modifier from edit page
    Then Validate the message "Modifier deleted" is displayed
