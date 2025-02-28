Feature:Back office Sales Feature Testing With Android Integration

  Scenario:Sale of Item With Discount
    Given new account is registered for country "MALDIVES" at backoffice application via api request
    And the currency is set to "Maldivian Rufiyaa (MVR)" via api request
    And discount is created via api request
      | Name         | Type       | Amount |
      | Buy1Get1Free | Amount     | 10     |
      | SALE         | Percentage | 20%    |
    And item is created via api request
      | Name              | Price  | Cost  | Discount1    | Discount2 |
      | ATestWithDiscount | 153.50 | 53.13 | Buy1Get1Free | SALE      |
    And POS application is logged in with the registered account
    And sale is processed with the created item and discount
    And amount is charged via "Cash" payment method
    When user navigates to "login" page in Back Office
    And login credentials are entered
    And redirect to sales summary report
    Then verify the sale data is present as below
      | Date         | Gross sales | Refunds | Discounts | Net sales | Gross profit |
      | Current Date | 153.50      | 0.00    | 40.70     | 112.80    | 59.67        |
    And redirect to Sales by  Discount report
    Then verify the discount data is present as below
      | Date         | Discounts    | Amount discounted |
      | Current Date | Buy1Get1Free | 10.00             |
      | Current Date | SALE         | 30.70             |

  Scenario:Sale of Item With Modifier
    Given new account is registered for country "MALDIVES" at backoffice application via api request
    And the currency is set to "Maldivian Rufiyaa (MVR)" via api request
    And Modifiers is created via api request
      | Name         | Option  | Price |
      | TestModifier | Option1 | 10.00 |
    And item is created via api request
      | Name              | Price | AverageCost | ModifierName |
      | ATestWithModifier | 30.15 | 15.00       | TestModifier |
    And POS application is logged in with the registered account
    And sale is processed with the created item and modifier option
    And amount is charged via "Cash" payment method
    When user navigates to "login" page in Back Office
    And login credentials are entered
    And redirect to sales summary report
    Then verify the sale data is present as below
      | Date         | Gross sales | Refunds | Discounts | Net sales | Gross profit |
      | Current Date | 40.15       | 0.00    | 0.00      | 40.15     | 25.15        |
    And redirect to Sales by modifier Report
    Then verify the discount data is present as below
      | Date         | ModifierName | Option  | Quantity sold | Gross sales |
      | Current Date | TestModifier | Option1 | 1             | 10.00       |

  Scenario:Sale of Item With Taxes
    Given new account is registered for country "MALDIVES" at backoffice application via api request
    And the currency is set to "Maldivian Rufiyaa (MVR)" via api request
    And Taxes is created via api request
      | Name         | Type       | Amount |
      | Added Tax    | Percentage | 10%    |
      | Included Tax | Percentage | 15%    |
    And item is created via api request
      | Name         | Price  | AverageCost | Taxe1     | Taxe2        |
      | ATestWithTax | 100.00 | 55.00       | Added Tax | Included Tax |
    And POS application is logged in with the registered account
    And sale is processed with the created item
    And amount is charged via "Cash" payment method
    When user navigates to "login" page in Back Office
    And login credentials are entered
    And redirect to sales summary report
    Then verify the sale data is present as below
      | Date         | Gross sales | Refunds | Discounts | Net sales | Gross profit |
      | Current Date | 100.00      | 0.00    | 0.00      | 100.00    | 45.00        |
    And redirect to Sales by Taxes report
    Then verify the Taxes data is present as below
      | Date         | Taxable sales | Non-taxable sales | Total net sales | Tax Name 1 | Tax Rate 1 | Taxable Sales 1 | Tax Amount 1 | Tax Name2    | Tax Rate 2 | Taxable Sales 2 | Tax Amount 2 | Total TAX |
      | Current Date | 100.00        | 0.00              | 100.00          | Added Tax  | 10%        | 100.00          | 8.70         | Included Tax | 15%        | 100.00          | 13.04        | 21.74     |

  Scenario:Sale of Item With variants
    Given new account is registered for country "MALDIVES" at backoffice application via api request
    And the currency is set to "Maldivian Rufiyaa (MVR)" via api request
    And item is created via api request
      | Name         | Price  | AverageCost | VariantName1 | Variant1Price | VariantName2 | Variant2Price |
      | ATestWithVar | 100.15 | 50.00       | Green        | 100.15        | Blue         | 100.15        |
    And POS application is logged in with the registered account
    And sale is processed with the created item and variant "Green" as default
    And amount is charged via "Cash" payment method
    When user navigates to "login" page in Back Office
    And login credentials are entered
    And redirect to sales summary report
    Then verify the sale data is present as below
      | Date         | Gross sales | Refunds | Discounts | Net sales | Gross profit |
      | Current Date | 100.00      | 0.00    | 0.00      | 100.00    | 50.00        |
    And redirect to Sales by item report
    Then verify the item data is present as below
      | Date         | ItemName     | VariantName | Item Sold | Net sales | Cost of goods | Gross profit |
      | Current Date | ATestWithVar | Green       | 1         | 100.00    | 50.00         | 50.00        |

  Scenario:Sale of Item without discount,variants, modifier and tax
    Given new account is registered for country "MALDIVES" at backoffice application via api request
    And the currency is set to "Maldivian Rufiyaa (MVR)" via api request
    And item is created via api request
      | Name  | Price  | AverageCost |
      | Mango | 100.15 | 50.00       |
    And POS application is logged in with the registered account
    And click on item on sale page
    And click on charge button
    And amount is charged via "Cash" payment method
    When user navigates to "login" page in Back Office
    And login credentials are entered
    And redirect to sales summary report
    Then verify the sale data is present as below
      | Date         | Gross sales | Refunds | Discounts | Net sales | Gross profit |
      | Current Date | 100.15      | 0.00    | 0.00      | 100.15    | 50.15        |
    #BN-T336 (1.0) - cover this after above step refund

  Scenario:Sale and Refund of Items With Discount, with Taxes, with variants, with modifiers (checking the Receipts and Sales summary report)
    Given new account is registered for country "MALDIVES" at backoffice application via api request
    And the currency is set to "Maldivian Rufiyaa (MVR)" via api request
    And discount is created via api request
      | Name         | Type       | Amount |
      | Buy1Get1Free | Amount     | 10     |
      | SALE         | Percentage | 20%    |
    And Modifiers is created via api request
      | Name      | Option  | Price |
      | TestModif | Option1 | 10.00 |
    And Taxes is created via api request
      | Name         | Type       | Amount |
      | Added Tax    | Percentage | 10%    |
      | Included Tax | Percentage | 15%    |
    And item is created via api request
      | Name           | Price  | AverageCost | ModifierName | Taxe1     | Taxe2        | VariantName1 | Variant1Price | Variant1Cost | VariantName2 | Variant2Price | Variant2Cost |
      | ATestNoVar     | 100.15 | 50.00       |              |           |              |              |               |              |              |               |              |
      | ATestWithVar   | 100.15 | 50.00       |              |           |              | Green        | 100.15        | 50.00        | Blue         | 100.15        | 50.00        |
      | ATestWithTaxes | 100.00 | 55.00       |              | Added Tax | Included Tax |              |               |              |              |               |              |
      | ATestWithMod   | 30.15  | 15.00       | TestModif    |           |              |              |               |              |              |               |              |
    And POS application is logged in with the registered account
    And Click on the ATestNoVar item
    And Click on the ATestWithVar item
    And Select blue and click Save
    And Click on the ATestWithTaxes item
    And Click on the ATestWithMod item
    And Click on the Option1 and click Save
    And select both discounts option
      | Discount1    | Discount2 |
      | Buy1Get1Free | SALE      |
    And click on charge button
    And amount is charged via "Cash" payment method
    When user navigates to "login" page in Back Office
    And login credentials are entered
    And redirect to sales summary report
    Then verify the sale data is present as below
      | Date         | Gross sales | Refunds | Discounts | Net sales | Gross profit |
      | Current Date | 340.30      | 0.00    | 78.06     | 262.24    | 92.24        |
    And verify the receipt "X-XXXX" for the current date is listed
    And verify the receipt data
    And POS application is logged in with the registered account
    And open  receipt "X-XXXX"
    And select all items and refund the receipt
    When user navigates to "login" page in Back Office
    And login credentials are entered
    And redirect to sales summary report
    Then verify the sale data is present as below
      | Date         | Gross sales | Refunds | Discounts | Net sales | Gross profit |
      | Current Date | 340.30      | 340.30  | 0.00      | 0.00      | 0.00         |
    And click on the receipt Y-XXXX and verify the data
