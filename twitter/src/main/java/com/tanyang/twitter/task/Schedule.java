package com.tanyang.twitter.task;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Schedule {
    @Scheduled(fixedRate = 200000)
    public void task(){
        System.out.println("启动定时任务："+ new Date());
    }

}
