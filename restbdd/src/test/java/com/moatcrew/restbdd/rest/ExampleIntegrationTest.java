package com.moatcrew.restbdd.rest;

import com.moatcrew.restbdd.model.Endpoint;
import com.moatcrew.restbdd.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maruku
 * on 7/31/16.
 *
 * The goal of this test is just to demo the way the whole thing works.
 *
 * This is not a test as such!!!
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test-application-context.xml")
public class ExampleIntegrationTest {

    @Autowired
    private EndpointDiscoveryService endpointDiscoveryService;

    @Autowired
    private RestService restService;

    @Test
    public void testPost() throws Exception {
        Map<String, Endpoint> endpointMap = endpointDiscoveryService.getContextEndpoints();
        Map<String, Object> urlVariables = new HashMap<>();
        urlVariables.put("name", "Maruku");
        String url = endpointMap.get("userEndpoint").getUrl();
        restService.call(url, User.class, urlVariables, HttpMethod.GET, new RestCallback<User>() {
            @Override
            public void onResponse(User response) {
                Assert.assertNotNull(response);
            }
        });
    }
}
