package com.moatcrew.restbdd.model;

/**
 * Created by maruku on 7/30/16.
 */
public class Endpoint {

    private String url;

    public Endpoint(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
