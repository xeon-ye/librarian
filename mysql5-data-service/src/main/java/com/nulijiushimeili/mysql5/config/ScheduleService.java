package com.nulijiushimeili.mysql5.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

/******************************************************
 * @Program: cdr-voice-search
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-15 13:59
 * @Desc:          定时任务
 ********************************************************/


@Slf4j
@Configuration
public class ScheduleService {


    @Scheduled(cron = "* */30 * * * *")
    public void syncVoiceTextFromOracle2Es(){

    }





}
