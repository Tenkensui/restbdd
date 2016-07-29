package com.moatcrew.restbdd.rest;

/**
 * Created by maruku on 7/29/16.
 */
public interface RestCallback<T> {

    void onResponse(T response);
}
