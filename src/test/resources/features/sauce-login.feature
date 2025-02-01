Feature: Login to the sauce demo system
  This feature will allow users to login to the system
  and block users with invalid credentials login to the system

  @SmokeTest
  Scenario: Login with valid user credentials
    Given user has accessed the sauce demo login page
    When user provide valid credentials
    Then user should be in the landing page

  @SmokeTest
  Example: Login with valid user credentials
    Given user has accessed the sauce demo login page
    When user provide valid credentials
    Then user should be in the landing page


  @RegressionTest
  Scenario Template:
    Given user has accessed the sauce demo login page
    When user provide valid credentials username "<username>" and password "<password>"
    Then user should see error message "<expectedError>"
    Scenarios: :
      | username      | password | expectedError                      |
      |               |          | Epic sadface: Username is required |
      |               | test     | Epic sadface: Username is required |
      | standard_user |          | Epic sadface: Username is required |

