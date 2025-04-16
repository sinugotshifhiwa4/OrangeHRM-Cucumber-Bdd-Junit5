Feature: Orange HRM Login Functionality

  As an authorized user of the Orange HRM system
  I want to authenticate using my credentials
  So that I can access my dashboard and perform assigned tasks

  Background:
    Given the user is on the Orange HRM login page

  @Sanity
  Scenario Outline: Successful authentication with valid credentials
    When the user enters valid username "<USERNAME>" and password "<PASSWORD>"
    And the user clicks the login button
    Then the user should login successfully
    And the user should be navigated to the dashboard page

    Examples:
      | USERNAME         | PASSWORD         |
      | <valid_username> | <valid_password> |

  @Sanity
  Scenario Outline: Failed authentication with invalid credentials
    When the user enters username "<USERNAME>" and password "<PASSWORD>"
    And the user clicks the login button
    Then the login should fail and an appropriate error message should be displayed

    Examples:
      | USERNAME           | PASSWORD           |
      | <invalid_username> | <invalid_password> |


  @Sanity
  Scenario: Forgot password functionality
    When the user clicks on the forgot your password? link
    Then the user should be redirected to the password reset page
    When the user enters a valid username
    And the user clicks the reset password button
    Then the user should receive an email with instructions to reset their password