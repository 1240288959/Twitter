package com.tanyang.twitter.service.impl;

import com.tanyang.twitter.config.EmailConfig;
import com.tanyang.twitter.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

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
        message.setText( "点击下列链接\nhttp:192.168.120.134:8080/vertify?email="+sendTo);
        mailSender.send(message);
    }
}
