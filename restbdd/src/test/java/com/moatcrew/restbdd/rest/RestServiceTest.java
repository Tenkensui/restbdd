package com.moatcrew.restbdd.rest;

import com.moatcrew.restbdd.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by maruku on 7/29/16.
 */
public class RestServiceTest {

    private RestService restService;
    private RestTemplate restTemplate;
    private String url;
    private User user;

    @Before
    public void setUp() throws Exception {
        restTemplate = Mockito.mock(RestTemplate.class);
        user = new User();
        when(restTemplate.getForObject(anyString(), any(Class.class), any(Map.class))).thenReturn(user);
        when(restTemplate.postForObject(anyString(), Matchers.anyObject(), any(Class.class), any(Map.class))).thenReturn(user);
        doNothing().when(restTemplate).put(anyString(), Matchers.anyObject(), any(Map.class));
        doNothing().when(restTemplate).delete(anyString(), any(Map.class));
        restService = new RestService(restTemplate);
        url = "http://url.com";
    }

    @Test
    public void testGet() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        restService.call(url, User.class, paramMap, HttpMethod.GET, new RestCallback<User>() {
            @Override
            public void onResponse(User response) {
                Assert.assertEquals(user, response);
            }
        });
    }

    @Test
    public void testPut() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        restService.call(url, Void.class, paramMap, HttpMethod.PUT, new RestCallback<Void>() {
            @Override
            public void onResponse(Void response) {
                Assert.assertNull(response);
            }
        });
    }

    @Test
    public void testDelete() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        restService.call(url, Void.class, paramMap, HttpMethod.DELETE, new RestCallback<Void>() {
            @Override
            public void onResponse(Void response) {
                Assert.assertNull(response);
            }
        });
    }

    @Test
    public void testPost() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        restService.call(url, User.class, paramMap, HttpMethod.POST, new RestCallback<User>() {
            @Override
            public void onResponse(User response) {
                Assert.assertEquals(user, response);
            }
        });
    }
}
