package com.demo.airline.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    public Map getStatus() {
        Map map = new HashMap<String, String>();
        map.put("app-name", "Spring-Boot-Airline");
        map.put("app-version", appVersion);
        return map;
    }
}