package com.nulijiushimeili.es.dataservice;

import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-08 10:41
 * @Desc: TODO
 ********************************************************/

@SpringBootApplication
public class ElasticSearchDataServiceApp implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchDataServiceApp.class, args);
    }


    @Override
    public void run(String... args) throws Exception {


    }
}
