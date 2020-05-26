package com.nulijiushimeili.librarianwebui.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


@Configuration
public class BeanConf {
    @Autowired
    private GsonBuilder gsonBuilder;

    @Bean
    public Gson gson() {
        return gsonBuilder.create();
    }

    @Autowired
    private RestTemplateBuilder builder;


    @Bean
    public Executor taskPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(4);
        //最大线程数
        executor.setMaxPoolSize(16);
        //缓冲队列
        executor.setQueueCapacity(10000);
        //允许线程的空闲时间
        executor.setKeepAliveSeconds(600);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
