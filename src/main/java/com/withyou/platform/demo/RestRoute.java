package com.withyou.platform.demo;

import com.withyou.platform.common.BaseRouter;
import org.apache.camel.model.rest.RestBindingMode;

/**
 * @Author admin
 * @Date 2019-09-25 17:22
 **/
public class RestRoute extends BaseRouter {
    @Override
    protected void setupRoute() {
        restConfiguration().component("servlet")
                .dataFormatProperty("prettyPrint", "true")
                .contextPath("")
                //host/port don't affect services at all, looking to fill in
                //issues with documentation generation.
                .port(8080)
                .host("localhost")
                .bindingMode(RestBindingMode.json)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "LabNetwork Pricing API")
                .apiProperty("api.version", "1.0");
    }
}
