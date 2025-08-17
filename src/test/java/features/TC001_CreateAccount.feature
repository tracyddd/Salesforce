Feature: Create Account Testing

  @Test
  Scenario Outline: Create an new account
    Given User click on Account button from top menu bar
    When User click on New button
    Then Select Client Radio and Next button
    And Input AccountName "<AccountName>" to create a new account
    Examples:
      |AccountName  |
      |Jane  |