package com.moatcrew.restbdd.rest;

import com.moatcrew.restbdd.model.Endpoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by maruku
 * on 7/30/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test-application-context.xml")
public class EndpointDiscoveryServiceTest {

    @Autowired
    private EndpointDiscoveryService endpointDiscoveryService;

    @Test
    public void testGetContextEndpoints() throws Exception {
        Map<String, Endpoint> endpoints = endpointDiscoveryService.getContextEndpoints();
        Assert.assertNotNull(endpoints);
        Assert.assertThat("No endpoints found", endpoints.size(), greaterThan(0));
    }
}
