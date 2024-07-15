Feature: Verify the File Upload and Download operations

Scenario: Verify user is able to Download the File
Given user lands on the HomePage
When clicks on Toggle Button
And clicks on View All
And selects AppLaucherOption as "service console"
And selects Service Console Option in the Files Page
And identify Recent Date
And open Recent File with "Title"
And download the File
Then Verify the File is Downloaded


Scenario: Verify user is able to Upload the File
Given user lands on the HomePage
When clicks on Toggle Button
And clicks on View All
And selects AppLaucherOption as "service console"
And selects Service Console Option in the Files Page
And check if the file with same name is already uploaded
And upload the File
And verify the File is Downloaded





