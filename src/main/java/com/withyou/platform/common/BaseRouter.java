package com.withyou.platform.common;

import org.apache.camel.builder.RouteBuilder;

/**
 * @Author zp
 * @Date 2019-09-25 10:16
 * Custom Camel route
 **/
public abstract class BaseRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        restConfiguration().host("localhost").port(8080);
        setupRoute();
    }

    protected abstract void setupRoute();
}
