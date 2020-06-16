package com.nulijiushimeili.mysql5.controller;

import com.nulijiushimeili.librariancommon.beans.entity.ResultEntity;
import com.nulijiushimeili.mysql5.service.IMysqlService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/******************************
 * @Project: librarian-parent
 * @FileName: Mysql5Controller.java
 * @ClassName: Mysql5Controller
 * @time 2020/5/14 22:26
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@Api(tags = "MySQL5数据治理服务")
@Slf4j
@RestController
@RequestMapping(value = "/mysql5")
public class Mysql5Controller {

    @Autowired
    private IMysqlService mysqlService;



    public ResultEntity listDatabase(@RequestParam String connName){

        return null;
    }


}
