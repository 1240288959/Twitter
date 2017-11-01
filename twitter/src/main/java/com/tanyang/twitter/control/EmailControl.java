package com.tanyang.twitter.control;

import com.tanyang.twitter.service.EmailService;
import com.tanyang.twitter.service.EmailServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmailControl {

    @Autowired
    private EmailServiceimpl emailServiceimpl;

}
