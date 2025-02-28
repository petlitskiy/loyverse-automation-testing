@Regression
Feature:Complete localization for accounts in which fractional numbers are separated by a comma


  Scenario:The system allows user to enter the monetary value (Cost) for Angola with Currency ALL(No cents).
    Given new account is registered for country "ao" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "Albanian Lek (ALL)" and cents are "disabled"
    And item category is clicked
    And item list option is selected
    And create the new item as "Apple Juice"
    And clear the cost field
    And enter the cost value as "1"
    Then verify the monetary value (Cost) is reflected as "Lek1"
    And clear the cost field
    And enter the cost value as "4578909"
    Then verify the monetary value (Cost) is reflected as "Lek4 578 909"
    And hit save


  Scenario: The system allows user to enter the monetary value (Cost) for UAE with Currency AZN(Has cents).
    Given new account is registered for country "ae" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "Azerbaijani Manat (AZN)" and cents are "disabled"
    And item category is clicked
    And item list option is selected
    And create the new item as "Apple Juice"
    And clear the cost field
    And enter the cost value as "345678.78"
    Then verify the monetary value (Cost) is reflected as "₼345,678.78"
    And hit save


  Scenario: The system allows user to enter the monetary value (cost) for Austria with Currency TWD(Has cents).
    Given new account is registered for country "at" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "New Taiwan Dollar (TWD)" and cents are "enabled"
    And item category is clicked
    And item list option is selected
    And create the new item as "Pasta"
    And clear the cost field
    And enter the cost value as "345678,78"
    Then verify the monetary value (Cost) is reflected as "NT$345.678,78"
    And hit save


  Scenario: The system allows user to enter the monetary value (cost) for ANGOLA with Currency AOA(Has cents).
    Given new account is registered for country "ao" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "Angolan Kwanza (AOA)" and cents are "disabled"
    And item category is clicked
    And item list option is selected
    And create the new item as "Apple Juice"
    And clear the cost field
    And enter the cost value as "787878.92"
    Then verify the monetary value (Cost) is reflected as "Kz787 878,92"
    And clear the cost field
    And enter the cost value as "1"
    Then verify the monetary value (Cost) is reflected as "Kz1,00"
    And hit save


  Scenario: The system populated missing zeros for cost for UAE with Currency AZN(Has cents).
    Given new account is registered for country "ae" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "Azerbaijani Manat (AZN)" and cents are "disabled"
    And item category is clicked
    And item list option is selected
    And create the new item as "Diet Coke"
    And clear the cost field
    And enter the cost value as ".67"
    Then verify the system has populated zero and value is "₼0.67"
    And hit save

  Scenario: The system populated missing zeros for cost for ANGOLA with Currency AOA(Has cents).
    Given new account is registered for country "ao" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "Angolan Kwanza (AOA)" and cents are "disabled"
    And item category is clicked
    And item list option is selected
    And create the new item as "Apple Juice"
    And clear the cost field
    And enter the cost value as ".3"
    Then verify the system has populated zero and value is "Kz0,30"
    And hit save


  Scenario: The system populated missing zeros for cost for Austria with Currency TWD(Has cents).
    Given new account is registered for country "at" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "New Taiwan Dollar (TWD)" and cents are "enabled"
    And item category is clicked
    And item list option is selected
    And create the new item as "Apple Juice"
    And clear the cost field
    And enter the cost value as "456"
    Then verify the system has populated zero and value is "NT$456,00"
    And clear the cost field
    And enter the cost value as ",90"
    Then verify the system has populated zero and value is "NT$0,90"
    And hit save


  Scenario: The System accepts only the accepted length for monetary value (Cost) for Angola with Currency ALL(No cents).
    Given new account is registered for country "ao" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "Albanian Lek (ALL)" and cents are "disabled"
    And item category is clicked
    And item list option is selected
    And create the new item as "Apple Juice"
    And clear the cost field
    And enter the cost value as "5654567450"
    Then verify the cost input accepts only the accepted length as "Lek56 545 674"
    And hit save


  Scenario: The System accepts only the accepted length for monetary value (Cost) for Angola with Currency AOA(No cents).
    Given new account is registered for country "ao" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "Angolan Kwanza (AOA)" and cents are "disabled"
    And item category is clicked
    And item list option is selected
    And create the new item as "Apple Juice"
    And clear the cost field
    And enter the cost value as "787878.922"
    Then verify the cost input accepts only the accepted length as "Kz787 878,92"
    And hit save


  Scenario: The System accepts only the accepted length for monetary value (Cost) for UAE with Currency AZN(Has cents).
    Given new account is registered for country "ae" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "Azerbaijani Manat (AZN)" and cents are "disabled"
    And item category is clicked
    And item list option is selected
    And create the new item as "Apple Juice"
    And clear the cost field
    And enter the cost value as "1234566.788"
    Then verify the cost input accepts only the accepted length as "₼123,456.78"
    And hit save

 
  Scenario: Validate the acceptable value with separators in discount field by percentage
    Given new account is registered for country "ae" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "Azerbaijani Manat (AZN)" and cents are "disabled"
    And item category is clicked
    And discount option is selected
    And add discount button is clicked
    And create the new discount item as "Happy Hours"
    And select the discount type by "Percentage"
    And clear the field and add discount by "Percentage" as "2.9"
    And save is clicked on discount page
    Then verify the discount "Happy Hours" of type "Percentage" is displayed with value "2.9%" and restricted access "No" on discounts page

  Scenario: Validate while displaying percentage values, non-significant zeros are NOT displayed
    Given new account is registered for country "ae" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "Azerbaijani Manat (AZN)" and cents are "disabled"
    And item category is clicked
    And discount option is selected
    And add discount button is clicked
    And create the new discount item as "Happy Hours"
    And select the discount type by "Percentage"
    And clear the field and add discount by "Percentage" as "090"
    Then verify the discount is populated as "90"
    And clear the field and add discount by "Percentage" as "100.00"
    Then verify the discount is populated as "100"
    And clear the field and add discount by "Percentage" as "12.00"
    And save is clicked on discount page
    Then verify the discount "Happy Hours" of type "Percentage" is displayed with value "12%" and restricted access "No" on discounts page


  Scenario: Boundary Value Case - Validate discount in percentage in the discount card (can take a value from 0 to 100.00%)
    Given new account is registered for country "ae" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And the currency is set to "Azerbaijani Manat (AZN)" and cents are "disabled"
    And item category is clicked
    And discount option is selected
    And add discount button is clicked
    And create the new discount item as "Happy Hours"
    And select the discount type by "Percentage"
    And clear the field and add discount by "Percentage" as "0"
    Then verify error message is displayed as  "The value cannot be less than 0.01%"
    And clear the field and add discount by "Percentage" as "0.00"
    Then verify error message is displayed as  "The value cannot be less than 0.01%"
    And clear the field and add discount by "Percentage" as "0."
    Then verify error message is displayed as  "The value cannot be less than 0.01%"
    And clear the field and add discount by "Percentage" as "0.01"
    Then verify the discount is populated as "0.01"
    And clear the field and add discount by "Percentage" as "100.20"
    Then verify error message is displayed as  "The value cannot exceed 100%"
    And clear the field and add discount by "Percentage" as "100.00"
    Then verify the discount is populated as "100"
    And save is clicked on discount page
    Then verify the discount "Happy Hours" of type "Percentage" is displayed with value "100%" and restricted access "No" on discounts page


#  Scenario: Percentage of tax in the tax card (can take a value from 0 to 99.99999%)
#    Given new account is registered for country "ae" at backoffice application via api request
#    When user navigates to "login" page
#    And login credentials are entered
#    And the currency is set to "Azerbaijani Manat (AZN)" and cents are "disabled"
#    And settings category is clicked
#    And taxes option is selected
#    And Add tax button is clicked
#    And create the new tax item as "VAT"
#    And clear the field and update the tax rate as "99.99999"
#    Then verify the tax value is populated in tax input as "99.99999"
#    And clear the field and update the tax rate as "-1"
#    Then verify the tax value is populated in tax input as "1"
#    And clear the field and update the tax rate as "100.99999"
#    Then verify the tax value is populated in tax input as "100"
#    And clear the field and update the tax rate as "10.00000"
#    Then verify the tax value is populated in tax input as "10"
#    And hit save

