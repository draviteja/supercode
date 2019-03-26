package com.supercode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }

    @RequestMapping("/ping")
    public String healthCheck() {
        return "PONG";
    }

}
