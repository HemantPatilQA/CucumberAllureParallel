package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.ConfigManager;
import java.io.ByteArrayInputStream;

import static io.restassured.RestAssured.given;

public class ResReqSteps {
    private Response response;
    static {
//        RestAssured.baseURI = "https://reqres.in";
        RestAssured.baseURI = ConfigManager.getBaseUrl();
    }


    @Given("I send a request to get the user details")
    public void iSendARequestToGetTheUserDetails() {
//        String url = "/api/users/2";
        String url = ConfigManager.getEndpoint("GET_USERS");
        System.out.println("Environment: " + ConfigManager.getEnvironment() + "Executing Thread: " + Thread.currentThread().getId() + " | Request: " + url);
        response = given().header("Content-Type", "application/json").get(url).then().extract().response();
        Allure.addAttachment("Request URL", url);
        Allure.addAttachment("Response Body", new ByteArrayInputStream(response.asByteArray()));
    }

    @Then("response status code should be {int}")
    public void responseStatusCodeShouldBe(int responseStatus) {
        System.out.println("Executing Thread: " + Thread.currentThread().getId() + " | Status Code: " + response.statusCode());
        Assert.assertEquals(response.getStatusCode(), responseStatus);
        Allure.addAttachment("Response Code", String.valueOf(response.statusCode()));
    }
}
