package com.micro.services.supplier.svc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/health")
public class HealthCheckController {

    @GetMapping(value = "ping")
    public String ping() {
        return "pong";
    }

}
