package com.tanyang.twitter.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Date;
import java.util.List;

@Controller
public class MessageControl {
    @Autowired
    private MessageServiceImpl messageServiceImpl;

    private Logger logger=LoggerFactory.getLogger(Message.class);

    @RequestMapping("/tomymessage")
    public String toMyMessage(Model model, HttpSession session){
        User currentUser= (User) session.getAttribute("user");
        session.setAttribute("toMyMessageTime",new Date(System.currentTimeMillis()));
        /*List<Message> messageList=messageServiceImpl.getMessageByReceiver(currentUser);
        model.addAttribute("messageList",messageList);*/
        /*logger.info(""+messageList);*/

        return "mymessage";
    };

    @RequestMapping("/getMyMessage")
    @ResponseBody
    public String getMyMessage(int page, HttpSession session){
        ObjectMapper mapper=new ObjectMapper();
        User currentUser= (User) session.getAttribute("user");
        Date time= (Date) session.getAttribute("toMyMessageTime");
        /*logger.info(time.toString());*/
        List<Message> messageList=messageServiceImpl.getMessagePageByReceiver(currentUser,time,page);
        messageServiceImpl.doMessageReaded(messageList);
        /*logger.info(""+messageList);*/
        messageServiceImpl.doMessageReaded(messageList);
        String jsonStr="";
        try {
            jsonStr=mapper.writeValueAsString(messageList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    };

    @RequestMapping("/getunread")
    @ResponseBody
    public int getUnread(HttpSession session){
        User currentUser= (User) session.getAttribute("user");
        /*System.out.println("userid "+currentUser.getId());*/
        int count=messageServiceImpl.countUnreadedMessageByReceiver(currentUser);
        return count;
    }
}
