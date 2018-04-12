package com.tanyang.twitter.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.impl.AttentionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AttentionControl {

    private Logger logger= LoggerFactory.getLogger(AttentionControl.class);

    @Autowired
    private AttentionServiceImpl attentionServiceimpl;

    @RequestMapping("/toattented")
    public String getAttentByUserId(Model model,HttpSession session){
      /*  User user=(User)session.getAttribute("user");
        List<User> list=attentionServiceimpl.getAttented(user.getId());
        model.addAttribute("list",list);*/
        return "attented";
    };

    @RequestMapping("/getAttented")
    @ResponseBody
    public String getAttentPageByUserId(int page,HttpSession session){
        ObjectMapper mapper=new ObjectMapper();
        User user=(User)session.getAttribute("user");
        List<User> list=attentionServiceimpl.getAttentedPage(user.getId(),page);
        String jsonStr="";
        try {
            jsonStr=mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    @RequestMapping("/addattented")
    @ResponseBody
    public boolean addAttented(String id,HttpSession session){
        User user=(User)session.getAttribute("user");
        return attentionServiceimpl.addAttented(user.getId(),id);
    }

    @RequestMapping("/deleteattented")
    @ResponseBody
    public boolean deleteByAttented(String id){
        return attentionServiceimpl.deleteByAttented(id);
    }


}
