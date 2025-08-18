Feature: Create Account Testing


  @AdminLogin @Test
  Scenario: Admin login Salesforce Homepage
    Given Launch Salesforce Homepage to login with Admin credentials
    When User input admin username and admin password
    Then Validate user login successfully

  @Test
  Scenario Outline: Create an new account
    Given User click on Account button from top menu bar
    When User click on New button
    Then Select Client Radio and Next button
    And Input AccountName "<AccountName>" to create a new account
    Examples:
      |AccountName  |
      |Jane  |


