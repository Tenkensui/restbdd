package com.moatcrew.restbdd.jbehave;

import com.moatcrew.restbdd.datasourcing.CsvReader;
import com.moatcrew.restbdd.rest.EndpointDiscoveryService;
import com.moatcrew.restbdd.rest.RestService;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.*;

/**
 * Created by maruku
 * on 8/1/16.
 */
public class AbstractSteps extends Steps implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Map<String, Collection<AssertionError>> assertionErrors;
    private EndpointDiscoveryService endpointDiscoveryService;
    private RestService restService;
    private CsvReader csvReader;

    private String contextHttpResult;
    private String contextCsvFileName;
    private String contextEndpointName;
    private String contextHttpMethod;
    private String contextExpectedResponse;

    @BeforeStories
    public void initialize() {
        assertionErrors = new LinkedHashMap<>();
    }

    @BeforeScenario
    public void setup() {

    }

    ArrayList<ArrayList<String>> loadData() {
        Assert.assertNotNull("Csv file name is null", contextCsvFileName);
        Resource resource = new ClassPathResource(contextCsvFileName);

        try {
            return csvReader.getCsvData(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            // TODO: Log and handle
            e.printStackTrace();
            return null;
        }
    }

    public String getContextCsvFileName() {
        return contextCsvFileName;
    }

    public void setContextCsvFileName(String contextCsvFileName) {
        this.contextCsvFileName = contextCsvFileName;
    }

    public String getContextEndpointName() {
        return contextEndpointName;
    }

    public void setContextEndpointName(String contextEndpointName) {
        this.contextEndpointName = contextEndpointName;
    }

    public String getContextHttpMethod() {
        return contextHttpMethod;
    }

    public void setContextHttpMethod(String contextHttpMethod) {
        this.contextHttpMethod = contextHttpMethod;
    }

    public String getContextExpectedResponse() {
        return contextExpectedResponse;
    }

    public void setContextExpectedResponse(String contextExpectedResponse) {
        this.contextExpectedResponse = contextExpectedResponse;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setEndpointDiscoveryService(EndpointDiscoveryService endpointDiscoveryService) {
        this.endpointDiscoveryService = endpointDiscoveryService;
    }

    public void setRestService(RestService restService) {
        this.restService = restService;
    }

    public void setCsvReader(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    public CsvReader getCsvReader() {
        return csvReader;
    }

    public RestService getRestService() {
        return restService;
    }

    public EndpointDiscoveryService getEndpointDiscoveryService() {
        return endpointDiscoveryService;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public String getContextHttpResult() {
        return contextHttpResult;
    }

    public void setContextHttpResult(String contextHttpResult) {
        this.contextHttpResult = contextHttpResult;
    }
}
