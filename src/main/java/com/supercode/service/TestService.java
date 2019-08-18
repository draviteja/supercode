package com.supercode.service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public  class  TestService  {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async("asyncThreadPoolTaskExecutor")
    public Future<String> asyncMethod () {
        sleep();
        logger.info( "Async method internal thread name: {}" , Thread.currentThread().getName());
        return new AsyncResult<>("hello async");
    }

    public void syncMethod ()  {
        sleep();
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep( 15 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
