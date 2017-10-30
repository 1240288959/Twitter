package com.tanyang.twitter.service;

import com.tanyang.twitter.config.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceimpl implements EmailService {

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMail(String sendTo) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(sendTo);
        message.setSubject(emailConfig.getTitle());
        message.setText(emailConfig.getContent());
        mailSender.send(message);
    }
}
