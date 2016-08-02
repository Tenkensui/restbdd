package com.moatcrew.restbdd.jbehave;

import com.moatcrew.restbdd.datasourcing.CsvReader;
import com.moatcrew.restbdd.model.Endpoint;
import com.moatcrew.restbdd.model.User;
import com.moatcrew.restbdd.rest.EndpointDiscoveryService;
import com.moatcrew.restbdd.rest.RestCallback;
import com.moatcrew.restbdd.rest.RestService;
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maruku
 * on 8/1/16.
 */
public class GenericSteps extends AbstractSteps {

    @Given("the input file $csvFileName")
    public void givenCsv(String csvFileName) {
        setContextCsvFileName(csvFileName + ".csv");
    }

    @When("the endpoint $endpointName is called using HTTP METHOD $httpMethod")
    public void whenEndpointCalled(String endpointName, String httpMethod) throws IOException {
        setContextEndpointName(endpointName);
        setContextHttpMethod(httpMethod);
        String[] headers = getCsvReader().getCsvHeader(getContextCsvFileName());
        ArrayList<ArrayList<String>> data = getCsvReader().getCsvData(getContextCsvFileName());
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> paramMap = new HashMap<>();
            for (int j = 0; j < headers.length; j++) {
                paramMap.put(headers[j], data.get(i).get(j));
            }
            // Run
            Map<String, Endpoint> endpointMap = getEndpointDiscoveryService().getContextEndpoints();
            Endpoint endpoint = endpointMap.get(getContextEndpointName());
            getRestService().call(endpoint.getUrl(), User.class, paramMap, HttpMethod.resolve(getContextHttpMethod()), new RestCallback<User>() {
                @Override
                public void onResponse(User response) {
                    setContextHttpResult("200");
                }
            });
        }

    }

    @Then("response should be $response")
    public void thenResponseShouldBe(String response) {
        setContextExpectedResponse(response);
        Assert.assertThat("Expected response not found.", getContextHttpResult(), Matchers.equalTo(response));
    }


}
