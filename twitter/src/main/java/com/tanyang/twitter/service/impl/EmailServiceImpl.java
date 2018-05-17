package com.tanyang.twitter.service.impl;

import com.tanyang.twitter.config.EmailConfig;
import com.tanyang.twitter.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${localHref}")
    private String localHref;

    @Override
    public void sendSimpleMail(String sendTo) {
        //SimpleMailMessage message=new SimpleMailMessage();
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=null;
        try {
            messageHelper=new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(emailConfig.getEmailFrom());
            messageHelper.setTo(sendTo);
            messageHelper.setSubject(emailConfig.getTitle());
            messageHelper.setText( "<html><head></head><body><a href='"+localHref+"/vertify?email="+sendTo+"'>点击下列链接进行验证</a></body></html>",true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
}
