package com.tanyang.twitter.control;

import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.AttentionServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AttentionControl {

    @Autowired
    private AttentionServiceimpl attentionServiceimpl;

    @RequestMapping("/toattented")
    public String getAttentByUserId(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        List<User> list=attentionServiceimpl.getAttented(user.getId());
        model.addAttribute("list",list);
        return "attented";
    };

    @RequestMapping("/addattented")
    @ResponseBody
    public boolean addAttented(String id,HttpSession session){
        User user=(User)session.getAttribute("user");
        return attentionServiceimpl.addAttented(user.getName(),id);
    }

    @RequestMapping("/deleteattented")
    @ResponseBody
    public boolean deleteByAttented(String id){
        return attentionServiceimpl.deleteByAttented(id);
    }
}
