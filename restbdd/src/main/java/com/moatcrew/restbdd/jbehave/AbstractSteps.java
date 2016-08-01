package com.moatcrew.restbdd.jbehave;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.steps.Steps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by maruku
 * on 8/1/16.
 */
public class AbstractSteps extends Steps implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Map<String, Collection<AssertionError>> assertionErrors;
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
        // TODO datasourcing for scenario?
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

}