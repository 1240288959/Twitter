package com.tanyang.twitter.service;

import com.tanyang.twitter.pojo.Message;
import com.tanyang.twitter.pojo.User;

import java.util.Date;
import java.util.List;

public interface MessageService {

    List<Message> getMessageByReceiver(User user);

    List<Message> getMessagePageByReceiver(User user,Date time, int page);

    int countUnreadedMessageByReceiver(User user);

    boolean doMessageReaded(List<Message> messageList);

    boolean save(Message message);
}
