package com.moatcrew.restbdd.rest;

import org.springframework.web.client.RestTemplate;

/**
 * Created by maruku on 7/29/16.
 */
public class RestService<T> {

    private Class<T> type;

    public RestService(Class<T> type) {
        this.type = type;
    }

    public T getObjectFromUrl(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, type);
    }

}
