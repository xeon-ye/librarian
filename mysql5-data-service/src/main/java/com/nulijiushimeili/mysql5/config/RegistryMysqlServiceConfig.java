package com.nulijiushimeili.mysql5.config;

import com.nulijiushimeili.librariancommon.utils.MyStringUtils;
import com.nulijiushimeili.librariancommon.utils.MyTelnetUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-16 11:56
 * @Desc: TODO
 ******************************************************/

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
        String domain = MyStringUtils.getDomainNameFromUrl(webServiceUrl);
        String ip = domain.replace("http://","").split(":")[0];
        int port = Integer.valueOf(domain.replace("http://","").split(":")[1]);
        boolean connRes = MyTelnetUtils.telnet(ip,port,5000);
        if(!connRes){
            log.error("无法连接到web服务，注册服务失败！");
        }else {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            // 请求的参数
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(webServiceUrl, request, String.class);
            log.info("请求成功，返回的状态码是：" + response.getStatusCode());

        }
    }
}
