package com.supercode.config;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(Message message) {
        System.out.println("Received <" + message.toString() + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}