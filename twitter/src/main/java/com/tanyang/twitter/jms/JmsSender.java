package com.tanyang.twitter.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Jms规范下的activemq（消息中间件） 消息发送者
 */
@Component
public class JmsSender {

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendByQueue(String message) {
        this.jmsMessagingTemplate.convertAndSend(queue, message);
    }

    public void sendByTopic(String message) {
        this.jmsMessagingTemplate.convertAndSend(topic,message);
    }

}
