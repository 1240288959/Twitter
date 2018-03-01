package com.tanyang.twitter.service.impl;

import com.tanyang.twitter.dao.MessageDao;
import com.tanyang.twitter.pojo.Message;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public List<Message> getMessageByReceiver(User user) {
        List<Message> list=null;
        try{
            list=messageDao.getMessageByReciver(user.getId());
        }catch (Exception e){
            e.printStackTrace();
            list=null;
        }
        return list;

    }

    @Override
    public int countUnreadedMessageByReceiver(User user) {
        int count=0;
        try{
            count=messageDao.countMessagesByReceiverAndIsread(user.getId());
        }catch (Exception e){
            e.printStackTrace();
            count=0;
        }
        return count;
    }

    @Override
    public boolean doMessageReaded(List<Message> messageList) {
        try{
            for(Message message:messageList){
                message.setIsread(1);
                messageDao.save(message);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean save(Message message) {
        try{
            messageDao.save(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}

