package com.tanyang.twitter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig {

    @Value("${spring.mail.username}")
    private String emailFrom;
    private String Content;
    private String title;

    public EmailConfig() {
        this.title="用户验证邮件";
        this.Content="恭喜你账户已经验证成功";
    }

    public String getEmailFrom(){
        return emailFrom;
    }

    public String getContent() {
        return Content;
    }

    public String getTitle() {
        return title;
    }
}
