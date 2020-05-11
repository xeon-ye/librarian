package com.nulijiushimeili.librarianwebui.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduledService {


    /**
     * 每天执行一次
     */
    @Scheduled(cron = "0 0 */1 * * *")
    public void socketMessage() {

    }
}
