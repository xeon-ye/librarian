package com.nulijiushimeili.librarianwebui.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/******************************
 * @Project: librarian
 * @FileName: ConnectionPageController.java
 * @ClassName: ConnectionPageController
 * @time 2020/5/12 0:07
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/
@Controller
@RequestMapping(value = "/page")
public class ConnectionPageController {

    @GetMapping(value = "/connection")
    public String connection(){
        return "/templates/page/connection";
    }

}
