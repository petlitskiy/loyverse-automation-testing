@Regression
Feature:Automate the inventory management functionality of backoffice

  Background:Register the new account and login to the Backoffice to set currency.
    Given new account is registered for country "gb" at backoffice application via api request
    And user navigates to "login" page
    And login credentials are entered
    And click on menu button
    And click Inventory management
    Then free trial to advanced inventory is opted
    And click on menu button

  @Regression @BN-1840
  Scenario:Create the new supplier under inventory management also validate the input and save fields
    Given the suppliers option is selected
    And  the add supplier button is clicked
    And verify the save supplier button is active
    And save button is clicked on create supplier page
    And Validate the message "Unable to save changes. Please fix the errors and try again." is displayed
    And Validate the message "This field cannot be blank" is displayed
    And the supplier's details are entered as
      | SupplierName | Contact | Email               | Phone         | Website            | Address1 | Address2      | City   | Postal/ZipCode | Country        | Region | Note                                           |
      | Happy And Co | Rodrigo | Mark@mailinator.com | +447878989979 | marksHomeFoods.com | Lane 2   | Victoria Road | London | WM3 T6Q        | United Kingdom | Zone 5 | Supplier deals with home manufactured products |
    When save button is clicked on create supplier page
    Then verify the supplier and its details are listed on the supplier page

  @Regression
  Scenario:Create and Receive Purchase order under inventory management
    Given the suppliers option is selected
    And  the add supplier button is clicked
    And the supplier's details are entered as
      | SupplierName | Contact | Email               | Phone         | Website            | Address1 | Address2      | City   | Postal/ZipCode | Country        | Region | Note                                           |
      | Happy & Co   | Rodrigo | Mark@mailinator.com | +447878989979 | marksHomeFoods.com | Lane 2   | Victoria Road | London | WM3 T6Q        | United Kingdom | Zone 5 | Supplier deals with home manufactured products |
    And save button is clicked on create supplier page
    And item category is clicked
    And item list option is selected
    And create the new item as "Chocolate Mousse"
    And enter the item details as
      | Price | Cost  | SKU   | Barcode |
      | 12.50 | 10.22 | 10000 | 12000   |
    And enable the Track Stock switch and enter the details as
      | InStock | LowStock | Supplier   | OptimalStock |
      | 20      | 5        | Happy & Co | 12           |
    And save is clicked on create item page
    And click Inventory management
    When the purchase order option is selected
    And add purchase button is clicked
    And the purchase order details are entered as
      | Supplier   | Notes                               | Item             | Quantity | Purchase Cost |
      | Happy & Co | This is the purchase order creation | Chocolate Mousse | 10       | 10.22         |
    And verify the total cost is updated as "£102.20"
    When create button is clicked on create purchase order screen and is marked "Pending"
    And order is received for quantity "10"
    And redirect to Purchase Orders list screen
    Then verify the PO is added to the Purchase Orders screen with status "Closed"
    And item category is clicked
    And item list option is selected
    And InStock value is updated to "30" on item list page

