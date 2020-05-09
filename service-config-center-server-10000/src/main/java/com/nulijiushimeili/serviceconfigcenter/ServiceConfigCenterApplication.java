package com.nulijiushimeili.serviceconfigcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServiceConfigCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConfigCenterApplication.class, args);
    }

}
