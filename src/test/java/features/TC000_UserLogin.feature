Feature: Login Testing

  @Login @Setup @Test
 Scenario Outline: Search product in Home/Landing Page to validate if it matches in ProductName Column from feature file
   Given User open Salesforce Homepage
   When User input Username "<Username>" and Password "<Password>" to login Salesforce Homepage
   Then Validate user login successfully
   Examples:
     |Username  | Password |
     |tracychrishu@mindful-unicorn-apdgty.com| asdfgh@1     |

  @mockup
  Scenario Outline: Admin login Salesforce Homepage
    Given User open Salesforce Homepage
    When User searched for shortname "<Name>" in offers page
    Then Validate product name in offers page matches with Landing page and quit
    Examples:
      |Name  |
      |Tom|
      |Ban|
      |Beet|

  @teardown @Test
  Scenario: Print driver quit
    Given print out driver quite

 # @regression @smoke
 #  Scenario: Open the GreenKart webpage, and I can find broccoli and apple carrot listed on the page.

 # @sanity
 #  Scenario: After opening GreenKart webpage, I can see "top deals" with hyperlink is displayed.

 # @regression @sanity
 #  Scenario: Clicking on the "+" button increases the quantity.

 #  Scenario: Clicking on "Add to Cart" adds the vegetable to the shopping bag.

 #  Scenario: I can click the "x" button to remove the vegetable from the shopping bag.