package com.nulijiushimeili.librarianwebui.controller.serviceregistry;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-10 20:02
 * @Desc: TODO
 ********************************************************/

@Api(tags = "服务注册接口")
@Slf4j
@RestController
@RequestMapping(value = "/datasourceServiceRegistry")
public class DataSourceServiceRegistryController {


    @PostMapping(value = "/registry")
    public void registry(String datasourceType){

    }
}
