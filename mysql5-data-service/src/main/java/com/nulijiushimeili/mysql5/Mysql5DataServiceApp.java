package com.nulijiushimeili.mysql5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-10 19:46
 * @Desc: TODO
 ********************************************************/

@EnableScheduling
@SpringBootApplication
public class Mysql5DataServiceApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Mysql5DataServiceApp.class,args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
