package com.nulijiushimeili.librarianwebui.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/******************************
 * @Project:
 * @ClassName: HelpPageController
 * @time 2020/4/6 23:37
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@Controller
@RequestMapping(value = "/help")
public class HelpPageController {


    // help---------------------------------------------------------------------------------
    @GetMapping(value = "/nav_icon")
    public String navIcon() {
        return "/html/pages/help/nav_icon.html";
    }


    @GetMapping(value = "/nav_operate")
    public String nav_operate() {
        return "/html/pages/help/nav_operate.html";
    }


    @GetMapping(value = "/nav_parameter")
    public String nav_parameter() {
        return "/html/pages/help/nav_parameter.html";
    }


    @GetMapping(value = "/ok_font")
    public String ok_font() {
        return "/html/pages/help/ok_font.html";
    }


    @GetMapping(value = "/okLayer")
    public String okLayer() {
        return "/html/pages/help/okLayer.html";
    }


    @GetMapping(value = "/okUtils")
    public String okUtils() {
        return "/html/pages/help/okUtils.html";
    }


    @GetMapping(value = "/plug_directory")
    public String plug_directory() {
        return "/html/pages/help/plug_directory.html";
    }
}