#  @Regression2 @BN-1840
#  Scenario:Create and Receive Purchase order under inventory management
#    Given the access token is generated for the account as "AccountAccessToken"
#    And the store UUID is fetched via API
#    And supplier is created via API
#    And the item is created via API
#   # And click on menu button
#    And click Inventory management when already expanded
#    And free trial to advanced inventory is opted
#    And add purchase button is clicked
#    And the purchase order details are entered as
#      | Supplier     | Notes                               | Item             | Quantity | Purchase Cost |
#      | Happy And Co | This is the purchase order creation | Chocolate Mousse | 10       | 10.22         |
#    And verify the total cost is updated as "£102.20"
#    And create button is clicked on create purchase order screen and is marked "Pending"
#    And order is received for quantity "10"
#    And redirect to Purchase Orders list screen
#    Then verify the PO is added to the Purchase Orders screen with status "Closed"
#    And item category is clicked
#    And item list option is selected
#    And InStock value is updated to "30" on item list page
#    #    And create the new item as "Chocolate Mousse"
##    And enter the item details as
##      | Price | Cost  | SKU   | Barcode |
##      | 12.50 | 10.22 | 10000 | 12000   |
##    And enable the Track Stock switch and enter the details as
##      | InStock | LowStock | Supplier   | OptimalStock |
##      | 20      | 5        | Happy & Co | 12           |
##    And save is clicked on create item page
##    And click Inventory management
##    When the purchase order option is selected

  @Regression
  Scenario:Create and receive transfer order under inventory management
    Given settings category is clicked
    And the Stores option is clicked
    And the ADD STORE button is clicked
    And the store details are entered as
      | Name               | Address            | City   | State          | ZipCode | Country        | Phone         | VatNumber  | Description                 |
      | EverydayEssentials | 31 Victoria Street | London | Greater London | G5T Y6Y | United Kingdom | +442345678989 | VAT1297A34 | Welcome to our new store !! |
    And save button is clicked on create store page
    And item category is clicked
    And item list option is selected
    And create the new item as "RiceBowl"
    And enter the item details as
      | Price | Cost  | SKU   | Barcode |
      | 12.50 | 10.22 | 10000 | 12000   |
    And enable the Track Stock switch
    And I enter the store details as
      | Price | InStock | LowStock |
      | 12.50 | 10.5    | 2.5      |
      | 12.50 | 12.5    | 4.20     |
    And save is clicked on create item page
    And click Inventory management
    And the transfer orders option is selected
    And the add transfer order button is clicked
    And the transfer order details are entered as:
      | Source Store | Destination Store  | Item     | Quantity |
      | Italian Cafe | EverydayEssentials | RiceBowl | 10       |
    When transfer order is created with status "In transit"
    And the transfer order is received
    Then verify the TO is listed on Transfer Orders screen with status "Transferred"
    And item category is clicked
    And item list option is selected
    And click on store "EverydayEssentials"
    And InStock value is updated to "20.5" on item list page
    And click on store "Italian Cafe"
    And InStock value is updated to "2.5" on item list page

  @Regression
  Scenario Outline:Create stock adjustments under inventory management for Damage
    And item category is clicked
    And item list option is selected
    And create the new item as "RiceBowl"
    And enter the item details as
      | Price | Cost  | SKU   | Barcode |
      | 12.50 | 10.22 | 10000 | 12000   |
    And enable the Track Stock switch and enter the details as
      | InStock | LowStock | OptimalStock |
      | 30      | 5        | 12           |
    And save is clicked on create item page
    And click Inventory management
    And the Stock adjustments option is selected
    And add Stock adjustments button is clicked
    When stock adjustments reason is selected as "<Reason>", item as "<Item>"and remove stock quantity as "<Remove Stock>"
    Then the stock adjustment for "<Reason>" with adjusted quantity as "<Remove Stock>" is created
    And item category is clicked
    And item list option is selected
    And InStock value is updated to "<InStock>" on item list page
    Examples:
      | Reason | Item     | Remove Stock | InStock |
      | Damage | RiceBowl | 5            | 25      |

  @Regression
  Scenario Outline:Create stock adjustments under inventory management for Loss
    And item category is clicked
    And item list option is selected
    And create the new item as "RiceBowl"
    And enter the item details as
      | Price | Cost  | SKU   | Barcode |
      | 12.50 | 10.22 | 10000 | 12000   |
    And enable the Track Stock switch and enter the details as
      | InStock | LowStock | OptimalStock |
      | 30      | 5        | 12           |
    And save is clicked on create item page
    And click Inventory management
    And the Stock adjustments option is selected
    And add Stock adjustments button is clicked
    When stock adjustments reason is selected as "<Reason>", item as "<Item>"and remove stock quantity as "<Remove Stock>"
    Then the stock adjustment for "<Reason>" with adjusted quantity as "<Remove Stock>" is created
    And item category is clicked
    And item list option is selected
    And InStock value is updated to "<InStock>" on item list page
    Examples:
      | Reason | Item     | Remove Stock | InStock |
      | Loss   | RiceBowl | 10           | 20      |


  @Regression
  Scenario Outline:Create stock adjustments under inventory management for Receive items
    Given item category is clicked
    And item list option is selected
    And create the new item as "RiceBowl"
    And enter the item details as
      | Price | Cost  | SKU   | Barcode  |
      | 12.50 | 10.22 | 10000 | 34572878 |
    And enable the Track Stock switch and enter the details as
      | InStock | LowStock | OptimalStock |
      | 30      | 5        | 12           |
    And save is clicked on create item page
    And click Inventory management
    And the Stock adjustments option is selected
    And add Stock adjustments button is clicked
    When stock adjustments details is selected as "<Reason>",item as "<Item>", AddStock as "<Add Stock>",Cost as "<Cost>" and Counted Stock as "<Counted Stock>"
    Then the stock adjustment for "<Reason>" with adjusted quantity as "<Add Stock>" is created
    And item category is clicked
    And item list option is selected
    And InStock value is updated to "<InStock Quantity>" on item list page
    Examples:
      | Reason        | Item     | Add Stock | Cost  | Counted Stock | InStock Quantity |
      | Receive items | RiceBowl | 30.55     | 11.22 |               | 60.55            |

  @Regression
  Scenario Outline:Create stock adjustments under inventory management for Inventory count
    Given item category is clicked
    And item list option is selected
    And create the new item as "RiceBowl"
    And enter the item details as
      | Price | Cost  | SKU   | Barcode  |
      | 12.50 | 10.22 | 10000 | 34572878 |
    And enable the Track Stock switch and enter the details as
      | InStock | LowStock | OptimalStock |
      | 30      | 5        | 12           |
    And save is clicked on create item page
    And click Inventory management
    And the Stock adjustments option is selected
    And add Stock adjustments button is clicked
    When stock adjustments details is selected as "<Reason>",item as "<Item>", AddStock as "<Add Stock>",Cost as "<Cost>" and Counted Stock as "<Counted Stock>"
    Then the stock adjustment for "<Reason>" with adjusted quantity as "<Counted Stock>" is created
    And item category is clicked
    And item list option is selected
    And InStock value is updated to "<InStock Quantity>" on item list page
    Examples:
      | Reason          | Item     | Add Stock | Cost | Counted Stock | InStock Quantity |
      | Inventory count | RiceBowl |           |      | 20.55         | 20.55            |

#
#  Scenario:Create the inventory count under inventory management
#    Given item category is clicked
#    And item list option is selected
#    And create the new item as "RiceBowl"
#    And enter the item details as
#      | Price | Cost  | SKU   | Barcode  |
#      | 12.50 | 10.22 | 10000 | 34572878 |
#    And enable the Track Stock switch and enter the details as
#      | InStock | LowStock | OptimalStock |
#      | 30      | 5        | 12           |
#    And save is clicked on create item page
#    And click Inventory management
#    And the inventory counts is selected
#    And add inventory counts button is clicked
#    And the inventory count details and clcik save
#      | Type | Notes                               |
#      | Full | This is the purchase order creation |
#    And update the details on count stock and confirm count
#      | CountItem | Quantity | Counted |
#      | RiceBowl  | 1        | 1       |
#    Then verify the details are updated on inventory Counts page
#
#  Scenario:Create the production under inventory management
#    Given an Average item is created from the api request
#    And an composite item is created from the api request
#    And click Inventory management
#    When the production option is selected
#    And add production button is clicked
#    And production details are updated as
#      | Item          | Quantity |
#      | compositeitem | 2        |
#    Then verify production details are updated on production page
#
