package com.supercode.controller;

import com.supercode.security.JwtAuthenticationFilter;
import com.supercode.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/api")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestService testService;

    @GetMapping("/async")
    public String testAsync() throws Exception {
        long start = System.currentTimeMillis();
        logger.info( "Asynchronous method start" );

        Future<String> stringFuture = testService.asyncMethod();
        //String result = stringFuture.get(60, TimeUnit.SECONDS);
        String result = stringFuture.get();
        logger.info("Asynchronous method return value:{}", result);

        logger.info( "Asynchronous method ends" );

        long end = System.currentTimeMillis();
        logger.info( "Total time: {} ms" , end - start);
        return stringFuture.get();
    }

    @GetMapping("/sync")
    public void testSync() {
        long start = System.currentTimeMillis();
        logger.info( "synchronization method start" );

        testService.syncMethod();

        logger.info( "synchronization method ends" );
        long end = System.currentTimeMillis();
        logger.info( "Total time: {} ms" , end - start);
    }

}
