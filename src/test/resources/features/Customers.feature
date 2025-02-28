Feature:Automate the customer menu functionality of backoffice

  Background:Register the new account and login to the Backoffice to set currency.
    Given new account is registered for country "gb" at backoffice application via api request
    When user navigates to "login" page
    And login credentials are entered
    And click on menu button

  @Regression
  Scenario:Create, Edit and Delete and Search a Customer along with field validations.
    Given the customers category is clicked
    When add customer button is clicked
    And customer details are saved
    Then Validate the message "This field cannot be blank" is displayed
    And Validate the message "Unable to save changes. Please fix the errors and try again." is displayed
    And the customer details are updated as
      | Name         | Email | Phone            | Address                                                                                                                                                                                           | City                                                              | State                                                             | ZipCode               | Country       | CustomerCode                              | Note                                                                                                                                                                                                                                                             |
      | David Miller | Dav   | 2345435345345445 | THisFieldcannotcontainmorethan192charactersTHisFieldcannotcontainmorethan192charactersTHisFieldcannotcontainmorethan192charactersTHisFieldcannotcontainmorethan192charactersTHisFieldcannotcontai | thisfieldcannotcontainmorethan64charactersthisfieldcannotcontainm | thisfieldcannotcontainmorethan64charactersthisfieldcannotcontainm | Thisfieldcannotcontai | United States | thisfieldcannotcontainmorethan40character | Thisfieldcannotcontainmorethan255charactersThisfieldcannotcontainmorethan255charactersThisfieldcannotcontainmorethan255charactersThisfieldcannotcontainmorethan255charactersThisfieldcannotcontainmorethan255charactersThisfieldcannotcontainmorethan255characte |
    And customer details are saved
    And Validate the message "Enter a valid email" is displayed
    And Validate the message "The number is too long" is displayed
    And Validate the message "Field cannot contain more than 192 characters" is displayed
    #And Validate the message "Field cannot contain more than 64 characters" is displayed #to be enabled when BN2988 is done
    #And Validate the message "Field cannot contain more than 64 characters" is displayed #to be enabled when BN2988 is done
    And Validate the message "Field cannot contain more than 20 characters" is displayed
    And Validate the message "Field cannot contain more than 20 characters" is displayed
    And Validate the message "Field cannot contain more than 40 characters" is displayed
    And Validate the message "256 / 255" is displayed
    When the customer details are updated as
      | Name         | Email              | Phone      | Address          | City   | State   | ZipCode | Country        | CustomerCode | Note                  |
      | David Miller | Dav@mailinator.com | 7556078987 | 31 lawrence Road | London | Central | SM31QN  | United Kingdom | CN787        | Customer Note Section |
    And customer details are saved
    Then Validate the message "Customer added" is displayed
    # update id's when added for below steps
    And verify the customer details on profile screen
      | Name         | Email              | Phone      | Address          | City   | State   | ZipCode | Country        | CustomerCode | Note                  |
      | David Miller | Dav@mailinator.com | 7556078987 | 31 lawrence Road | London | Central | SM31QN  | United Kingdom | CN787        | Customer Note Section |
    And click on customer base icon to redirect to customer list screen
    And validate the customer details on customer list page
      | Name         | Note                  | Email              | Phone      | First visit | Last visit | Total visits | Total spent | Points balance |
      | David Miller | Customer Note Section | Dav@mailinator.com | 7556078987 | —           | —          | 0            | £0.00       | 0.00           |
    And click on existing customer "David Miller" to edit
    And update and verify the edit points balance to "10.00"
    And click on edit profile button
    And the customer details are updated as
      | Name         | Email                | Phone       | Address         | City    | State  | ZipCode | Country       | CustomerCode | Note                     |
      | Peter Muller | Peter@mailinator.com | 72346070087 | 34 Spencer Road | NewYork | Zone 5 | SMQAQN  | United States | CN787ED      | Customer Details updated |
    And click on Cancel button on edit customer screen
    And click on Continue Editing button
    And customer details are saved
    And verify the customer details on profile screen
      | Name         | Email                | Phone       | Address         | City    | State  | ZipCode | Country       | CustomerCode | Note                     |
      | Peter Muller | Peter@mailinator.com | 72346070087 | 34 Spencer Road | NewYork | Zone 5 | SMQAQN  | United States | CN787ED      | Customer Details updated |
    And click on customer base icon to redirect to customer list screen
    And validate the customer details on customer list page
      | Name         | Note                     | Email                | Phone       | First visit | Last visit | Total visits | Total spent | Points balance |
      | Peter Muller | Customer Details updated | Peter@mailinator.com | 72346070087 | —           | —          | 0            | £0.00       | 10.00          |
    And add customer button is clicked when customer is already present
    And the customer details are updated as
      | Name                | Email                | Phone      | Address         | City   | State  | ZipCode | Country        | CustomerCode | Note                    |
      | Create Customer two | Peter@mailinator.com | 7556088987 | 98 Philips Road | London | Middle | SM44AQN | United Kingdom | CN787ED      | Second Customer created |
    And customer details are saved
    And Validate the message "Unable to save changes. Please fix the errors and try again." is displayed
    And Validate the message "A customer with this email already exists" is displayed
    And update the customer email as "Customer2@mailinator.com"
    And customer details are saved
    And Validate the message "Customer with this code already exists" is displayed
    And Click ok on unable to save customer profile popup
    And update the customer code as "Customer Code Two"
    And customer details are saved
    And verify the customer details on profile screen
      | Name                | Email                    | Phone      | Address         | City   | State  | ZipCode | Country        | CustomerCode      | Note                    |
      | Create Customer two | Customer2@mailinator.com | 7556088987 | 98 Philips Road | London | Middle | SM44AQN | United Kingdom | Customer Code Two | Second Customer created |
    And click on edit profile button
    And update the customer email as "Customer2update@mailinator.com"
    And update the customer code as "CustomerCodeTwoUpdate"
    And click on Cancel button on edit customer screen
    And click on  Discard changes on edit customer screen
    And delete the customer from customer profile screen
    And Validate the message "Customer deleted" is displayed
    #delete from list
    And the customer is selected to delete from the list
    And the existing customer is deleted
    And Validate the message "Customer deleted" is displayed
    #and search operation
    And add customer button is clicked
    And the customer details are updated as
      | Name         | Email              | Phone      | Address          | City   | State   | ZipCode | Country        | CustomerCode | Note                  |
      | David Miller | Dav@mailinator.com | 7556078987 | 31 lawrence Road | London | Central | SM31QN  | United Kingdom | CN787        | Customer Note Section |
    And customer details are saved
    And click on customer base icon to redirect to customer list screen
    And add customer button is clicked when customer is already present
    And the customer details are updated as
      | Name      | Email                | Phone      | Address          | City   | State   | ZipCode | Country        | CustomerCode | Note                  |
      | Peter Pan | Peter@mailinator.com | 7556078987 | 31 lawrence Road | London | Central | SM31QN  | United Kingdom | CN787AZ      | Customer Note Section |
    And customer details are saved
    And click on customer base icon to redirect to customer list screen
    And add customer button is clicked when customer is already present
    And the customer details are updated as
      | Name | Email               | Phone      | Address          | City   | State   | ZipCode | Country        | CustomerCode | Note                  |
      | Neha | Neha@mailinator.com | 7556078987 | 31 lawrence Road | London | Central | SM31QN  | United Kingdom | CN787QW      | Customer Note Section |
    And customer details are saved
    And click on customer base icon to redirect to customer list screen
    #And verify "Peter Pan" is displayed on top when "Peter" is searched

