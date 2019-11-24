package com.micro.services.supplier.svc.configuration;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    @Bean
    public TopicExchange senderTopicExchange() {
        return new TopicExchange("supplierExchange");
    }

}
