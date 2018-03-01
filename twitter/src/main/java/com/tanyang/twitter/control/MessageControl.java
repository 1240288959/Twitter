package com.tanyang.twitter.control;

import com.tanyang.twitter.pojo.Message;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.impl.MessageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MessageControl {
    @Autowired
    private MessageServiceImpl messageServiceImpl;

    private Logger logger=LoggerFactory.getLogger(Message.class);

    @RequestMapping("/tomymessage")
    public String toMyMessage(Model model, HttpSession session){
        User currentUser= (User) session.getAttribute("user");
        List<Message> messageList=messageServiceImpl.getMessageByReceiver(currentUser);
        model.addAttribute("messageList",messageList);
        logger.info(""+messageList);
        messageServiceImpl.doMessageReaded(messageList);
        return "mymessage";
    };

    @RequestMapping("/getunread")
    @ResponseBody
    public int getUnread(HttpSession session){
        User currentUser= (User) session.getAttribute("user");
        System.out.println("userid "+currentUser.getId());
        int count=messageServiceImpl.countUnreadedMessageByReceiver(currentUser);
        return count;
    }
}
