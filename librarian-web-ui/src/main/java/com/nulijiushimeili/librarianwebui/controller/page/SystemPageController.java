package com.nulijiushimeili.librarianwebui.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/******************************************************
 * Program: project-management
 * Author: 努力就是魅力
 * QQ : 734131757
 * DateTime: 2020-04-07 11:28
 * Desc:  TODO
 ********************************************************/

@RequestMapping(value = "/system")
@Controller
public class SystemPageController {

    // system---------------------------------------------------------------------------------

    @GetMapping(value = "/403")
    public String page403() {
        return "/html/pages/system/403.html";
    }


    @GetMapping(value = "/404")
    public String page404() {
        return "/html/pages/system/404.html";
    }


    @GetMapping(value = "/500")
    public String page500() {
        return "/html/pages/system/500.html";
    }


    @GetMapping(value = "/alertSkin")
    public String alertSkin() {
        return "/html/pages/system/alertSkin.html";
    }


    @GetMapping(value = "/log")
    public String log() {
        return "/html/pages/system/log.html";
    }


    @GetMapping(value = "/setup")
    public String setup() {
        return "/html/pages/system/setup.html";
    }


    @GetMapping(value = "/shield")
    public String shield() {
        return "/html/pages/system/shield.html";
    }


}
