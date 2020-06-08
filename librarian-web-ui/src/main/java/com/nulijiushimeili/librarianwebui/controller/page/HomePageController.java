package com.nulijiushimeili.librarianwebui.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/******************************
 * @ClassName: PageController
 * @time 2020/3/31 23:42
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@Controller
public class HomePageController {


    @GetMapping(value = {"/", "/index"})
    public String toIndex() {
        return "/html/index.html";
    }


    @GetMapping(value = "/toLogin")
    public String toLogin() {
        return "/html/login.html";
    }

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "/html/welcome.html";
    }


    @GetMapping(value = "/pages/console")
    public String toConsole() {
        return "/html/pages/console.html";
    }


    @GetMapping(value = "/pages/console1")
    public String toConsole1() {
        return "/html/pages/console1.html";
    }


    @GetMapping(value = "/pages/forget")
    public String toForget() {
        return "/html/pages/forget.html";
    }


    @GetMapping(value = "/pages/register")
    public String toRegister() {
        return "/html/pages/register.html";
    }


    @GetMapping(value = "/pages/weather")
    public String toWeather() {
        return "/html/pages/weather.html";
    }


    // other---------------------------------------------------------------------------------
    @GetMapping(value = "/other/donate")
    public String donate() {
        return "/html/pages/other/donate.html";
    }


    // tripartite---------------------------------------------------------------------------------


    // txtedit---------------------------------------------------------------------------------


}
