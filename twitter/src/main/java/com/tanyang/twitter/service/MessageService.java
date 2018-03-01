package com.tanyang.twitter.service;

import com.tanyang.twitter.pojo.Message;
import com.tanyang.twitter.pojo.User;

import java.util.List;

public interface MessageService {

    List<Message> getMessageByReceiver(User user);

    int countUnreadedMessageByReceiver(User user);

    boolean doMessageReaded(List<Message> messageList);

    boolean save(Message message);
}
