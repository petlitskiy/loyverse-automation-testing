@Regression
Feature:Backoffice Registration


  @Regression @BN-1777
  Scenario:On Boarding Dialogue - Cancel the settings
    Given user navigates to "registration" page
    When the user submits the following registration details:
      | BusinessName  | Country        |
      | Bar and Grill | United Kingdom |
    Then a dialog box with the message "Thank you for registering!" is displayed
    And the user clicks on the "Google Play" badge, verify opening links in new browser tab and web Title as "Loyverse POS - Point of Sale - Apps on Google Play"
    And the user clicks the Next button on the dialog box
    And the "Feature settings" dialog is displayed
    And verify below feature setting icons are "disabled"
      | Feature                  |
      | Shifts                   |
      | Time clock               |
      | Open tickets             |
      | Kitchen printers         |
      | Customer displays        |
      | Dining options           |
      | Low stock notifications  |
      | Negative stock alerts    |
      | Weight embedded barcodes |
    And the user clicks the Cancel button on feature settings dialouge
    And the "Item list" page is displayed
    And click on menu button
    And settings category is clicked
    Then verify below feature setting icons are "disabled"
      | Feature                  |
      | Shifts                   |
      | Time clock               |
      | Open tickets             |
      | Kitchen printers         |
      | Customer displays        |
      | Dining options           |
      | Low stock notifications  |
      | Negative stock alerts    |
      | Weight embedded barcodes |

  @BN-1777 @Regression
  Scenario:Onboarding Dialogue- Save the settings  / Perform Registration and logout
    Given user navigates to "registration" page
    When the user submits the following registration details:
      | BusinessName  | Country        |
      | Bar and Grill | United Kingdom |
    And the user clicks on the "Apple Play" badge, verify opening links in new browser tab and web Title as "Loyverse POS - Point of Sale on the App Store"
    And the user clicks the Next button on the dialog box
    And the "Feature settings" dialog is displayed
    And the user clicks the Save button on feature settings dialouge
    Then Validate the message "Feature settings changed" is displayed
    And the "Item list" page is displayed
    And user clicks on sign out button

  @BN-1777 @Regression
  Scenario:Onboarding Dialogue- setting up Time Clock feature with PIN setup
    Given user navigates to "registration" page
    When the user submits the following registration details:
      | BusinessName  | Country        |
      | Bar and Grill | United Kingdom |
    And the user clicks the Next button on the dialog box
    And update the feature settings
      | Feature    | Status  |
      | Time clock | enabled |
    And the user clicks the Save button on feature settings dialouge
    And Enter "3456" on the set PIN dialogue
    And click confirm on Set Pin dialouge
    And the "Item list" page is displayed
    And click on menu button
    And settings category is clicked
    Then verify below feature setting icons are "enabled"
      | Feature    |
      | Time clock |

  @BN-1777 @Regression
  Scenario:Onboarding Dialogue- Cancelling the Pin Setup for Time clock
    Given user navigates to "registration" page
    When the user submits the following registration details:
      | BusinessName  | Country        |
      | Bar and Grill | United Kingdom |
    And the user clicks the Next button on the dialog box
    And update the feature settings
      | Feature    | Status  |
      | Time clock | enabled |
    And the user clicks the Save button on feature settings dialouge
    And Enter "3456" on the set PIN dialogue
    And click cancel on Set Pin dialouge
    And the "Item list" page is displayed
    And click on menu button
    And settings category is clicked
    Then verify below feature setting icons are "disabled"
      | Feature    |
      | Time clock |

  @BN-1777 @Regression
  Scenario:Onboarding Dialogue setting up/ enabling the feature/s including Time Clock
    Given user navigates to "registration" page
    When the user submits the following registration details:
      | BusinessName  | Country        |
      | Bar and Grill | United Kingdom |
    Then the user clicks the Next button on the dialog box
    And update the feature settings
      | Feature                  | Status  |
      | Shifts                   | enabled |
      | Time clock               | enabled |
      | Open tickets             | enabled |
      | Kitchen printers         | enabled |
      | Customer displays        | enabled |
      | Dining options           | enabled |
      | Low stock notifications  | enabled |
      | Negative stock alerts    | enabled |
      | Weight embedded barcodes | enabled |
    And the user clicks the Save button on feature settings dialouge
    And Enter "3456" on the set PIN dialogue
    And click confirm on Set Pin dialouge
    And click on menu button
    And settings category is clicked
    Then verify below feature setting icons are "enabled"
      | Feature                  |
      | Shifts                   |
      | Time clock               |
      | Open tickets             |
      | Kitchen printers         |
      | Customer displays        |
      | Dining options           |
      | Low stock notifications  |
      | Negative stock alerts    |
      | Weight embedded barcodes |


  @BN-1777 @Regression
  Scenario:Onboarding Dialogue setting up/ enabling the feature/s excluding Time Clock
    Given user navigates to "registration" page
    When the user submits the following registration details:
      | BusinessName  | Country        |
      | Bar and Grill | United Kingdom |
    Then the user clicks the Next button on the dialog box
    And update the feature settings
      | Feature                  | Status  |
      | Shifts                   | enabled |
      | Open tickets             | enabled |
      | Kitchen printers         | enabled |
      | Customer displays        | enabled |
      | Dining options           | enabled |
      | Low stock notifications  | enabled |
      | Negative stock alerts    | enabled |
      | Weight embedded barcodes | enabled |
    And the user clicks the Save button on feature settings dialouge
    And click on menu button
    And settings category is clicked
    Then verify below feature setting icons are "enabled"
      | Feature                  |
      | Shifts                   |
      | Open tickets             |
      | Kitchen printers         |
      | Customer displays        |
      | Dining options           |
      | Low stock notifications  |
      | Negative stock alerts    |
      | Weight embedded barcodes |

  @Regression
  Scenario:Validate the successful logout for backoffice after registration is done from API.
    Given new account is registered for country "ao" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And user clicks on sign out button
