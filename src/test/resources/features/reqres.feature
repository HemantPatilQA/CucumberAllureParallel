Feature: Test reqres APIs with Cucumber

  Scenario: Verify reqres get user API Response
    Given I send a request to get the user details
    Then response status code should be 200

  Scenario Outline: Verify reqres get user API Response - data driven
    Given I send a request to get the user details for user with id "<userid>"
    Then response status code should be <responsecode>
    Examples:
      |  userid  |  responsecode  |
      |  2       |  200           |
      |  3       |  200           |
      |  a       |  404           |
