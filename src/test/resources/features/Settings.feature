Feature:Automate the settings menu functionality of backoffice

  Background:Register the new account and login to the Backoffice to set currency.
    Given new account is registered for country "gb" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And click on menu button

  @BN-1738 
  Scenario:Create,Edit and Delete the Store and POS devices under settings for VAT Country with field validations of min and max length.
    Given settings category is clicked
    And the Stores option is clicked
    And the ADD STORE button is clicked
    And the store details are entered as
      | Name | Address                                                                                                                                                                                                                                                          | City                                                              | State                                                             | ZipCode               | Country        | Phone   | VatNumber        | Description           |
      | S    | thestringistwofiftysixcharcatersthestringistwofiftysixcharcatersthestringistwofiftysixcharcatersthestringistwofiftysixcharcatersthestringistwofiftysixcharcatersthestringistwofiftysixcharcatersthestringistwofiftysixcharcatersthestringistwofiftysixcharcaters | thestringistwofiftysixcharcatersthestringistwofiftysixcharcaterss | thestringistwofiftysixcharcatersthestringistwofiftysixcharcaterss | thestringistwofiftysi | United Kingdom | 1223455 | thestringistwofi | validate store errors |
    And Validate the message "Field must contain at least 2 characters" is displayed
    And Validate the message " field cannot contain more than 255 characters" is displayed
    #And Validate the message "Field cannot contain more than 64 characters" is displayed
    #And Validate the message "Field cannot contain more than 64 characters" is displayed
    And Validate the message "Field cannot contain more than 20 characters" is displayed
    And Validate the message "Phone number is too short" is displayed
    #And Validate the message "VAT number should not exceed 15 characters" is displayed
    And the store details are entered as
      | Name          | Address            | City   | State          | ZipCode | Country        | Phone         | VatNumber | Description                 |
      | Cafe Rockstar | 31 Victoria Street | London | Greater London | G5T Y6Y | United Kingdom | +442345678989 | VAT1234   | Welcome to our new store !! |
    When save button is clicked on create store page
    Then verify "Cafe Rockstar" store is created with address details as "31 Victoria Street, London, Greater London, G5T Y6Y, United Kingdom" with number of pos as "0"
    And select the existing "Cafe Rockstar" store
    And edit the store details as
      | Name       | Address              | City   | State  | ZipCode | Country        | Phone         | VatNumber | Description          |
      | Coffee Bar | 31 Marshall's Street | London | Oxford | G9T Y8Y | United Kingdom | +442345678978 | VAT989    | Update Store Details |
    And save button is clicked on create store page
    Then verify "Coffee Bar" store is created with address details as "31 Marshall's Street, London, Oxford, G9T Y8Y, United Kingdom" with number of pos as "0"
    And select the existing "Coffee Bar" store
    And delete the store from edit store page
    Then Validate the message "Store deleted" is displayed
    And the ADD STORE button is clicked
    And save button is clicked on create store page
    And Validate the message "This field cannot be blank" is displayed
    And the store details are entered as
      | Name | Address              | City   | State  | ZipCode | Country        | Phone        | VatNumber | Description          |
      | Ana  | 31 Marshall's Street | London | Oxford | G9T Y8Y | United Kingdom | 442345678978 | VAT989    | Update Store Details |
    And save button is clicked on create store page
    Then verify "Ana" store is created with address details as "31 Marshall's Street, London, Oxford, G9T Y8Y, United Kingdom" with number of pos as "0"
    And Pos device option is selected
    And the ADD POS DEVICE button is clicked
    And save button is clicked on create POS page
    Then Validate the message "This field cannot be blank" is displayed
    Then Validate the message "Select a store" is displayed
    And pos name and store is entered as "PosStoreAna" and "Ana"
    And save button is clicked on create POS page
    Then Validate the message "POS created" is displayed
    And verify the pos "PosStoreAna" is created with store "Ana" on pos list page with status "Not activated"
    And the ADD POS DEVICE button is clicked
    And pos name and store is entered as "PosStoreItalianCafe" and "Italian Cafe"
    And save button is clicked on create POS page
    Then Validate the message "POS created" is displayed
    And verify the pos "PosStoreItalianCafe" is created with store "Italian Cafe" on pos list page with status "Not activated"
    And select the store "Ana" on post list page
    And store is shown as "PosStoreAna" on pos list page
    And select the store "Italian Cafe" on post list page
    And store is shown as "POS 1" on pos list page
    And open the pos "PosStoreItalianCafe" from the list page
    And update the pos name as "PosStoreItalianCafeUpdated"
    And save button is clicked on create POS page
    Then Validate the message "POS edited" is displayed
    And verify the pos "PosStoreItalianCafeUpdated" is created with store "Italian Cafe" on pos list page with status "Not activated"
    And open the pos "PosStoreItalianCafeUpdated" from the list page
    And delete the pos from edit pos page
    Then Validate the message "POS deleted" is displayed



  Scenario:Create ,Edit and Delete the Tax for Included and Added tax type with different options under settings.
    Given settings category is clicked
    When taxes option is selected
    And Add tax button is clicked
    And hit save on create tax page
    Then assert error message is displayed "This field cannot be blank" for Tax Name and Tax Rate
    And create the new tax item as "VAT Included"
    And clear the field and update the tax rate as "23"
    And select the tax type as "Included in the price"
    And select the tax option as "Apply the tax to the new items"
    And hit save on create tax page
    Then verify the tax created is shown as "VAT Included" with "Yes" as Apply to new items and tax rate "23%"
    And Add tax button is clicked when taxes are already present
    And create the new tax item as "GST ADDED"
    And clear the field and update the tax rate as "10"
    And select the tax type as "Added to the price"
    And select the tax option as "Apply the tax to existing items"
    And hit save on create tax page
    And click on ok for Applying tax to existing items
    Then verify the tax created is shown as "GST ADDED" with "No" as Apply to new items and tax rate "10%"
    And Add tax button is clicked when taxes are already present
    And create the new tax item as "Additional TAX ADDED"
    And clear the field and update the tax rate as "10"
    And select the tax type as "Added to the price"
    And select the tax option as "Apply the tax to all new and existing items"
    And hit save on create tax page
    And click on ok for Applying tax to existing items
    Then verify the tax created is shown as "Additional TAX ADDED" with "Yes" as Apply to new items and tax rate "10%"
    And select the existing tax "VAT Included"
    And update the tax name as "VAT Included Updated"
    And clear the field and update the tax rate as "17"
    And hit save on create tax page
    Then verify the tax created is shown as "VAT Included Updated" with "Yes" as Apply to new items and tax rate "17%"
    And select the existing tax "VAT Included Updated"
    And delete the tax from edit tax page
    Then Validate the message "Tax deleted" is displayed

  @Regression
  Scenario:Add the Receipt settings
    Given settings category is clicked
    When the receipt option is selected
    And set the header and footer as "Italian Cafe" and "Please call customer care to return any item"
    And the show customer info and show comments is enabled
    And the languages is selected as "English"
    And click save on Receipt settings page
    Then I should see the message "Receipt settings updated" is displayed


  Scenario:Add the payments types
    Given settings category is clicked
    And the Stores option is clicked
    And the ADD STORE button is clicked
    And the store details are entered as
      | Name | Address              | City   | State  | ZipCode | Country        | Phone        | VatNumber | Description          |
      | Ana  | 31 Marshall's Street | London | Oxford | G9T Y8Y | United Kingdom | 442345678978 | VAT989    | Update Store Details |
    And save button is clicked on create store page
    When payment types option is clicked under settings
    And Add payment type button is clicked
    And click "Save" on Payments type page
    Then Validate the message "Select a payment type" is displayed
    And Validate the message "This field can not be empty" is displayed
    And Validate the message "Unable to save changes. Please fix the errors and try again." is displayed
    And payment type and name is entered as "Card" and "Card payment"
    And click "Cancel" on Payments type page
    And click on "Discard Changes" on unsaved changed popup
    And Add payment type button is clicked
    And payment type and name is entered as "Card" and "Card payment"
    And click "Cancel" on Payments type page
    And click on "Continue Editing" on unsaved changed popup
    And click "Save" on Payments type page
    Then Validate the message "Payment type created" is displayed
    And verify "Payment Name" is listed on the payment type list page
      | Payment Name |
      | Card payment |
    And Add payment type button is clicked
    And payment type and name is entered as "Card" and "Card payment"
    And click "Save" on Payments type page
    Then Validate the message "A payment type with this name already exists" is displayed
    And payment type and name is entered as "Check" and "Check payment"
    And select store as "Ana"
    And click "Save" on Payments type page
    When Add payment type button is clicked
    And payment type and name is entered as "Other" and "Other payment"
    And click "Save" on Payments type page
    When Add payment type button is clicked
    And payment type and name is entered as "SumUp" and "SumUp payment"
    And enable the Collect tool tip
    And clear the existing tip amount for "Tip Value 1"
    Then Validate the message "This field can not be blank" is displayed
    And clear the existing tip amount for "Tip Value 2"
    And Validate the message "This field can not be blank" is displayed
    And clear the existing tip amount for "Tip Value 3"
    And Validate the message "This field can not be blank" is displayed
    And update the collect tip value  for Tip 1 , Tip 2 and Tip 3 as "2.00" , "6.00" and "13.00"
    And click "Save" on Payments type page
    When Add payment type button is clicked
    And payment type and name is entered as "Zettle" and "Zettle payment"
    And click "Save" on Payments type page
    And open the payment type "Other"
    And payment type and name is entered as "Card" and "Field cannot have more"
    And click "Save" on Payments type page
    Then Validate the message "Field cannot contain more than 20 characters" is displayed
    And payment type and name is entered as "Card" and "Card payment 2"
    And click "Save" on Payments type page
    Then Validate the message "Payment type is edited" is displayed
    And open the payment type "Card"
    And delete the payment type from edit payment type page
    Then Validate the message "Payment type deleted" is displayed
   #store search and all deletion from list page



