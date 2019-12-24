package com.micro.services.supplier.svc.service;

import com.micro.services.event.bus.publisher.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.services.event.bus.event.Event;

import java.io.IOException;

@Service
public class ProductEventService {
    private final Logger logger = LoggerFactory.getLogger(ProductEventService.class);

    @Autowired
    private EventPublisher eventPublisher;

    public void publish(Event event, String failureMessage) {
        try {
            eventPublisher.publish(event);
        } catch (IOException ex) {
            logger.error(failureMessage);
        }
    }

}
