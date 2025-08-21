Feature: Create Account Testing
  @AdminLogin @Test @setup
  Scenario: Admin login Salesforce Homepage
    Given User open Salesforce Homepage
    When User input admin username and admin password
    Then Validate user login successfully

  @Test
  Scenario Outline: Create an new account
    Given Redirect to Account Page
    When User click on New button from Account Page
    Then Select Client Radio and Next button
    And Input AccountName "<AccountName>" to create a new account
    Examples:
      |AccountName  |
      |Jane  |
      |Mike  |

  @teardown @Test
  Scenario: Print driver quit
    Given print out driver quite