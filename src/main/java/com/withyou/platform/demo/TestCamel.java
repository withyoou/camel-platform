package com.withyou.platform.demo;

import org.springframework.stereotype.Component;

/**
 * @Author admin
 * @Date 2019-09-25 16:07
 **/
@Component
public class TestCamel {

    private Long a;

    public TestCamel() {}

    public TestCamel(Long a) {
        this.a = a;
    }

    public TestCamel test() {
        System.out.println("----------------");
        return new TestCamel(1000L);
    }

    public Long getA() {
        return a;
    }

    public void setA(Long a) {
        this.a = a;
    }
}
