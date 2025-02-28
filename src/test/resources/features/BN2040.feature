@BN2040
Feature:After unsubscribing from Integration subscription, Backoffice still is blocked

  Background:Register the new account and login to the Backoffice to set currency.
    Given new account is registered for country "gb" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And click on menu button

  Scenario:After unsubscribing from Integration subscription, Backoffice still is blocked
    Given settings category is clicked
    When billing and subscription option is clicked
    And click on add payment method
    And add the payment method using below details:
      | CardNumber          | ExpiryDate | CVV |
      | 4111 1111 1111 1111 | 01/26      | 468 |
    And trial subscription is enabled for "EmployeeManagement"
    And the add employee button is clicked
    And the authentication PIN code "6780" is entered
    And the following employee details are entered:
      | Name       | Email               | Phone      | Role          |
      | John Lewis | John@mailinator.com | 6767564534 | Administrator |
    And the Save button is clicked, and the Employee Management for Free is started
    And verify the employee "John Lewis" is displayed on the Employee List page
    And settings category is clicked
    And billing and subscription option is clicked
    And trial subscription is enabled for "Integrations"
    And trial subscription is enabled for "AdvancedInventory"
    And the Employee Category is selected
    When the Employee List option is selected
    And the existing employee is deleted
    And settings category is clicked
    And billing and subscription option is clicked
    And change the trial subscription to via API Request for below:
      | SubscriptionType | SubscriptionStatus | SubscriptionState    |
      | EMPSTORE         | false              | TRIALEND_NOSUB_NOACT |
      | INTEGRATION      | true               | TRIALEND_SUB_NOACT   |
      | INVENTORY        | true               | TRIALEND_SUB_NOACT   |
    And refresh the backoffice application
    And validate the billing status is "BLOCKED"
    And click on update payment
    # - might find slow here - internally framework waits for 2mins otherwise payment screen shows GUI error to wait .
    And update the payment details as
      | CardNumber          | ExpiryDate | CVV |
      | 4111 1111 1111 1111 | 01/26      | 456 |
    And ACTIVATE the "annual" plan for "AdvancedInventory"
    #confirm is pressed internally by the script
    And refresh the backoffice application
    And verify the success paid invoice is displayed with below details
      | Service                | Amount  |
      | Advanced inventory (1) | £240.00 |
    And ACTIVATE the "annual" plan for "Integrations"
     #cancel is pressed internally by the script
    And UNSUBSCRIBE for "Integrations" subscription
    Then verify Backoffice is active








