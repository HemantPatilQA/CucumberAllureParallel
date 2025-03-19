Feature: Test reqres APIs with Cucumber

  Scenario: Verify reqres get user API Response
    Given I send a request to get the user details
    Then response status code should be 200
