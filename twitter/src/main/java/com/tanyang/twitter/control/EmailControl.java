package com.tanyang.twitter.control;

import com.tanyang.twitter.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmailControl {

    @Autowired
    private EmailServiceImpl emailServiceImpl;

}
