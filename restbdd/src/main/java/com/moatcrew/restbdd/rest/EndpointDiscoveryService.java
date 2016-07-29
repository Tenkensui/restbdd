package com.moatcrew.restbdd.rest;

import com.moatcrew.restbdd.model.Endpoint;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.Map;

/**
 * Created by maruku on 7/30/16.
 */
public class EndpointDiscoveryService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Collection<Endpoint> getContextEndpoints() {
        Map<String, Endpoint> endpointMap = this.applicationContext.getBeansOfType(Endpoint.class);
        return endpointMap.values();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
