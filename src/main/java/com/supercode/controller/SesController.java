package com.supercode.controller;

import com.supercode.email.ses.AmazonEmail;
import com.supercode.email.ses.SESFrom;
import com.supercode.email.ses.SESProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SesController {

    @RequestMapping("/")
    public String index() {

        //send Email using default NO_REPLY from email
        SESProcessor.getInstance().add(new AmazonEmail(
                "hi@attacomsian.com",
                "Hey Atta",
                "We have an offer for you :)"));

        //send Email using ATTA from email
        SESProcessor.getInstance().add(new AmazonEmail(
                "hi@attacomsian.com",
                SESFrom.ATTA,
                "Hey Atta",
                "We have an offer for you :)"));

        return "Emails Sent!";
    }
}