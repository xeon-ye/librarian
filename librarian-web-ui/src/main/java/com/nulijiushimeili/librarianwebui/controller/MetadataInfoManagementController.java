package com.nulijiushimeili.librarianwebui.controller;

import com.nulijiushimeili.librarian.beans.result.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/******************************
 * @Project: librarian
 * @FileName: MetadataInfoManagementController.java
 * @ClassName: MetadataInfoManagementController
 * @time 2020/5/12 0:06
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@RestController
@RequestMapping(value = "/metadataInfo")
public class MetadataInfoManagementController {

    @GetMapping(value = "/test")
    public RequestEntity test(){
        return RequestEntity.success();
    }
}
