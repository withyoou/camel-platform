package com.withyou.platform.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @Author admin
 * @Date 2019-09-24 15:06
 **/
@Component
public class DemoRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:initial//start?period=10000")
                .routeId("TIME_ROUTE")
                .to("log:executed");

    }
}
