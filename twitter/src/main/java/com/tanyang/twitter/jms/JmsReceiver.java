package com.tanyang.twitter.jms;

import com.tanyang.twitter.config.JmsConfiguration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class JmsReceiver {

    @JmsListener(destination = JmsConfiguration.QUEUE_NAME)
    public void receiveByQueue(String message) {
        System.out.println("接受队列消息:"+message);
    }

    @JmsListener(destination = JmsConfiguration.TOPIC_NAME)
    public void receiveByTopic(String message) {
        System.out.println("接受主题消息:"+message);
    }
}
