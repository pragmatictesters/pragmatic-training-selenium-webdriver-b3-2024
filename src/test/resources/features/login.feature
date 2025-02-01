Feature: Login to the application

  @SmokeTest
  Example: Valid Login
    Given the user is on the login page
    When the user enters valid credentials
    Then the user should be redirected to the homepage

  @SmokeTest
  Example: Valid Login
    Given the user is on the login page
    When the user enters valid credentials
    Then the user should be redirected to the homepage

  Scenario: Valid Login with credentials passed as parameters
    Given the user is on the login page
    When the user enters valid credentials username "standard_user" and password "secret_sauce"
    Then the user should be redirected to the homepage

  @RegressionTest
  Rule: Users with invalid credentials should not be able to login
  Appropriate error messages should be displayed


  Scenario Outline: Invalid Login
    Given the user is on the login page
    When the user enters invalid credentials "<username>" and "<password>"
    Then the user should see an error message "<expected_error>"

    Examples:
      | username      | password       | expected_error                                                             |
      | invalid_user  | secret_sauce   | Epic sadface: Username and password do not match any user in this service  |
      | standard_user | wrong_password | Epic sadface: Username and password do not match any user in this services |
      |               | secret_sauce   | Epic sadface: Username is required                                         |
      | standard_user |                | Epic sadface: Password is required                                         |
