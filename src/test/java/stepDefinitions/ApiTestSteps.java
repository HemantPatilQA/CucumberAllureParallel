package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import java.io.ByteArrayInputStream;

public class ApiTestSteps {
    private Response response;

    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String url) {
        System.out.println("Executing Thread: " + Thread.currentThread().getId() + " | Request: " + url);
        response = RestAssured.get(url);
        Allure.addAttachment("Request URL", url);
        Allure.addAttachment("Response Body", new ByteArrayInputStream(response.asByteArray()));
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        System.out.println("Executing Thread: " + Thread.currentThread().getId() + " | Status Code: " + response.statusCode());
        Assert.assertEquals(response.statusCode(), statusCode);
        Allure.addAttachment("Response Code", String.valueOf(response.statusCode()));
    }
}
