Feature: SauceDemo Layout Validation

  Scenario: UC-1 Login Page Layout
    Given I open the SauceDemo login page
    Then I see the "Username" field and "Password" field
    And I see the "Login" button and "Swag Labs" header text

  Scenario: UC-2 Inventory Page Layout
    Given I am logged in as "standard_user" with password "secret_sauce"
    Then I am redirected to the "inventory" page
    And I see the "Swag Labs" header and "Cart Icon"
    And I see social links for "Twitter", "Facebook", and "Linkedin"
    And the sorting dropdown contains Name and Price options
    When I click the menu icon
    Then the menu contains "All Items", "About", "Logout", and "Reset App State"

  Scenario: UC-3 Inventory Item Page Layout
    Given I am logged in as "standard_user" with password "secret_sauce"
    Then I am redirected to the "inventory" page
    When I click on any inventory item
    Then I see the product image, name, description, and price
    And I see the "Add to cart" button and "Back to products" link
    And the item page keeps inventory header, cart, menu button, and social links