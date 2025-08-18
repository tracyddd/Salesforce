Feature: Login Testing

  @Login
 Scenario Outline: Search product in Home/Landing Page to validate if it matches in ProductName Column from feature file
   Given User open Salesforce Homepage
   When User input Username "<Username>" and Password "<Password>" to login Salesforce Homepage
   Then Validate user login successfully
   Examples:
     |Username  | Password |
     |tracychrishu@mindful-unicorn-apdgty.com| Luckygirl.1     |


  @regression
  Scenario Outline: Create an new test
    Given User is on GreenKart landing page
    When User searched with shortname <Name> and extracted actual name of product
    Then User searched for shortname "<Name>" in offers page
    And Validate product name in offers page matches with Landing page
    Examples:
      |Name  |
      |Tom|
      |Ban|
      |Beet|

 # @regression @smoke
 #  Scenario: Open the GreenKart webpage, and I can find broccoli and apple carrot listed on the page.

 # @sanity
 #  Scenario: After opening GreenKart webpage, I can see "top deals" with hyperlink is displayed.

 # @regression @sanity
 #  Scenario: Clicking on the "+" button increases the quantity.

 #  Scenario: Clicking on "Add to Cart" adds the vegetable to the shopping bag.

 #  Scenario: I can click the "x" button to remove the vegetable from the shopping bag.