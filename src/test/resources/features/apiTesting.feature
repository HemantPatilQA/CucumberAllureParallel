Feature: API Testing with Cucumber

  Scenario: Verify API Response
    Given I send a GET request to "https://jsonplaceholder.typicode.com/posts/1"
    Then the response status code should be 200
