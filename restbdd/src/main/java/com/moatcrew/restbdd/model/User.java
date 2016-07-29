package com.moatcrew.restbdd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by maruku on 7/29/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String lastConnection;
    private String name;
    private Long x;
    private Long y;

    public String getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(String lastConnection) {
        this.lastConnection = lastConnection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
