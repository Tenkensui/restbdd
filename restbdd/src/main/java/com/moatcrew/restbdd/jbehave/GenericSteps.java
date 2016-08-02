package com.moatcrew.restbdd.jbehave;

import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.http.HttpMethod;

/**
 * Created by maruku
 * on 8/1/16.
 */
public class GenericSteps extends AbstractSteps {

    @Given("the input file $csvFileName")
    public void givenCsv(String csvFileName) {
        setContextCsvFileName(csvFileName + ".csv");
        loadData();
    }

    @When("the endpoint $endpointName is called using HTTP METHOD $httpMethod")
    public void whenEndpointCalled(String endpointName, String httpMethod) {
        setContextEndpointName(endpointName);
        setContextHttpMethod(httpMethod);
    }

    @Then("response should be $response")
    public void thenResponseShouldBe(String response) {
        setContextExpectedResponse(response);
        Assert.assertThat("Expected response not found.", getContextHttpResult(), Matchers.equalTo(response));
    }


}
