package com.withyou.platform.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author admin
 * @Date 2019-09-29 16:01
 **/
@RestController
public class DemoRoute {

    private Logger log = LoggerFactory.getLogger(DemoRoute.class);

    @GetMapping("/test/a")
    public TestCamel demo() {
        log.info("A rest call from remote.");
        return new TestCamel(1000L);
    }
}
