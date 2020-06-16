package com.nulijiushimeili.mysql5.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-16 11:56
 * @Desc: TODO
 ********************************************************/

@Slf4j
@Service
public class RegistryMysqlServiceConfig {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${librarian.web.service-url}")
    private String webServiceUrl;


    /**
     * 向 webService 注册自己的服务信息
     */
    @PostConstruct
    public void registryMysqlService(){
//        restTemplate.postForEntity()

    }
}
