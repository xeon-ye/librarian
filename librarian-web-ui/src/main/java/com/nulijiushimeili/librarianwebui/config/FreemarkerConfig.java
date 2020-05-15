//package com.nulijiushimeili.librarianwebui.config;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import javax.annotation.PostConstruct;
//
///**
// * @Program: spring-boot2-mybatis
// * @Author: 努力就是魅力
// * @Since: 2019-02-22 22:40
// * Description:
// *
// *     freemarker  配置 全局变量
// **/
//
//@Slf4j
//@Configuration
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class FreemarkerConfig {
//
//
//    @Autowired
//    private freemarker.template.Configuration configuration;
//
//
//    private InternalResourceViewResolver resourceViewResolver;
////    @Value("${ctx}")
////    private String ctx;
//
//
//    // Spring 初始化的时候加载配置
//    @PostConstruct
//    public void setConfigure() throws Exception {
//
//        // 加载html的资源路径
//        configuration.setSharedVariable("ctx", "我是全局变量");
//
//    }
//
//}
