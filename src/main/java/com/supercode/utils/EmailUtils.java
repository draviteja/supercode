package com.supercode.utils;

import com.supercode.exception.ApiException;
//import com.supercode.properties.AppProperties;
//import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

@Component
public class EmailUtils {

    Properties mailServerProperties;
    Session session;
    MimeMessage generateMailMessage;

    /*@Autowired
    AppProperties appProperties;*/

    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    /**
     * Configure default values at load
     */
    public EmailUtils(){
        mailServerProperties = System.getProperties();
        //TODO move the properties properties file
        mailServerProperties.put("mail.smtp.port", "465");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.socketFactory.port", "465");
        mailServerProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailServerProperties.put("mail.smtp.socketFactory.fallback", "false");
        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        logger.info("Mail Server Properties have been setup successfully..");
    }

    /**
     * Send Email
     * @param reciepent
     * @param subject
     * @param body
     * @return
     */
    /*public void sendEmail(String reciepent, String subject, String body) throws ApiException{
        logger.info("Sending email with subject : " + subject);
        boolean emailSent = false;
        try{
            session = Session.getInstance(mailServerProperties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(appProperties.getMailFrom(), appProperties.getMailPwd());
                }
            });
            generateMailMessage = new MimeMessage(session);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(reciepent));

            generateMailMessage.setSubject(subject);
            generateMailMessage.setContent(body, "text/html");
            Transport transport = session.getTransport("smtp");

            transport.connect("smtp.gmail.com", appProperties.getMailFrom(), appProperties.getMailPwd());
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            logger.info("Email sent.");
            emailSent = true;
        } catch (MessagingException ex) {
            logger.error("Could not send otp. Error : " + ex.getMessage());
            throw new ApiException("Failed to Send Email", "Exception occurred while sending email", ex);
        }
    }*/

    /**
     * Send email notification
     * @param recipient
     * @param subject
     * @param message
     * @return
     */
    /*public void sendMailNotification(String recipient, String subject, String message) throws ApiException{
        logger.info("Sending mail notification to : " + recipient);
        String body = getMailTemplate("EmailTemplate.html");
        if(body.isEmpty()){
            throw new ApiException("Could not send email.", "Could not fetch email template.");
        }
        body = body.replace("{{message}}", message);
        sendEmail(recipient, subject, body);
    }*/

    /**
     * Send otp to email id
     * @param recipient
     * @param otp
     * @return
     */
    /*public void sendOTP(String recipient, String otp) throws ApiException{
        logger.info("Sending OTP to : " + recipient);
        String body = getMailTemplate("EmailOtpTemplate.html");
        if(body.isEmpty()){
            logger.debug("body is empty. returning false.");
            throw new ApiException("Could not send email.", "Could not fetch email template.");
        }
        body = body.replace("{{otp}}", otp);

        sendEmail(recipient, "Temporary Password - Environment Monitoring", body);
    }*/

    /**
     * Get email template from resources
     * @param templateId
     * @return
     */
    /*public String getMailTemplate(String templateId){
        String content = null;
        try {
            InputStream stream = new ClassPathResource("templates/" + templateId).getInputStream();
            content = IOUtils.toString(stream, StandardCharsets.UTF_8);
            logger.debug("content for template found: " + content);
        } catch (IOException ex) {
            logger.error("Could not read template. Error : " + ex.getMessage());
        }
        return content;
    }*/
}
