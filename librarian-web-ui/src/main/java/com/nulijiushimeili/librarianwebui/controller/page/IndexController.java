package com.nulijiushimeili.librarianwebui.controller.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/******************************
 * @Project: librarian-parent
 * @FileName: IndexController.java
 * @ClassName: IndexController
 * @time 2020/5/14 22:44
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/


@Controller
public class IndexController {

    @GetMapping(value = {"/","/index"})
    public String showMarkdown(){
        return "templates/index";
    }



    @GetMapping(value = {"/page/console"})
    public String showConsole(){
        return "templates/show/console";
    }
}
