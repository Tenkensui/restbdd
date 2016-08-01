package com.moatcrew.restbdd.jbehave;

import com.moatcrew.restbdd.datasourcing.CsvReader;
import com.moatcrew.restbdd.rest.EndpointDiscoveryService;
import com.moatcrew.restbdd.rest.RestService;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

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
    }


}
