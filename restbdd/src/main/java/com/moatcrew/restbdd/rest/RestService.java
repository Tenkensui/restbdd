package com.moatcrew.restbdd.rest;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by maruku on 7/29/16.
 */
public class RestService {

    private RestTemplate restTemplate;

    public RestService() {
        this.restTemplate = new RestTemplate();
    }

    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T>void call(String url, Class<T> type, Map<String, ?> urlVariables, HttpMethod httpMethod, RestCallback<T> callback) {
        callback.onResponse(this.execute(url, type, urlVariables, httpMethod));
    }

    private <T>T execute(String url, Class<T> type, Map<String, ?> urlVariables, HttpMethod httpMethod) {
        switch (httpMethod) {
            case GET:
                return restTemplate.getForObject(url, type, urlVariables);
            case POST:
                return restTemplate.postForObject(url, null, type, urlVariables);
            case PUT:
                restTemplate.put(url, null, urlVariables);
                break;
            case DELETE:
                restTemplate.delete(url, urlVariables);
                break;
        }
        return null;
    }

}
