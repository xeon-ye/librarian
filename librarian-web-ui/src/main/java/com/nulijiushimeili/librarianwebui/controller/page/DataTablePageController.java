package com.nulijiushimeili.librarianwebui.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/******************************
 * @Project: project-management
 * @FileName: null.java
 * @ClassName: DataTablePageController
 * @time 2020/4/6 23:48
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@Controller
@RequestMapping(value = "/datatable")
public class DataTablePageController {


    // datatable---------------------------------------------------------------------------------
    @GetMapping(value = "/bsgrid")
    public String bsgrid() {
        return "/html/pages/datatable/bsgrid.html";
    }


    @GetMapping(value = "/datatables")
    public String datatables() {
        return "/html/pages/datatable/datatables.html";
    }
}
