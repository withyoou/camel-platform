package com.withyou.platform.demo;

import com.withyou.platform.common.BaseRouter;
import org.springframework.stereotype.Component;

/**
 * @Author admin
 * @Date 2019-09-24 15:06
 **/
@Component
public class DemoRoute extends BaseRouter {


    @Override
    protected void setupRoute() {

//        from("timer:initial//start?period=10000")
//                .routeId("TIME_ROUTE")
//                .to("log:executed");

        //rest api demo
        rest("/test")
                .get("/a")

                .route().to("bean:testCamel?method=test").endRest();

    }
}
