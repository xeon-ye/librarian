package com.nulijiushimeili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/******************************
 * @Project: librarian
 * @FileName: EurekaServerApp.java
 * @ClassName: EurekaServerApp
 * @time 2020/5/10 1:40
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp.class, args);
    }


}
