Feature:Automate the creation of item,modifiers,discounts and categories and their operations.

  Background:Register the new account and login to the Backoffice to set currency.
    Given new account is registered for country "gb" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And click on menu button

  @Regression
  Scenario:Create and update New Average Item with Track Stock details including category and modifier - SingleStore
    Given item category is clicked
    And categories option is selected
    And category is created as "Desserts"
    And the category is saved
    And modifiers option is selected
    And modifier is created as "Toppings"
    And modifier option name and price is entered as
      | OptionName    | Price |
      | Double Cheese | 3.50  |
    And modifier is saved
    When item list option is selected
    And create the new item as "Chocolate Mousse"
    And select the category from the category dropdown as "Desserts"
    And enter the description as "Caramel flavoured Mousse"
    And enter the item details as
      | Price | Cost  | SKU   | Barcode  |
      | 2.50  | 10.22 | 10000 | 34572878 |
    And enable the Track Stock switch
    And enter the In Stock and Low stock as "12.22" and "10" respectively
    And the modifier is "enabled"
    #And item image is uploaded
    And save is clicked on create item page
    Then verify the item is created on item list page
    #And select the existing item to edit
    #And update the item details as
     # | Price | Cost | SKU   | Barcode | Name            | Description         | In Stock | Low stock |
     # | 5.50 | 2.30 | 12000 | 500001 | Choco Lava cake | Rich chocolate cake | 20.66 | 10.55 |
    #And save is clicked on create item page
    #Then verify the item is created on item list page

  @Regression
  Scenario:Create New Item with Variants - SingleStore
    Given item category is clicked
    And categories option is selected
    And category is created as "Italian Cuisine"
    And the category is saved
    When item list option is selected
    And create the new item as "Supreme Pizza"
    And select the category from the category dropdown as "Italian Cuisine"
    And enter the description as "Fresh Oven Baked pizza available in different sizes and seasoning of choice."
    And enter the item details as
      | Price | Cost | SKU      | Barcode |
      | 12.50 | 8.50 | SKU10001 | 10000   |
    And enable the Track Stock switch
    And enter the In Stock and Low stock as "50.45" and "12.20" respectively
    And create the "Regular","Medium" variant for option "Size"
    And update details for variants
     # | VariantType | Price | Cost | InStock | LowStock | OptimalStock | SKU      | Barcode |
     # | Regular | 8.50 | 4.25 | 50.45 | 12.2 | 10.5 | SKU10001 | 10000 |
      #| Medium | 4.50 | 2.25 | 50.45 | 12.2 | 10.5 | SKU10001 | 10001 |
    And save is clicked on create item page
    Then verify the item and variant details are populated on item list page.
    #And update the item details as
     # | Price | Cost | SKU   | Barcode | Name            | Description         | In Stock | Low stock |
     # | 5.50 | 2.30 | 12000 | 500001 | Choco Lava cake | Rich chocolate cake | 20.66 | 10.55 |
    #And save is clicked on create item page
    #Then verify the item is updated on item list page

  @Regression
  Scenario:Create the composite item - SingleStore
    Given item category is clicked
    When categories option is selected
    And category is created as "Beverages"
    And the category is saved
    And item list option is selected
    And create the new item as "Orange Juice"
    And select the category from the category dropdown as "Beverages"
    And enter the description as "Refreshing taste of freshly squeezed orange juice, specially crafted for the summer season."
    And enter the item details as
      | Price | Cost | SKU      | Barcode |
      | 12.50 | 8.50 | SKU10001 | 10000   |
    And save is clicked on create item page
    And create the new item as "Orange Mocktail"
    And select the category from the category dropdown as "Beverages"
    And enter the description as "Revitalizing and flavorful mocktail perfect for the summer season."
    And enter the item details as
      | Price | Cost  | SKU      | Barcode |
      | 13.50 | 10.21 | SKU20001 | 20000   |
    And enable the composite Item switch and update the quantity as "12.50" for item "Orange Juice"
    Then assert the total composite component cost populated is equal to cost in cost field
    And save is clicked on create item page
    #Then verify the item is created on item list page
   # And update the item details as
     # | Price | Cost | SKU   | Barcode | Name            | Description         | In Stock | Low stock |
     # | 5.50 | 2.30 | 12000 | 500001 | Choco Lava cake | Rich chocolate cake | 20.66 | 10.55 |
    #And save is clicked on create item page
    #Then verify the item is updated on item list page

  @Regression
  Scenario:Create new Average item - MultiStore
    Given settings category is clicked
    When the Stores option is clicked
    And the ADD STORE button is clicked
    And the store details are entered as
      | Name          | Address            | City   | State          | ZipCode | Country        | Phone         | VatNumber | Description                 |
      | Cafe Rockstar | 31 Victoria Street | London | Greater London | G5T Y6Y | United Kingdom | +442345678989 | VATRF454  | Welcome to our new store !! |
    And save button is clicked on create store page
    And item category is clicked
    And categories option is selected
    And category is created as "Desserts"
    And the category is saved
    And item list option is selected
    And create the new item as "Chocolate Mousse"
    And select the category from the category dropdown as "Desserts"
    And enter the description as "Caramel flavoured Mousse"
    And enter the item details as
      | Price | Cost | SKU    | Barcode |
      | 2.50  | 1.50 | SKU000 | BA0001  |
    And enable the Track Stock switch
    Then I enter the store details as
      | Price | InStock | LowStock |
      | 2.50  | 10.5    | 2.5      |
      | 3.50  | 12.5    | 4.20     |
    And save is clicked on create item page
    #Then verify the item is created on item list page

  Scenario:Edit the existing item from item list page - MultiStore
    Given item category is clicked
    And categories option is selected
    And category is created as "Desserts"
    And the category is saved
    When item list option is selected
    And create the new item as "Chocolate Mousse"
    And enter the description as "Caramel flavoured Mousse"
    And enter the item details as
      | Price | Cost  | SKU   | Barcode  |
      | 2.50  | 10.22 | 10000 | 34572878 |
    And enable the Track Stock switch
    And enter the In Stock and Low stock as "12.22" and "10" respectively
    And the modifier is "enabled"
    And save is clicked on create item page
    Then verify the item is created on item list page
    And update the item details from item list page as
      | Category | Price | Cost | InStock |
      | Desserts | 14.55 | 5.00 | 20.22   |
    Then verify the values are updated on item list card


  Scenario:Create New Average Item with Track Stock details including category and modifier - SingleStore
    Given item category is clicked
    And categories option is selected
    And category is created as "Desserts"
    And the category is saved
    And another category is created as "Sweets"
    And the category is saved
    And modifiers option is selected
    And modifier is created as "Toppings"
    And modifier option name and price is entered as
      | OptionName    | Price |
      | Double Cheese | 3.50  |
    And modifier is saved
    When item list option is selected
    And click Add item button
    And create new item with following details
      | ItemName         | Category | Description              | Price | Cost  | SKU   | Barcode  | TrackStockSwitch | InStock | LowStock | ModifierIcon |
      | Chocolate Mousse | Desserts | Caramel flavoured Mousse | 2.50  | 10.22 | 10000 | 34572878 | enabled          | 12.22   | 10       | enabled      |
    And save is clicked on create item page
    Then verify the item is created on item list page
    And select the existing item to edit
    And update the item details as
      | Price | Cost | SKU   | Barcode | Name            | Description         | In Stock | Low stock | ItemName       | Category | Description                     |
      | 5.50  | 2.30 | 12000 | 500001  | Choco Lava cake | Rich chocolate cake | 20.66    | 10.55     | ChocoLava Cake | Sweets   | House Favourite choco lave cake |
    And save is clicked on create item page
    Then verify the item is updated on item list page