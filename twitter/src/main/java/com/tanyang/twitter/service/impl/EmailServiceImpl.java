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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private TemplateEngine templateEngine;

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
            Context context = new Context();
            context.setVariable("localHref",localHref);
            context.setVariable("sendTo",sendTo);
            String text = templateEngine.process("mail",context);
            //System.out.println(text);

            messageHelper=new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(emailConfig.getEmailFrom());
            messageHelper.setTo(sendTo);
            messageHelper.setSubject(emailConfig.getTitle());
            messageHelper.setText( text,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
}
