package com.nulijiushimeili.librarianwebui.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/******************************
 * @Project:
 * @ClassName: ChartPageController
 * @time 2020/4/6 23:33
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@RequestMapping(value = "/chart")
@Controller
public class ChartPageController {


    // chart---------------------------------------------------------------------------------
    @GetMapping(value = "/chart1")
    public String chart1() {
        return "/html/pages/chart/chart1.html";
    }


    @GetMapping(value = "/chart2")
    public String chart2() {
        return "/html/pages/chart/chart2.html";
    }


    @GetMapping(value = "/chart3")
    public String chart3() {
        return "/html/pages/chart/chart3.html";
    }


    @GetMapping(value = "/chart4")
    public String chart4() {
        return "/html/pages/chart/chart4.html";
    }


    @GetMapping(value = "/chart5")
    public String chart5() {
        return "/html/pages/chart/chart5.html";
    }


    @GetMapping(value = "/chart6")
    public String chart6() {
        return "/html/pages/chart/chart6.html";
    }


    @GetMapping(value = "/chartArea")
    public String chartArea() {
        return "/html/pages/chart/chart-area.html";
    }


    @GetMapping(value = "/chartChina")
    public String chartChina() {
        return "/html/pages/chart/chart-china.html";
    }

}
