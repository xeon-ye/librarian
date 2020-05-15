package com.nulijiushimeili.librarianwebui.controller;

import com.nulijiushimeili.librarian.beans.result.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/******************************
 * @Project: librarian-parent
 * @FileName: ConnectionInfoManagementController.java
 * @ClassName: ConnectionInfoManagementController
 * @time 2020/5/16 0:01
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/
@RestController
@RequestMapping(value = "/conn")
public class ConnectionInfoManagementController {

    @PostMapping(value = "/addMysqlConnInfo")
    public RequestEntity addMysqlConnInfo(){


        return RequestEntity.success();
    }



}
