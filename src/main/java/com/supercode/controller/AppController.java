package com.supercode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AppController {

    private RedisTemplate redisTemplate;
    private RestTemplate restTemplate;

    @Autowired
    public AppController(RedisTemplate redisTemplate, RestTemplate restTemplate) {
        this.redisTemplate = redisTemplate;
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }

    //@CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/ping")
    public String healthCheck() {
        System.out.println("===================api entry point==================");
        if (redisTemplate.hasKey("test")) {
            System.out.println(redisTemplate.opsForList().range("test", 0, -1));
        }
        else{
            List<String> test = new ArrayList<>();
            test.add("1");
            test.add("2");
            test.add("3");
            test.add("4");
            redisTemplate.opsForList().rightPushAll("test", test);
        }
        String result = restTemplate.getForObject("https://labs.supercode.info/robots.txt", String.class);
        //System.out.println("result========="+result);

        return "PONG";
    }

}
