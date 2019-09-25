package com.withyou.platform.config;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Author admin
 * @Date 2019-09-25 15:17
 **/
@Configuration
@ConditionalOnProperty(name = "activemq.endpoint", prefix = "", havingValue = "tcp://localhost:61617")
public class ActivemqServer {

    private BrokerService broker;
    private Logger log = LoggerFactory.getLogger(ActivemqServer.class);

    @PostConstruct
    public void initBroker() {
        log.info("Starting embedded Activemq broker.");
        broker = new BrokerService();
        try {
            broker.setDataDirectory(".activemq");
            broker.addConnector("tcp://localhost:61617");
            broker.start();
        } catch (Exception e) {
            throw new RuntimeException("Could not start embedded Activemq broker.", e);
        }
    }
}
