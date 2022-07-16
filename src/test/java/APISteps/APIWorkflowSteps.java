package APISteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstans;

import static io.restassured.RestAssured.given;

public class APIWorkflowSteps {

    RequestSpecification request;
    Response response;

    @Given("a request is prepared to create an employee")
    public void aRequestIsPreparedToCreateAnEmployee() {
        request = given().header(APIConstans.HEADER_CONTENT_TYPE, APIConstans.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstans.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                body("");
    }

    @When("a POST call is made to create an employee")
    public void aPOSTCallIsMadeToCreateAnEmployee() {
    }

    @Then("the status code for the created employee is")
    public void theStatusCodeForTheCreatedEmployeeIs() {
    }
}
