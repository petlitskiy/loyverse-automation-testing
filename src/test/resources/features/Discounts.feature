
Feature:Automate the discounts feature and its operations.

  Background:Register the new account and login to the Backoffice to set currency.
    Given new account is registered for country "gb" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And click on menu button

  @Regression @discounts
  Scenario:Add discounts for type Percentage without restricted Access
    Given item category is clicked
    When discount option is selected
    And add discount button is clicked
    And create the new discount item as "Happy Hours"
    And select the discount type by "Percentage"
    And clear the field and add discount by "Percentage" as "2.9"
    And restricted access is "disabled" for discount
    And save is clicked on discount page
    Then verify the discount "Happy Hours" of type "Percentage" is displayed with value "2.9%" and restricted access "No" on discounts page

  @Regression @discounts
  Scenario:Add discounts for type Percentage with restricted Access
    Given item category is clicked
    When discount option is selected
    And add discount button is clicked
    And create the new discount item as "Happy Hours"
    And select the discount type by "Percentage"
    And clear the field and add discount by "Percentage" as "45.6"
    And restricted access is "enabled" for discount
    And save is clicked on discount page
    Then verify the discount "Happy Hours" of type "Percentage" is displayed with value "45.6%" and restricted access "Yes" on discounts page

  @Regression @discounts
  Scenario:Add discounts for type Amount without restricted Access
    Given item category is clicked
    When discount option is selected
    And add discount button is clicked
    And create the new discount item as "Buy 2 Get 1 Free"
    And select the discount type by "Amount"
    And clear the field and add discount by "Amount" as "45.66"
    And restricted access is "enabled" for discount
    And save is clicked on discount page
    Then verify the discount "Buy 2 Get 1 Free" of type "Amount" is displayed with value "£45.66" and restricted access "Yes" on discounts page

  @Regression @discounts
  Scenario:Create the discount of type Amount with empty value and with restricted Access
    Given item category is clicked
    When discount option is selected
    And add discount button is clicked
    And create the new discount item as "Buy 2 Get 1 Free"
    And select the discount type by "Amount"
    And restricted access is "enabled" for discount
    And save is clicked on discount page
    Then verify the discount "Buy 2 Get 1 Free" of type "Amount" is displayed with value "Variable, ∑" and restricted access "Yes" on discounts page

  @discounts
  Scenario:Add discounts for type Percentage with restricted Access and without Restricted Access with discount value empty an filled along with multiple deletion on list page
    Given item category is clicked
    When discount option is selected
    And add discount button is clicked
    And create the new discount item as "Happy Hours"
    And select the discount type by "Percentage"
    And clear the field and add discount by "Percentage" as "45.6"
    And restricted access is "enabled" for discount
    And save is clicked on discount page
    Then verify the discount "Happy Hours" of type "Percentage" is displayed with value "45.6%" and restricted access "Yes" on discounts page
    And add discount button is clicked
    And create the new discount item as "Sale Bonanza"
    And select the discount type by "Percentage"
    And clear the field and add discount by "Percentage" as "12.9"
    And save is clicked on discount page
    Then verify the discount "Sale Bonanza" of type "Percentage" is displayed with value "12.9%" and restricted access "No" on discounts page
    And add discount button is clicked
    And create the new discount item as "Friday Drinks Offer"
    And select the discount type by "Percentage"
    And restricted access is "enabled" for discount
    And save is clicked on discount page
    Then verify the discount "Friday Drinks Offer" of type "Percentage" is displayed with value "Variable, %" and restricted access "Yes" on discounts page
    And add discount button is clicked
    And create the new discount item as "The best for less"
    And select the discount type by "Percentage"
    And save is clicked on discount page
    Then verify the discount "The best for less" of type "Percentage" is displayed with value "Variable, %" and restricted access "No" on discounts page
    And select all the existing discount from discount list page and delete from the list screen


 @discounts
  Scenario:Add discounts for type Amount with restricted Access and without restricted access / Cancel and Discard Changes / Continue Editing/ Duplicate Discount /Field validation
    Given item category is clicked
    When discount option is selected
    And add discount button is clicked
    And save is clicked on discount page
    And Validate the message "Unable to save changes. Please fix the errors and try again." is displayed
    And Validate the message "This field cannot be blank" is displayed for discount Name
    And create the new discount item as "NameFieldCannotHaveMoreThanFortyCharacters"
    And Validate the message "Field cannot contain more than 40 characters" is displayed for discount Name
    And update the discount item name as "Buy 2 Get 1 Free"
    And select the discount type by "Amount"
    And clear the field and add discount by "Amount" as "45.66"
    And restricted access is "enabled" for discount
    And save is clicked on discount page
    Then verify the discount "Buy 2 Get 1 Free" of type "Amount" is displayed with value "£45.66" and restricted access "Yes" on discounts page
    And add discount button is clicked
    And create the new discount item as "Buy 2 Get 1 Free"
    And select the discount type by "Amount"
    And clear the field and add discount by "Amount" as "45.66"
    And restricted access is "enabled" for discount
    And cancel is clicked on discount page
    And Continue Editing button is clicked
    And save is clicked on discount page
    Then Validate the message "A discount with this name and value already exists" is displayed for discount Name
    And ok is clicked on Error creating discount popup
    And cancel is clicked on discount page
    And Discard Changes button is clicked
    And select the existing discount from discount list page and delete from the list screen

 @discounts
  Scenario:Create the discount of type Amount with empty value and with restricted Access and without Restricted Access
    Given item category is clicked
    When discount option is selected
    And create the new discount item as "Buy 2 Get 1 Free"
    And select the discount type by "Amount"
    And restricted access is "enabled" for discount
    And save is clicked on discount page
    Then verify the discount "Buy 2 Get 1 Free" of type "Amount" is displayed with value "Variable, ∑" and restricted access "Yes" on discounts page

 @discounts
  Scenario:Edit and delete the discount from edit discount screen

 @discounts
  Scenario:Create discount for multiple store and search on the list page