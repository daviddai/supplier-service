package com.micro.services.supplier.svc.service;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    public void publish(String message) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), "supplier.createProduct", message);
    }

}
