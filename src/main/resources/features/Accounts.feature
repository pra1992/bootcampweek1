Feature: Create And Edit Account In The Salesforce


Scenario: S11-6 Create Accounts
Given user lands on the HomePage
When clicks on Toggle Button
And clicks on View All
And selects AppLaucherOption as "Sales"
And clicks on Accounts
And clicks on New Account button in SellerHomePage
Then verify CreateNewAccount popup is opened
And enter basic Account Details in the Popup
And click on Save Button
Then verify success message toaster message is displayed for Account Creation
When Navigates to the Dashboard Page
And select Accounts
And search for New Account In Table
Then validate Account is Added in the Table

Scenario: S11-7 Edit Accounts
Given user lands on the HomePage
When clicks on Toggle Button
And clicks on View All
And selects AppLaucherOption as "Sales"
When Navigates to the Dashboard Page
And select Accounts
And search for New Account In Table
And click on "Edit" Action in the Table
And edit Account Details Phone Number
And edit Account Type
And select Industry
And enter Billing Details
And enter Shipping Details
And select SLA
And select Active Status
And select Upsell
And click on Save
Then Verify Edit Success Message is displayed
And verify PhoneNo in the Grid











